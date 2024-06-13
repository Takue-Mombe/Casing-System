package com.cases.cases.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter@Setter
public class Cases {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private String caseName;
    private String caseDescription;
    private LocalDateTime caseReportDate;


}
