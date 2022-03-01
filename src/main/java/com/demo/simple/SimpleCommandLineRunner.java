package com.demo.simple;

import com.demo.simple.service.SimpleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	SimpleJobService simpleJobService;
	
	@Override
	public void run (String... args) throws Exception {
		for (int i = 1; i <= 20; i++) {
			simpleJobService.doJob (String.format ("#%02d", i));
		}
	}

}
