package com.ajromero.webapp.web.dto;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.webapp.web.validator.EmailUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto implements IDto {

    @Size(min = 2, max = 50, message = "min length is 2 and max length is 50 characters")
    @NotBlank(message = " is required")
    @Pattern(regexp = "^([a-zA-ZáéíóúüÁÉÍÓÚÜñÑ]{2,50}[\\,\\-\\.]{0,1}[\\s]{0,1}){1,3}$",
            message = "is not valid pattern")
    private String firstName;

    @Size(min = 2, max = 50, message = "min length is 2 and max length is 50 characters")
    @NotBlank(message = " is required")
    @Pattern(regexp = "^([a-zA-ZáéíóúüÁÉÍÓÚÜñÑ]{2,50}[\\,\\-\\.]{0,1}[\\s]{0,1}){1,3}$",
            message = "is not valid pattern")
    private String lastName;

    @Email(message = "should be valid")
    @NotBlank(message = " is required")
    @EmailUnique
    private String email;

    @Pattern(regexp = "^\\+503(?:\\s\\d{4}){2}$",
            message = "invalid mobile number entered ")
    private String phone;

    public CustomerDto() {
        //
    }
}
