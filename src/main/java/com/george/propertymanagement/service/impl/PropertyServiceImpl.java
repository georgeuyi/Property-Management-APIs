package com.george.propertymanagement.service.impl;
import com.george.propertymanagement.converter.PropertyConverter;
import com.george.propertymanagement.dto.PropertyDto;
import com.george.propertymanagement.entity.PropertyEntity;
import com.george.propertymanagement.entity.UserEntity;
import com.george.propertymanagement.exception.BusinessException;
import com.george.propertymanagement.exception.ErrorModel;
import com.george.propertymanagement.repository.PropertyRepository;
import com.george.propertymanagement.repository.UserRepository;
import com.george.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDto saveProperty(PropertyDto propertyDto) {

        Optional<UserEntity> optUserEnt = userRepository.findById(propertyDto.getUserId());

        if(optUserEnt.isPresent()) {
            PropertyEntity pe = propertyConverter.convertDtoToEntity(propertyDto);
            pe.setUserEntity(optUserEnt.get());
            pe = propertyRepository.save(pe);

            propertyDto = propertyConverter.convertEntityToDto(pe);

        }else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Invalid User ID");
            errorModel.setMessage("This user is not present");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        return propertyDto;
    }

    @Override
    public List<PropertyDto> getAllProperties() {

        List<PropertyEntity> propEntity = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDto> propDto = new ArrayList<>();
        for (PropertyEntity pe : propEntity){
            PropertyDto dto = propertyConverter.convertEntityToDto(pe);
            propDto.add(dto);
        }
        return propDto;
    }

    @Override
    public List<PropertyDto> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> propEntity = (List<PropertyEntity>)propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDto> propDto = new ArrayList<>();
        for (PropertyEntity pe : propEntity){
            PropertyDto dto = propertyConverter.convertEntityToDto(pe);
            propDto.add(dto);
        }
        return propDto;
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto, Long id) {
        Optional<PropertyEntity> dbEntity = propertyRepository.findById(id);
        if(dbEntity.isPresent()){
            PropertyEntity pe = dbEntity.get();
            pe.setAddress(propertyDto.getAddress());
            pe.setDescription(propertyDto.getDescription());
            pe.setPrice(propertyDto.getPrice());
            pe.setTitle(propertyDto.getTitle());
            propertyRepository.save(pe);
            propertyDto = propertyConverter.convertEntityToDto(pe);

        }
        return propertyDto;
    }

    @Override
    public PropertyDto updatePropertyDescription(PropertyDto propertyDto, Long id) {
        Optional<PropertyEntity> dbEntity = propertyRepository.findById(id);
        if(dbEntity.isPresent()){
            PropertyEntity pe = dbEntity.get();
            pe.setDescription(propertyDto.getDescription());
            propertyRepository.save(pe);
            propertyDto = propertyConverter.convertEntityToDto(pe);
        }
        return propertyDto;    }

    @Override
    public PropertyDto updatePropertyPrice(PropertyDto propertyDto, Long id) {
        Optional<PropertyEntity> dbEntity = propertyRepository.findById(id);
        if(dbEntity.isPresent()){
            PropertyEntity pe = dbEntity.get();
            pe.setPrice(propertyDto.getPrice());
            propertyRepository.save(pe);
            propertyDto = propertyConverter.convertEntityToDto(pe);
        }
        return propertyDto;    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
