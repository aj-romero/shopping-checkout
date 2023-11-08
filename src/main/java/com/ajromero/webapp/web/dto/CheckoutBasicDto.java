package com.ajromero.webapp.web.dto;

import com.ajromero.common.interfaces.IDto;
import jakarta.validation.Valid;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CheckoutBasicDto implements IDto {
    private Long id;

    private Set<@Valid CheckoutProductDto> products;

    public CheckoutBasicDto(Set<@Valid CheckoutProductDto> products) {
        this.products = new TreeSet<>(products);
    }

    public CheckoutBasicDto() {
        //
        this.products = new TreeSet<>();
    }

    public double getTotalCheckout() {
        return this.products.stream().mapToDouble(CheckoutProductDto::getTotal).sum();
    }
}
