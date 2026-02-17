package com.example.quickstudy.services;

import com.example.quickstudy.mappers.UserRepository;
import com.example.quickstudy.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(String username) {
        User user = new User(username);
        return userRepository.save(user);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
