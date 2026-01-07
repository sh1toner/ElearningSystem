package com.example.elearning.repository;

import com.example.elearning.model.StudentModule;
import com.example.elearning.model.User;
import com.example.elearning.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentModuleRepository extends JpaRepository<StudentModule, Long> {
    List<StudentModule> findByStudent(User student);
    List<StudentModule> findByModule(Module module);
}