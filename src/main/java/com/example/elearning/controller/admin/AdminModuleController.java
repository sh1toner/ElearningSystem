package com.example.elearning.controller.admin;

import com.example.elearning.model.Course;
import com.example.elearning.model.Module;
import com.example.elearning.repository.CourseRepository;
import com.example.elearning.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses/{courseId}/modules")
public class AdminModuleController {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String listModules(@PathVariable Long courseId, Model model) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        model.addAttribute("course", course);
        model.addAttribute("modules", moduleRepository.findByCourseId(courseId));
        return "admin/module-list";
    }

    @GetMapping("/new")
    public String showModuleForm(@PathVariable Long courseId, Model model) {
        Module module = new Module();
        Course course = courseRepository.findById(courseId).orElseThrow();
        module.setCourse(course);
        model.addAttribute("module", module);
        model.addAttribute("course", course);
        return "admin/module-form";
    }

    @PostMapping
    public String createModule(@PathVariable Long courseId, @ModelAttribute Module module) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        module.setCourse(course);
        moduleRepository.save(module);
        return "redirect:/admin/courses/" + courseId + "/modules";
    }

    @GetMapping("/{id}/edit")
    public String editModule(@PathVariable Long courseId, @PathVariable Long id, Model model) {
        Module module = moduleRepository.findById(id).orElseThrow();
        model.addAttribute("module", module);
        model.addAttribute("course", module.getCourse());
        return "admin/module-form";
    }

    @PostMapping("/{id}")
    public String updateModule(@PathVariable Long courseId, @PathVariable Long id, @ModelAttribute Module module) {
        module.setId(id);
        module.setCourse(courseRepository.findById(courseId).orElseThrow());
        moduleRepository.save(module);
        return "redirect:/admin/courses/" + courseId + "/modules";
    }

    @GetMapping("/{id}/delete")
    public String deleteModule(@PathVariable Long courseId, @PathVariable Long id) {
        moduleRepository.deleteById(id);
        return "redirect:/admin/courses/" + courseId + "/modules";
    }
}