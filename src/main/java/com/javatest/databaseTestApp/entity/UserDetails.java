package com.javatest.databaseTestApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_details_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {

    @Id
    @Column(name = "user_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(mappedBy = "userDetails", fetch = FetchType.EAGER)
    private Photo photo;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
