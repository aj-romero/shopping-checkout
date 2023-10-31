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

    public Checkout updateProductQuantity(Long id, Long idProduct, Integer qty, ICheckout checkouts) {
        verifyContent.verifyBadRequest(!checkouts.existsById(id),id + " id URI not found in checkouts");
        Checkout checkout = checkouts.findById(id).orElseThrow();
        boolean idExistsInCk = checkout.getProducts().stream().
                anyMatch(item -> item.getProduct().getId().equals(idProduct));
        verifyContent.verifyContent(!idExistsInCk, "Product with id "+idProduct+" no found in checkout");
        basicValidator.validate(ckMapper.toDto(checkout));
        CheckoutProduct ckproduct = checkout.getProducts().stream()
                .filter(detail -> detail.getProduct().getId().equals(idProduct))
                .findFirst().orElseThrow();
        ckproduct.adjustQuatity(qty); //previo a esto se tendria que validar que si la nueva cantidad es menor que zero elimnar el checkout
        checkout.addDetail(ckproduct);
        return checkout;

    }

    public CheckoutBasicDto toDto(Checkout resource) {
        return ckMapper.toDto(resource);
    }



    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void removeCheckoutProduct(Long id, Long idProduct, ICheckout checkouts) {
        verifyContent.verifyBadRequest(!checkouts.existsById(id),id + " id URI not found in checkouts");
        Checkout checkout = checkouts.findById(id).orElseThrow();
        boolean idExistsInCk = checkout.getProducts().stream().
                anyMatch(item -> item.getProduct().getId().equals(idProduct));
        verifyContent.verifyContent(!idExistsInCk, "Product with id "+idProduct+" no found in checkout");
        CheckoutProduct rmProduct = checkout.getProducts().stream()
                .filter(detail -> detail.getProduct().getId().equals(idProduct))
                .findFirst().orElseThrow();
        checkout.getProducts().remove(rmProduct);
        checkouts.save(checkout);
        if (checkout.getProducts().isEmpty()) {
            checkouts.delete(checkout);
        }
    }
}
