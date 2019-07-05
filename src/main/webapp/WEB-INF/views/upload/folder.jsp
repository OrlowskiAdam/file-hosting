<%--
  Created by IntelliJ IDEA.
  User: feruu
  Date: 04.07.2019
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/folder.css"/>
    <script src="https://kit.fontawesome.com/e94dc59271.js"></script>
    <script src="/js/parallax.js"></script>
    <title>Add new folder</title>
</head>
<body>
<main id="box">
    <div class="layer" id="1">
        <div class="parallax" id="welcome"><img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg">
        </div>
    </div>
    <div class="container">
        <span id="info">Add new folder<span class="thin">
            <c:out value="${dir}"/></span>
        <hr/>
        <form method="POST">
            <input type="text" name="folderName" placeholder="Folder name">
            <input type="submit" value="Add">
            <hr/>
        </form>
            <input type="submit" onclick="location.href='/space'" value="My space"/>
    </div>
</main>
</body>
</html>
