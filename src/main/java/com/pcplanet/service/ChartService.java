package com.pcplanet.service;

import com.pcplanet.entity.*;
import jakarta.persistence.criteria.Fetch;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChartService {
    @Autowired
    private ChartRepository repo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserService userRepo;
    @Autowired
    private HttpSession session;


    public void addToCart(Integer productId , Integer userId) {
        // Retrieve the product from the database
        Product product = productRepo.findById(productId).orElse(null);
        User user= userRepo.findUSerById(userId);

        if (product != null) {

            Cart cartItem = new Cart(product,user );
           repo.save(cartItem);
        }
    }

    public void removeFromCart(Integer cartItemId) {
        // Business logic for removing an item from the cart, if needed
        repo.deleteById(cartItemId);
    }

    public List<Cart> viewCart() {
        // Business logic for retrieving the cart items
        return repo.findAll();
    }
    public long getCartItemCount() {
        // Business logic to get the count of items in the cart
        return repo.count();
    }
}
