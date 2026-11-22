package com.guicheng.student_api.repository;

import com.guicheng.student_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastNameIgnoreCase(String lastName);
}
