package com.example.elearning.controller;

import com.example.elearning.model.Module;
import com.example.elearning.model.Quiz;
import com.example.elearning.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ModulePageController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/modules/{id}")
    public String getModulePage(@PathVariable Long id, Model model) {
        Module module = moduleService.getModuleById(id);
        List<Quiz> quizzes = moduleService.getQuizzesByModuleId(id);

        model.addAttribute("module", module);
        model.addAttribute("quizzes", quizzes);

        return "module";
    }
}