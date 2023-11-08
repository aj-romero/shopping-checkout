package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.web.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "cards", ignore = true)
    Customer toEntity(CustomerDto customerDto);


}
