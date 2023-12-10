package com.pcplanet.controller;


import com.pcplanet.entity.Cart;
import com.pcplanet.entity.UserDetails;
import com.pcplanet.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller

public class CartController {
    @Autowired
    private ChartService cartService;

    @PostMapping("/add/{productId}")
    @ResponseBody
    public ModelAndView addToCart(@PathVariable Integer productId ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            // L'utilisateur n'est pas authentifié, rediriger vers la page de connexion
            return new ModelAndView("redirect:/login");
        }
        Integer userId = getUserIdFromAuthentication(authentication);
        cartService.addToCart(productId, userId);

        return new ModelAndView("redirect:/productDetails/{productId}");
    }
    private Integer getUserIdFromAuthentication(Authentication authentication) {
        // Check if the principal is an instance of YourUserDetails
        if (authentication.getPrincipal() instanceof UserDetails) {
            // Cast the principal to YourUserDetails and retrieve the ID
            return ((UserDetails) authentication.getPrincipal()).getId();
        }

        // If not an instance of YourUserDetails, handle accordingly
        return null; // or throw an exception, depending on your requirements
    }
/*    @GetMapping("/productDetails")
    @ResponseBody
    public long getCartItemCount() {
        return cartService.getCartItemCount();
    }*/
}
