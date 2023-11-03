package com.ajromero.webapp.service.interfaces;

import com.ajromero.webapp.web.dto.*;

public interface ICheckoutService {

    CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource);

    CheckoutBasicDto addProduct(Long id, CheckoutProductDto product);

    CheckoutBasicDto updateQuantityProduct(Long id, CheckoutProductDto product);

    void deleteCheckoutProduct(Long id,Long idProduct);

    CheckoutWithShippingDto saveShippingAddress(Long id, ShippingDto resource);

    CheckoutWithShippingDto updateShippingAddress(Long id, ShippingDto idCustomerAddress);

    String savePaymentMethod(Long id, CheckoutPaymentDto resource);

    String updatePaymentMethod(Long id, CheckoutPaymentDto resource);

    CheckoutInfoDto getCheckoutInfo(Long id);
}
