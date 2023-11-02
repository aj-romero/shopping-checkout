package com.ajromero.webapp.web.dto;


import com.ajromero.common.interfaces.IDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CCheckoutDto implements IDto {

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[0-9]{13,19}$", message = "no valid")
    private String creditCardNumber;

    @NotBlank(message = "is required")
    private String cardholderName;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$")
    private String expirationDate;

    @NotBlank(message = "is required")
    @Size(min = 3, max = 4)
    private String securityCode;
}
