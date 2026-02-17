package com.example.quickstudy2.service;

import com.example.quickstudy2.mappers.HobbyMapper;
import com.example.quickstudy2.models.Hobby;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    public List<Hobby> allHobbies() {
        return hobbyMapper.getHobbies();
    }

    public List<Hobby> getHobbiesByUserId(long userId) {
        return hobbyMapper.getHobbiesByUserId(userId);
    }

    public void addHobbyToUser(long userId, String hobbyName) {
        var h = hobbyMapper.getHobbyByName(hobbyName);
        if (h == null) {
            h = new Hobby();
            h.setName(hobbyName);
            hobbyMapper.saveHobby(h);
        }
        if(!hobbyMapper.doesHobbiesUsersByHobbyIdAndUserIdExists(h.getId(), userId)) {
            hobbyMapper.addHobbyToUser(userId, h.getId());
        }
    }

}
