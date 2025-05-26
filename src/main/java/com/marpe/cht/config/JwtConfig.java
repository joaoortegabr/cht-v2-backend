package com.marpe.cht.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marpe.cht.jwt.JwtProperties;

@Configuration
@EnableConfigurationProperties
public class JwtConfig {

	@Bean(name = "jwtProperties")
    JwtProperties jwtProperties() {
        return new JwtProperties();
    }
	
}

