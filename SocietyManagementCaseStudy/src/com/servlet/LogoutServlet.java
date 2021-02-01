package com.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        HttpSession currentSession = request.getSession(false); // used to get current session
        //logger.info("Logged out from Session"+currentSession.getId()+" Time : "+currentSession.getLastAccessedTime());
        currentSession.invalidate(); // logout
        writer.print("Logged out from Session");
        response.sendRedirect("http://localhost:8080/SocietyManagement/"); // redirect to login page
    }
}
