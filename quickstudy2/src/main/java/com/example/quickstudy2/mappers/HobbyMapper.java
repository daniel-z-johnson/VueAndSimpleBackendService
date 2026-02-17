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

    @Select("SELECT id, name FROM hobbies WHERE id = #{id}")
    List<Hobby> getHobbyById(long id);
}
