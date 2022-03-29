package com.yuyongsong.jsp_week3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void init() throws ServletException {
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
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<meta charset='UTF-8'>");
        out.print("<title>UserList</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1 align='center'>UserList</h1>");
        out.print("<hr>");
        out.print("<table border='2px' align='center' width='50%'>");
        out.print("<tr>");
        out.print("<th>ID</th>");
        out.print("<th>UserName</th>");
        out.print("<th>Password</th>");
        out.print("<th>Email</th>");
        out.print("<th>Gender</th>");
        out.print("<th>Birthdate</th>");
        out.print("</tr>");

        try {
            String sql = "select count(*) sum from register;";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            String id=null;
            while (resultSet.next()) {
                 id = resultSet.getString("sum");
            }
            String sql1 = "insert into register(id, username, password, email, gender, birthdate) values (?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setString(1, String.valueOf(Integer.valueOf(id).intValue()+1));
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, birthday);
            int num = preparedStatement.executeUpdate();

            String sql2 = "select * from register";
            preparedStatement = conn.prepareStatement(sql2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String id2 = resultSet.getString("id");
                String username2 = resultSet.getString("username");
                String password2 = resultSet.getString("password");
                String email2 = resultSet.getString("email");
                String gender2 = resultSet.getString("gender");
                String birthday2 = resultSet.getString("birthdate");

                out.print("<tr>");
                out.print("<td>"+ id2 +"</td>");
                out.print("<td>"+ username2 +"</td>");
                out.print("<td>"+ password2 +"</td>");
                out.print("<td>"+ email2 +"</td>");
                out.print("<td>"+ gender2 +"</td>");
                out.print("<td>"+ birthday2 +"</td>");
                out.print("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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


        out.print("        </table>");
        out.print("        <hr>");
        out.print("    </body>");
        out.print("</html>");

        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
