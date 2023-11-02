package com.ajromero.webapp.web.validation;

import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.web.exception.EmailResourceException;
import com.ajromero.webapp.web.exception.MyBadRequestException;
import com.ajromero.webapp.web.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerifyContent implements IVerifyContent{

    @Override
    public void verifyContent(boolean resource, String msj) {
        if(resource){
            log.warn(msj);
            throw new ResourceNotFoundException(msj);
        }
    }

    @Override
    public void verifyEmail(ICustomerRepository customers, String email) {
        if(customers.existsByEmail(email)) {
            log.warn(email + " customer already exists with the given email account");
            throw new EmailResourceException(email + " customer already exists with the given email account");
        }
    }

    @Override
    public void verifyBadRequest(boolean resource, String msj) {
        if(resource){
            log.warn(msj);
            throw new MyBadRequestException(msj);
        }
    }
}
