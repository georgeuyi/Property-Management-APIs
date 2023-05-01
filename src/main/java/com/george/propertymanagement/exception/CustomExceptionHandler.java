package com.george.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldException(MethodArgumentNotValidException manV){

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = manV.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrorList){
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
