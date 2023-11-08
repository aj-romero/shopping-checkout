package com.ajromero.webapp.web.utils;


import com.ajromero.common.interfaces.IDto;

public interface ICalcTotal<T extends IDto> {
    Double calculateTotal(T product);
}
