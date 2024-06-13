package com.cases.cases.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    public Users() {

    }
}
