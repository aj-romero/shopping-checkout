package com.ajromero.webapp.web.mapper;


import com.ajromero.webapp.persistence.domain.CustomerAddress;
import com.ajromero.webapp.web.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(CustomerAddress address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    CustomerAddress toEntity(AddressDto address);
}
