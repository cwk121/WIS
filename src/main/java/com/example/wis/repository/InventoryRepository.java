package com.example.wis.repository;

import com.example.wis.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Inventory.InventoryPK> {
    List<Inventory> findAllByLocation(String location);
    List<Inventory> findAllByProductCode(String productCode);
}