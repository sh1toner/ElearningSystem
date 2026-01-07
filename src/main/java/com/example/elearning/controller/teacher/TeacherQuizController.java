package com.example.elearning.controller.teacher;

import com.example.elearning.model.Quiz;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/quizzes")
public class TeacherQuizController {

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public String listQuizzes(Model model) {
        model.addAttribute("quizzes", quizRepository.findAll());
        return "teacher/quizzes";
    }
}