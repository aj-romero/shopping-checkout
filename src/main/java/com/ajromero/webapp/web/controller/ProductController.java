package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.service.interfaces.IProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("products")
@AllArgsConstructor
@Validated
public class ProductController {


    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.findAll());
    }

}
