package com.crud;

import com.crud.user.User;
import com.crud.user.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("najouaelhabti@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Najoua");
        user.setLastName("El Habti");
        User saveUser = repo.save(user);
        Assertions.assertNotNull(saveUser);
        Assertions.assertTrue(saveUser.getId()>0);
    }
        @Test
        public void testListAll(){
            Iterable<User> users = repo.findAll();
            Assertions.assertTrue(users.iterator().hasNext(), "Iterable should not be empty");
    }
    @Test
    public void testUpdate(){
        Integer userId = 4;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        user.setPassword("589746");
        repo.save(user);
//        User updateUser = repo.findById(userId).get();
//        Assertions.assertEquals(updateUser.getPassword(), "");

    }
    @Test
    public void testGet(){
        Integer userId=5;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get());
    }
//    @Test
//    public void testDelete(){
//        Integer userId = 5;
//        repo.deleteById(userId);
//
//        Optional<User> optionalUser = repo.findById(userId);
//        Assertions.assertTrue(optionalUser.isPresent());
//    }
}
