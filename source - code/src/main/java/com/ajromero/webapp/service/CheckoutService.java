package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.repositories.ICheckoutRepository;
import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutConfirmDto;
import com.ajromero.webapp.web.dto.CheckoutInfoDto;
import com.ajromero.webapp.web.dto.CheckoutPaymentDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import com.ajromero.webapp.web.dto.ShippingDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
public class CheckoutService implements ICheckoutService {

    private CheckoutServiceFacade facade;
    private ICheckoutRepository checkouts;


    @Override
    @Transactional
    public CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource) {
        Checkout checkout = facade.basicRespCheckout(resource);
        Checkout result = checkouts.save(checkout);
        log.info("{} Service >> saving init checkout with products", getClass());
        return facade.toDto(result);
    }

    @Override
    @Transactional
    public CheckoutBasicDto addProduct(Long id, CheckoutProductDto product) {
        CheckoutProduct newProduct = facade.addProduct(id, product);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        checkout.addDetail(newProduct);
        Checkout result = checkouts.save(checkout);
        log.info("{} Service >> saving - adding product to checkout", getClass());
        return facade.toDto(result);
    }

    @Override
    @Transactional
    public CheckoutBasicDto updateQuantityProduct(Long id, CheckoutProductDto product) {
        log.info("{} Service >> updating quantity of products in checkout", getClass());
        return facade.updateProductQuantity(id, product);
    }

    @Override
    @Transactional
    public void deleteCheckoutProduct(Long id, Long idProduct) {
        Checkout checkout = facade.removeCheckoutProduct(id, idProduct);
        log.info("{} Service >> deleting product", getClass());
        if (checkout.getProducts().isEmpty()
                && checkout.getStatus().equals(Checkout.Status.OPEN)) {
            checkouts.delete(checkout);
        }
    }

    @Override
    @Transactional
    public CheckoutWithShippingDto saveShippingAddress(Long id, ShippingDto resource) {
        log.info("{} Service >> saving shipping address", getClass());
        return facade.saveShippingAddress(id, resource);
    }

    @Override
    @Transactional
    public CheckoutWithShippingDto updateShippingAddress(Long id, ShippingDto resource) {
        log.info("{} Service >> updating shipping address", getClass());
        return facade.updateShippingAddress(id, resource);
    }

    @Override
    @Transactional
    public String savePaymentMethod(Long id, CheckoutPaymentDto resource) {
        log.info("{} Service >> saving payment method", getClass());
        return facade.setPaymentMethod(id, resource);
    }

    @Override
    @Transactional
    public String updatePaymentMethod(Long id, CheckoutPaymentDto resource) {
        log.info("{} Service >> updating payment method", getClass());
        return facade.updatePaymentMethod(id, resource);
    }

    @Override
    public CheckoutInfoDto getCheckoutInfo(Long id) {
        return facade.getCheckoutInformation(id);
    }

    @Override
    @Transactional
    public CheckoutInfoDto confirmOrder(Long id, CheckoutConfirmDto resource) {
        log.info("{} Service >> confirming order", getClass());
        return facade.confirmOrder(id, resource);
    }
}
