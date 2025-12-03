package com.example.Demo.CustomerRelationshipManagement.Controller;

import java.util.List;
import java.util.Optional;

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
    
    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
    
    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionRepository.save(permission);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(
            @PathVariable Long id,
            @RequestBody Permission permissionDetails) {

        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        if (!optionalPermission.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Permission permission = optionalPermission.get();
        permission.setPermissionName(permissionDetails.getPermissionName());
        permission.setDescription(permissionDetails.getDescription());

        Permission updated = permissionRepository.save(permission);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        if (!permissionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        permissionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
