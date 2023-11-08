package com.ajromero.webapp.payment;

import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.web.utils.ICalcTotal;

public class CalcTotalEntity implements ICalcTotal<CheckoutProduct> {
    @Override
    public Double calculateTotal(CheckoutProduct product) {
        return product.getPrice() * product.getQuantity();
    }
}
