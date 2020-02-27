package com.mycomp.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ServletContext是一个域对象,可以用来存取数据.
 * 它的作用域是整个web应用.
 * 整个web工程中只有一个ServletContext对象,任何地方获取的ServletContext对象都是同一个.
 */

@WebServlet("/contexttestfirst")
public class ContextTestFirst extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        System.out.println(servletContext);
        servletContext.setAttribute("name", "zzqgo");
    }

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    }

}
