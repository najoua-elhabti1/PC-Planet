package com.pcplanet.service;
//
//import com.pcplanet.entity.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.pcplanet.entity.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.swing.event.ListDataEvent;
//import java.util.List;
//@Service
//public class CategoryService {
//    @Autowired
//    private CategoryRepository catrepo;
//    public  void registerCategory(Category cat) {
//
//        catrepo.save(cat);
//    }


import com.pcplanet.entity.Category;
import com.pcplanet.entity.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public List<Category> listALl() {
        return (List<Category>) repo.findAll();
    }


    public Category findById(Integer l) {
            Optional<Category> optionalCategory = repo.findById(l);

        return optionalCategory.orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + l));
    }

    public Optional<Category> getCategoryById(Integer categoryId) {
        return repo.findById(categoryId);
    }
}
