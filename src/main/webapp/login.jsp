<%--
  Created by IntelliJ IDEA.
  User: wyyuy
  Date: 2022/3/24
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Login</h1>
<%
    if (!(request.getAttribute("message") == null)) {
        out.println(request.getAttribute("message"));
    }
%>
<%
    Cookie[] allCookies = request.getCookies();
    String username = "";
    String password = "";
    String rememberMeVale = "";
    if (allCookies != null) {
        for (Cookie c : allCookies) {
            if (c.getName().equals("cUsername")) {
                username = c.getValue();
            }
            if (c.getName().equals("cPassword")) {
                username = c.getValue();
            }
            if (c.getName().equals("cRememberMe")) {
                username = c.getValue();
            }
        }
    }
%>
<form method="post" action="${pageContext.request.contextPath}/login">
    username<input type="text" name="username"/><br/>
    password<input type="password" name="password"/><br/>
    <!--<input type="checkbox" name="rememberMe" value="1" <%=rememberMeVale.equals("1") ?"checked":""%>checked/> RememberMe <br/>-->
    <input type="submit" value="login"/>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
