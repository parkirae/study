package com.zerock.jdbcex.controller;

import com.zerock.jdbcex.dto.TodoDTO;
import com.zerock.jdbcex.service.TodoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTNACE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));

            TodoDTO todoDTO = todoService.get(tno);

            req.setAttribute("dto", todoDTO);

            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
                exist = true;
            }

            log.info("exist: " + exist);

            if (!exist) {
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60 * 60 * 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        Cookie targetCookie = null;

        if (cookies != null && cookies.length > 0 ) {
            for (Cookie ck:cookies) {
                if (ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }

        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60 * 60 * 24);
        }

        return targetCookie;
    }
}
