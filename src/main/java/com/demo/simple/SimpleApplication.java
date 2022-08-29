package com.demo.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}

	/*
	@Bean
	public Executor taskExecutor () {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor ();
		executor.setCorePoolSize (3);
		executor.setMaxPoolSize (5);
		executor.setQueueCapacity (500);
		executor.initialize ();
		return executor;
	}
	*/
}
