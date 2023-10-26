package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private IProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.findAll());
    }
}
