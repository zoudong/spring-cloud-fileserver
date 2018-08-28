package com.zoudong.fileserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class XxxListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(XxxListener.class);


    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {
        //root application context 没有parent，才执行他.避免执行2次初始化
        if (contextRefreshedEvent.getApplicationContext().getParent().getId().equals("bootstrap")) {
            logger.info("启动xxx监听执行");
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
             //doxxx
            logger.info("结束xxx监听执行");
        }
    }

}
