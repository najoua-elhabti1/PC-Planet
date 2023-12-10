package com.pcplanet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_chart;

    @Column(nullable = true, length = 15)
    private  Integer quantity=0;

    public Integer getUser() {
        return user.getId() ;
    }
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Cart(Product pr , User user ){
        this.product = pr;
        this.user = user;
        this.product.setQte_stock(pr.getQte_stock()-1);
        quantity+=1;
    }

}
