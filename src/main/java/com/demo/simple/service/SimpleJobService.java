package com.demo.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class SimpleJobService {
	@Async ("asyncTask")
	public void doJob (String name) {
		log.info ("Do job : {}", name);
		
		stepOne (name);
		stepTwo (name);
		stepDone (name);
	}
	
    public void doTest (String name) {
        log.info ("Do Test : {}", name);
        
        stepOne (name);
        stepTwo (name);
        stepDone (name);
    }

    // 아래 처럼 리턴하는 경우 "Caused by: org.springframework.aop.AopInvocationException: Null return value from advice does not match primitive return type for: public int com.demo.simple.service.SimpleJobService.getRandom(java.lang.String)" 발생함 
    @Async ("asyncTask")
    public int getRandom (String name) {
        log.info ("Do Random : {}", name);
        
        stepDone (name);
        
        return (new Random ()).nextInt (1200);
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
	
    @Async ("asyncTask")
    public void doSomeJob (String name) {
        log.info ("Do some job : {}", name);
        
        stepOne (name);
        stepDone (name);
    }
	
    @Async ("asyncTask")
    public void doThreeJob (String name) {
        log.info ("Do three job : {}", name);
        
        stepOne (name);
        stepDone (name);
    }

    @Async ("asyncTask")
    public void doFourJob (String name) {
        log.info ("Do four job : {}", name);
        
        stepOne (name);
        stepDone (name);
    }
}
