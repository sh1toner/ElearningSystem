package com.example.elearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanelController {
    @GetMapping({"/admin", "/admin/index"})
    public String adminPanel() {
        return "admin/index";
    }
}