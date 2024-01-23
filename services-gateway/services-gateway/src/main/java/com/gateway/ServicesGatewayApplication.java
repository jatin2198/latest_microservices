package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.security.securityconfig.Autowired;

@SpringBootApplication
public class ServicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesGatewayApplication.class, args);
	}

}
