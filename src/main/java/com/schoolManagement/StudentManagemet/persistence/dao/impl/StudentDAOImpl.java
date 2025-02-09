package com.schoolManagement.StudentManagemet.persistence.dao.impl;

import com.schoolManagement.StudentManagemet.persistence.dao.StudentDAO;
import com.schoolManagement.StudentManagemet.persistence.entity.Student;
import com.schoolManagement.StudentManagemet.persistence.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentDAOImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
