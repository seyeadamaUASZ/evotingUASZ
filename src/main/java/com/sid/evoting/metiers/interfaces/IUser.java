package com.sid.evoting.metiers.interfaces;

import com.sid.evoting.entities.User;

import javax.servlet.http.HttpServletRequest;

public interface IUser {
    public User userLogged(HttpServletRequest request);
}
