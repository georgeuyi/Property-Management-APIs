package com.george.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private long id;
    private String ownerName;
    @NotNull(message = "Please Enter Your Email")
    @NotEmpty(message = "Email must be provided")
    @Size(min = 5, max = 30, message = "Email must be between 5 to 10 characters")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password cannot be empty")
    @NotEmpty(message = "Password must be provided")
    private String password;
    private String houseNo;
    private String street;
    private String city;
    private String postalCode;
    private String Country;
}
