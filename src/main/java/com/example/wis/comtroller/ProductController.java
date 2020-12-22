package com.example.wis.comtroller;

import com.example.wis.model.Product;
import com.example.wis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String productPage(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "") String q,
                              Map<String, Object> model) {

        if(search.equals("code")) {
            model.put("products", productRepository.findAllByCode(q.trim()));
        } else if (search.equals("name")) {
            model.put("products", productRepository.findAllByName(q.trim()));
        } else {
            Pageable pageable = PageRequest.of(page, 100, Sort.Direction.ASC, "code");
            model.put("products", productRepository.findAll(pageable).toList());
        }

        return "product";
    }

    @PostMapping("/product/upload")
    public String uploadProducts(@RequestParam("file") MultipartFile file, Map<String, Object> model){
        try{
            productRepository.saveAll(CsvUtils.read(Product.class, file.getInputStream()));
            model.put("status", true);
        } catch (Exception ex){
            model.put("status", false);
        }
        return "upload";
    }
}
