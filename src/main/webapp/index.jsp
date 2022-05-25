<%@ page import="com.yuyongsong.jsp_week2.HelloServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>
    <%="Name: yu yongsong"%>
</h2>
<h2>
    <%="ID:2020211001001306"%>
</h2>
<h2>

    <%="Date and Time Sun March 7 20:50:21 CST 2022"%>
</h2>
<form method="get" target="_blank" action="search">
    <input type="text" name="txt" size=30/>
    <select name="search">
        <option value="baidu">baidu</option>
        <option value="bing">Bing</option>
        <option value="google">Google</option>
    </select>
    <input type="submit" value="Search">
</form>
<%@include file="footer.jsp" %>
</body>

</html>