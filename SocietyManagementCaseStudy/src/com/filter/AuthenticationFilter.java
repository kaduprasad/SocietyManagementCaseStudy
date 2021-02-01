package com.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@SuppressWarnings("serial")
public abstract class AuthenticationFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();
        System.out.println(path);
        HttpSession session = request.getSession(false);//new session not created
        System.out.println(session+" session info ");

        if(session != null && (path.contains("/login") || path.contains("/loginPage.html"))){
            System.out.println("Session present redirecting to home page");

            response.sendRedirect("http://localhost:8080/SocietyManagement/");
        }
        else if (session == null  && (path.contains("/login") || path.contains("/logout") || path.contains("loginPage.html") || path.contains("css"))) {
            System.out.println("redirecting to login page");

            chain.doFilter(request, response); // Just continue chain.
        } else {
            if(session == null) {
                System.out.println("No Active session redirecting to login page.");
                response.sendRedirect("http://localhost:8080/SocietyManagement/loginPage.html");
            }else
                chain.doFilter(request, response); // Just continue chain.
        }
    }


}
