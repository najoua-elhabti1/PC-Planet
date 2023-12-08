package com.pcplanet.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "product")
public class ProductsType {

    private Integer idProduct;
    private String productName;
    private String image;
    private double price;


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQteStock(double qteStock) {
        this.qteStock = qteStock;
    }

    private double qteStock;

    public String getProductName() {
        return productName;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public double getQteStock() {
        return qteStock;
    }

    @XmlElement(name = "id_product")
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }}
