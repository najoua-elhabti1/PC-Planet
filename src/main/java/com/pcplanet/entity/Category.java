package com.pcplanet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "categoriy")
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {
    public String getId_category() {
        return id_category;
    }
    @Override
    public String toString() {
        return "Category{id_category=" + id_category + ", description='" + description + "'}";
    }
    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getDescription() {
        return description;
    }

    public Category(String id, String desc) {
        id_category=id;
        description=desc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_category;
    @Column(nullable = false, unique = true, length = 40)
    private String description;
    @OneToMany( mappedBy="category" , cascade = CascadeType.ALL)
    private List<Product> product;
}
