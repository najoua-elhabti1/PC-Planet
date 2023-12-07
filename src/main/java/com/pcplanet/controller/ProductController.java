package com.pcplanet.controller;

import com.pcplanet.entity.Product;
import com.pcplanet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/filterByPrice")
    public List<Product> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.filterProductsByPrice(minPrice, maxPrice);
    }
}
