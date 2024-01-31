package com.pcplanet.controller;

import com.pcplanet.entity.Order;
import com.pcplanet.entity.Product;
import com.pcplanet.entity.User;
import com.pcplanet.service.ChartService;
import com.pcplanet.service.OrderService;
import com.pcplanet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class OrderController {

    private UserService userService;


    private ChartService cartService;


    private OrderService orderService;
//
//    @PostMapping("/place")
//    public String placeOrder() {
//        User user = userService.getCurrentUser();
//        List<Product> cartContents = cartService.getCartContents(user);
//
//        if (user != null && !cartContents.isEmpty()) {
//            Order order = orderService.createOrder(user, cartContents);
//            orderService.saveOrder(order);
//            // Effacer le panier après avoir passé la commande
//            cartService.clearCart(user);
//        }
//
//        return "redirect:/orders/view";
//    }
}
