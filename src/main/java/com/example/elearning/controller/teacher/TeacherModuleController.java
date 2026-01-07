package com.example.elearning.controller.teacher;

import com.example.elearning.model.Module;
import com.example.elearning.model.Course;
import com.example.elearning.repository.ModuleRepository;
import com.example.elearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/modules")
public class TeacherModuleController {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String listModules(Model model) {
        model.addAttribute("modules", moduleRepository.findAll());
        return "teacher/modules";
    }

    @GetMapping("/add")
    public String addModuleForm(Model model) {
        model.addAttribute("module", new Module());
        model.addAttribute("courses", courseRepository.findAll());
        return "teacher/module-add";
    }

    @PostMapping("/add")
    public String addModule(@ModelAttribute Module module, @RequestParam Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        module.setCourse(course);
        moduleRepository.save(module);
        return "redirect:/teacher/modules";
    }
}