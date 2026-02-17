package com.example.quickstudy2.mappers;

import com.example.quickstudy2.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Results(id = "userResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username")
    })
    @Insert("INSERT INTO users (username) VALUES (#{username})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveUser(User user);

    @Select("SELECT id, username FROM users")
    List<User> allUsers();

    @Select("SELECT id, username FROM users WHERE id = #{id}")
    User getUserById(long id);
}
