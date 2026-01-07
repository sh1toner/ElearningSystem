package com.example.elearning.controller.student;

import com.example.elearning.model.User;
import com.example.elearning.model.StudentModule;
import com.example.elearning.model.Module;
import com.example.elearning.model.Course;
import com.example.elearning.model.Quiz;
import com.example.elearning.service.UserService;
import com.example.elearning.repository.StudentModuleRepository;
import com.example.elearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class StudentCoursesController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentModuleRepository studentModuleRepository;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/student/courses")
    public String getStudentCourses(Model model, Authentication authentication) {
        String username = authentication.getName();
        User student = userService.findByUsername(username);


        List<StudentModule> studentModules = studentModuleRepository.findByStudent(student);

        Map<Course, List<Module>> modulesByCourse = new LinkedHashMap<>();
        for (StudentModule sm : studentModules) {
            Module module = sm.getModule();
            Course course = module.getCourse();
            modulesByCourse.computeIfAbsent(course, k -> new ArrayList<>()).add(module);
        }

        Map<Long, List<Quiz>> quizzesByModule = new HashMap<>();
        for (StudentModule sm : studentModules) {
            Module module = sm.getModule();
            List<Quiz> quizzes = quizRepository.findByModuleId(module.getId());
            quizzesByModule.put(module.getId(), quizzes);
        }

        model.addAttribute("modulesByCourse", modulesByCourse);
        model.addAttribute("quizzesByModule", quizzesByModule);
        return "student/courses";
    }
}