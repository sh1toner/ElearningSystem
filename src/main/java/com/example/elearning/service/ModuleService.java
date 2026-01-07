package com.example.elearning.service;

import com.example.elearning.model.Module;
import com.example.elearning.model.Quiz;

import java.util.List;

public interface ModuleService {
    Module getModuleById(Long id);
    List<Quiz> getQuizzesByModuleId(Long moduleId);
}