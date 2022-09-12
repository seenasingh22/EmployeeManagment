package com.example.employee.schema;

import com.example.employee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserSchemaTest {

    @Autowired
    public UserRepository repository;

    @Test
    void createAndGetAndUpdateAndDeleteUser(){
        UserSchema user = new UserSchema();
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setPhone("+1234567890");
        repository.save(user);
        Optional<UserSchema> u = repository.findById(user.getId());
        assertEquals(user.getName(),u.get().getName());
        assertEquals(user.getEmail(),u.get().getEmail());
        assertEquals(user.getPhone(),u.get().getPhone());
        user.setName("Test1");
        repository.save(user);
        Optional<UserSchema> u1 = repository.findById(user.getId());
        assertEquals(user.getName(),u1.get().getName());

        //delete
        String id = user.getId();
        repository.deleteById(user.getId());
        Optional<UserSchema> u2 = repository.findById(id);
        assertFalse(u2.isPresent());

    }

}