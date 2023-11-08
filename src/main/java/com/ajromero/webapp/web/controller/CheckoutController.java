package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutConfirmDto;
import com.ajromero.webapp.web.dto.CheckoutInfoDto;
import com.ajromero.webapp.web.dto.CheckoutPaymentDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import com.ajromero.webapp.web.dto.ShippingDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("checkouts")
@AllArgsConstructor
@Validated
public class CheckoutController {

    private final ICheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutBasicDto> basicCheckout(
            @RequestBody @Valid final CheckoutBasicDto checkout) {
        return new ResponseEntity<>(checkoutService
                .saveCheckoutBasic(checkout), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<CheckoutBasicDto> addProductCheckout(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final CheckoutProductDto product) {
        return new ResponseEntity<>(checkoutService.addProduct(id,product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/products")
    public ResponseEntity<CheckoutBasicDto> updateQuantityProduct(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final CheckoutProductDto product) {
        return new ResponseEntity<>(checkoutService
                .updateQuantityProduct(id,product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/products/{idProduct}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCheckoutProduct(
            @PathVariable("id") final @Positive Long id,
            @PathVariable("idProduct") final @Positive Long idProduct) {
        checkoutService.deleteCheckoutProduct(id,idProduct);
    }

    @PostMapping("/{id}/shippingAddress")
    public ResponseEntity<CheckoutWithShippingDto> saveShippingAddress(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final ShippingDto resource) {
        return new ResponseEntity<>(checkoutService
                .saveShippingAddress(id,resource), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/shippingAddress")
    public ResponseEntity<CheckoutWithShippingDto> updateShippingAddress(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final ShippingDto resource) {
        return new ResponseEntity<>(checkoutService
                .updateShippingAddress(id,resource), HttpStatus.OK);
    }

    @PostMapping("/{id}/cardPayment")
    public ResponseEntity<String> savePaymentMethod(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final CheckoutPaymentDto resource) {
        return new ResponseEntity<>(checkoutService
                .savePaymentMethod(id,resource), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cardPayment")
    public ResponseEntity<String> updatePaymentMethod(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final CheckoutPaymentDto resource) {
        return new ResponseEntity<>(checkoutService
                .updatePaymentMethod(id,resource), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckoutInfoDto> getCheckout(
            @PathVariable("id") final @Positive Long id) {
        return new ResponseEntity<>(checkoutService.getCheckoutInfo(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/generateOrder")
    public ResponseEntity<CheckoutInfoDto> confirmOrder(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final CheckoutConfirmDto resource) {
        return new ResponseEntity<>(checkoutService.confirmOrder(id,resource), HttpStatus.OK);
    }




}
