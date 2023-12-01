package com.pcplanet.service;

import com.pcplanet.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
    public List<Product> listALl(){
        return (List<Product>) repo.findAll();
    }


}
