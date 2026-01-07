package com.example.elearning.controller.student;

import com.example.elearning.model.User;
import com.example.elearning.model.StudentModule;
import com.example.elearning.model.Quiz;
import com.example.elearning.service.UserService;
import com.example.elearning.repository.StudentModuleRepository;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class StudentQuizzesController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentModuleRepository studentModuleRepository;
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/student/quizzes")
    public String getStudentQuizzes(Model model, Authentication authentication) {
        String username = authentication.getName();
        User student = userService.findByUsername(username);
        List<StudentModule> studentModules = studentModuleRepository.findByStudent(student);

        Set<Quiz> quizzes = new HashSet<>();
        for (StudentModule sm : studentModules) {
            quizzes.addAll(quizRepository.findByModuleId(sm.getModule().getId()));
        }

        List<Quiz> quizzesList = new ArrayList<>(quizzes);
        quizzesList.sort(Comparator.comparing(Quiz::getName));

        model.addAttribute("quizzes", quizzesList);
        return "student/quizzes";
    }

    @GetMapping("/student/quizzes/{id}")
    public String getQuizDetails(@PathVariable Long id, Model model, Authentication authentication) {
        String username = authentication.getName();
        User student = userService.findByUsername(username);

        Optional<Quiz> quizOpt = quizRepository.findById(id);
        if (quizOpt.isEmpty()) return "redirect:/student/quizzes?notfound";
        Quiz quiz = quizOpt.get();

        boolean hasModule = studentModuleRepository.findByStudent(student).stream()
                .anyMatch(sm -> sm.getModule().getId().equals(quiz.getModule().getId()));
        if (!hasModule) return "redirect:/student/quizzes?forbidden";

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", quiz.getQuestions());
        return "student/quiz";
    }
}