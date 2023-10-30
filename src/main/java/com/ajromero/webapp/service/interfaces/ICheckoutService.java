package com.ajromero.webapp.service.interfaces;

import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;

public interface ICheckoutService {

    CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource);

    CheckoutBasicDto addProduct(Long id, CheckoutProductDto product);
}
