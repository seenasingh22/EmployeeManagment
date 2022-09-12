package com.example.employee.repository;

import com.example.employee.schema.UserSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserSchema,String> {
}
