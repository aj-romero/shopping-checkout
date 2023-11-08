package com.ajromero.webapp.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CheckoutConfirmDto {
    @NotNull(message = "is required")
    private Long id;

    @NotBlank(message = "is required")
    @Size(min = 3, max = 4)
    private String securityCode;
}
