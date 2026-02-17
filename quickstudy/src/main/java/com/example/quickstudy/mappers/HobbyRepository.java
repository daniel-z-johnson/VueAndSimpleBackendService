package com.example.quickstudy.mappers;

import com.example.quickstudy.models.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    Hobby findByName(String name);
}
