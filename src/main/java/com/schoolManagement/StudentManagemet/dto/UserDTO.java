package com.schoolManagement.StudentManagemet.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UserDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String schId;

    private String password;

    private Long contactNumber;

    private String className;

    private Integer userType;

    private String address;

    private String dateOfBirth;
}
