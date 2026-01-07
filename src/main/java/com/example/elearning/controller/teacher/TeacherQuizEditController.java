package com.example.elearning.controller.teacher;

import com.example.elearning.model.Quiz;
import com.example.elearning.model.Module;
import com.example.elearning.repository.QuizRepository;
import com.example.elearning.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/quizzes")
public class TeacherQuizEditController {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        model.addAttribute("quiz", quiz);
        model.addAttribute("modules", moduleRepository.findAll());
        return "teacher/quiz-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String name,
                       @RequestParam Long moduleId) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        quiz.setName(name);
        quiz.setModule(module);
        quizRepository.save(quiz);
        return "redirect:/teacher/quizzes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        quizRepository.deleteById(id);
        return "redirect:/teacher/quizzes";
    }
}