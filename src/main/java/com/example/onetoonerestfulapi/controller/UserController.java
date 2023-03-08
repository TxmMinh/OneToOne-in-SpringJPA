package com.example.onetoonerestfulapi.controller;

import com.example.onetoonerestfulapi.entity.Address;
import com.example.onetoonerestfulapi.entity.User;
import com.example.onetoonerestfulapi.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){
        Address address = Address.builder()
                .street("Vo Thi Minh Khai")
                .city("Ho Chi Minh")
                .build();

        User user = User.builder()
                .name("Txm Minh")
                .phone("0341234567")
                .build();

        user.setAddress(address);
        userService.save(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        User userData = userService.findById(id);
        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.remove(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User tempUser = new User();
        tempUser.setName(user.getName());
        tempUser.setPhone(user.getPhone());
        tempUser.setAddress(user.getAddress());
        return new ResponseEntity<>(userService.save(tempUser), HttpStatus.CREATED);
    }

    /*
    @RequestBody nói với Spring Boot rằng hãy chuyển Json trong request body
    thành đối tượng User
    */
    @PutMapping("/todo/{userId}")
    public ResponseEntity<User> editUser(@PathVariable(name = "userId") Long userId,
                         @RequestBody User user){
        User tempuser = userService.findById(userId);
        if (tempuser != null) {
            tempuser.setName(user.getName());
            tempuser.setPhone(user.getPhone());
            tempuser.setAddress(user.getAddress());
            return new ResponseEntity<>(userService.save(tempuser), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
