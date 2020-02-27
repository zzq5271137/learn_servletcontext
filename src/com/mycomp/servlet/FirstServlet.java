package com.mycomp.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        System.out
                .println("ServletContext from first init: " + servletContext);
    }

    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("first service...");

        /*
         * 这种方式获取ServletContext,本质上还是通过ServletConfig来获取.
         * 但是必须在重写的init方法(如果有重写的话)里调用父类的init方法. 
         * 否则,父类中的config会没有值,导致ServletContext获取失败.
         */
        ServletContext servletContext = this.getServletContext();
        System.out.println(
                "ServletContext from first service: " + servletContext);

        /*
         * 获取全局初始化参数
         */
        String username = servletContext.getInitParameter("username");
        String password = servletContext.getInitParameter("password");
        System.out.println(username + " " + password);

        Enumeration<String> initParameterNames = servletContext
                .getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String paramName = initParameterNames.nextElement();
            String value = servletContext.getInitParameter(paramName);
            System.out.println(paramName + " = " + value);
        }

        /*
         * 获取文件绝对路径 web应用的根路径: C:\dev\Tomcat\apache-tomcat-9.0.30\webapps\Learn_ServletContext
         * getReatPath会把传入的字符串(文件名)与根路径进行拼接,得出一个新的地址 
         * 当你需要加载一个文件的时候,就要使用文件的绝对路径
         */
        String realPath = servletContext.getRealPath("a.txt");
        System.out.println(realPath);

        /*
         * 如果此文件在WEB-INF中,需要在传入的字符串前面加上WEB-INF 
         * getRealPath的拼接只到web应用的根路径
         */
        realPath = servletContext.getRealPath("WEB-INF/b.txt");
        System.out.println(realPath);

        /*
         * 如果文件在src或resource里面(在运行时会编译成字节码放入WEB-INF/classes中)
         * 你可以用getRealPath拼接(很麻烦,需要加上WEB-INF/classes/.../abc.file)
         * 
         * 或者可以用类加载器来获取字节码的路径 
         * (参考Learn_HttpServlet中的JDBCUtil)
         * 
         * 这种方式是真实的在加载文件,和getRealPath的拼接不同 
         * 所以如果传入的文件名不存在会报错
         */
        realPath = FirstServlet.class.getClassLoader().getResource("c.txt")
                .getPath();
        System.out.println(realPath);

        /*
         * 用类加载器来获取字节码路径只到字节码的根目录
         * (即C:/dev/Tomcat/apache-tomcat-9.0.30/webapps/Learn_ServletContext/WEB-INF/classes) 
         * 如果想要加载包中的文件,需要在文件名前面拼上相应的包名
         */
        realPath = FirstServlet.class.getClassLoader()
                .getResource("com/mycomp/servlet/d.txt").getPath();
        System.out.println(realPath);
        
    }

}
