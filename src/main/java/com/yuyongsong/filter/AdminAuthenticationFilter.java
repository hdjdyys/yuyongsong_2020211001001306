package com.yuyongsong.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("userList") != null);

        String loginURI = httpRequest.getContextPath() + "/admin/login";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");
        if(isLoggedIn && (isLoginRequest || isLoginPage)){
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/admin/home");
            dispatcher.forward(req,resp);
        }else if (isLoggedIn || isLoginRequest){
            chain.doFilter(req,resp);
        }else{
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin/login");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
