package com.marpe.cht;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("pt", "BR"));
		SpringApplication.run(ChtApplication.class, args);
	}


}
