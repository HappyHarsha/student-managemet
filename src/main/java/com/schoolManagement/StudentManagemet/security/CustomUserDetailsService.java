package com.schoolManagement.StudentManagemet.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsernameAndPassWord(String username, String password) throws UsernameNotFoundException;
}
