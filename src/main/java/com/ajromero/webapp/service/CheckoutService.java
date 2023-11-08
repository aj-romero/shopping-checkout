package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.repositories.ICheckoutRepository;
import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional (readOnly = true)
public class CheckoutService implements ICheckoutService {

   private CheckoutServiceFacade facade;
   private ICheckoutRepository checkouts;


    @Override
    @Transactional
    public CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource) {
        Checkout checkout = facade.basicRespCheckout(resource);
        //checkout.getProducts();
        Checkout result = checkouts.save(checkout);

        return facade.toDto(result);
    }

    @Override
    @Transactional
    public CheckoutBasicDto addProduct(Long id, CheckoutProductDto product) {
        CheckoutProduct newProduct = facade.addProduct(id, product);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        checkout.addDetail(newProduct);
        Checkout result = checkouts.save(checkout);
        return facade.toDto(result);
    }

    @Override
    @Transactional
    public CheckoutBasicDto updateQuantityProduct(Long id, CheckoutProductDto product) {
        /*Checkout updateCheckout = facade.updateProductQuantity(id, product);
        Checkout result = checkouts.save(updateCheckout);
        return facade.toDto(result);*/
        return  facade.updateProductQuantity(id,product);
    }

    @Override
    @Transactional
    public void deleteCheckoutProduct(Long id, Long idProduct) {
        Checkout checkout = facade.removeCheckoutProduct(id, idProduct);

        if (checkout.getProducts().isEmpty() &&
                checkout.getStatus().equals(Checkout.Status.OPEN)) {
            checkouts.delete(checkout);
        }
    }

    @Override
    @Transactional
    public CheckoutWithShippingDto saveShippingAddress(Long id, ShippingDto resource) {
        return facade.saveShippingAddress(id,resource);
    }

    @Override
    @Transactional
    public CheckoutWithShippingDto updateShippingAddress(Long id, ShippingDto resource) {
        return facade.updateShippingAddress(id,resource);
    }

    @Override
    @Transactional
    public String savePaymentMethod(Long id, CheckoutPaymentDto resource) {
        return facade.setPaymentMethod(id,resource);
    }

    @Override
    @Transactional
    public String updatePaymentMethod(Long id, CheckoutPaymentDto resource) {
        return facade.updatePaymentMethod(id,resource);
    }

    @Override
    public CheckoutInfoDto getCheckoutInfo(Long id) {
        return facade.getCheckoutInformation(id);
    }

    @Override
    @Transactional
    public CheckoutInfoDto confirmOrder(Long id, CheckoutConfirmDto resource) {
        return facade.confirmOrder(id,resource);
    }
}
