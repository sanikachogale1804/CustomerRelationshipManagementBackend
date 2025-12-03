package com.example.Demo.CustomerRelationshipManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.CustomerRelationshipManagement.Entity.AuditLog;
import com.example.Demo.CustomerRelationshipManagement.service.AuditLogService;

@RestController
public class AuditLogController {

	@Autowired
	private AuditLogService service;
	
	@GetMapping
	public List<AuditLog> getLogs(){
		return service.getAllLogs();
	}
	
	@PostMapping
	public AuditLog addLog(@RequestBody AuditLog log) {
		return service.saveLog(log);
	}
	
}
