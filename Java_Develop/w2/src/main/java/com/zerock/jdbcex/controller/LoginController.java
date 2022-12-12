package com.zerock.jdbcex.controller;

import com.zerock.jdbcex.dto.MemberDTO;
import com.zerock.jdbcex.service.MemberService;
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

        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("login post...");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");
        }
    }
}
