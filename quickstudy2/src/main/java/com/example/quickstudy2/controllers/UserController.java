package com.example.quickstudy2.controllers;

import com.example.quickstudy2.dto.UserDto;
import com.example.quickstudy2.mappers.UserMapper;
import com.example.quickstudy2.models.User;
import com.example.quickstudy2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        userService.createUser(user);
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
