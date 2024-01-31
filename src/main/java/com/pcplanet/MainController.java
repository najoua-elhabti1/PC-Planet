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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public MainController(ProductService productService , CategoryService categoryService) {
        this.productService = productService;
        this.categoryService= categoryService;
    }


//    public String showHomePage() {
//        System.out.println("MainController");
//        return "home";
//    }

//@GetMapping("/")
//    public String showHomePage(Model model) {
//    model.addAttribute("products", productService.listALl());
//    model.addAttribute("categories", categoryService.listALl());
//    return "index";
//    }
    @GetMapping("/")
    public String getProducts(Model model) {
        // Charger les données XML
        Map<String, List<Product>> categoryProductsMap = productService.getProductsFromXML();
        String[] keys = new String[3];
        int i =0;

        for (Map.Entry<String, List<Product>> entry : categoryProductsMap.entrySet()) {
            String categoryId = entry.getKey();
            keys[i]=categoryId;
            i++;
            List<Product> productList = entry.getValue();
            System.out.println("Category ID: " + categoryId);
            for (Product product : productList) {
                System.out.println("Product Name: " + product.getProduct_name());
                System.out.println("Product Price: " + product.getPrice());
                System.out.println("Product Image: " + product.getImage());
                System.out.println("Id  " + product.getId_product());
                // Add more fields as needed
                System.out.println(); // Add a newline for better readability
            }
        }
        model.addAttribute("categoryProductsMap", categoryProductsMap);
        model.addAttribute("keys", keys);
        model.addAttribute("products", productService.listALl());
        model.addAttribute("categories", categoryService.listALl());

        return "index";
    }

   /* @GetMapping("/")
    public String showHomePage (Model model){
        model.addAttribute("products", productService.listALl());
        model.addAttribute("categories", categoryService.listALl());
        return "index";
    }*/


//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login";
//    }

//    @GetMapping("/register")
//    public String showRegisterPage(){
////        System.out.println("MainController");
//        return "register";
//    }


//    @GetMapping("/register")
//    public String showRegisterPage() {
//        return "register";
//    }
//    @GetMapping("/shop/{categoryId}")
//    public String showProductsByCategory(@PathVariable Integer categoryId, Model model) {
//        model.addAttribute("products", productService.listByCategory(categoryId));
//        model.addAttribute("categories" , categoryService.listALl());
//      /*  Category category = categoryService.findById(categoryId);
//
//
//        List<Product> productsByCategory = productService.listByCategory(category);
//        model.addAttribute("productsCat", productsByCategory);
//        model.addAttribute("selectedCategory", category);*/
//        return "Products";
//    }
//    @GetMapping("/shop")
//    public String showProducts(Model model ){
//
//        return "Products";
//    }
/*@GetMapping("/products/{categoryId}")
public String getProductsByCategory(@PathVariable Integer categoryId, Model model) {
    List<Product> products = productService.listByCategory(categoryId);
    model.addAttribute("products", products);
    return "Products"; // Retourne le nom de la vue (template Thymeleaf)
}*/

}
