package com.example.demo2.repository;

import com.example.demo2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT r FROM Product r WHERE r.deleted = 0")
    List<Product> findProduct();
}
