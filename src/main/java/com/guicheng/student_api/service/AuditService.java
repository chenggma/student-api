package com.guicheng.student_api.service;

import com.guicheng.student_api.entity.AuditLog;
import com.guicheng.student_api.repository.AuditLogRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String message) {
        auditLogRepository.save(new AuditLog(message));
    }
}
