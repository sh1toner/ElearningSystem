package com.example.elearning.controller.teacher;

import com.example.elearning.model.QuizAttempt;
import com.example.elearning.repository.QuizAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/quiz-attempts")
public class TeacherQuizAttemptController {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @GetMapping
    public String listAttempts(Model model) {
        model.addAttribute("attempts", quizAttemptRepository.findAll());
        return "teacher/quiz-attempts";
    }
}