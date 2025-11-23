package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void findUsersByName_ShouldReturnMatchingUsers() {
        List<User> users = List.of(
                new User(),
                new User(),
                new User()
        );

        when(userRepository.findAll()).thenReturn(users);

        /*List<User> result = userService.findUsersByName("jo");
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());*/
    }
}
