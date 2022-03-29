package com.yuyongsong.jsp_week4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
@WebServlet(name = "JDBCDemoServlet",urlPatterns = {"/jdbc"},
    initParams = {
    @WebInitParam(name="driver",value = "com.mysql.cj.jdbc.Driver"),
            @WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/userdb?serverTimezone=UTC"),
            @WebInitParam(name="username",value = "root"),
            @WebInitParam(name="password",value = "495808"),
    },
        loadOnStartup = 1
)*/
@WebServlet(urlPatterns = {"/jdbc"},loadOnStartup = 1)
public class JDBCDemoServlet extends HttpServlet {
    Connection conn = null;
    @Override
    public void init() throws ServletException {
//        String driver = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/userdb?serverTimezone=UTC";
//        String username = "root";
//        String password = "495808";
//        ServletConfig config = getServletConfig();
//        String driver = config.getInitParameter("driver");
//        String url = config.getInitParameter("url");
//        String username = config.getInitParameter("username");
//        String password = config.getInitParameter("password");
        ServletContext context =getServletContext();
        String driver = context.getInitParameter("driver");
        System.out.println(driver);
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection -->"+conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
