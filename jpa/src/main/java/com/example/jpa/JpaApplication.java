package com.example.jpa;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan 
@EnableAutoConfiguration 
@SpringBootApplication
public class JpaApplication extends SpringBootServletInitializer {
	

	public static void main(String[] args) throws SQLException {
		Server.createTcpServer(args).start();
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JpaApplication.class);
	}

}
