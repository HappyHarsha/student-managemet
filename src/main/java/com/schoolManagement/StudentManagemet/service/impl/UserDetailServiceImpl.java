package com.schoolManagement.StudentManagemet.service.impl;

import com.schoolManagement.StudentManagemet.dto.UserDTO;
import com.schoolManagement.StudentManagemet.persistence.dao.UserDetailDAO;
import com.schoolManagement.StudentManagemet.persistence.entity.UserDetail;
import com.schoolManagement.StudentManagemet.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private  final UserDetailDAO userDetailDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailServiceImpl(UserDetailDAO userDetailDAO, PasswordEncoder passwordEncoder) {
        this.userDetailDAO = userDetailDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String registerUser(UserDTO userDTO) {

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        UserDetail user = UserDetail.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .userName(userDTO.getUserName())
                .password(encodedPassword)
                .email(userDTO.getEmail())
                .build();
        UserDetail userDetail  = userDetailDAO.registerUser(user);
        if(userDetail != null){
            return "User Created Successfully";
        }
        return "User Not  Created Successfully";
    }
}
