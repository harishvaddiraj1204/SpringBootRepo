package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // Create user with duplicate email check
    public User saveUser(User user) {
        Optional<User> existing = repo.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        return repo.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    // Delete user
    public void deleteUser(String id) {
        repo.deleteById(id);
    }

    // Update user
    public User updateUser(String id, User updatedUser) {
        return repo.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return repo.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    /*@PostConstruct
    public void initData() {
        // Insert sample users
        userRepository.save(new User("Alice", 25));
        userRepository.save(new User("Bob", 30));

        // Print all users
        userRepository.findAll().forEach(user ->
                System.out.println(user.getId() + " " + user.getName() + " " + user.getAge())
        );
    }*/
}
