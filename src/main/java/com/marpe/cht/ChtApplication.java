package com.marpe.cht;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import com.marpe.cht.jwt.JwtProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(JwtProperties.class)
public class ChtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("pt", "BR"));
		SpringApplication.run(ChtApplication.class, args);
	}


}
