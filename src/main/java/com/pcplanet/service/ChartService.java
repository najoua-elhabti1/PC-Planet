package com.pcplanet.service;

import com.pcplanet.entity.*;
import jakarta.persistence.criteria.Fetch;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private ChartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

//    public void addToCart(User user, Product product, int quantity) {
//        Cart cart = user.getCart();
//
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(user);
//            cart.setProducts(new ArrayList<>());
//        }
//
//        // Vérifiez si le produit est déjà dans le panier
//        Optional<Product> existingProduct = cart.getProducts().stream()
//                .filter(p -> p.equals(product))
//                .findFirst();
//
//        if (existingProduct.isPresent()) {
//            // Si le produit est déjà dans le panier, mettez à jour la quantité
//            // (Note : Si la quantité peut changer, vous devrez peut-être ajuster cette logique)
//        } else {
//            // Sinon, ajoutez le produit avec la quantité spécifiée
//            for (int i = 0; i < quantity; i++) {
//                cart.getProducts().add(product);
//            }
//        }
//
//        // Sauvegardez le panier
//        cartRepository.save(cart);
//    }


    public void addToCart(User user, Product product, int quantity) {
        Cart cart = user.getCart();

        // Vérifiez si le panier existe, sinon créez-le
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setProducts(new ArrayList<>());
        }

        // Ajoutez le produit au panier avec la quantité spécifiée
        for (int i = 0; i < quantity; i++) {
            cart.getProducts().add(product);
        }

        // Enregistrez le panier dans la base de données
        cartRepository.save(cart);
    }

    public User findUserById(Integer userId) {
        User userOptional = userRepo.findUSerById(userId);
        return userOptional;
    }

//    public void addToCart(Integer productId , Integer userId) {
//        // Retrieve the product from the database
//        Product product = productRepo.findById(productId).orElse(null);
//        User user= userRepo.findUSerById(userId);
//
//        if (product != null) {
//
//            Cart cartItem = new Cart(product,user );
//           repo.save(cartItem);
//        }
//    }

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
    public Optional<Cart> getCartByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }
    public double calculateTotalPrice(Cart cart) {
        List<Product> products = cart.getProducts();

        // Calculer le prix total en itérant sur la liste des produits
        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        return totalPrice;
    }
    public List<Product> getProducts(Integer userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        return cartOptional.map(Cart::getProducts).orElse(List.of());
    }
    public Cart getCartById(Integer cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        return cartOptional.orElse(null);
    }
}
