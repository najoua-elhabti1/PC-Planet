//package com.pcplanet;
//import com.pcplanet.entity.*;
//import com.pcplanet.service.CategoryService;
//import com.pcplanet.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//@SpringBootTest
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class CategoryTest {
//
//
//
//
//    @Autowired
//    private CategoryRepository catrepo;
//
//    @Autowired
//    private CategoryService catService;
//    @Autowired
//    private ProductRepository  repoP;
//
//    @Autowired
//    private ProductService productService;
//
//    @Test
//    public void testAddNew1() {
//        Category cat = new Category();
//        cat.setDescription(Desc.PC_Portable);
//        catService.registerCategory(cat);
//    }
//    @Test
//    public void testAddNew2() {
//        Category cat = new Category();
//        cat.setDescription(Desc.Ord_Bureau);
//        catService.registerCategory(cat);
//    }
//    @Test
//    public void testAddNew3() {
//        Category cat = new Category();
//        cat.setDescription(Desc.Accesoires);
//        catService.registerCategory(cat);
//    }
//
//}
//
