package com.pcplanet.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
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


    @ManyToMany
    @JoinTable(name="product_orders" , joinColumns = @JoinColumn(name="id_product"), inverseJoinColumns = @JoinColumn(name="ref_order"))
    private List<Order> orders;

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
}
