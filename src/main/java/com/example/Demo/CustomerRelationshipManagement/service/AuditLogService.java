package com.example.Demo.CustomerRelationshipManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Demo.CustomerRelationshipManagement.Entity.AuditLog;
import com.example.Demo.CustomerRelationshipManagement.Repository.AuditLogRepository;

@Service
public class AuditLogService {
	
	@Autowired
	private AuditLogRepository repo;
	
	public List<AuditLog> getAllLogs(){
		return repo.findAll(Sort.by(Sort.Direction.DESC,"timestamp"));
	}
	
	public AuditLog saveLog(AuditLog log) {
		log.setTimeStamp(LocalDateTime.now());
		return repo.save(log);
	}

}
