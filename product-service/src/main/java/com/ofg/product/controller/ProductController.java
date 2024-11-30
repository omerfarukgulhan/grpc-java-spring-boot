package com.ofg.product.controller;

import com.ofg.product.model.entity.Product;
import com.ofg.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.add(product);
    }
}
