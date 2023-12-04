package com.pcplanet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_category;
    @Column(nullable = false, unique = true, length = 40)
    private String description;
    @OneToMany( mappedBy="category" , cascade = CascadeType.ALL)
    private List<Product> product;
}
