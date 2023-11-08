package com.ajromero.webapp.service.interfaces;

import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutConfirmDto;
import com.ajromero.webapp.web.dto.CheckoutInfoDto;
import com.ajromero.webapp.web.dto.CheckoutPaymentDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import com.ajromero.webapp.web.dto.ShippingDto;

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

    CheckoutInfoDto confirmOrder(Long id, CheckoutConfirmDto resource);
}
