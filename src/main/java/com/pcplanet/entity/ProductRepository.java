package com.pcplanet.entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

/*    List<Product> findById_product(Integer categoryId);*/
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(Category category);
//    List <Product> ListProductById(Integer id);


}