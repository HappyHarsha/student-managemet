package com.schoolManagement.StudentManagemet.persistence.dao;

import com.schoolManagement.StudentManagemet.persistence.entity.Student;

import java.util.List;

public interface StudentDAO {
    Student addStudent(Student student);

    List<Student> getAll();
}
