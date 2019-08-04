package com.coinf.dto;


import com.coinf.validation.PasswordMatches;
import com.coinf.validation.ValidEmail;
import com.coinf.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches(message = "{PasswordMatches.user.password}")
public class UserRegistrationDTO {

    @NotNull
    @ValidEmail(message = "{ValidEmail.user.email}")
    private String email;

    @NotNull
    @ValidPassword(message = "{ValidPassword.user.password}")
    private String password;

    private String passwordConfirmation;

    @NotNull
    @Size(max = 60, message = "{Size.user.firstName}")
    private String firstName;

    @NotNull
    @Size(max = 60, message = "{Size.user.surName}")
    private String surName;

}
