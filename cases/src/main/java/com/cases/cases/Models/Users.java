package com.cases.cases.Models;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String fname;
    private String lname;;
    private Long phone;
    private String address;
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SupportCase> supportCases;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public boolean checkPassword(String rawPassword) {
        // Implement password check logic
        return this.password.equals(rawPassword);
    }

    public Users() {
    }
}