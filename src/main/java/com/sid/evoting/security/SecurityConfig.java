package com.sid.evoting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SimpleAuthenticationSuccess success;
   @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    public void globalConfig(AuthenticationManagerBuilder builder, DataSource dataSource)throws Exception{
        //builder.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("SUPERVISEUR");
        //builder.inMemoryAuthentication().withUser("prof1").password("{noop}1234").roles("ELECTEUR");

        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, true from user where active=true and username= ?")
                .authoritiesByUsernameQuery("select user.username as principal, role.role_name as role from user_roles,user,role where user_id_user = user.id_user and roles_id_role = role.id_role  and user.username= ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(encoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests()
               .antMatchers("/css/**","/js/**","/images/**","/fonts/**","/webfonts/**").permitAll()
               .antMatchers("/menu_electeur").hasRole("ELECTEUR")
               .antMatchers("/menu_candidat").hasRole("CANDIDAT")
               .antMatchers("/menu_superviseur").hasRole("SUPERVISEUR")
               .and()
               .formLogin().successHandler(success)
               .loginPage("/login")
               .permitAll();
    }
}
