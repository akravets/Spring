package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo.config")
public class AccountingDepartmentConfig {
	@Bean
	public String name() {
		return "Accounting Department";
	}
}
