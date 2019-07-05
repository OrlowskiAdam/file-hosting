<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: feruu
  Date: 03.07.2019
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/uploadError.css"/>
    <script src="https://kit.fontawesome.com/e94dc59271.js"></script>
    <script src="/js/parallax.js"></script>
    <title>Upload error</title>
</head>
<body>
<main id="box">
    <div class="layer" id="1">
        <div class="parallax" id="welcome"><img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg">
        </div>
    </div>
    <div class="container">
        <h1>Error</h1>
        <br/>
        <span id="errorMsg">You are trying to upload a file that weights: <span class="bold"> <c:out
                value="${fileMemory}"/>MB</span><hr/> your free space: <span class="bold"><c:out
                value="${totalMemory}"/>MB</span></span>
        <input type="submit" onclick="location.href='/upload'" value="Upload again"/>
        <input type="submit" onclick="location.href='/space'" value="My space"/>
        <br>
        <div class="error-sign">
            <i class="fas fa-exclamation-triangle"></i>
        </div>
    </div>
</main>
</body>
</html>
