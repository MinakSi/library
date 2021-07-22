package com.minakov.validation.validators;

import com.minakov.dto.UserDto;
import com.minakov.validation.annotations.PasswordsMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validator to check if password and its confirmation field match each other in UserDto
 */
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserDto> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
