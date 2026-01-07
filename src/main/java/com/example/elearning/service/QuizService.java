package com.example.elearning.service;

import com.example.elearning.model.Quiz;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getQuizzesByModuleId(Long moduleId) {
        return quizRepository.findByModuleId(moduleId);
    }
}