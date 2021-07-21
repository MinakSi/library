package com.minakov.validation.validators;

import com.minakov.dto.UserDto;
import com.minakov.validation.annotations.PasswordsMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) object;
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
