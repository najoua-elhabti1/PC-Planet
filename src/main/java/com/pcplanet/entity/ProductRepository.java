package com.pcplanet.entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.product_name = :product_name")
    Optional<Product> findByProduct_name(@Param("product_name") String product_name);

/*    List<Product> findById_product(Integer categoryId);*/
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(Category category);
//    List <Product> ListProductById(Integer id);


}