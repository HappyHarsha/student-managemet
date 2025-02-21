package com.schoolManagement.StudentManagemet.persistence.repository;

import com.schoolManagement.StudentManagemet.persistence.entity.SchUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchUserDetailRepository extends JpaRepository<SchUserDetails, String> {
    List<SchUserDetails> findByEmail(String username);

    Optional<SchUserDetails> findByUserNameAndPassword(String username, String password);

    SchUserDetails findByUserName(String username);

}
