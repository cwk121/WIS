package com.example.wis.controller;

import com.example.wis.model.Inventory;
import com.example.wis.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public String inventoryPage(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "") String search,
                                @RequestParam(defaultValue = "") String q,
                                Map<String, Object> model) {
        if(search.equals("location")) {
            model.put("inventories", inventoryRepository.findAllByLocation(q.trim()));
        } else if (search.equals("productCode")) {
            model.put("inventories", inventoryRepository.findAllByProductCode(q.trim()));
        } else {
            Pageable pageable = PageRequest.of(page, 100, Sort.Direction.ASC, "location");
            model.put("inventories", inventoryRepository.findAll(pageable).toList());
        }
        return "inventory";
    }

    @PostMapping("/inventory/upload")
    public String uploadProducts(@RequestParam MultipartFile file, Map<String, Object> model){
        try{
            inventoryRepository.saveAll(CsvUtils.read(Inventory.class, file.getInputStream()));
            model.put("status", true);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            model.put("status", false);
        }
        return "upload";
    }

    @PostMapping(path="/inventory/transfer")
    public String transferInventory(@RequestParam String from, @RequestParam String to, @RequestParam String pCode, @RequestParam Integer quantity, Map<String, Object> model){
        String fromLocation = from.trim();
        String toLocation = to.trim();
        String productCode = pCode.trim();

        Inventory fromInventory = inventoryRepository.findById(new Inventory.InventoryPK(fromLocation, productCode)).orElse(null);
        Inventory toInventory = inventoryRepository.findById(new Inventory.InventoryPK(toLocation, productCode)).orElse(null);
        if(fromInventory == null || fromInventory.getQuantity() < quantity || quantity <= 0){
            model.put("status", false);
            model.put("inventories", inventoryRepository.findAll());
            return "inventory";
        }

        fromInventory.setQuantity(fromInventory.getQuantity() - quantity);
        if(fromInventory.getQuantity() == 0){
            inventoryRepository.deleteById(new Inventory.InventoryPK(fromLocation, productCode));
        }

        if(toInventory == null){
            toInventory = new Inventory(toLocation, productCode, quantity);
        } else {
            toInventory.setQuantity(toInventory.getQuantity() + quantity);
        }

        inventoryRepository.save(toInventory);

        model.put("status", true);
        model.put("inventories", inventoryRepository.findAll());
        return "inventory";
    }
}
