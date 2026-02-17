package com.example.quickstudy2.service;

import com.example.quickstudy2.mappers.UserMapper;
import com.example.quickstudy2.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final HobbyService hobbyService;

    public User createUser(User user) {
        userMapper.saveUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userMapper.allUsers();
    }

    public User getUserById(long id) {
        var user = userMapper.getUserById(id);
        user.setHobbies(hobbyService.getHobbiesByUserId(user.getId()));
        return user;
    }
}
