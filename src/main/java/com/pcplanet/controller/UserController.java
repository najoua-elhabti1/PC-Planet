package com.pcplanet.controller;
import com.pcplanet.entity.User;
import com.pcplanet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; // Assuming "login" is the name of your login form template
//    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Add any necessary model attributes for the form
        model.addAttribute("user", new User());
        return "register"; // Assuming "registration" is the name of your registration form template
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user) {
        // Process the registration and save the user to the database
        // You may want to perform validation, hashing passwords, etc.

        // For simplicity, let's assume you have a UserService to handle user-related logic

        System.out.println(user);
        userService.registerUser(user);
        return "redirect:/login"; // Redirect to the login page after successful registration
    }
}
