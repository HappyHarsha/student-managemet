package com.schoolManagement.StudentManagemet.service.impl;

import com.schoolManagement.StudentManagemet.persistence.dao.StudentDAO;
import com.schoolManagement.StudentManagemet.persistence.entity.Student;
import com.schoolManagement.StudentManagemet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }
}
