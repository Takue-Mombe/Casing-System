package com.cases.cases.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class SupportCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "supportCase", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "supportCase", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
}
