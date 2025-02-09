package com.schoolManagement.StudentManagemet.service;

import com.schoolManagement.StudentManagemet.persistence.entity.Student;

import java.util.List;

public interface StudentService  {
    Student addStudent(Student student);

    List<Student> getAll();
}
