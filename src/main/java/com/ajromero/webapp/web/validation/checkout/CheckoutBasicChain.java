package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CheckoutBasicChain implements ICheckoutChain<CheckoutBasicDto>{

    private ICheckoutChain<CheckoutBasicDto> validator;
    private ICustomerRepository customers;

    public CheckoutBasicChain(ICustomerRepository customers) {
        this.customers = customers;
    }

    public CheckoutBasicChain() {
        //
    }
    @Override
    public Boolean validate(CheckoutBasicDto resource) {
        boolean result = true;
        if(resource.getProducts().iterator().hasNext()){
            String idUser = SecurityContextHolder.getContext().getAuthentication().getName();
            if (customers.existsById(idUser)){
                result = true;
            } else {
                Customer newCustomer = new Customer();
                newCustomer.setId(idUser);
                customers.save(newCustomer);
            }

        } else {
            result = false;
        }

        if( validator != null) {
            return this.validator.validate(resource);
        }


        return result;
    }

    @Override
    public void nextValidate(ICheckoutChain<CheckoutBasicDto> validator) {
        this.validator = validator;
    }
}
