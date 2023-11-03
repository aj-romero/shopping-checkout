package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.web.dto.CheckoutInfoDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CheckoutInfoMapper {

    CheckoutProduct checkoutProduct = Mappers.getMapper(CheckoutProduct.class);
    @Mapping(target = "shippingAddress", source = "shippingAddress")
    @Mapping(target = "customer.addresses", ignore = true)
    @Mapping(target = "customer.cards", ignore = true)
    @Mapping(target = "cardPayment", source = "cardPayment")
    CheckoutInfoDto toDto(Checkout checkout);
    Set<CheckoutProductDto> toDtoList(Set<CheckoutProduct> entities);

    @Mapping(target = "idProduct", source = "product.id")
    @Mapping(target = "total", ignore = true)
    CheckoutProductDto checkoutProductToCheckoutProductDto(CheckoutProduct checkoutProduct);

    @Mapping(target = "paymentProcessor", ignore = true)
    Checkout toEntity(CheckoutInfoDto dto);
    Set<CheckoutProduct> toEntityList(Set<CheckoutProductDto> dtos);

    @Mapping( target = "product.id", source = "idProduct")
    @Mapping( target = "checkout", ignore = true)
    @Mapping(target = "total", ignore = true)
    CheckoutProduct checkoutProductDtoToCheckoutProduct(CheckoutProductDto checkoutProductDto);
}
