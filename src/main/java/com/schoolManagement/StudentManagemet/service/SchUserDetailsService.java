package com.schoolManagement.StudentManagemet.service;

import com.schoolManagement.StudentManagemet.dto.UserDTO;

public interface SchUserDetailsService  {
    String registerUser(UserDTO userDTO);
}
