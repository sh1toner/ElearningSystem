package com.example.elearning.controller.teacher;

import com.example.elearning.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/assigned-modules")
public class TeacherAssignedModulesController {

    @Autowired
    private StudentModuleRepository studentModuleRepository;

    @GetMapping
    public String viewAssignedModules(Model model) {
        model.addAttribute("assignedModules", studentModuleRepository.findAll());
        return "teacher/assigned-modules";
    }

    @PostMapping("/delete/{id}")
    public String deleteAssignedModule(@PathVariable Long id) {
        studentModuleRepository.deleteById(id);
        return "redirect:/teacher/assigned-modules";
    }
}