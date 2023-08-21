package com.studentmanagement.app.mvc.controller;

import com.studentmanagement.app.mvc.entity.Student;
import com.studentmanagement.app.mvc.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/create")
    public String createStudent(Model model) {
        //creating student obj to hold form data.
        Student student = new Student();
        model.addAttribute("student",student);
        return "create";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute ("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit";
    }

}
