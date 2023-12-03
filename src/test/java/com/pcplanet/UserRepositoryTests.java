package com.pcplanet;

import com.pcplanet.entity.Product;
import com.pcplanet.entity.User;
import com.pcplanet.service.ProductService;
import com.pcplanet.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)


public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository  repoP;

    @Autowired
    private ProductService productService;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setUsername("user1");
        user.setEmail("najouaelhabti@gmail.com");
        user.setPassword("123456");
        userService.registerUser(user);
    }
    @Test
    public void ListAll() {
        List<Product> list = productService.listALl();

        for (Product product : list) {
            System.out.println(product);
        }


    }
}