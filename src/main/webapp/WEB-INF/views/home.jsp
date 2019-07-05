<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <c:if test="${empty sessionScope.user}"><input type="submit" onclick="location.href='/login'" value="Log me in"/></c:if>
    <c:if test="${empty sessionScope.user}"><input type="submit" onclick="location.href='/register'" value="Register"/></c:if>
    <c:if test="${not empty sessionScope.user}"><input type="submit" onclick="location.href='/space'" value="My space"/></c:if>
    <c:if test="${not empty sessionScope.user}"><input type="submit" onclick="location.href='/logout'" value="Log out"/></c:if>
    <c:if test="${not empty sessionScope.user}"><div class="welcome">Logged in as <c:out value="${sessionScope.user.firstName}"></c:out>.</div></c:if>
</div>
</main>
</body>
</html>
