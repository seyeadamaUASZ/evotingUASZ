package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.RoleRepository;
import com.sid.evoting.dao.UserRepository;
import com.sid.evoting.entities.Role;
import com.sid.evoting.entities.User;
import com.sid.evoting.metiers.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAccountImplement implements IAccountService {
    @Autowired
    private UserRepository userR;
    @Autowired
    private RoleRepository roleR;
    @Override
    public User addRoleToUser(String username, String roleName) {
        User user=userR.findUserByUsername(username);
        Role role=roleR.findRoleByRoleName(roleName);
        user.getRoles().add(role);
        userR.save(user);
        return user;
    }
}
