package com.example.elearning.controller.teacher;

import com.example.elearning.model.Question;
import com.example.elearning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teacher/quizzes/questions")
public class TeacherAllQuestionsController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public String allQuestions(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "teacher/all-questions";
    }
}