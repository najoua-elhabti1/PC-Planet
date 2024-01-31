package com.pcplanet.service;
import lombok.Data;
import com.pcplanet.entity.Order;
import com.pcplanet.entity.OrderRepository;
import com.pcplanet.entity.Product;
import com.pcplanet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
public class OrderService {


//    @Autowired
//    private OrderRepository orderRepository;
//
//
//    public Order createOrder(User user, List<Product> products) {
//        // Créer une nouvelle commande
//        Order order = new Order();
//        order.setUser(user);
//        order.setProducts(products);
//        order.setDate(new Date()); // Vous pouvez ajuster cela en fonction de votre modèle de données
//
//        double total = products.stream().mapToDouble(Product::getPrice).sum();
//        order.setTotalPrice(total);
//
//        return order;
//    }
//
//
//    public void saveOrder(Order order) {
//        // Enregistrer la commande dans la base de données
//        orderRepository.save(order);
//    }
}
