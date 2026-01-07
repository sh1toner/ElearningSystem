package com.example.elearning.controller;

import com.example.elearning.model.Course;
import com.example.elearning.model.Module;
import com.example.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CoursePageController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses/{id}")
    public String getCoursePage(@PathVariable Long id, Model model) {

        Course course = courseService.getCourseById(id);

        List<Module> modules = courseService.getModulesByCourseId(id);

        model.addAttribute("course", course);
        model.addAttribute("modules", modules);

        return "course";
    }
}