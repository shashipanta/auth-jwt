package com.jwt.jwtdemo.service;

import com.jwt.jwtdemo.validation.BaseValidation;

public class BaseService {

    public <T> void validate(T objectToValidate){
        new BaseValidation<T>().validate(objectToValidate);
    }
}
