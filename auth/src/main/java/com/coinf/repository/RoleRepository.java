package com.coinf.repository;

import com.coinf.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(Role.RoleType roleType);

}
