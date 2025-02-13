package com.example.jpa_scheduler.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/api/members/signup","/api/members/signin"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("LOGIN FILTER");

        if(!isWhiteList(requestURI)){
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("loggedIn") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
