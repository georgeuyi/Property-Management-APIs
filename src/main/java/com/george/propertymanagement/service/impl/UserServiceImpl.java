package com.george.propertymanagement.service.impl;

import com.george.propertymanagement.converter.UserConverter;
import com.george.propertymanagement.dto.UserDto;
import com.george.propertymanagement.entity.AddressEntity;
import com.george.propertymanagement.entity.UserEntity;
import com.george.propertymanagement.exception.BusinessException;
import com.george.propertymanagement.exception.ErrorModel;
import com.george.propertymanagement.repository.AddressRepository;
import com.george.propertymanagement.repository.UserRepository;
import com.george.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto register(UserDto userDto) {
        Optional<UserEntity> userEntityOpt = userRepository.findByOwnerEmail(userDto.getOwnerEmail());
        if(userEntityOpt.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL EXISTS");
            errorModel.setMessage("This Email have been registered before");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        UserEntity userEntity = userConverter.convertDtoToEntity(userDto);
        userEntity = userRepository.save(userEntity);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(userDto.getCity());
        addressEntity.setHouseNo(userDto.getHouseNo());
        addressEntity.setCountry(userDto.getCountry());
        addressEntity.setStreet(userDto.getStreet());
        addressEntity.setPostalCode(userDto.getPostalCode());
        addressEntity.setUserEntity(userEntity);
        addressRepository.save(addressEntity);
        userDto = userConverter.convertEntityToDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto login(String ownerEmail, String password) {
        UserDto userDto = null;
        Optional<UserEntity>  optionalUserEntity = userRepository.findByOwnerEmailAndPassword(ownerEmail, password);
        if(optionalUserEntity.isPresent()){
            userDto = userConverter.convertEntityToDto(optionalUserEntity.get());
        }else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID DETAILS");
            errorModel.setMessage("Invalid Email or Password");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        return userDto;
    }
}
