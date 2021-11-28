package com.myexample.audit.repository;

import com.myexample.audit.entity.StatusAudit;

import java.util.List;

public interface StatusAuditRepository extends ReadOnlyRepository<StatusAudit, Integer> {
    List<StatusAudit> findAllById(Long id);
}
