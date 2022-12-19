package com.zerock.jdbcex.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebListener
public class LoginListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        String name = event.getName();

        Object obj = event.getValue();

        if (name.equals("loginInfo")) {
            log.info("A user logined...");
            log.info(obj);
        }
    }
}
