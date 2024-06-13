package com.cases.cases.Repositories;

import com.cases.cases.Models.SupportCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportCaseRepository extends JpaRepository<SupportCase,Long> {

    List<SupportCase> findByUserId(Long userId);
}
