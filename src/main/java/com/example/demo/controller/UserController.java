package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = service.saveUser(user);
        return ResponseEntity.ok(saved);
    }

    // GET all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return service.getUserById(id).map(existing -> {
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            User updated = service.saveUser(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
