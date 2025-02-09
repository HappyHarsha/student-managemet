package com.schoolManagement.StudentManagemet.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {
    private String access_token;
    private String refresh_token;
}
