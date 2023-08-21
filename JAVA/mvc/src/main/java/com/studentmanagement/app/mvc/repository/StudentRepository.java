package com.studentmanagement.app.mvc.repository;

import com.studentmanagement.app.mvc.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Long> {
}
