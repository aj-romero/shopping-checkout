package com.ajromero.webapp.web.dto;

import com.ajromero.common.interfaces.IDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckoutPaymentDto implements IDto {

    @NotNull(message = " is required")
    @Positive
    private Long id;

    @NotNull(message = " is required")
    @Positive
    private Long idCard;
}
