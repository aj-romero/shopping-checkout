package com.ajromero.webapp.service.interfaces;

import com.ajromero.common.service.IService;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.web.dto.AddressDto;

import java.util.List;

public interface IAddressService{
    AddressDto save(final AddressDto resource);

    // update

    AddressDto update(Long id,final AddressDto resource);

    List<AddressDto> findAll();

}
