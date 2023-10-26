package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.repositories.ICustomers;
import com.ajromero.webapp.service.interfaces.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import com.ajromero.webapp.web.mapper.CustomerMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {


    private final ICustomers customers;


    private final IVerifyContent verifyContent;


    CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> findAll() {
        List<CustomerDto> list = customers.findAll()
                .stream().map(customerMapper::toDto).toList();
        verifyContent.verifyContent(list.isEmpty(),"Customers aren't available");
        return list;
    }

    @Override
    public CustomerDto getCustomer() {
        String userId = this.getUserId();
        CustomerDto customer = customerMapper.toDto(customers.findById(userId).orElseThrow());
        verifyContent.verifyContent(customer == null,"Customer not found");
        return customer;
    }

    @Override
    public Optional<CustomerDto> findById(String id) {
        return Optional.of(customers.findById(id).map(customerMapper::toDto).orElseThrow());
    }

    @Override
    public CustomerDto save(CustomerDto resource) {
        String userId = this.getUserId();
        verifyContent.verifyEmail(customers, resource.getEmail());
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
        String userId = this.getUserId();
        Customer customer = customerMapper.toEntity(resource);
        customer.setId(userId);
        return customerMapper.toDto(customers.save(customer));
    }

    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public void simpleNew() {
        String id = this.getUserId();
        Customer newCustomer = new Customer();
        newCustomer.setId(id);
        if(customers.findById(id).isEmpty()){
            customers.save(newCustomer);
        }
    }
}
