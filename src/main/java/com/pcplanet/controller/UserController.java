package com.pcplanet.controller;
import com.pcplanet.entity.User;
import com.pcplanet.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

@GetMapping("/login")
public String showLoginForm(Model model) {
    model.addAttribute("user", new User());
    return "login";
}


    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        if (userService.isValidLogin(username, password)) {

            Optional<User> userOptional = userService.getUserByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();


                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return "redirect:/";
            } else {

                return "redirect:/login?error=userNotFound";
            }
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Add any necessary model attributes for the form
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, Model model) {
        // Check if passwords match
        if (!user.getPassword().equals(user.getRetypePassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "redirect:/register?error=passwordMismatch";
        }else {
            userService.registerUser(user);
        }

        return "redirect:/login";
    }

}
