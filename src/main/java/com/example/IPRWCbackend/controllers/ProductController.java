package com.example.IPRWCbackend.controllers;

import com.example.IPRWCbackend.daos.ProductDAO;
import com.example.IPRWCbackend.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/product")
@CrossOrigin(origins = "${frontend_url}")
public class ProductController {

    private final ProductDAO productDAO;


    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") UUID id) {
        return productDAO.getProduct(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        productDAO.deleteProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productDAO.addProduct(product);
    }

    @PutMapping()
    public void updateProduct(@RequestBody Product product) {
        productDAO.updateProduct(product);
    }
}
