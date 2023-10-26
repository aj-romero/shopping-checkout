package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid final CustomerDto customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomerDto> getCustomer() {
        return ResponseEntity.ok().body(customerService.getCustomer());
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid final CustomerDto customer) {
        return new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
    }

}
