package com.example.quickstudy2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private List<Hobby> hobbies;
}
