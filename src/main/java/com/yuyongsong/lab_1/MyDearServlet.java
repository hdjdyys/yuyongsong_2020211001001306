package com.yuyongsong.lab_1;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyDearServlet",value = "/MyDear")
public class MyDearServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletConfig().getServletContext();
        String name = request.getParameter("name");
        String Class = request.getParameter("class");
        String id = request.getParameter("id");
        String submit = request.getParameter("submit");
        request.setAttribute("name",name);
        request.setAttribute("class",Class);
        request.setAttribute("id",id);
        request.setAttribute("submit",submit);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>MyDearJsp</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("name:"+name+"<br>");
        out.println("submit:"+submit+"<br>");
        out.println("class:"+Class+"<br>");
        out.println("id:"+id+"<br>");
        out.println("</body>");
        out.println("</html>");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("MyDearJSp.jsp").forward(request,response);
    }
}
