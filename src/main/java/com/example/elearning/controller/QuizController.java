package com.example.elearning.controller;

import com.example.elearning.model.Quiz;
import com.example.elearning.model.Question;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/{quizId}")
    public String viewQuiz(@PathVariable Long quizId, Model model) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);
        if (quizOpt.isPresent()) {
            model.addAttribute("quiz", quizOpt.get());
            return "quiz-view";
        }
        return "redirect:/";
    }

    @PostMapping("/{quizId}/submit")
    public String submitQuiz(@PathVariable Long quizId,
                             @RequestParam Map<String, String> params,
                             Model model) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);
        if (quizOpt.isEmpty()) return "redirect:/";
        Quiz quiz = quizOpt.get();

        Map<Long, String> answers = new HashMap<>();
        for (Question q : quiz.getQuestions()) {
            String key = "q_" + q.getId();
            answers.put(q.getId(), params.getOrDefault(key, ""));
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("answers", answers);
        return "quiz-result";
    }
}