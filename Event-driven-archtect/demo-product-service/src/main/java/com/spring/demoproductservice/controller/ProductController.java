package com.spring.demoproductservice.controller;

import com.spring.demoproductservice.model.Product;
import com.spring.demoproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/load")
    public Product createProduct(@RequestBody Product product){
        return service.saveProducts(product);
    }

    @GetMapping("/all")
    public List<Product> findAllProducts(){
        return service.fetchAllProducts();
    }
}
