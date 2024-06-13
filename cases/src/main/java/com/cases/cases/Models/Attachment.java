package com.cases.cases.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity(name="attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "support_case_id")
    private SupportCase supportCase;
    private String fileName;
    private byte[] fileData;

}

