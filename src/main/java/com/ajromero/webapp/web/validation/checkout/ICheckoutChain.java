package com.ajromero.webapp.web.validation.checkout;

import org.springframework.stereotype.Component;

@Component
public interface ICheckoutChain<T> {

    Boolean validate(T resource);

    void nextValidate(ICheckoutChain<T> validator);
}
