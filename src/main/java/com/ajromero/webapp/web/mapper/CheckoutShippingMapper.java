package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutShippingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CheckoutShippingMapper {
    CheckoutProduct checkoutProduct = Mappers.getMapper(CheckoutProduct.class);
    @Mapping(target = "shippingAddress", source = "shippingAddress")
    CheckoutShippingDto toDto(Checkout entity);
    Set<CheckoutProductDto> toDtoList(Set<CheckoutProduct> entities);

    @Mapping(target = "idProduct", source = "product.id")
    @Mapping(target = "total", ignore = true)
    CheckoutProductDto checkoutProductToCheckoutProductDto(CheckoutProduct checkoutProduct);

    /*@Mapping(target = "addressShipping", source = "address")
    OrderShippingDto toDto(OrderShipping entity);*/

    @Mapping(target = "shippingAddress", source = "shippingAddress")
    Checkout toEntity(CheckoutShippingDto dto);
    Set<CheckoutProduct> toEntityList(Set<CheckoutProductDto> dtos);

    @Mapping( target = "product.id", source = "idProduct")
    @Mapping( target = "checkout", ignore = true)
    @Mapping(target = "total", ignore = true)
    CheckoutProduct checkoutProductDtoToCheckoutProduct(CheckoutProductDto checkoutProductDto);

   /* @Mapping(target = "address", source = "addressShipping")
    @Mapping( target = "checkout", ignore = true)
    OrderShipping toEntity(OrderShippingDto dto);*/
}
