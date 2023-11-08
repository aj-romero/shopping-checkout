package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(
            @RequestBody @Valid final CustomerDto customer) {
        return new ResponseEntity<>(customerService
                .save(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomerDto> getCustomer() {
        return ResponseEntity.ok().body(customerService.getCustomer());
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(
            @RequestBody @Valid final CustomerDto customer) {
        return new ResponseEntity<>(customerService
                .update(customer), HttpStatus.OK);
    }

}
