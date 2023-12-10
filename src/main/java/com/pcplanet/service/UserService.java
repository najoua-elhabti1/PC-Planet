package com.pcplanet.service;
import com.pcplanet.entity.User;
import com.pcplanet.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
    public class UserService {

        @Autowired
        private  UserRepository userRepository;


        @Autowired
        private  PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean isValidLogin(String username, String password) {
        // Fetch user details from the database
        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.map(user -> passwordEncoder.matches(password, user.getPassword())).orElse(false);
    }

        public  User registerUser(User user) {

            // Hash the password before saving it
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

//             Save the user to the database
            return userRepository.save(user);
        }
        public User findUSerById(Integer id){
        return findUSerById(id);
        }
    }



