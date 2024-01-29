package com.example.IPRWCbackend.daos;

import com.example.IPRWCbackend.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@Component
@CrossOrigin(origins = "${frontend_url}")
public class ProductDAO {

    private final ProductRepository productRepository;


    @Autowired
    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product.getName(), product.getDescription(), product.getPrice(), product.getId());
    }
}
