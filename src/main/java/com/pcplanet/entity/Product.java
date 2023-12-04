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
}
