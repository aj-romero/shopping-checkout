package com.ajromero.webapp.web.mapper;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CheckoutBasicMapper {
    CheckoutProduct checkoutProduct = Mappers.getMapper(CheckoutProduct.class);


    CheckoutBasicDto toDto(Checkout entity);
    Set<CheckoutProductDto> toDtoList(Set<CheckoutProduct> entities);

    @Mapping(target = "idProduct", source = "product.id")
    CheckoutProductDto checkoutProductToCheckoutProductDto(CheckoutProduct checkoutProduct);

   // @Mapping(source = "products.idProduct", target = "products.product")
    Checkout toEntity(CheckoutBasicDto dto);
    Set<CheckoutProduct> toEntityList(Set<CheckoutProductDto> dtos);

    @Mapping( target = "product.id", source = "idProduct")
    @Mapping( target = "checkout", ignore = true)
    CheckoutProduct checkoutProductDtoToCheckoutProduct(CheckoutProductDto checkoutProductDto);
}
