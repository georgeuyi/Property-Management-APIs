package com.george.propertymanagement.service;


import com.george.propertymanagement.dto.UserDto;

public interface UserService {

    UserDto register(UserDto userDto);

   UserDto login(String ownerEmail, String password);
}
