package com.cases.cases.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="support_case")@Getter@Setter

public class SupportCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    private Users user;

    @OneToMany(mappedBy = "supportCase", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
