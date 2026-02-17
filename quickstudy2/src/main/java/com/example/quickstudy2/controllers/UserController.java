package com.example.quickstudy2.controllers;

import com.example.quickstudy2.dto.UserDto;
import com.example.quickstudy2.mappers.UserMapper;
import com.example.quickstudy2.models.Hobby;
import com.example.quickstudy2.models.User;
import com.example.quickstudy2.service.HobbyService;
import com.example.quickstudy2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final HobbyService hobbyService;

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        userService.createUser(user);
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        var users =  userService.getAllUsers();
        users.forEach(user -> user.setHobbies(hobbyService.getHobbiesByUserId(user.getId())));
        return users;
    }

    @PostMapping("/{userId}/hobbies/{hobbyName}")
    public User addHobbyToUser(@PathVariable long userId, @PathVariable String hobbyName) {
        hobbyService.addHobbyToUser(userId, hobbyName);
        var user = userService.getUserById(userId);
        return user;
    }
}
