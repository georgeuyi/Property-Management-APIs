package com.george.propertymanagement.service;

import com.george.propertymanagement.dto.PropertyDto;
import com.george.propertymanagement.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PropertyService {
    public PropertyDto saveProperty(PropertyDto propertyDto);

    public List<PropertyDto> getAllProperties();

    public List<PropertyDto> getAllPropertiesForUser(Long userId);

    public PropertyDto updateProperty(PropertyDto propertyDto, Long id);

    public PropertyDto updatePropertyDescription(PropertyDto propertyDto, Long id);

    public PropertyDto updatePropertyPrice(PropertyDto propertyDto, Long id);

    void deleteProperty(Long id);

}
