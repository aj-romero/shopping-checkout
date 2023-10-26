package com.ajromero.webapp.service;

import com.ajromero.common.service.IService;
import com.ajromero.webapp.web.dto.CustomerDto;

public interface ICustomerService extends IService<CustomerDto,String> {

    void simpleNew();

    CustomerDto getCustomer();
}
