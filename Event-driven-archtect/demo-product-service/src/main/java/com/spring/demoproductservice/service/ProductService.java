package com.spring.demoproductservice.service;

import com.spring.demoproductservice.model.Product;
import com.spring.demoproductservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public Product saveProducts(Product product) {
        return productRepo.save(product);
    }

    public List<Product> fetchAllProducts() {
        return productRepo.findAll();
    }
}
