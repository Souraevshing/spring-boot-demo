package com.studentmanagement.app.mvc.service;

import com.studentmanagement.app.mvc.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Student student);

}
