package com.guicheng.student_api.service;

import com.guicheng.student_api.entity.Student;
import com.guicheng.student_api.exception.ResourceNotFoundException;
import com.guicheng.student_api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        Student existing = getById(id);

        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setEmail(student.getEmail());

        return studentRepository.save(existing);
    }

    public void delete(Long id) {
        Student existing = getById(id);
        studentRepository.delete(existing);
    }
}
