package com.pcplanet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
//        System.out.println("MainController");
        return "home";
    }
    @GetMapping("/login")
    public String showLoginPage(){
//        System.out.println("MainController");
        return "login";
    }
//    @GetMapping("/register")
//    public String showRegisterPage(){
////        System.out.println("MainController");
//        return "register";
//    }



}
