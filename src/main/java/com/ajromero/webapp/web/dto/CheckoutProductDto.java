package com.ajromero.webapp.web.dto;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.web.utils.CalculateTotal;
import com.ajromero.webapp.web.utils.ICalcTotal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


public class CheckoutProductDto implements IDto,Comparable<CheckoutProductDto> {
    @Getter @Setter private Long id;

    @NotNull(message = "is required")
    @Getter @Setter private Long idProduct;

    @NotNull(message = "is required")
    @Getter @Setter private Integer quantity;

    @Getter @Setter private Double price;

    private final ICalcTotal<CheckoutProductDto> total;

    public CheckoutProductDto() {
        //
        this.total = new CalculateTotal();
    }

    public Double getTotal() {
        return this.total.calculateTotal(this);
    }

    @Override
    public int compareTo(CheckoutProductDto o) {
        return this.getIdProduct().compareTo(o.getIdProduct());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckoutProductDto that = (CheckoutProductDto) o;
        return this.getIdProduct().equals(that.getIdProduct());
    }

    @Override
    public int hashCode() {
        return this.getIdProduct().hashCode() + 53;
    }
}
