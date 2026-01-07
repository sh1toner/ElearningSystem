    package com.example.elearning.controller.teacher;

    import com.example.elearning.model.Role;
    import com.example.elearning.model.User;
    import com.example.elearning.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    import java.util.List;

    @Controller
    @RequestMapping("/teacher/students")
    public class TeacherStudentController {

        @Autowired
        private UserRepository userRepository;

        @GetMapping
        public String listStudents(Model model) {
            List<User> students = userRepository.findByRole(Role.valueOf("STUDENT"));
            model.addAttribute("students", students);
            return "teacher/students";
        }
    }