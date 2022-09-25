package com.RolesAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RolesAuth.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
