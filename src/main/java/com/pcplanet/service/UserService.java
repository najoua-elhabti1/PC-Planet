package com.pcplanet.service;
import com.pcplanet.entity.User;
import com.pcplanet.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    public class UserService {

        @Autowired
        private  UserRepository userRepository;

        @Autowired
        private  PasswordEncoder passwordEncoder;

        public  User registerUser(User user) {

            // Hash the password before saving it
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

//             Save the user to the database
            return userRepository.save(user);
        }
    }



