package com.API_Basic.Books_Reserve.controller;


import com.API_Basic.Books_Reserve.model.UserData;
import com.API_Basic.Books_Reserve.service.UserServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceApplication userServiceApplication;
    public UserController(UserServiceApplication userServiceApplication) {
        this.userServiceApplication = userServiceApplication;
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getAllUsers(){
        List<UserData> searchAllUsers = userServiceApplication.getAllUsers();
        return ResponseEntity.ok(searchAllUsers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserData> getUserByID(@PathVariable int id){
        Optional<UserData> searchUserByID = userServiceApplication.getUserByID(id);
        return searchUserByID.map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/post")
    public ResponseEntity<UserData> createUser(@RequestBody UserData newUser){
        UserData userNew = userServiceApplication.createUser(newUser);
        return  ResponseEntity.ok(userNew);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<UserData> updateUser(@PathVariable int id, @RequestBody UserData newUser){
        Optional<UserData> userNew = userServiceApplication.uptadeUserByID(id, newUser);
        return userNew.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserData> deleteUserByID(@PathVariable int id){
        Optional<UserData> userDelete = userServiceApplication.deleteUserByID(id);
        return userDelete.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
