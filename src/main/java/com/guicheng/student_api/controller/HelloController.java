package com.guicheng.student_api.controller;

import com.guicheng.student_api.entity.Student;
import com.guicheng.student_api.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final StudentService studentService;

    public HelloController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/api/students/count")
    public long count() {
        return studentService.getAll().size();
    }

    @GetMapping("/api/students/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }
}
