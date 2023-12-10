package com.pcplanet.controller;

import com.pcplanet.entity.Cart;
import com.pcplanet.entity.Product;
import com.pcplanet.service.ChartService;
import com.pcplanet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ChartService cartService;
    @GetMapping("/filterByPrice")
    public List<Product> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.filterProductsByPrice(minPrice, maxPrice);
    }
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

}
