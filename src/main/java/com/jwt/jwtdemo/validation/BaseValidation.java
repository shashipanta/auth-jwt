package com.jwt.jwtdemo.validation;

import com.jwt.jwtdemo.error.codes.ErrorCodes;
import com.jwt.jwtdemo.error.exception.impl.BadRequestException;
import com.jwt.jwtdemo.error.model.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
public class BaseValidation<T> {

    private final List<ValidationError> errors = new ArrayList<>();

    public BaseValidation(BindException exception) {
        for (ObjectError error : exception.getAllErrors()) {
            ValidationError validationError = ValidationError.builder()
                    .field(error.getObjectName())
                    .message(error.getDefaultMessage())
                    .build();

            errors.add(validationError);
        }
    }

    public static void sendValidationError(List<ValidationError> errors) {
        throw new BadRequestException(ErrorCodes.BAD_REQUEST,
                "Validation Failed. Please check your data and try again", errors);
    }

    public static void sendValidationError(ValidationError errors) {
        sendValidationError(Collections.singletonList(errors));
    }

    public void validate(T objectToValidate) {
        constraintViolationValidation(objectToValidate);
        if(!errors.isEmpty()){
            sendValidationError(errors);
        }
    }

    private void checkError(List<ValidationError> errors) {
        if (errors == null || errors.isEmpty()) return;
        this.errors.addAll(errors);
    }

    private void constraintViolationValidation(T objectToValidate) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> violationSet = validator.validate(objectToValidate);

        violationSet.forEach(violation -> {
            String pathName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            ValidationError validationError = ValidationError.builder()
                    .field(pathName)
                    .message(message)
                    .build();
            errors.add(validationError);
        });

    }

    public List<ValidationError> getErrors(){return errors;}


}
