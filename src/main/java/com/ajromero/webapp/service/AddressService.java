package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.persistence.repositories.ICustomerAddresses;
import com.ajromero.webapp.persistence.repositories.ICustomers;
import com.ajromero.webapp.service.interfaces.IAddressService;
import com.ajromero.webapp.web.dto.AddressDto;
import com.ajromero.webapp.web.mapper.AddressMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService implements IAddressService {
    private final ICustomerAddresses addresses;
    private final ICustomers customers;
    private final IVerifyContent verifyContent;
    private final AddressMapper addressMapper;

    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public AddressDto save(AddressDto resource) {
        String userId = this.getUserId();
        Optional<Customer> customer = customers.findById(userId);
        verifyContent.verifyContent(customer.isEmpty(),"Error customer no found");
        CustomerAddress address = addressMapper.toEntity(resource);
        address.setCustomer(customer.orElseThrow());
        return addressMapper.toDto(addresses.save(address));
    }

    @Override
    public AddressDto update(Long id, AddressDto resource) {
        verifyContent.verifyBadRequest(resource.getId()==null,"Id is required");
        verifyContent.verifyBadRequest(!id.equals(resource.getId()), "id and URI id don't match");
        verifyContent.verifyContent(addresses.findById(resource.getId()).isEmpty(),"Address no found");
        CustomerAddress address = addresses.save(addressMapper.toEntity(resource));
        return addressMapper.toDto(address);
    }

    @Override
    public List<AddressDto> findAll() {
        List<AddressDto> list = addresses.findAll().stream().map(addressMapper::toDto).toList();
        verifyContent.verifyContent(list.isEmpty(), "No addresses found");
        return list;
    }

    @Override
    public Optional<AddressDto> findById(Long id) {
        Optional<AddressDto> address = addresses.findById(id).map(addressMapper::toDto);
        verifyContent.verifyContent(address.isEmpty(), "Address not found");
        return address;
    }
}
