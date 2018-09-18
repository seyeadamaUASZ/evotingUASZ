package com.sid.evoting.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class SimpleAuthenticationSuccess implements AuthenticationSuccessHandler {
    private RedirectStrategy strategy=new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication)
            throws IOException, ServletException {
        Collection<GrantedAuthority> authorities= (Collection<GrantedAuthority>) authentication.getAuthorities();

        authorities.forEach(grantedAuthority ->{
            if(grantedAuthority.getAuthority().equals("ROLE_ELECTEUR")){
                try{
                    strategy.sendRedirect(httpServletRequest,httpServletResponse,"/menu_electeur");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if(grantedAuthority.getAuthority().equals("ROLE_CANDIDAT")){
                try{
                   strategy.sendRedirect(httpServletRequest,httpServletResponse,"menu_candidat");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_SUPERVISEUR")){
                try{
                    strategy.sendRedirect(httpServletRequest,httpServletResponse,"/menu_superviseur");

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                throw new IllegalStateException();
            }
        });
    }
}
