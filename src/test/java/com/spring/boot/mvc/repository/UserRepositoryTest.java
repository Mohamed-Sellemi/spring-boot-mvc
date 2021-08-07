package com.spring.boot.mvc.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.spring.boot.mvc.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {
    
    @Autowired
    private UserRepository repository;
    
    @Test
    @Rollback(false)
    void add_User_WithSuccess() {
        User u = new User("hameda", "mohammed.sellemi@gmail.com");
        User u1 = repository.save(u);
        Assertions.assertNotNull(u1, "user add in DB");
    }
    
    @Test
    void add_User_WithFailure() {
        User u = new User(null, "mohammed.sellemi@gmail.com");
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            repository.save(u);
        });
        
    }
    
    @Test
    void get_User_WithId_ReturnSuccess() {
        User u = new User("hameda", "mohammed.sellemi@gmail.com");
        repository.save(u);
        User u1 = repository.findById(1L).get();
        Assertions.assertNotNull(u, "user find");
    }
    
    @Test
    void get_User_WithId_ReturnFailure() {
        Optional<User> opt = repository.findById(20L);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            opt.get();
        });
        
    }
}
