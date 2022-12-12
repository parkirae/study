package com.zerock.jdbcex.controller;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("log out...");

        HttpSession session = req.getSession();

        session.removeAttribute("loginInfo");
        session.invalidate();

        resp.sendRedirect("/");
    }
}
