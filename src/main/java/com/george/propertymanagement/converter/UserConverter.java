package com.george.propertymanagement.converter;

import com.george.propertymanagement.dto.UserDto;
import com.george.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDtoToEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();

        userEntity.setOwnerEmail(userDto.getOwnerEmail());
        userEntity.setOwnerName(userDto.getOwnerName());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setPhone(userDto.getPhone());

        return userEntity;
    }

    public UserDto convertEntityToDto(UserEntity userEntity){

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setOwnerName(userEntity.getOwnerName());
        userDto.setOwnerEmail(userEntity.getOwnerEmail());
        userDto.setPhone(userEntity.getPhone());
        return userDto;
    }
}
