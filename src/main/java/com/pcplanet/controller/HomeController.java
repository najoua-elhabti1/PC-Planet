package com.pcplanet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        System.out.println("HomeController - Home method called");
        return "home"; // Assuming "home" is the name of your home template
    }

}