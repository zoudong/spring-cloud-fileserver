package com.zoudong.fileserver.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@EnableScheduling
public class XxxTask {

    /**
     * 图片服务器日常维护任务
     * @Scheduled(cron = "0 0/5 * * * ? ")
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 60000)
    @Scheduled(initialDelay=5*60000, fixedDelay = 2 * 60000)
    public synchronized void accept() throws Exception {
        log.info("开始Xxx定时任务");
        //do Xxx
        log.info("结束Xxx定时任务");
    }


}