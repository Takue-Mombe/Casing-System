package com.cases.cases.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "support_case_id")
    private SupportCase supportCase;

    private String fileName;
    private byte[] fileData;

    // Getters and Setters
}

