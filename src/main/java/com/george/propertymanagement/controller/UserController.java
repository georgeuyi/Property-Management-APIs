package com.george.propertymanagement.controller;
import com.george.propertymanagement.dto.UserDto;
import com.george.propertymanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        userDto = userService.register(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody UserDto userDto) {
        userDto = userService.login(userDto.getOwnerEmail(), userDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}