package com.mycomp.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contexttestsecond")
public class ContextTestSecond extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        System.out.println(servletContext);
        String name = (String) servletContext.getAttribute("name");
        System.out.println(name);
    }

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    }

}
