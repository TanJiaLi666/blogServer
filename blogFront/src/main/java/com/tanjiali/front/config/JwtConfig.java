package com.tanjiali.front.config;

import com.tanjiali.blogpublicapi.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtConfig {
    @Bean
    public JwtTokenUtil config(){
        return new JwtTokenUtil();
    }
}
