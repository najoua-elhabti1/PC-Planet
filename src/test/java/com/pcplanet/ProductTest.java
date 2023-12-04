package com.pcplanet;

import com.pcplanet.entity.Product;
import com.pcplanet.entity.ProductRepository;
import com.pcplanet.entity.User;
import com.pcplanet.entity.UserRepository;
import com.pcplanet.service.ProductService;
import com.pcplanet.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@SpringBootTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class ProductTest {
    @Autowired
    private ProductRepository  repoP;

    @Autowired
    private ProductService productService;

        @Test
    public void ListAll() {
        List<Product> list = productService.listALl();

        for (Product product : list) {
            System.out.println(product);
        }


    }
}
