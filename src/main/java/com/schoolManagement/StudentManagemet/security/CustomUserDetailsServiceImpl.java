package com.schoolManagement.StudentManagemet.security;

import com.schoolManagement.StudentManagemet.persistence.entity.SchUserDetails;
import com.schoolManagement.StudentManagemet.persistence.repository.SchUserDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final SchUserDetailRepository userRepository;

    @Autowired
    public CustomUserDetailsServiceImpl(SchUserDetailRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userName : {}",username);
        List<SchUserDetails> user1 = userRepository.findByEmail(username);
        SchUserDetails user = userRepository.findByUserName(username);

        log.info("userDetails :{}",user1);
        log.info("userDetails :{}",user);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return JwtUser.builder()
                .userType(user.getUserType())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(role -> (new SimpleGrantedAuthority("ROLE_USER"))).collect(Collectors.toSet()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsernameAndPassWord(String username, String password) throws UsernameNotFoundException {
        log.info("userName : {}",username);
        SchUserDetails user = userRepository.findByUserNameAndPassword(username, password).orElse(null);

        log.info("userDetails :{}",user);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return JwtUser.builder()
                .userType(user.getUserType())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(role -> (new SimpleGrantedAuthority("ROLE_USER"))).collect(Collectors.toSet()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
