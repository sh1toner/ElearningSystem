package com.example.elearning.controller.teacher;

import com.example.elearning.model.Role;
import com.example.elearning.model.User;
import com.example.elearning.model.Module;
import com.example.elearning.model.StudentModule;
import com.example.elearning.repository.UserRepository;
import com.example.elearning.repository.ModuleRepository;
import com.example.elearning.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/assign-module")
public class TeacherAssignModuleController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private StudentModuleRepository studentModuleRepository;

    @GetMapping
    public String assignForm(Model model) {
        model.addAttribute("students", userRepository.findByRole(Role.valueOf("STUDENT")));
        model.addAttribute("modules", moduleRepository.findAll());
        return "teacher/assign-module";
    }

    @PostMapping
    public String assign(@RequestParam Long studentId, @RequestParam Long moduleId) {
        User student = userRepository.findById(studentId).orElseThrow();
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        StudentModule sm = new StudentModule();
        sm.setStudent(student);
        sm.setModule(module);
        studentModuleRepository.save(sm);
        return "redirect:/teacher/assign-module";
    }
}