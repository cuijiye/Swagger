package com.example.swagger.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    //cron表达式 秒 分 时 日 月 周几
    @Scheduled(cron = "30 27 14 * * 0-7")
    public void testTask(){
        System.out.println("测试定时任务执行！");
    }
}
