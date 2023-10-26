package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.ICustomers;
import com.ajromero.webapp.persistence.repositories.IProducts;
import com.ajromero.webapp.web.dto.CustomerDto;
import com.ajromero.webapp.web.mapper.CustomerMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private ICustomers customers;

    @Autowired
    IVerifyContent verifyContent;

    @Autowired
    CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> findAll() {
        List<CustomerDto> list = customers.findAll()
                .stream().map(customerMapper::toDto).toList();
        verifyContent.verifyListContent(list.isEmpty());
        return list;
    }

    @Override
    public CustomerDto getCustomer() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomerDto customer = customerMapper.toDto(customers.findById(userId).orElseThrow());
        return customer;
    }

    @Override
    public Optional<CustomerDto> findById(String id) {
        return Optional.of(customers.findById(id).map(customerMapper::toDto).orElseThrow());
    }

    @Override
    public CustomerDto save(CustomerDto resource) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer newCustomer = customerMapper.toEntity(resource);
        if(userId.isEmpty()){
            newCustomer.setId("uuid-new-customer01" + Math.random());
        } else {
            newCustomer.setId(userId);
        }

        return customerMapper.toDto(customers.save(newCustomer));
    }

    @Override
    public CustomerDto update(CustomerDto resource) {
        return null;
    }


    @Override
    public void simpleNew() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer newCustomer = new Customer();
        newCustomer.setId(id);
        if(customers.findById(id).isEmpty()){
            customers.save(newCustomer);
        }
    }
}
