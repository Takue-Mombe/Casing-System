package com.cases.cases.Repositories;

import com.cases.cases.Models.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findBySupportCaseId(Long supportCaseId);
}