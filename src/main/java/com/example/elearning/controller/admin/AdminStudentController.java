package com.example.elearning.controller.admin;

import com.example.elearning.model.Role;
import com.example.elearning.model.User;
import com.example.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/students")
public class AdminStudentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", userRepository.findByRole(Role.STUDENT));
        return "admin/student-list";
    }

    @GetMapping("/new")
    public String showStudentForm(Model model) {
        User user = new User();
        user.setRole(Role.STUDENT);
        model.addAttribute("student", user);
        return "admin/student-form";
    }

    @PostMapping
    public String addStudent(@ModelAttribute("student") User student) {
        student.setRole(Role.STUDENT);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        userRepository.save(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<User> studentOpt = userRepository.findById(id);
        if (studentOpt.isPresent() && studentOpt.get().getRole() == Role.STUDENT) {
            User student = studentOpt.get();
            student.setPassword("");
            model.addAttribute("student", student);
            return "admin/student-edit-form";
        }
        return "redirect:/admin/students";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") User editStudent) {
        Optional<User> studentOpt = userRepository.findById(id);
        if (studentOpt.isPresent() && studentOpt.get().getRole() == Role.STUDENT) {
            User student = studentOpt.get();
            student.setUsername(editStudent.getUsername());
            student.setEmail(editStudent.getEmail());
            if (editStudent.getPassword() != null && !editStudent.getPassword().isBlank()) {
                student.setPassword(passwordEncoder.encode(editStudent.getPassword()));
            }
            userRepository.save(student);
        }
        return "redirect:/admin/students";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        Optional<User> studentOpt = userRepository.findById(id);
        if (studentOpt.isPresent() && studentOpt.get().getRole() == Role.STUDENT) {
            userRepository.deleteById(id);
        }
        return "redirect:/admin/students";
    }
}