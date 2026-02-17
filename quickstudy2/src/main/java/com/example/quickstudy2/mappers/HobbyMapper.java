package com.example.quickstudy2.mappers;

import com.example.quickstudy2.models.Hobby;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HobbyMapper {
    @Results(id = "hobbyResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    @Insert("INSERT INTO hobbies (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveHobby(Hobby hobby);

    @Select("SELECT id, name FROM hobbies")
    List<Hobby> getHobbies();

    @Select("""
        SELECT h.id id, h.name name
        FROM hobbies h
        JOIN hobbies_users uh ON h.id = uh.hobby_id
        WHERE uh.user_id = #{userId}
    """)
    List<Hobby> getHobbiesByUserId(long userId);


    @Insert("""
        INSERT INTO hobbies_users (user_id, hobby_id) VALUES (#{userId}, #{hobbyId})
    """)
    void addHobbyToUser(@Param("userId") long userId, @Param("hobbyId") long hobbyId);

    @Select("SELECT id, name FROM hobbies WHERE name = #{name}")
    Hobby getHobbyByName(String name);

    @Select("""
        SELECT COUNT(*) != 0 FROM hobbies_users
        WHERE hobby_id = #{hobbyId} AND user_id = #{userId}
    """)
    boolean doesHobbiesUsersByHobbyIdAndUserIdExists(@Param("hobbyId") long hobbyId, @Param("userId") long userId);
}
