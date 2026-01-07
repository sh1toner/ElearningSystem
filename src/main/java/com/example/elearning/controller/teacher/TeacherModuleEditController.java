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
public class TeacherModuleEditController {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Module module = moduleRepository.findById(id).orElseThrow();
        model.addAttribute("module", module);
        model.addAttribute("courses", courseRepository.findAll());
        return "teacher/module-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String topic,
                       @RequestParam String description,
                       @RequestParam Long courseId) {
        Module module = moduleRepository.findById(id).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        module.setTopic(topic);
        module.setDescription(description);
        module.setCourse(course);
        moduleRepository.save(module);
        return "redirect:/teacher/modules";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        moduleRepository.deleteById(id);
        return "redirect:/teacher/modules";
    }
}