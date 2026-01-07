package com.example.elearning.service;

import com.example.elearning.model.Course;
import com.example.elearning.model.Module;
import com.example.elearning.repository.CourseRepository;
import com.example.elearning.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Курс із ID " + id + " не знайдено"));
    }

    public List<Module> getModulesByCourseId(Long courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Курс із ID " + id + " не знайдено");
        }
        course.setId(id);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Курс із ID " + id + " не знайдено");
        }
        courseRepository.deleteById(id);
    }
}