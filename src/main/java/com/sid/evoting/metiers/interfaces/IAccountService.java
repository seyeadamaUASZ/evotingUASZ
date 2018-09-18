package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.User;

public interface IAccountService {
    public User addRoleToUser(String username, String roleName);
}
