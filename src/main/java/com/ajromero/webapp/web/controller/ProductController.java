package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.service.interfaces.IProductService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

   /* @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getUser(
            @PathVariable("id")
            final @Positive Long id) {
        return ResponseEntity.ok().body(productService.findById(id).orElseThrow());
    }*/
}
