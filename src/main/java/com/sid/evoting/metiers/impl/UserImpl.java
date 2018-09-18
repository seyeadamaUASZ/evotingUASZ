package com.sid.evoting.metiers.impl;

import com.sid.evoting.dao.UserRepository;
import com.sid.evoting.entities.User;
import com.sid.evoting.metiers.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserImpl implements IUser {
    @Autowired
    private UserRepository repository;
    @Override
    public User userLogged(HttpServletRequest request) {
       HttpSession session=request.getSession();
        SecurityContext context= (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=context.getAuthentication().getName();
        User user=repository.findUserByUsername(username);
        return user;
    }
}
