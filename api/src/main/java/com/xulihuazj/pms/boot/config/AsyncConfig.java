package com.xulihuazj.pms.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class AsyncConfig {

    /**
     * 自定义异步调用时，所用的线程池
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 设置核心线程池大小
        taskExecutor.setCorePoolSize(100);
        // 设置最大线程池大小
        taskExecutor.setMaxPoolSize(200);
        // 设置排队队列长度
        taskExecutor.setQueueCapacity(20);
        // 设置线程保活时间（单位：秒）
        taskExecutor.setKeepAliveSeconds(30);
        // 对拒绝的任务处理策略
//        taskExecutor.setRejectedExecutionHandler();
//        taskExecutor.setThreadFactory(new ThreadFactory() {
//            private final AtomicLong index = new AtomicLong(1);
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread("异步处理，当前逻辑ID：" + index.getAndIncrement());
//            }
//        });
        return taskExecutor;
    }
}
