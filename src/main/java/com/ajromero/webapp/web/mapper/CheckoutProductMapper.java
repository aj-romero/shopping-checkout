package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckoutProductMapper {

    @Mapping(target = "total", ignore = true)
    @Mapping(target = "idProduct", source = "product.id")
    CheckoutProductDto toDto(CheckoutProduct entity);

    @Mapping(target = "total", ignore = true)
    @Mapping(target = "product.id", source = "idProduct")
    @Mapping(target = "checkout", ignore = true)
    CheckoutProduct toEntity(CheckoutProductDto dto);
}
