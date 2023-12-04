package com.pcplanet.service;

import com.pcplanet.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcplanet.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository catrepo;
    public  void registerCategory(Category cat) {

        catrepo.save(cat);
    }

}
