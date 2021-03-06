package com.demo.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class SimpleJobService {
	@Async
	public void doJob (String name) {
		log.info ("Do job : {}", name);
		
		stepOne (name);
		stepTwo (name);
		stepDone (name);
	}
	
	private void stepOne (String name) {
		long elapsed = 3000L + (new Random ()).nextInt (1200);
		log.info ("Step #1 : {} [{}]", name, elapsed);

		try {
			Thread.sleep (elapsed);
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}

	private void stepTwo (String name) {
		long elapsed = 3000L + (new Random ()).nextInt (1200);
		log.info ("Step #2 : {} [{}]", name, elapsed);

		try {
			Thread.sleep (elapsed);
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}

	private void stepDone (String name) {
		log.info ("Step #Done: {}", name);
	}
	
    @Async
    public void doSomeJob (String name) {
        log.info ("Do some job : {}", name);
        
        stepOne (name);
        stepDone (name);
    }
	
    @Async
    public void doThreeJob (String name) {
        log.info ("Do three job : {}", name);
        
        stepOne (name);
        stepDone (name);
    }

    @Async
    public void doFourJob (String name) {
        log.info ("Do four job : {}", name);
        
        stepOne (name);
        stepDone (name);
    }
}
