package com.george.propertymanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String address;
    private Long userId;
}
