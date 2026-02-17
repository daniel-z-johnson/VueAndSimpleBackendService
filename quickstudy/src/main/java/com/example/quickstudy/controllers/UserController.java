package com.example.quickstudy.controllers;

import com.example.quickstudy.dto.HobbyDTO;
import com.example.quickstudy.dto.UserDTO;
import com.example.quickstudy.mappers.HobbyRepository;
import com.example.quickstudy.mappers.UserRepository;
import com.example.quickstudy.models.Hobby;
import com.example.quickstudy.models.User;
import com.example.quickstudy.services.HobbyService;
import com.example.quickstudy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final HobbyService hobbyService;

    @GetMapping
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO.getUsername());
    }

    @PostMapping("/{userId}/hobbies/{hobbyName}")
    public Hobby tieHobbyToUser(@PathVariable(name = "userId") long userId,
                                @PathVariable(name = "hobbyName") String hobbyName) {
        return hobbyService.tieHobbyToUser(hobbyName, userId);
    }
}
