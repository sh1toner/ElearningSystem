package com.example.elearning.controller.admin;

import com.example.elearning.model.Module;
import com.example.elearning.model.Quiz;
import com.example.elearning.repository.ModuleRepository;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/modules/{moduleId}/quizzes")
public class AdminQuizController {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping
    public String listQuizzes(@PathVariable Long moduleId, Model model) {
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        model.addAttribute("module", module);
        model.addAttribute("quizzes", quizRepository.findByModuleId(moduleId));
        return "admin/quiz-list";
    }

    @GetMapping("/new")
    public String showQuizForm(@PathVariable Long moduleId, Model model) {
        Quiz quiz = new Quiz();
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        quiz.setModule(module);
        model.addAttribute("quiz", quiz);
        model.addAttribute("module", module);
        return "admin/quiz-form";
    }

    @PostMapping
    public String createQuiz(@PathVariable Long moduleId, @ModelAttribute Quiz quiz) {
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        quiz.setModule(module);
        quizRepository.save(quiz);
        return "redirect:/admin/modules/" + moduleId + "/quizzes";
    }

    @GetMapping("/{id}/edit")
    public String editQuiz(@PathVariable Long moduleId, @PathVariable Long id, Model model) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        model.addAttribute("quiz", quiz);
        model.addAttribute("module", quiz.getModule());
        return "admin/quiz-form";
    }

    @PostMapping("/{id}")
    public String updateQuiz(@PathVariable Long moduleId, @PathVariable Long id, @ModelAttribute Quiz quiz) {
        quiz.setId(id);
        quiz.setModule(moduleRepository.findById(moduleId).orElseThrow());
        quizRepository.save(quiz);
        return "redirect:/admin/modules/" + moduleId + "/quizzes";
    }

    @GetMapping("/{id}/delete")
    public String deleteQuiz(@PathVariable Long moduleId, @PathVariable Long id) {
        quizRepository.deleteById(id);
        return "redirect:/admin/modules/" + moduleId + "/quizzes";
    }
}