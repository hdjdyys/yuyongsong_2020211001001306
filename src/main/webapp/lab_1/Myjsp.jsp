<%--
  Created by IntelliJ IDEA.
  User: wyyuy
  Date: 2022/5/18
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>i am MyJsp.jsp</h1>
<from Action="<%=request.getContextPath()%>/lab1/MyDearJSp.jsp" Method="post">
    name<input type="text" name="name"/><br/>
    class<input type="text" name="class"/><br/>
    ID<input type="text" name="id"/><br/>
    <input type="submit" value="Send date to server"/>
</from>
</body>
</html>
