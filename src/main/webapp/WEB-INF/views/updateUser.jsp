<%@ page import="com.yuyongsong.model.User" %><%--
  Created by IntelliJ IDEA.
  User: wyyuy
  Date: 2022/4/19
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1> User Update </h1>
<%
    User user = (User) session.getAttribute("user");
%>
<form method="post" action="updateUser">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    username<input type="text"  name="username" value="<%=user.getUsername()%>"/><br/>
    password<input type="password" name="password" value="<%=user.getPassword()%>"/><br/>
    Email<input type="text" name="email" value="<%=user.getEmail()%>"><br/>
    Gender <input type="radio" name="gender" value="male" <%= "male".equals(user.getGender())?"checked" : ""%>>Male
    <input type="radio" name="gender" value="female" <%= "female".equals(user.getGender())?"checked" : ""%>>Female<br>
    Date of Birth:<input type="text name=" name="birthDate" value="Save Changes" ><br>

    <br><br><br>
    <input type="submit" value="Register" >
</form>


<%@include file="footer.jsp"%>
