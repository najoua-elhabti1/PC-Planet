package com.pcplanet.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_product;

    @Column(name = "product_name" , nullable = false)
    private String product_name;

    @Column(name = "pr_description", nullable = false)

    private String pr_description;

    public Product(String productName, String description) {
        this.product_name=productName;
        this.pr_description=description;
    }

    public Product(String product_name, String pr_description, String image) {
        this.product_name = product_name;
        this.pr_description = pr_description;
        this.image = image;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId_product() {
        return id_product;
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
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private Category category;
    @ManyToMany(mappedBy = "products")
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
        this.product_name = productName;
    }

    public Product(String product_name, String pr_description, String image, double price, double qte_stock, Category category) {
        this.product_name = product_name;
        this.pr_description = pr_description;
        this.image = image;
        this.price = price;
        this.qte_stock = qte_stock;
        this.category = category;
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
    public void setcategoryid(String id , String desc){
        category = new Category(id, desc);

    }
    @Override
    public String toString() {
        return "Product{id_product=" + id_product + ", product_name='" + product_name + "', ...}";
    }
}
