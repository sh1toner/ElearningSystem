package com.example.elearning.controller.teacher;

import com.example.elearning.model.Course;
import com.example.elearning.model.User;
import com.example.elearning.repository.CourseRepository;
import com.example.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/courses")
public class TeacherCourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listCourses(Model model, Authentication authentication) {
        String username = authentication.getName();
        User teacher = userRepository.findByUsername(username).orElse(null);
        model.addAttribute("courses", courseRepository.findByTeacher(teacher));
        return "teacher/courses";
    }

    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "teacher/course-add";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, Authentication authentication) {
        String username = authentication.getName();
        User teacher = userRepository.findByUsername(username).orElse(null);
        course.setTeacher(teacher);
        courseRepository.save(course);
        return "redirect:/teacher/modules";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id).orElseThrow();
        model.addAttribute("course", course);
        return "teacher/course-edit";
    }

    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, @ModelAttribute Course courseForm) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setName(courseForm.getName());
        course.setDescription(courseForm.getDescription());
        courseRepository.save(course);
        return "redirect:/teacher/courses";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "redirect:/teacher/courses";
    }
}