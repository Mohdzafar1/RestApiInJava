package com.example.demo.repo;

import com.example.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Long> {

    List<Question> findByCategory(String category);
}
