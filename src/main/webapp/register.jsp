<%--
  Created by IntelliJ IDEA.
  User: wyyuy
  Date: 2022/3/7
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script language="JavaScript">
    function on_submit() {
        let username = document.getElementById("1").value;
        if (username == null || username === '') {
            alert("username can not be null!")
            return false;
        }
        let password = document.getElementById("2").value;
        if (password == null || password === '') {
            alert("password can not be null!")
            return false;
        }
        if (password.length < 8) {
            alert("lengh must be at least 8 characters!");
            return false;
        }
        let Email = document.getElementById("3").value;
        const reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(Email)) {
            alert("invalid Email")
            return false;
        }
    }
</script>
<head>
    <title>New User Registration</title>
    <style type="text/css">
        * {
            /*初始化 清除页面元素的内外边距*/
            padding: 0;
            margin: 0;
            /*盒子模型*/
            box-sizing: border-box;
        }

        body {
            /*弹性布局 让页面元素垂直+水平居中*/

            /*justify-content: center;*/
            /*align-items: center;*/
            /*让页面始终占浏览器可视区域总高度*/
            height: 100vh;
            /*背景渐变色*/
            background-color: #243b55;
        }

        .login {
            /*弹性布局 让子元素称为弹性项目*/
            display: flex;
            /*让弹性项目垂直排列  原理是改变弹性盒子的主轴方向
            父元素就是弹性盒子  现在改变后的主轴方向是向下了*/
            flex-direction: column;
            /*让弹性项目在交叉轴方向水平居中  现在主轴的方向是向下
            交叉轴的方向是与主轴垂直 交叉轴的方向是向右*/
            align-items: center;
            width: 400px;

            padding: 40px;
            background-color: rgba(0, 0, 0, 0.2);
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.4);

        }

        .login h2 {
            color: #fff;
            margin-bottom: 30px;
        }

        .login {
            /*相对定位*/
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .login .login_box input {
            /*清除input框自带的边框和轮廓*/
            outline: none;
            border: none;
            width: 100%;
            padding: 10px 0;
            margin-bottom: 30px;
            color: #fff;
            font-size: 16px;
            border-bottom: 1px solid #fff;
            /*背景颜色为透明色*/
            background-color: transparent;
        }

        .login .login_box input:focus,
        .login .login_box input:valid {
            top: -20px;
            color: #03e9f4;
            font-size: 12px;
        }

        .login .login_box1 {
            outline: none;
            border: none;
            position: relative;
            padding: 10px 30px;
            background-color: transparent;
            color: #fff;
        }

        .login_box2 {
            color: white;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<br/>
<div class="login">
    <form action="${pageContext.request.contextPath}/register" align="center" name="form1" method="post"
          onsubmit="return on_submit()">
        <h2>New User Registration!</h2><br>
        <div class="login_box">
            <input type="text" name="username" id="1" placeholder="Username">
            <input type="password" name="password" id="2" placeholder="password">
            <input type="text" name="email" id="3" placeholder=Email><br>
            <input type="date" name="birthDate" placeholder="Date of birth(yyyy-mm-dd)"><br>
        </div>
        <div class="login_box2">
            <strong>Gender</strong> <input type="radio" name="gender" value="male">Male
            <input type="radio" name="gender" value="female">Female<br>
        </div>
        <br><br><br>
        <input type="submit" class="login_box1" value="Register">
    </form>
</div>
<br/>
<%@include file="footer.jsp" %>
</body>
</html>
