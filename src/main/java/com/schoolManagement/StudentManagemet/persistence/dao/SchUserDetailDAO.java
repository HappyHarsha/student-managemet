package com.schoolManagement.StudentManagemet.persistence.dao;

import com.schoolManagement.StudentManagemet.persistence.entity.SchUserDetails;

public interface SchUserDetailDAO {
    SchUserDetails registerUser(SchUserDetails user);
}
