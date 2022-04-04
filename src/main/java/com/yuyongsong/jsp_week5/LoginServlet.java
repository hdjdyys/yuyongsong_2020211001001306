package com.yuyongsong.jsp_week5;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public void init() throws ServletException {
        super.init();
        /*
        ServletContext context = getServletConfig().getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

         */
        conn = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String username1 = null;
        String password1 = null;
        String id1 = null;
        String email1 = null;
        String gender1 = null;
        String birthdate1 = null;

        String sql = "select * from register where username = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id1 = resultSet.getString("id");
                username1 = resultSet.getString("username");
                password1 = resultSet.getString("password");
                email1 = resultSet.getString("email");
                gender1 = resultSet.getString("gender");
                birthdate1 = resultSet.getString("birthdate");
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (username.equals(username1)&&password.equals(password1)){
//                out.print("Login Successful!!");
//                out.print("<br/>");
//                out.print("Welcome,"+username);

                request.setAttribute("id",id1);
                request.setAttribute("username",username1);
                request.setAttribute("password",password1);
                request.setAttribute("email",email1);
                request.setAttribute("gender",gender1);
                request.setAttribute("birthdate",birthdate1);
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else{
                //out.print("Username or password Error");
                request.setAttribute("message","Username or Password Error");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement !=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
