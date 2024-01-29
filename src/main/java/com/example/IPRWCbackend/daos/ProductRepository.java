package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, UUID> {


    @Modifying
    @Transactional
    @Query("update Product p set p.name = ?1, p.description = ?2, p.price = ?3 where p.id = ?4")
    void updateProduct(String name, String description, double price, UUID id);
}