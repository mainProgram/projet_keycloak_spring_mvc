<%--
  Created by IntelliJ IDEA.
  User: fazeyna
  Date: 08/09/2024
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <style><%@include file="/WEB-INF/css/style.css"%></style>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <img src="https://i.postimg.cc/MTRK7Thf/bg-form.jpg" alt="">
        <div class="container">
            <form method="post" action="login">
                <h1 class="">Login</h1>
                <br>
                <div class="input-box">
                    <input type="email" name="email" placeholder="Email">
                </div>

                <div class="input-box">
                    <input type="password" name="password" placeholder="Password">
                </div>

                <button class="btn" type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
