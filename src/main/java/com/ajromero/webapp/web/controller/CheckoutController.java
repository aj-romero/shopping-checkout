package com.ajromero.webapp.web.controller;

import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CCheckoutDto;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutShippingDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("checkouts")
@AllArgsConstructor
@Validated
public class CheckoutController {

    private final ICheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutBasicDto> basicCheckout(@RequestBody @Valid final CheckoutBasicDto checkout) {
        return new ResponseEntity<>(checkoutService.saveCheckoutBasic(checkout), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<CheckoutBasicDto> addProductCheckout(
            @PathVariable("id") final @Positive Long id,
            @RequestBody @Valid final CheckoutProductDto product) {
        return new ResponseEntity<>(checkoutService.addProduct(id,product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/products/{idProduct}")
    public ResponseEntity<CheckoutBasicDto> updateQuantityProduct(
            @PathVariable("id") final @Positive Long id,
            @PathVariable("idProduct") final @Positive Long idProduct,
            @RequestBody @DecimalMin(value =  "-9999") final Integer quantity) {
        return new ResponseEntity<>(checkoutService.updateQuantityProduct(id,idProduct,quantity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/products/{idProduct}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCheckoutProduct(
            @PathVariable("id") final @Positive Long id,
            @PathVariable("idProduct") final @Positive Long idProduct) {
        checkoutService.deleteCheckoutProduct(id,idProduct);
        //return new ResponseEntity<>(checkoutService.deleteCheckoutProduct(id,idProduct), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/shippingAddress/{idCustomerAddress}")
    public ResponseEntity<CheckoutShippingDto> saveShippingAddress(
            @PathVariable("id") final @Positive Long id,
            @PathVariable("idCustomerAddress") final @Positive Long idCustomerAddress) {
        return new ResponseEntity<>(checkoutService.saveShippingAddress(id,idCustomerAddress), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/shippingAddress/{idCustomerAddress}")
    public ResponseEntity<CheckoutShippingDto> updateShippingAddress(
            @PathVariable("id") final @Positive Long id,
            @PathVariable("idCustomerAddress") final @Positive Long idCustomerAddress) {
        return new ResponseEntity<>(checkoutService.updateShippingAddress(id,idCustomerAddress), HttpStatus.OK);
    }

    @PostMapping("/{id}/cardPayment/{idCustomerCard}")
    public ResponseEntity<String> savePaymentMethod(
            @PathVariable("id") final @Positive Long id,
            @PathVariable("idCustomerCard") final @Positive Long idCustomerCard) {
        return new ResponseEntity<>(checkoutService.savePaymentMethod(id,idCustomerCard), HttpStatus.CREATED);
    }


}
