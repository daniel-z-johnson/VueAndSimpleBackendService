package com.example.quickstudy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    public UserDTO(String username) {
        this.username = username;
    }

    private String username;
}
