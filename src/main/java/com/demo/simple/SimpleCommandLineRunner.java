package com.demo.simple;

import com.demo.simple.service.SimpleJobService;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleCommandLineRunner implements CommandLineRunner {
	
    @Autowired
    TaskConfigurer executorConfig;
    
	@Autowired
	SimpleJobService simpleJobService;
	
	@Override
	public void run (String... args) throws Exception {

	    executorConfig.setUpdate ();
	    for (int i = 1; i <= 20; i++) {
		    log.info ("For loop #{}", i);
            simpleJobService.doJob (String.format ("#%02d", i));
            simpleJobService.doSomeJob (String.format ("#%02d", i));
            simpleJobService.doThreeJob (String.format ("#%02d", i));
            simpleJobService.doFourJob (String.format ("#%02d", i));
            executorConfig.showActiveStats ();
		}
		
        executorConfig.setConfig (100, 400, 501);
		simpleJobService.doTest (String.format ("#%02d", 0));
		for (int i = 1; i <= 20; i++) {
            log.info ("For loop #{}", i);
            simpleJobService.doJob (String.format ("#%02d", i));
            simpleJobService.doSomeJob (String.format ("#%02d", i));
            simpleJobService.doThreeJob (String.format ("#%02d", i));
            simpleJobService.doFourJob (String.format ("#%02d", i));
            executorConfig.showActiveStats ();
		}
	}

}
