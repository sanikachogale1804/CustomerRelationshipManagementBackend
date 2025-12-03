package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.CustomerRelationshipManagement.Entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

}
