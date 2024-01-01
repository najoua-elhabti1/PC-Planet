package com.pcplanet.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_product;

    @Column(name = "product_name" , nullable = false)
    private String productName;

    @Column(name = "pr_description", nullable = false)

    private String pr_description;

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPr_description() {
        return pr_description;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public double getQte_stock() {
        return qte_stock;
    }

    public Category getCategory() {
        return category;
    }

    //
//<<<<<<< HEAD
//
//=======
////    @Lob
//>>>>>>> bfce99bc101464c7491276428a987da1e2da7a08
    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "qte_stock" , nullable = false)
    private double qte_stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Cart> carts;


    @ManyToMany
    @JoinTable(name="product_orders" , joinColumns = @JoinColumn(name="id_product"), inverseJoinColumns = @JoinColumn(name="ref_order"))
    private List<Order> orders;
    public List<Cart> getCartItems() {
        return carts;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.carts = cartItems;
    }
    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPr_description(String pr_description) {
        this.pr_description = pr_description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQte_stock(double qte_stock) {
        this.qte_stock = qte_stock;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void setcategoryid(int id , String desc){
        category = new Category(id, desc);

    }
    @Override
    public String toString() {
        return "Product{id_product=" + id_product + ", product_name='" + productName + "', ...}";
    }
}
