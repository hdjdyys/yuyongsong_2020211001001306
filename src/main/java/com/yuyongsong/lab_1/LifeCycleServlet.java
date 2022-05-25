package com.yuyongsong.lab_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {

    private int a = 0;

    @Override
    public void init() throws ServletException {
        System.out.println("I Am from default constructor");
        System.out.println("init");

        System.out.println(a);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("service");
        a++;
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Since loading,this servlet has been accessed " + a + "times" + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
