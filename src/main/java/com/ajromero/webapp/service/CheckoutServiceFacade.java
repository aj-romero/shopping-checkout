package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.repositories.ICheckout;
import com.ajromero.webapp.persistence.repositories.ICustomers;
import com.ajromero.webapp.persistence.repositories.IProducts;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.mapper.CheckoutBasicMapper;
import com.ajromero.webapp.web.mapper.CheckoutProductMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.CheckoutValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
@AllArgsConstructor
public class CheckoutServiceFacade {

    private final ICustomers customers;
    private final CheckoutBasicMapper ckMapper;
    private final IProducts products;
    private final IVerifyContent verifyContent;
    private final CheckoutProductMapper ckProductMapper;

    private CheckoutValidator basicValidator;

    public Checkout basicRespCheckout(CheckoutBasicDto resource) {
        boolean valid = basicValidator.validate(resource);
        Customer customer;
        Checkout checkout = ckMapper.toEntity(resource);
        if (valid) {
            customer = customers.findById(this.getUserId()).orElseThrow();
            checkout.setCustomer(customer);
        }
        checkout.getProducts().stream().forEach(product -> {product.setCheckout(checkout);});

        checkout.setStatus(Checkout.Status.OPEN);

        return checkout;
    }

    public CheckoutProduct addProduct(Long id, CheckoutProductDto productDto, ICheckout checkouts) {
        verifyContent.verifyBadRequest(!checkouts.existsById(id),id + " id URI not found in checkouts");
        boolean valid = basicValidator.validateProduct(productDto);
        CheckoutProduct newProduct = null;

        if (valid) {
            newProduct = ckProductMapper.toEntity(productDto);
        }

        return newProduct;
    }

    public CheckoutBasicDto toDto(Checkout resource) {
        return ckMapper.toDto(resource);
    }



    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
