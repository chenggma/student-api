package com.guicheng.student_api.service;

import com.guicheng.student_api.dto.StudentRequest;
import com.guicheng.student_api.dto.StudentResponse;
import com.guicheng.student_api.entity.Student;
import com.guicheng.student_api.exception.ResourceNotFoundException;
import com.guicheng.student_api.mapper.StudentMapper;
import com.guicheng.student_api.repository.StudentRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AuditService auditService;

    public StudentService (StudentRepository studentRepository, AuditService auditService){
        this.studentRepository = studentRepository;
        this.auditService = auditService;
    }

    private Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public StudentResponse getById(Long id) {
        Student student = findStudent(id);
        return StudentMapper.toResponse(student);
    }

    public List<StudentResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Student> result = studentRepository.findAll(pageable);
        return result.getContent()
                .stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

    public long count() {
        return studentRepository.count();
    }

    public List<StudentResponse> searchByLastName(String lastName) {
        return studentRepository.findByLastNameIgnoreCase(lastName)
                .stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

    public StudentResponse create(StudentRequest request) {
        Student student = StudentMapper.toEntity(request);
        Student saved = studentRepository.save(student);
        return StudentMapper.toResponse(saved);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student existing = findStudent(id);

        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());

        Student saved = studentRepository.save(existing);
        return StudentMapper.toResponse(saved);
    }

    public void delete(Long id) {
        Student existing = findStudent(id);
        studentRepository.delete(existing);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<StudentResponse> createAll(List<StudentRequest> requests) {
        auditService.log("Batch create requested: " + requests.size() + " students");
        List<StudentResponse> result = new ArrayList<>();
        for (StudentRequest request : requests) {
            result.add(create(request));
        }
        return result;
    }
}
