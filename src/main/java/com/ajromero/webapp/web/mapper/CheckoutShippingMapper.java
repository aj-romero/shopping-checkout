package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutWithShippingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CheckoutShippingMapper {
    CheckoutProduct checkoutProduct = Mappers.getMapper(CheckoutProduct.class);
    @Mapping(target = "shippingAddress", source = "shippingAddress")
    CheckoutWithShippingDto toDto(Checkout entity);
    Set<CheckoutProductDto> toDtoList(Set<CheckoutProduct> entities);

    @Mapping(target = "idProduct", source = "product.id")
    @Mapping(target = "total", ignore = true)
    CheckoutProductDto checkoutProductToCheckoutProductDto(CheckoutProduct checkoutProduct);



    @Mapping(target = "shippingAddress", source = "shippingAddress")
    Checkout toEntity(CheckoutWithShippingDto dto);
    Set<CheckoutProduct> toEntityList(Set<CheckoutProductDto> dtos);

    @Mapping( target = "product.id", source = "idProduct")
    @Mapping( target = "checkout", ignore = true)
    @Mapping(target = "total", ignore = true)
    CheckoutProduct checkoutProductDtoToCheckoutProduct(CheckoutProductDto checkoutProductDto);

}
