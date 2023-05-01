package com.george.propertymanagement.controller;
import com.george.propertymanagement.dto.PropertyDto;
import com.george.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/property")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto propertyDto) {
        propertyDto = propertyService.saveProperty(propertyDto);
        return new ResponseEntity<>(propertyDto, HttpStatus.CREATED);
    }

    @GetMapping("/propertyList")
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDto>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/propertyList/user/{userId}")
    public ResponseEntity<List<PropertyDto>> getAllPropertiesForUser(@PathVariable("userId") Long userId) {
        List<PropertyDto> propertyList = propertyService.getAllPropertiesForUser(userId);
        ResponseEntity<List<PropertyDto>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/propertyUpdate/{id}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto propertyDto, @PathVariable Long id) {
        propertyDto = propertyService.updateProperty(propertyDto, id);
        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDto, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/propertyUpdate/description/{id}")
    public ResponseEntity<PropertyDto> updatePropertyDescription(@RequestBody PropertyDto propertyDto, @PathVariable Long id) {
        propertyDto = propertyService.updatePropertyDescription(propertyDto, id);
        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDto, HttpStatus.OK);
        return responseEntity;

    }

    @PatchMapping("/propertyUpdate/price/{id}")
    public ResponseEntity<PropertyDto> updatePropertyPrice(@RequestBody PropertyDto propertyDto, @PathVariable Long id) {
        propertyDto = propertyService.updatePropertyPrice(propertyDto, id);
        ResponseEntity<PropertyDto> responseEntity = new ResponseEntity<>(propertyDto, HttpStatus.OK);
        return responseEntity;

    }

    @DeleteMapping("property/{id}")
    public ResponseEntity deleteProperty(@PathVariable Long id){
        propertyService.deleteProperty(id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        return responseEntity;
    }
}