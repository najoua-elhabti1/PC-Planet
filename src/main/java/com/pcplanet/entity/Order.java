package com.pcplanet.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ref_order;
    private String address;
    private Date date;
    @ManyToOne
    private User user;

   @ManyToMany
   @JoinTable(name="product_orders" , joinColumns = @JoinColumn(name="ref_order"), inverseJoinColumns = @JoinColumn(name="id_product"))
    private List<Product> products;

}