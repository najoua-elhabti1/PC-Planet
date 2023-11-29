package com.crud;
import org.springframework.ui.Model;
import com.crud.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listALl();

        model.addAttribute("listUsers", listUsers);
        return "users";

    }
}
