package com.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfilePageServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
//        logger.info("inside the doPost of profilePageServlet ");
//        String user = request.getParameter("Username");

        HttpSession session = request.getSession(false); //this will check for current session,and not create a new session
        String name = session.getAttribute("name").toString();

        writer.print("<h2>Hello "+name+" You are successfully logged in.</h2>");
        writer.print("<h4> Your session ID is "+session.getId()+"</h4>");
        writer.print("<a name=\"logout\" href=\"logout\">Logout</a>");
        writer.print("<br>");
        writer.print("<a name=\"society\" href=\"society\">Society</a>");
        writer.print("<br>");
        writer.print("<a name=\"society\" href=\"Society.html\">SocietyHTML</a>");
        writer.print("<br>");
        writer.print("<a name=\"society\" href=\"Member.html\">Member</a>");
        writer.print("<br>");
        writer.print("<a name=\"society\" href=\"Home.html\">Home</a>");
    }
}
