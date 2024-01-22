package com.pcplanet.service;
import com.pcplanet.entity.User;
import com.pcplanet.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
    public class UserService {

        @Autowired
        private  UserRepository userRepository;


        @Autowired
        private  PasswordEncoder passwordEncoder;

    public Optional<User> getUserByUsername(String username) {
        // Implémentez la logique pour récupérer un utilisateur par son nom d'utilisateur
        // Cela dépend de votre méthode de stockage des utilisateurs (par exemple, base de données, fichier, etc.)
        return userRepository.findByUsername(username);
    }
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Integer getUserIdFromAuthentication(Authentication authentication) {
        // Check if the principal is an instance of YourUserDetails (replace YourUserDetails with your actual UserDetails class)
        if (authentication.getPrincipal() instanceof User) {
            // Cast the principal to YourUserDetails and retrieve the ID
            return ((User) authentication.getPrincipal()).getId();
        }

        // If not an instance of YourUserDetails, handle accordingly
        return null; // or throw an exception, depending on your requirements
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



