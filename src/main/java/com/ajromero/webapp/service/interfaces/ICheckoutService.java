package com.ajromero.webapp.service.interfaces;

import com.ajromero.webapp.web.dto.CCheckoutDto;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutShippingDto;

public interface ICheckoutService {

    CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource);

    CheckoutBasicDto addProduct(Long id, CheckoutProductDto product);

    CheckoutBasicDto updateQuantityProduct(Long id, Long idProduct, Integer quantity);

    void deleteCheckoutProduct(Long id,Long idProduct);

    CheckoutShippingDto saveShippingAddress(Long id, Long idCustomerAddress);

    CheckoutShippingDto updateShippingAddress(Long id, Long idCustomerAddress);

    String savePaymentMethod(Long id, Long idCustomerCard);
}
