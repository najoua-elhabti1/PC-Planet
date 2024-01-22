package com.pcplanet.controller;

import com.pcplanet.entity.Cart;
import com.pcplanet.entity.Category;
import com.pcplanet.entity.Product;
import com.pcplanet.service.CategoryService;
import com.pcplanet.service.ChartService;
import com.pcplanet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ChartService cartService;
    @Autowired
    private CategoryService categoryService;
//    @GetMapping("/filterByPrice")
//    public List<Product> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
//        return productService.filterProductsByPrice(minPrice, maxPrice);
//    }
    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable Integer id, Model model) {
        // Fetch the product from the database
        Product product = productService.getProductById(id);
        long cartItemCount = cartService.getCartItemCount();
        model.addAttribute("cartItemCount", cartItemCount);
        // Add the product to the model
        model.addAttribute("productDetail", product);

        // Return the product detail view
        return "productDetails";
    }
    @GetMapping("/products/{categoryId}")
    public String showProductsByCategory(@PathVariable Integer categoryId, Model model) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            List<Product> products = productService.getProductsByCategory(category);
            model.addAttribute("category", category.get());
            model.addAttribute("products", products);
        }
        return "Products"; // Ceci est le nom de votre fichier HTML (products-by-category.html)
    }
    @GetMapping("/products/filter")
    public String filterProductsByPrice(@RequestParam(name = "minPrice", required = false) Double minPrice,
                                        @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                        Model model) {
        List<Product> filteredProducts = productService.filterProductsByPrice(minPrice, maxPrice);
        model.addAttribute("products", filteredProducts);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "Products";
    }

}
