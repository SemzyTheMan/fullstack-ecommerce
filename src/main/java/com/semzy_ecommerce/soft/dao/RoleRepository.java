package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByName(Role.ERole name);
}
