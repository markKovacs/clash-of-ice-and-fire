package com.coinf.service;

import com.coinf.dto.UserInfoDTO;
import com.coinf.dto.UserRegistrationDTO;
import com.coinf.exception.EmailExistsException;
import com.coinf.model.Role;
import com.coinf.model.User;
import com.coinf.repository.RoleRepository;
import com.coinf.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @Value(value = "${application.send.activation}")
    private boolean sendActivation;

    @Autowired
    private MailService mailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = EmailExistsException.class)
    public UserInfoDTO register(UserRegistrationDTO userRegDto) throws EmailExistsException {

        User userWithEmail = userRepository.findByEmail(userRegDto.getEmail());

        if (userWithEmail != null) {
            log.info("Registration failed. Email already registered: '" + userRegDto.getEmail() + "'.");
            throw new EmailExistsException();
        }

        User newUser = userMapper.mapRegToUser(userRegDto);

        Role userRole = roleRepository.findByRoleType(Role.RoleType.USER);
        newUser.addRole(userRole);

        String activation = null;
        if (sendActivation) {
            activation = UUID.randomUUID().toString();
        } else {
            newUser.setEnabled(true);
        }
        newUser.setActivation(activation);

        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            log.info("Registration failed. Email already registered: '" + userRegDto.getEmail() + "'.");
            throw new EmailExistsException();
        }

        log.info("User with email '" + userRegDto.getEmail() + "' has been saved.");

        if (sendActivation) {
            mailService.sendEmail(userRegDto.getEmail(), userRegDto.getFirstName(), activation, MailService.EmailType.ACTIVATION);
        }
        return userMapper.mapToUserInfoDTO(newUser);
    }

    @Transactional
    public boolean activate(String givenActivation) {

        User user = userRepository.findOneByActivation(givenActivation);

        if (user == null) {
            log.warn("Account activation failed. Activation code: " + givenActivation);
            return false;
        }

        user.setEnabled(true);
        user.setActivation(null);

        userRepository.save(user);
        log.info("Account activation with email '" + user.getEmail() + "' was successful.");

        mailService.sendEmail(user.getEmail(), user.getFirstName(), MailService.EmailType.WELCOME);

        return true;
    }

}
