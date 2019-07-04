<%--
  Created by IntelliJ IDEA.
  User: feruu
  Date: 03.07.2019
  Time: 09:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/upload.css"/>
    <script src="https://kit.fontawesome.com/e94dc59271.js"></script>
    <script src="/js/parallax.js"></script>
    <title>Upload file</title>
</head>
<body>
<main id="box">
    <div class="layer" id="1">
        <div class="parallax" id="welcome"><img src="http://hdqwalls.com/wallpapers/small-memory-lp.jpg">
        </div>
    </div>
    <div class="container">
        <h1>File upload</h1><br>
        <h3>Max size is 1GB</h3>
        <hr/>
        <br>
        <form method="POST" action="/upload" enctype="multipart/form-data">
            <input type="file" name="file"/><br/><br/>
            <input type="submit" value="Submit"/><br>
            <hr/>
        </form>
        <input type="submit" onclick="location.href='http://localhost:8080/space'" value="My space"/>
    </div>
</main>
</body>
</html>
