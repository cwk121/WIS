package com.example.wis;

import com.example.wis.model.Inventory;
import com.example.wis.model.Product;
import com.example.wis.repository.InventoryRepository;
import com.example.wis.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryIntegrationTest {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindById_thenReturnInventory() {
        // given
        Inventory mask = new Inventory("CWB", "FM-HKTV01", 100);
        inventoryRepository.save(mask);

        // when
        Inventory found = inventoryRepository.findById(new Inventory.InventoryPK("CWB", "FM-HKTV01")).orElse(null);

        // then
        assertEquals(mask, found);
    }

    @Test
    public void whenSaveInventoryWithSameKey_thenUpdateInventory() {
        // given
        Inventory mask = new Inventory("CWB", "FM-HKTV01", 100);
        inventoryRepository.save(mask);

        // when
        Inventory mask2 = new Inventory("CWB", "FM-HKTV01", 200);
        inventoryRepository.save(mask2);
        Inventory found = inventoryRepository.findById(new Inventory.InventoryPK("CWB", "FM-HKTV01")).orElse(null);

        // then
        assertEquals(mask2, found);
    }

    @Test
    public void whenFindById_thenReturnProduct() {
        // given
        Product mask = new Product("FM-HKTV01", "face mask", 100);
        productRepository.save(mask);

        // when
        Product found = productRepository.findById("FM-HKTV01").orElse(null);

        // then
        assertEquals(mask, found);
    }

}
