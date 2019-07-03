<%--
  Created by IntelliJ IDEA.
  User: feruu
  Date: 02.07.2019
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    <script src="/js/parallax.js"></script>
    </head>
<body>
<main id="box">
<div class="layer" id="1">
<img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg"/>
</div>
<form:form method="post" modelAttribute="user" class="box">
    <h1>Login</h1>
    <hr/>
    <form:input type="password" path="login" placeholder="Username"/>
    <form:errors path="login" cssClass="error"/>
    <form:password path="password" placeholder="Password"/>
    <form:errors path="password" cssClass="error"/>
    <input type="submit" value="Log me in"/>
    <span class="not-registered">Not registered yet?</span> <span class="not-registered"><a href="/register">Register</a></span>
</form:form>
</main>
</body>
</html>
