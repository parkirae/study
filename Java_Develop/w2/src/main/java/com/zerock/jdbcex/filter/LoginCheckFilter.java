package com.zerock.jdbcex.filter;

import com.zerock.jdbcex.dto.MemberDTO;
import com.zerock.jdbcex.service.MemberService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
@WebFilter(urlPatterns = {"/todo/*"})
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Login check filter...");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (session.getAttribute("loginInfo") == null) {
            chain.doFilter(request, response);

            return;
        }

        // 세션에 loginInfo가 없다면 쿠키를 체크합니다.
        Cookie cookie = findCookie(req.getCookies(), "remember-me");

        // 세션에도 쿠키에도 없다면 로그인 화면으로 이동합니다.
        if (cookie == null) {
            resp.sendRedirect("/login");
            return;
        }

        // 쿠키가 있는 상태라면
        log.info("cookie는 존재하는 상황");
        // uuid값
        String uuid = cookie.getValue();

        try {
            // DB를 확인합니다.
            MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);

            log.info("쿠키의 값으로 사용자 정보를 조회합니다: " + memberDTO);
            if (memberDTO == null) {
                throw new Exception("Cookie value is not valid");
            }
            // 회원 정보를 세션에 추가합니다.
            session.setAttribute("loginInfo", memberDTO);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/login");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        
        if (cookies == null || cookies.length == 0) {
            return null;
        }

        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(ck -> ck.getName().equals(name))
                .findFirst();

        return result.isPresent()?result.get():null;
    }
}

