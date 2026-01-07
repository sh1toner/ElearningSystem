package com.example.elearning.service.impl;

import com.example.elearning.model.Module;
import com.example.elearning.model.Quiz;
import com.example.elearning.repository.ModuleRepository;
import com.example.elearning.repository.QuizRepository;
import com.example.elearning.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository, QuizRepository quizRepository) {
        this.moduleRepository = moduleRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Module not found: " + id));
    }

    @Override
    public List<Quiz> getQuizzesByModuleId(Long moduleId) {
        return quizRepository.findByModuleId(moduleId);
    }
}