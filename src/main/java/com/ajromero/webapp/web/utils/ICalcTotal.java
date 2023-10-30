package com.ajromero.webapp.web.utils;


import com.ajromero.webapp.web.dto.CheckoutProductDto;

public interface ICalcTotal {
    Double calculateTotal(CheckoutProductDto product);
}
