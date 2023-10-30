package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.repositories.ICheckout;
import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService implements ICheckoutService {

   private CheckoutServiceFacade facade;
   private ICheckout checkouts;


    @Override
    public CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource) {
        Checkout checkout = facade.basicRespCheckout(resource);
        checkout.getProducts();
        Checkout result = checkouts.save(checkout);

        return facade.toDto(result);
    }

    @Override
    public CheckoutBasicDto addProduct(Long id, CheckoutProductDto product) {
        CheckoutProduct newProduct = facade.addProduct(id, product,checkouts);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        checkout.addDetail(newProduct);
        Checkout result = checkouts.save(checkout);
        return facade.toDto(result);
    }
}
