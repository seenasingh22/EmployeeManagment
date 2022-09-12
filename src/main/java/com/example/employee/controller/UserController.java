package com.example.employee.controller;

import com.example.employee.repository.UserRepository;
import com.example.employee.schema.UserSchema;
import com.example.employee.utils.Messages;
import com.example.employee.utils.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    public UserRepository repository;

    @GetMapping(value = Routes.USER)
    public ResponseEntity<?> get(){
        List<UserSchema> users = repository.findAll();
        if(users.size()>0){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(Messages.USER_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = Routes.USER + "/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        try {
            Optional<UserSchema> user = repository.findById(id);
            if(user.isPresent()){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(Messages.USER_NOT_FOUND,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = Routes.USER)
    public ResponseEntity<?> create(@RequestBody UserSchema body){
         try{
             body.setCreatedAt(new Date(System.currentTimeMillis()));
             repository.save(body);
             return new ResponseEntity<>(body,HttpStatus.OK);
         }
         catch (Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @PutMapping(value = Routes.USER+"/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody UserSchema body){
        try{
            Optional<UserSchema> user = repository.findById(id);
            if(user.isPresent()){
                user.get().setName(body.getName()!= null?body.getName():user.get().getName());
                user.get().setDesignation(body.getDesignation()!=null?body.getDesignation():user.get().getDesignation());
                user.get().setPhone(body.getPhone() != null?body.getPhone():user.get().getPhone());
                if(body.getEmail() != null){
                    return new ResponseEntity<>(Messages.EMAIL_ERROR,HttpStatus.BAD_REQUEST);
                }
                user.get().setUpdatedAt(new Date(System.currentTimeMillis()));
                repository.save(user.get());
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(Messages.USER_NOT_FOUND,HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = Routes.USER + "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            Optional<UserSchema> user = repository.findById(id);
            if (user.isPresent()){
                repository.deleteById(id);
                return new ResponseEntity<>(Messages.DELETE,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(Messages.USER_NOT_FOUND,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
