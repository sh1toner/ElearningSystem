package com.example.elearning.controller.teacher;

import com.example.elearning.model.Module;
import com.example.elearning.model.Quiz;
import com.example.elearning.repository.ModuleRepository;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/quizzes")
public class TeacherQuizCreateController {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/create")
    public String createQuizForm(Model model) {
        model.addAttribute("modules", moduleRepository.findAll());
        return "teacher/quizzes-create";
    }

    @PostMapping("/create")
    public String createQuiz(@RequestParam String name,
                             @RequestParam(required = false) String description,
                             @RequestParam Long moduleId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setModule(module);
        quizRepository.save(quiz);
        return "redirect:/teacher/quizzes";
    }
}