package com.schoolManagement.StudentManagemet.service.impl;

import com.schoolManagement.StudentManagemet.dto.UserDTO;
import com.schoolManagement.StudentManagemet.persistence.dao.SchUserDetailDAO;
import com.schoolManagement.StudentManagemet.persistence.entity.SchUserDetails;
import com.schoolManagement.StudentManagemet.service.SchUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SchUserDetailsServiceImpl implements SchUserDetailsService {

    private  final SchUserDetailDAO userDetailDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SchUserDetailsServiceImpl(SchUserDetailDAO userDetailDAO, PasswordEncoder passwordEncoder) {
        this.userDetailDAO = userDetailDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String registerUser(UserDTO userDTO) {

        String encodedPassword = passwordEncoder.encode(userDTO.getDateOfBirth());

        SchUserDetails user = SchUserDetails.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .dateOfBirth(userDTO.getDateOfBirth())
                .password(encodedPassword)
                .email(userDTO.getEmail())
                .schId(userDTO.getSchId())
                .userType(userDTO.getUserType())
                .address(userDTO.getAddress())
                .contactNumber(userDTO.getContactNumber())
                .className(userDTO.getClassName())
                .build();
        SchUserDetails userDetail  = userDetailDAO.registerUser(user);
        if(userDetail != null){
            return "User Created Successfully";
        }
        return "User Not  Created Successfully";
    }
}
