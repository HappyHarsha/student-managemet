package com.schoolManagement.StudentManagemet.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sch_user_details")
public class SchUserDetails implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "sch_id")
    private String schId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name = "contact_number")
    private Long contactNumber;

    @Column(name = "class_name")
    private String className;

    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "roles")
    private List<String> roles;

    @Column(name = "date_of_birth")
    private String dateOfBirth;
}
