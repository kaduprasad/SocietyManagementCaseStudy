package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public class LoginServlet extends HttpServlet  {



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        PrintWriter writer = response.getWriter();
        response.setContentType("text/json");
        String userName = request.getParameter("Username");
        String password = request.getParameter("Password");

        if(userName.equals("Prasad") && password.equals("123")){
            HttpSession session = request.getSession();//creates new session.
            session.setAttribute("name",userName);
            request.getRequestDispatcher("profile").forward(request,response);

        }
        else
            writer.println("Invalid Login Credentials");
            request.getRequestDispatcher("login");
    }
}
