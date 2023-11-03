package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.service.interfaces.ICustomerService;
import com.ajromero.webapp.web.dto.CustomerDto;
import com.ajromero.webapp.web.mapper.CustomerMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional (readOnly = true)
public class CustomerService implements ICustomerService {


    private final ICustomerRepository customers;


    private final IVerifyContent verifyContent;


    CustomerMapper customerMapper;
    @Override
    @Transactional (readOnly = true)
    public List<CustomerDto> findAll() {
        List<CustomerDto> list = customers.findAll()
                .stream().map(customerMapper::toDto).toList();
        verifyContent.verifyContent(list.isEmpty(),"Customers aren't available");
        return list;
    }

    @Override
    @Transactional (readOnly = true)
    public CustomerDto getCustomer() {
        String userId = this.getUserId();
        CustomerDto customer = customerMapper.toDto(customers.findById(userId).orElseThrow());
        verifyContent.verifyContent(customer == null,"Customer not found");
        return customer;
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<CustomerDto> findById(String id) {
        return Optional.of(customers.findById(id).map(customerMapper::toDto).orElseThrow());
    }

    @Override
    @Transactional
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
    @Transactional
    public CustomerDto update(CustomerDto resource) {
        String userId = this.getUserId();
        Optional<Customer> dbCustomer = customers.findById(userId);
        dbCustomer.ifPresent((customer)->{
            customer.setFirstName(resource.getFirstName());
            customer.setLastName(resource.getLastName());
            customer.setEmail(resource.getEmail());
            customer.setPhone(resource.getPhone());
        });

        return customerMapper.toDto(dbCustomer.orElseThrow());
    }

    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
