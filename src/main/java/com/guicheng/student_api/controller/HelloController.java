package com.guicheng.student_api.controller;

import com.guicheng.student_api.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final StudentRepository studentRepository;

    public HelloController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/api/students/count")
    public long count() {
        return studentRepository.count();
    }
}
