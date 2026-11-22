package com.guicheng.student_api.controller;

import com.guicheng.student_api.dto.StudentRequest;
import com.guicheng.student_api.dto.StudentResponse;
import com.guicheng.student_api.service.StudentService;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request) {
        StudentResponse saved = studentService.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/count")
    public long getCount() {
        return studentService.count();
    }

    @GetMapping
    public List<StudentResponse> getAll(
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (lastName != null) {
            return studentService.searchByLastName(lastName);
        }
        return studentService.getAll(page, size);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<StudentResponse>> createAll(@RequestBody List<StudentRequest> requests) {
        List<StudentResponse> saved = studentService.createAll(requests);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
