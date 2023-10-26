package com.ajromero.webapp.web.validation;

import com.ajromero.webapp.persistence.repositories.ICustomers;

public interface IVerifyContent {

    void verifyContent(boolean resource, String msj);

    void verifyEmail(ICustomers customers, String email);

    void verifyBadRequest(boolean resource, String msj);


}
