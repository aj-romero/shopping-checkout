package com.ajromero.webapp.web.dto;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.persistence.domain.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto implements IDto {

    private Long id;

    @Size(min = 2, max = 50, message = "min length is 2 and max length is 50 characters")
    @NotBlank(message = " is required")
    private String addressName;

    @Size(min = 2, max = 50, message = "min length is 2 and max length is 50 characters")
    @NotBlank(message = " is required")
    private String state;

    @Size(min = 2, max = 50, message = "min length is 2 and max length is 50 characters")
    @NotBlank(message = " is required")
    private String city;

    @Size(min = 2, max = 100, message = "min length is 2 and max length is 100 characters")
    @NotBlank(message = " is required")
    private String street;

    @NotBlank(message = " is required")
    @Pattern(regexp = "^\\+503(?:\\s\\d{4}){2}$",
            message = "invalid mobile number entered ")
    private String referencePhone;

    @Size(max = 10, message = "min length is 2 and max length is 10 characters")
    @NotBlank(message = " is required")
    private String houseNumber;

    @NotBlank(message = " is required")
    @Size(min = 2, max = 50, message = "min length is 2 and max length is 50 characters")
    private String referenceName;

    public AddressDto() {
        //
    }

}
