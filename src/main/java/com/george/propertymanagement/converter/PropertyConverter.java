package com.george.propertymanagement.converter;

import com.george.propertymanagement.dto.PropertyDto;
import com.george.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDtoToEntity(PropertyDto propertyDto){

        PropertyEntity pe = new PropertyEntity();

        pe.setAddress(propertyDto.getAddress());
        pe.setDescription(propertyDto.getDescription());
        pe.setPrice(propertyDto.getPrice());
        pe.setTitle(propertyDto.getTitle());

        return  pe;
    }

    public PropertyDto convertEntityToDto(PropertyEntity propertyEntity){

        PropertyDto pDto = new PropertyDto();

        pDto.setId(propertyEntity.getId());
        pDto.setAddress(propertyEntity.getAddress());
        pDto.setDescription(propertyEntity.getDescription());
        pDto.setPrice(propertyEntity.getPrice());
        pDto.setTitle(propertyEntity.getTitle());
        pDto.setUserId(propertyEntity.getUserEntity().getId());

        return pDto;
    }
}
