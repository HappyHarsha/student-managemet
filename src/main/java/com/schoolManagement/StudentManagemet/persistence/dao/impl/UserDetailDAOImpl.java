package com.schoolManagement.StudentManagemet.persistence.dao.impl;

import com.schoolManagement.StudentManagemet.persistence.dao.UserDetailDAO;
import com.schoolManagement.StudentManagemet.persistence.entity.UserDetail;
import com.schoolManagement.StudentManagemet.persistence.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailDAOImpl implements UserDetailDAO {

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailDAOImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserDetail registerUser(UserDetail user) {
        return userDetailRepository.save(user);
    }
}
