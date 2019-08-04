package com.coinf.service;

import com.coinf.dto.UserInfoDTO;
import com.coinf.dto.UserRegistrationDTO;
import com.coinf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfoDTO mapToUserInfoDTO(User user) {

        return UserInfoDTO.builder()
                .email(user.getEmail())
                .name(user.getFirstName() + " " + user.getSurName())
                .registeredAt(user.getRegisteredAt())
                .build();
    }

    public User mapRegToUser(UserRegistrationDTO userRegDto) {

        return new User(userRegDto.getEmail(),
                userRegDto.getFirstName(),
                userRegDto.getSurName(),
                passwordEncoder.encode(userRegDto.getPassword())
        );
    }

}
