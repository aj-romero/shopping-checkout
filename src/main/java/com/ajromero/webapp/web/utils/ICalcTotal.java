package com.ajromero.webapp.web.utils;


import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;

public interface ICalcTotal<T extends IDto> {
    Double calculateTotal(T product);
}
