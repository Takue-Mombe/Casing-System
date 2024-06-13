package com.cases.cases.Models;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "support_case_id")
    private SupportCase supportCase;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String content;
    private LocalDateTime createdAt;
}
