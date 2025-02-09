package com.schoolManagement.StudentManagemet.controller;

import com.schoolManagement.StudentManagemet.persistence.entity.Student;
import com.schoolManagement.StudentManagemet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("add")
    public ResponseEntity<String> addStudent(@RequestBody  Student student){
     Student student1 = studentService.addStudent(student);
     if(student1 != null){
         return ResponseEntity.ok("Student Added Successfully");
     }
        return ResponseEntity.ok("Student Not  Added Successfully");
    }


    @GetMapping("getAll")
    public ResponseEntity<List<Student>> addStudent(){
        List<Student> student1 = studentService.getAll();
        if(student1 != null && !student1.isEmpty()){
            return ResponseEntity.ok(student1);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
            return ResponseEntity.ok("hello");
    }
}
