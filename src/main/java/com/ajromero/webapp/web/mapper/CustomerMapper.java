package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.web.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    Customer toEntity(CustomerDto customerDto);


}
