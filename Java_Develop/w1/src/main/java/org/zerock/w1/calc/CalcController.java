package org.zerock.w1.calc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "calcController", urlPatterns = "/calc/makeResult")
public class CalcController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num1");

        System.out.printf("num1: %s", num1);
        System.out.printf("num2: %s", num2);
    }
}
