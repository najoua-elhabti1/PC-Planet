package com.pcplanet.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_table")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}