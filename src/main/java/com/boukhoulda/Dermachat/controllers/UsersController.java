package com.boukhoulda.Dermachat.controllers;

import com.boukhoulda.Dermachat.models.User;
import com.boukhoulda.Dermachat.storage.UserStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
public class UsersController {

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        System.out.println(user.getUserName());

        return user.getUserName().equals("user") && user.getPassword().equals("password");
    }
    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("Handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception exc) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }
}
