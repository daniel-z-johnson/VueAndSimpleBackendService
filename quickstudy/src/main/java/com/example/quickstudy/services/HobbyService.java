package com.example.quickstudy.services;

import com.example.quickstudy.mappers.HobbyRepository;
import com.example.quickstudy.mappers.UserRepository;
import com.example.quickstudy.models.Hobby;
import com.example.quickstudy.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final UserRepository userRepository;

    public Hobby createHobby(String hobbyName) {
        Hobby hobby = new Hobby(hobbyName);
        return hobbyRepository.save(hobby);
    }

    @Transactional
    public Hobby tieHobbyToUser(String hobbyname, long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Hobby hobby = hobbyRepository.findByName(hobbyname);
        if (hobby == null) {
            hobby = new Hobby(hobbyname);
            hobby = hobbyRepository.save(hobby);
        }

        // modify the owner side so JPA will update the join table
        user.getHobbies().add(hobby);
        // keep both sides in sync
        hobby.getUsers().add(user);

        // save and flush the owner side to ensure join table rows are written
        userRepository.saveAndFlush(user);

        return hobby;
    }
}
