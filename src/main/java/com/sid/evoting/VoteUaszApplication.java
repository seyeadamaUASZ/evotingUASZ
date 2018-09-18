package com.sid.evoting;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VoteUaszApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteUaszApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder getPBCE(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }
}
