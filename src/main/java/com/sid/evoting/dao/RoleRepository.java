package com.sid.evoting.dao;

import com.sid.evoting.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleByRoleName(String rolename);

}
