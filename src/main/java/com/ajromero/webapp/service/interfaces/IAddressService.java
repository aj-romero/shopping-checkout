package com.ajromero.webapp.service.interfaces;

import com.ajromero.webapp.web.dto.AddressDto;
import java.util.List;

public interface IAddressService {
    AddressDto save(final AddressDto resource);


    AddressDto update(Long id,final AddressDto resource);

    List<AddressDto> findAll();

}
