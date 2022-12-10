package com.zerock.jdbcex.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("login get...");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String str = mid + mpw;

        HttpSession session = req.getSession();

        session.setAttribute("loginInfo", str);

        resp.sendRedirect("/todo/list");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
}
