package com.guicheng.student_api.mapper;

import com.guicheng.student_api.dto.StudentRequest;
import com.guicheng.student_api.dto.StudentResponse;
import com.guicheng.student_api.entity.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        return student;
    }

    public static StudentResponse toResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        return studentResponse;
    }


}
