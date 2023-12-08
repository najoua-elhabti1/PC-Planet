package com.pcplanet.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "product")
public class ProductType {

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

    private Integer idProduct;
    private String productName;
    private String image;
    private double price;
    private double qteStock;

    @XmlElement(name = "id_product")
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

}
