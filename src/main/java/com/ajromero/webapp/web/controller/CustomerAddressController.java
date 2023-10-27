package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.service.interfaces.IAddressService;
import com.ajromero.webapp.web.dto.AddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("addresses")
@AllArgsConstructor
@Validated
public class CustomerAddressController {

    private final IAddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getCustomerAddresses() {
        return ResponseEntity.ok().body(addressService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AddressDto> getAddress(
            @PathVariable("id")
            final @Positive Long id) {
        return ResponseEntity.ok().body(addressService.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<AddressDto> saveAddress(@RequestBody @Valid final AddressDto address) {
        return new ResponseEntity<>(addressService.save(address), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("id")
                                                        final @Positive Long id,
                                                    @RequestBody @Valid final AddressDto address) {
        return new ResponseEntity<>(addressService.update(id,address), HttpStatus.OK);
    }
}
