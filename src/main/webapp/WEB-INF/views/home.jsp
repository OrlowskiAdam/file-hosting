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

<%--<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">--%>
<%--    <ol class="carousel-indicators">--%>
<%--        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>--%>
<%--        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>--%>
<%--        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>--%>
<%--    </ol>--%>
<%--    <div class="carousel-inner">--%>
<%--        <div class="carousel-item active">--%>
<%--            <img src="..." class="d-block w-100" alt="...">--%>
<%--        </div>--%>
<%--        <div class="carousel-item">--%>
<%--            <img src="..." class="d-block w-100" alt="...">--%>
<%--        </div>--%>
<%--        <div class="carousel-item">--%>
<%--            <img src="..." class="d-block w-100" alt="...">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">--%>
<%--        <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--        <span class="sr-only">Previous</span>--%>
<%--    </a>--%>
<%--    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">--%>
<%--        <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--        <span class="sr-only">Next</span>--%>
<%--    </a>--%>
<%--</div>--%>

</body>
</html>
