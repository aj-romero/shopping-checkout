package com.ajromero.webapp.web.validation;

import com.ajromero.webapp.persistence.repositories.ICustomerRepository;

public interface IVerifyContent {

    void verifyContent(boolean resource, String msj);

    void verifyEmail(ICustomerRepository customers, String email);

    void verifyBadRequest(boolean resource, String msj);


}
