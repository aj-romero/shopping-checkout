package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.persistence.repositories.ICustomerAddressRepository;
import com.ajromero.webapp.persistence.repositories.ICustomerRepository;
import com.ajromero.webapp.service.interfaces.IAddressService;
import com.ajromero.webapp.web.dto.AddressDto;
import com.ajromero.webapp.web.mapper.AddressMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import java.util.List;
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
public class AddressService implements IAddressService {
    private final ICustomerAddressRepository addresses;
    private final ICustomerRepository customers;
    private final IVerifyContent verifyContent;
    private final AddressMapper addressMapper;

    private String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    @Transactional
    public AddressDto save(AddressDto resource) {
        String userId = this.getUserId();
        Optional<Customer> customer = customers.findById(userId);
        verifyContent.verifyContent(customer.isEmpty(),"Error customer no found");
        CustomerAddress address = addressMapper.toEntity(resource);
        address.setCustomer(customer.orElseThrow());
        log.info("{} Service >> saving", getClass());
        return addressMapper.toDto(addresses.save(address));
    }

    @Override
    @Transactional
    public AddressDto update(Long id, AddressDto resource) {
        verifyContent.verifyBadRequest(resource.getId() == null,
                "Id is required");
        verifyContent.verifyBadRequest(!id.equals(resource.getId()),
                "id and URI id don't match");
        verifyContent.verifyContent(addresses.findById(resource.getId()).isEmpty(),
                "Address no found");
        CustomerAddress address = addressMapper.toEntity(resource);

        String userId = this.getUserId();
        Optional<Customer> customer = customers.findById(userId);
        verifyContent.verifyContent(customer.isEmpty(),"Error customer no found");
        address.setCustomer(customer.orElseThrow());

        CustomerAddress result = addresses.save(address);
        log.info("{} Service >> updating", getClass());
        return addressMapper.toDto(result);
    }

    @Override
    @Transactional (readOnly = true)
    public List<AddressDto> findAll() {
        String userId = getUserId();
        Customer actual = customers.findById(userId).orElseThrow();
        List<AddressDto> list = addresses.findByCustomer(actual)
                .stream().map(addressMapper::toDto).toList();
        verifyContent.verifyContent(list.isEmpty(), "No addresses found");
        return list;
    }

}
