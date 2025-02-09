package com.schoolManagement.StudentManagemet.security;

import com.schoolManagement.StudentManagemet.persistence.entity.UserDetail;
import com.schoolManagement.StudentManagemet.persistence.repository.UserDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserDetailRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userName : {}",username);
        List<UserDetail> user1 = userRepository.findByEmail(username);
        UserDetail user = userRepository.findByUserName(username);

        log.info("userDetails :{}",user1);
        log.info("userDetails :{}",user);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return JwtUser.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
