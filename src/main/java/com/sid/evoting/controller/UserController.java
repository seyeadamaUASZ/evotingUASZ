package com.sid.evoting.controller;

import com.sid.evoting.dao.UserRepository;
import com.sid.evoting.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}

	//method add role to user
     //get log user
    @GetMapping("/getLogUser")
    @ResponseBody
    public Map<String,Object> getLogUser(HttpServletRequest request){
        HttpSession httpSession=request.getSession();
        SecurityContext context= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=context.getAuthentication().getName();
        List<String> roles=new ArrayList<>();
        for(GrantedAuthority ga:context.getAuthentication().getAuthorities()){
            roles.add(ga.getAuthority());
        }
        Map<String,Object> params=new HashMap<>();
        params.put("username",username);
        params.put("roles",roles);
        return params;
    }

    //recuperer id user
    @GetMapping("/getIdUser")
    @ResponseBody
    public Long getUserId(HttpServletRequest request){
	    HttpSession httpSession=request.getSession();
	    SecurityContext context= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
	    String username=context.getAuthentication().getName();
	    User user=userRepository.findUserByUsername(username);
	    return user.getIdUser();
    }


}
