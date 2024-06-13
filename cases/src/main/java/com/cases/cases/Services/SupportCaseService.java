package com.cases.cases.Services;

import com.cases.cases.Models.SupportCase;
import com.cases.cases.Repositories.SupportCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupportCaseService {

    @Autowired
    private SupportCaseRepository supportCaseRepository;

    public Optional<SupportCase> findById(Long id) {
        return supportCaseRepository.findById(id);
    }

    public List<SupportCase> findByUserId(Long userId) {
        return supportCaseRepository.findByUserId(userId);
    }

    public SupportCase save(SupportCase supportCase) {
        return supportCaseRepository.save(supportCase);
    }
}
