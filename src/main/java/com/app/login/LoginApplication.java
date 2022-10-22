package com.app.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class LoginApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(LoginApplication.class, args);
	}

}
