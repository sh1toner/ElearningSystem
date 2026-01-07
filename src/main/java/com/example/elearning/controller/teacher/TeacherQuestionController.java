package com.example.elearning.controller.teacher;

import com.example.elearning.model.Question;
import com.example.elearning.model.Quiz;
import com.example.elearning.repository.QuestionRepository;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher/quizzes/{quizId}/questions")
public class TeacherQuestionController {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public String list(@PathVariable Long quizId, Model model) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        List<Question> questions = questionRepository.findByQuizId(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        return "teacher/questions";
    }

    @GetMapping("/new")
    public String createForm(@PathVariable Long quizId, Model model) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        Question question = new Question();
        question.setQuiz(quiz);
        model.addAttribute("quiz", quiz);
        model.addAttribute("question", question);
        return "teacher/question-form";
    }

    @PostMapping
    public String save(@PathVariable Long quizId, @ModelAttribute Question question) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        question.setQuiz(quiz);
        questionRepository.save(question);
        return "redirect:/teacher/quizzes/" + quizId + "/questions";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long quizId, @PathVariable Long id, Model model) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        Question question = questionRepository.findById(id).orElseThrow();
        model.addAttribute("quiz", quiz);
        model.addAttribute("question", question);
        return "teacher/question-form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long quizId, @PathVariable Long id, @ModelAttribute Question question) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        Question existing = questionRepository.findById(id).orElseThrow();
        existing.setText(question.getText());
        questionRepository.save(existing);
        return "redirect:/teacher/quizzes/" + quizId + "/questions";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long quizId, @PathVariable Long id) {
        questionRepository.deleteById(id);
        return "redirect:/teacher/quizzes/" + quizId + "/questions";
    }
}