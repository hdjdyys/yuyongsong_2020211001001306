<%@ page import="com.yuyongsong.model.User" %><%--
  Created by IntelliJ IDEA.
  User: wyyuy
  Date: 2022/4/4
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<h1> User Info </h1>
<%
    User user = (User) session.getAttribute("user");
%>
<table>
    <tr>
        <td>Username:</td>
        <td><%=user.getUsername()%>
        </td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><%=user.getPassword()%>
        </td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><%=user.getEmail()%>
        </td>
    </tr>
    <tr>
        <td>Gender:</td>
        <td><%=user.getGender()%>
        </td>
    </tr>
    <tr>
        <td>Birth Date:</td>
        <td><%=user.getBirthdate()%>
        </td>
    </tr>

</table>
<a href="updateUser">Update</a> <br>

<%@include file="footer.jsp" %>
