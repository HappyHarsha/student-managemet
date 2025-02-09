package com.schoolManagement.StudentManagemet.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UserDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;
}
