package com.base.msgapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.base.msgapplication.Repository")
@EnableScheduling
public class MsgApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsgApplication.class, args);
	}
}
