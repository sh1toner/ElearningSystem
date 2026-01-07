package com.example.elearning.controller;

import com.example.elearning.model.Quiz;
import com.example.elearning.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuizPageController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String getQuizzesPage(@RequestParam("moduleId") Long moduleId, Model model) {

        List<Quiz> quizzes = quizService.getQuizzesByModuleId(moduleId);

        model.addAttribute("quizzes", quizzes);

        return "quizzes";
    }
}