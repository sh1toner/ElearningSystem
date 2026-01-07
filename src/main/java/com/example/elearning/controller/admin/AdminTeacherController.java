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
@RequestMapping("/admin/teachers")
public class AdminTeacherController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", userRepository.findByRole(Role.TEACHER));
        return "admin/teacher-list";
    }

    @GetMapping("/new")
    public String showTeacherForm(Model model) {
        User user = new User();
        user.setRole(Role.TEACHER);
        model.addAttribute("teacher", user);
        return "admin/teacher-form";
    }

    @PostMapping
    public String addTeacher(@ModelAttribute("teacher") User teacher) {
        teacher.setRole(Role.TEACHER);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        userRepository.save(teacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<User> teacherOpt = userRepository.findById(id);
        if (teacherOpt.isPresent() && teacherOpt.get().getRole() == Role.TEACHER) {
            User teacher = teacherOpt.get();

            teacher.setPassword("");
            model.addAttribute("teacher", teacher);
            return "admin/teacher-edit-form";
        }
        return "redirect:/admin/teachers";
    }

    @PostMapping("/{id}/edit")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute("teacher") User editTeacher) {
        Optional<User> teacherOpt = userRepository.findById(id);
        if (teacherOpt.isPresent() && teacherOpt.get().getRole() == Role.TEACHER) {
            User teacher = teacherOpt.get();
            teacher.setUsername(editTeacher.getUsername());
            teacher.setEmail(editTeacher.getEmail());

            if (editTeacher.getPassword() != null && !editTeacher.getPassword().isBlank()) {
                teacher.setPassword(passwordEncoder.encode(editTeacher.getPassword()));
            }
            userRepository.save(teacher);
        }
        return "redirect:/admin/teachers";
    }

    @GetMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Long id) {
        Optional<User> teacherOpt = userRepository.findById(id);
        if (teacherOpt.isPresent() && teacherOpt.get().getRole() == Role.TEACHER) {
            userRepository.deleteById(id);
        }
        return "redirect:/admin/teachers";
    }
}