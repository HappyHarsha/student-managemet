package com.schoolManagement.StudentManagemet.persistence.repository;

import com.schoolManagement.StudentManagemet.persistence.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
    List<UserDetail> findByEmail(String username);

    Optional<UserDetail> findByUserNameAndPassword(String username, String password);

    UserDetail findByUserName(String username);
}
