package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.service.interfaces.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import com.ajromero.webapp.web.mapper.CustomerMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@AllArgsConstructor
@Transactional (readOnly = true)
public class CustomerService implements ICustomerService {


    private final ICustomerRepository customers;


    private final IVerifyContent verifyContent;


    CustomerMapper customerMapper;


    @Override
    @Transactional (readOnly = true)
    public CustomerDto getCustomer() {
        String userId = this.getUserId();
        CustomerDto customer = customerMapper.toDto(customers.findById(userId).orElseThrow());
        verifyContent.verifyContent(customer == null,"Customer not found");
        return customer;
    }


    @Override
    @Transactional
    public CustomerDto save(CustomerDto resource) {
        String userId = this.getUserId();
        verifyContent.verifyEmail(customers, resource.getEmail());
        Customer newCustomer = customerMapper.toEntity(resource);
        if (userId.isEmpty()) {
            newCustomer.setId("uuid-new-customer01" + Math.random());
        } else {
            newCustomer.setId(userId);
        }
        log.info("{} Service >> saving", getClass());
        return customerMapper.toDto(customers.save(newCustomer));
    }

    @Override
    @Transactional
    public CustomerDto update(CustomerDto resource) {
        String userId = this.getUserId();
        Optional<Customer> dbCustomer = customers.findById(userId);
        verifyContent.verifyBadRequest(
                dbCustomer.orElseThrow().getId().equals(userId),
                "Customer no have privilege to update this information"
        );
        dbCustomer.ifPresent(customer -> {
            customer.setFirstName(resource.getFirstName());
            customer.setLastName(resource.getLastName());
            customer.setEmail(resource.getEmail());
            customer.setPhone(resource.getPhone());
        });
        log.info("{} Service >> updating", getClass());

        return customerMapper.toDto(dbCustomer.orElseThrow());
    }

    private String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
