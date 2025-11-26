package com.example.Demo.CustomerRelationshipManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Demo.CustomerRelationshipManagement.Entity.Permission;
import com.example.Demo.CustomerRelationshipManagement.Repository.PermissionRepository;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @PostMapping("/bulk")
    public ResponseEntity<List<Permission>> addPermissions(@RequestBody List<Permission> permissions) {
        List<Permission> savedPermissions = permissionRepository.saveAll(permissions);
        return ResponseEntity.ok(savedPermissions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
