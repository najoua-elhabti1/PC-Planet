package com.crud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
//        System.out.println("MainController");
        return "Home";
    }
    @GetMapping("/login")
    public String login() {
        return "login"; // Assurez-vous d'avoir une vue ou un mapping approprié pour la page de connexion
    }
    @GetMapping("/register")
    public String register() {
        return "register"; // Assurez-vous d'avoir une vue ou un mapping approprié pour la page de connexion
    }


}
