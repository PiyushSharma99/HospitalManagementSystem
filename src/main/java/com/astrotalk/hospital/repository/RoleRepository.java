package com.astrotalk.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astrotalk.hospital.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
