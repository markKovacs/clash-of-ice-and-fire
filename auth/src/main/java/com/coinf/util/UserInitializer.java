package com.coinf.util;

import com.coinf.model.Role;
import com.coinf.model.User;
import com.coinf.repository.RoleRepository;
import com.coinf.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value="app.data.initialization",
        havingValue = "true",
        matchIfMissing = true)
public class UserInitializer implements ApplicationRunner {

    private final static Logger LOG = Logger.getLogger(UserInitializer.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO: Remove in production.
        LOG.info("Start initializing user data.");

        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMIN");

        User user  = new User("tom@tom.com", "Tom", "Smith",
                passwordEncoder.encode("111"), userRole, adminRole);
        user.setEnabled(true);

        userRepository.save(user);

        LOG.info("Finished initializing data.");
    }

}
