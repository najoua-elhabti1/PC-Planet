package com.pcplanet;

import com.pcplanet.entity.Category;
import com.pcplanet.entity.Product;
import com.pcplanet.service.CategoryService;
import com.pcplanet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public MainController(ProductService productService , CategoryService categoryService) {
        this.productService = productService;
        this.categoryService= categoryService;
    }

    @GetMapping("")

    public String showHomePage() {
//        System.out.println("MainController");
        return "home";
    }
    public String showHomePage(Model model) {
        model.addAttribute("products", productService.listALl());
        model.addAttribute("categories" , categoryService.listALl());
        return "Home";

    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

//    @GetMapping("/register")
//    public String showRegisterPage(){
////        System.out.println("MainController");
//        return "register";
//    }


//    @GetMapping("/register")
//    public String showRegisterPage() {
//        return "register";
//    }
    /*@GetMapping("/shop/{categoryId}")
    public String showProductsByCategory(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("products", productService.listALl());
        model.addAttribute("categories" , categoryService.listALl());
        Category category = categoryService.findById(categoryId);
>>>>>>> 1c8e11afccea7ddfb8940091da74bc389dcfe8e6

        List<Product> productsByCategory = productService.listByCategory(category);
        model.addAttribute("productsCat", productsByCategory);
        model.addAttribute("selectedCategory", category);
        return "Products";
    }*/
    @GetMapping("/shop")
    public String showProducts( Model model ) {

        return "Products";
    }

}
