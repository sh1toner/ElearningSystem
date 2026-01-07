package com.example.elearning.config;

import com.example.elearning.model.Role;
import com.example.elearning.model.User;
import com.example.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            Role role = user.getRole();
            if (role == Role.TEACHER) {
                response.sendRedirect("/teacher/panel");
            } else if (role == Role.STUDENT) {
                response.sendRedirect("/student/courses");
            } else if (role == Role.ADMIN) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/login?error");
            }
        } else {
            response.sendRedirect("/login?error");
        }
    }
}