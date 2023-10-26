package com.ajromero.webapp.service.interfaces;

import com.ajromero.common.service.IService;
import com.ajromero.common.service.IServiceBasic;
import com.ajromero.webapp.web.dto.CustomerDto;

public interface ICustomerService extends IServiceBasic<CustomerDto,String> {

    void simpleNew();

    CustomerDto getCustomer();

    CustomerDto save(final CustomerDto resource);

    // update

    CustomerDto update(final CustomerDto resource);
}
