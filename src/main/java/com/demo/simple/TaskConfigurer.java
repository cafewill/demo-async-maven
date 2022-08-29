package com.demo.simple;

import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@EnableAsync
@Configuration
public class TaskConfigurer  implements AsyncConfigurer {
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer () {
        return new PropertySourcesPlaceholderConfigurer ();
    }
    
    @Value ("${task.corePoolSize}")
    Integer corePoolSize = 1;
    
    @Value ("${task.maxPoolSize}")
    Integer maxPoolSize = Integer.MAX_VALUE;

    @Value ("${task.queueCapacity}")
    Integer queueCapacity = Integer.MAX_VALUE;

    @Resource (name = "asyncTask")
    ThreadPoolTaskExecutor asyncTask;
    
    @Override
    public Executor getAsyncExecutor () {
        return taskExecutor ();
    }
    
    @Bean (name = "asyncTask")
    public Executor taskExecutor () {
        
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor ();
        executor.setMaxPoolSize (maxPoolSize);
        executor.setCorePoolSize (corePoolSize);
        executor.setQueueCapacity (queueCapacity);
        executor.setThreadNamePrefix ("AsyncTask-");
        executor.initialize ();

        return executor;
    }
    
    public void setUpdate () {
        asyncTask.setMaxPoolSize (maxPoolSize);
        asyncTask.setCorePoolSize (corePoolSize);
        asyncTask.setQueueCapacity (queueCapacity);
    }
    
    public void setConfig (int corePoolSize, int maxPoolSize, int queueCapacity) {
        setMaxPoolSize (maxPoolSize);
        setCorePoolSize (corePoolSize);
        setQueueCapacity (queueCapacity);
        setUpdate ();
    }
    
    public void showActiveStats () {
        log.info ("Check AsyncTask Config - CorePoolSize : {}, MaxPoolSize : {}, QueueCapacity : {}", getCorePoolSize (), getMaxPoolSize (), getQueueCapacity ());
        log.info ("Check AsyncTask Real Stats - ActiveCount : {}, PoolSize : {}, CorePoolSize : {}, MaxPoolSize : {}, QueueSize : {}", asyncTask.getActiveCount (), asyncTask.getPoolSize (), asyncTask.getCorePoolSize (), asyncTask.getMaxPoolSize (), asyncTask.getThreadPoolExecutor ().getQueue ().size ());
    }
}
