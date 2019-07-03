<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My space</title>
    <link rel="stylesheet" type="text/css" href="/css/space.css"/>
    <script src="https://kit.fontawesome.com/e94dc59271.js"></script>
    <script src="/js/parallax.js"></script>
</head>
<body>
<main id="box">
    <div class="layer" id="1">
        <div class="parallax" id="welcome"><img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg">
        </div>
    </div>

    <div class="left">
        <div class="menu">

            <li class="item">
                <a href="" class="btn"><i class="fas fa-user"></i>Profile</a>
                <div class="smenu">
                    <a href="">Settings</a>
                </div>
            </li>

            <li class="item">
                <a href="" class="btn"><i class="fas fa-language"></i>Language</a>
                <div class="smenu">
                    <a href="">Polish</a>
                    <a href="">English</a>
                </div>
            </li>

            <li class="btn"></li>

        </div>
    </div>

    <div id="upload">
        <a href="/upload">&nbsp UPLOAD FILE &nbsp</a><br>
    </div>
    <div class="container">
        <c:out value="${overload}"/>

        <c:forEach items="${files}" var="file">
            <a href="/space/download/${file.name}"><c:out value="${file.name}"/></a>
            <a href="/space/delete/${file.name}">
                <button><i class="fas fa-trash"></i></button>
            </a>
            <hr/>
        </c:forEach>
        <c:out value="${memory}"/>
    </div>
</main>
</body>
</html>
