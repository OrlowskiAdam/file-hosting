<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/home.css"/>
    <script src="/js/parallax.js"></script>
    <title>Start page</title>
</head>
<body>
<main id="box">
    <div class="layer" id="1">
        <div class="parallax" id="welcome"><img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg">
        </div>
    </div>
<div class="container">
    <span class="text1">Welcome to</span>
    <span class="text2">file hosting</span>
    <input type="submit" onclick="location.href='http://localhost:8080/login'" value="Log me in"/>
    <input type="submit" onclick="location.href='http://localhost:8080/register'" value="Register"/>
    <input type="submit" onclick="location.href='http://localhost:8080/space'" value="My space"/>
</div>
</main>
</body>
</html>
