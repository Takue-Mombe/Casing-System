package com.cases.cases.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity@Setter@Getter
@AllArgsConstructor
public class Users {

    @Id@GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column@Email
    private String email;
    private String fname;
    private String lname;
    private Long phone;
    private String address;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SupportCase> supportCases;
    public Users() {

    }
}
