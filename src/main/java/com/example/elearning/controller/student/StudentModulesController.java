package com.example.elearning.controller.student;

import com.example.elearning.model.User;
import com.example.elearning.model.StudentModule;
import com.example.elearning.model.Module;
import com.example.elearning.service.UserService;
import com.example.elearning.repository.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentModulesController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentModuleRepository studentModuleRepository;

    @GetMapping("/student/modules")
    public String getStudentModules(Model model, Authentication authentication) {
        String username = authentication.getName();
        User student = userService.findByUsername(username);
        List<StudentModule> studentModules = studentModuleRepository.findByStudent(student);

        List<Module> modules = studentModules.stream()
                .map(StudentModule::getModule)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("modules", modules);
        return "student/modules";
    }
}