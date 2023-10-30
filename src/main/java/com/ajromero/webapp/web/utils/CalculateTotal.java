package com.ajromero.webapp.web.utils;

//Strategy pattern

import com.ajromero.webapp.web.dto.CheckoutProductDto;

public class CalculateTotal implements ICalcTotal {
    @Override
    public Double calculateTotal(CheckoutProductDto product) {
        return product.getPrice() * product.getQuantity();
    }
}
