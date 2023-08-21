package com.studentmanagement.app.mvc.service;

import com.studentmanagement.app.mvc.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    Student saveStudent(Student student);

}
