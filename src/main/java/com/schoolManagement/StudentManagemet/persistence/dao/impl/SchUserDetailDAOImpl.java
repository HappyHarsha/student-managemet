package com.schoolManagement.StudentManagemet.persistence.dao.impl;

import com.schoolManagement.StudentManagemet.persistence.dao.SchUserDetailDAO;
import com.schoolManagement.StudentManagemet.persistence.entity.SchUserDetails;
import com.schoolManagement.StudentManagemet.persistence.repository.SchUserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SchUserDetailDAOImpl implements SchUserDetailDAO {

    private final SchUserDetailRepository userDetailRepository;

    @Autowired
    public SchUserDetailDAOImpl(SchUserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public SchUserDetails registerUser(SchUserDetails user) {
        return userDetailRepository.save(user);
    }
}
