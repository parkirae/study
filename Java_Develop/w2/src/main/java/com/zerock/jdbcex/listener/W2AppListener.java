package com.zerock.jdbcex.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebListener
public class W2AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        log.info("---info---");
        log.info("---info---");
        log.info("---info---");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("---destroy---");
        log.info("---destroy---");
        log.info("---destroy---");
    }
}
