package com.pcplanet.controller;
import com.pcplanet.entity.User;
import com.pcplanet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

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
            return "redirect:/";
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
    public String processRegistration(@ModelAttribute("user") User user) {
        // Check if passwords match
        if (!user.getPassword().equals(user.getRetypePassword())) {
            return "redirect:/register?error=passwordMismatch";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }
}
