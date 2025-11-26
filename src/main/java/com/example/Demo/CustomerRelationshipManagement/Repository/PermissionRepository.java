package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.CustomerRelationshipManagement.Entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
