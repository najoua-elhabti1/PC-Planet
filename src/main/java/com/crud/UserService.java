package com.crud;

import com.crud.user.User;
import com.crud.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listALl(){
        return (List<User>) repo.findAll();
    }
}
