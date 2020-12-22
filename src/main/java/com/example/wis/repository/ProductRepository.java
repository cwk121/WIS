package com.example.wis.repository;

import com.example.wis.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByCode(String code);
    List<Product> findAllByName(String name);
}
