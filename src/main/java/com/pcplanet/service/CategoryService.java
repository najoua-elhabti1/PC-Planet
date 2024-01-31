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


import com.pcplanet.entity.*;
import io.github.classgraph.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService  implements CommandLineRunner {
    @Autowired
    private CategoryRepository repo;
    @Autowired
    private ProductRepository productRepository;

    public List<Category> listALl() {
        return (List<Category>) repo.findAll();
    }


    public Category findById(String l) {
//            Optional<Category> optionalCategory = repo.findById(l);

            Optional<Category> optionalCategory = repo.findById(Integer.valueOf(l));


        return optionalCategory.orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + l));
    }
//    @Override
//    public void run(String... args) {
//        parseAndSaveCategories("Data/Categories.xml");
//    }
@Override
public void run(String... args) {
    // Load and save categories
    parseAndSaveCategories("Data/Categories.xml");

    // Load and save products
    parseAndSaveProducts("Data/Products.xml");
//    try {
//        // Load the XML file using Spring's Resource interface
//        ClassPathResource resource = new ClassPathResource("Data/Categories.xml");
//
//        // Create JAXB context and unmarshaller
//        JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//        // Unmarshal XML file to Categories object
//        Categories categoriesWrapper = (Categories) unmarshaller.unmarshal(resource.getInputStream());
//
//        // Extract individual Category objects from the wrapper
//        List<Category> categories = categoriesWrapper.getCategories();
//
//        // Save Category objects to the database
//        repo.saveAll(categories);
//    } catch (JAXBException | IOException e) {
//        // Handle JAXBException or IOException, log, or rethrow as needed
//        throw new RuntimeException("Error parsing XML and saving categories", e);
//    }
}
//    public void parseAndSaveProducts(String filePath) {
//        try {
//            // JAXBContext and Unmarshaller for Products
//            ClassPathResource resource = new ClassPathResource(filePath);
//            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//            // Unmarshal XML file to Products object
//            Products productsWrapper = (Products) unmarshaller.unmarshal(resource.getInputStream());
//
//            // Save Product objects to the database
//            List<Product> products = productRepository.saveAll(productsWrapper.getProducts());
//        } catch (JAXBException | IOException e) {
//            throw new RuntimeException("Error parsing XML and saving products", e);
//        }
//    }
public void parseAndSaveProducts(String filePath) {
    try {
        // JAXBContext and Unmarshaller for Products
        ClassPathResource resource = new ClassPathResource(filePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // Unmarshal XML file to Products object
        Products productsWrapper = (Products) unmarshaller.unmarshal(resource.getInputStream());

        // Iterate through products and save or update them
        for (Product product : productsWrapper.getProducts()) {
            // Check if the product already exists by name
            Optional<Product> existingProduct = productRepository.findByProduct_name(product.getProduct_name());

            if (existingProduct.isPresent()) {
                // Product with the same name already exists, update qte_stock
                Product existing = existingProduct.get();
                existing.setQte_stock(existing.getQte_stock() + product.getQte_stock());
                productRepository.save(existing);
            } else {
                // Save the new product
                productRepository.save(product);
            }
        }
    } catch (JAXBException | IOException e) {
        throw new RuntimeException("Error parsing XML and saving products", e);
    }
}


//    public Category findCategoryById(String idCategory) {
//        return repo.findByIdCategory(idCategory);
//    }
    public void parseAndSaveCategories(String filePath) {
        try {
            // Provide the path to your XML file
            ClassPathResource resource = new ClassPathResource(filePath);

            // Create JAXB context and unmarshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal XML file to Categories object
            Categories categoriesWrapper = (Categories) unmarshaller.unmarshal(resource.getInputStream());

            // Extract individual Category objects from the wrapper
            List<Category> categories = categoriesWrapper.getCategories();

            // Iterate through categories and save them
            for (Category category : categories) {
                // Check if the category has a valid ID
                if (category.getId_category() != null && !category.getId_category().isEmpty()) {
                    // Save the category (automatically handles both insert and update)
                    repo.save(category);
                }
            }
        } catch (JAXBException | IOException e) {
            // Handle JAXBException, log, or rethrow as needed
            throw new RuntimeException("Error parsing XML and saving categories", e);
        }
    }

//    public List<Category> parseAndSaveCategories(File xmlFile) {
//        try {
//            // Create JAXB context and unmarshaller
//            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//            // Unmarshal XML file to Categories object
//            Categories categoriesWrapper = (Categories) unmarshaller.unmarshal(xmlFile);
//
//            // Extract individual Category objects from the wrapper
//            List<Category> categories = categoriesWrapper.getCategories();
//
//            // Save Category objects to the database
//            return repo.saveAll(categories);
//        } catch (JAXBException e) {
//            // Handle JAXBException, log or rethrow as needed
//            throw new RuntimeException("Error parsing XML and saving categories", e);
//        }
//    }

    public Optional<Category> getCategoryById(String categoryId) {



        try {
            Integer categoryIdInt = Integer.parseInt(categoryId);
            return repo.findById(categoryIdInt);
        } catch (NumberFormatException e) {
            // Handle the case where categoryId is not a valid integer
            return Optional.empty();
        }

    }
}
