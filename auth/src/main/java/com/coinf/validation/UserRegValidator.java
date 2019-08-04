package com.coinf.validation;

import com.coinf.dto.UserRegistrationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserRegValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        // obj could be casted to UserRegistrationDTO and validate all fields
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "message.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surName", "message.surName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.email");
    }

}
