<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    <script src="/js/parallax.js"></script>
</head>
<body>
<main id="box">
    <div class="layer" id="1">
        <img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg"/>
    </div>
<form:form method="post" modelAttribute="user" class="box">
    <h1>Register</h1>
    <hr/>
    <form:password path="login" placeholder="Username"/>
             <form:errors path="login" cssClass="error"/>
    <form:password path="password" placeholder="Password"/>
              <form:errors path="password" cssClass="error"/>
    <input type="password" name="password2" placeholder="Repeat password"/>
    <form:input type="email" path="email" placeholder="Email"/>
            <form:errors path="email" cssClass="error"/>
    <hr/>
    <form:input type="firstName" path="firstName" placeholder="First name"/>
                <form:errors path="firstName" cssClass="error"/>
    <form:input type="lastName" path="lastName" placeholder="Last name"/>
               <form:errors path="lastName" cssClass="error"/>
    <input type="submit" value="Register"/>
    <span class="not-registered">Already registered?</span> <span class="not-registered"><a href="/login">Log in</a></span>
</form:form>
</main>
</body>
</html>
