package com.schoolManagement.StudentManagemet.persistence.repository;

import com.schoolManagement.StudentManagemet.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
