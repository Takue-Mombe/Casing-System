package com.cases.cases.Services;

import com.cases.cases.Repositories.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public List<Attachment> findBySupportCaseId(Long supportCaseId) {
        return attachmentRepository.findBySupportCaseId(supportCaseId);
    }

    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
}

