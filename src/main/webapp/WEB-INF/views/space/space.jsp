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
    <a href="/upload?dir=${dir}">
        <div id="upload">
            &nbsp UPLOAD FILE &nbsp<br>
        </div>
    </a>
    <a href="/space">
        <div class="fast-back">
            <i class="fas fa-fast-backward"></i>
        </div>
    </a>
    <a href="/space/lastPath?dir=${dir}">
        <div class="back">
            <i class="fas fa-step-backward"></i>
        </div>
    </a>
    <a href="/folder?dir=${dir}">
        <div class="add">
            <i class="fas fa-folder-plus"></i>
        </div>
    </a>
    <a href="/logout">
        <div class="logout">
            <i class="fas fa-sign-out-alt"></i>
        </div>
    </a>
    <a href="/settings">
        <div class="settings">
            <i class="fas fa-cogs"></i>
        </div>
    </a>
    <div class="path">
        <c:out value="${dir}"/>
    </div>
    <div class="container">
        <c:forEach items="${directories}" var="directory">
            <div class="center">
            <a href="/space?dir=${dir}/${directory.toString()}" style="padding-top: 16px; display: inline-block;"><i class="fas fa-folder"></i>&nbsp &nbsp<c:out value="${directory.toString()}"/></a>
            <div class="option col-2">
                <button type="button" class="btn btn-info"><i class="fas fa-info-circle"></i></button>
                <button type="button" class="btn btn-secondary"><i class="fas fa-file-signature"></i></button>
                <a href="/space/delete/${directory.toString()}?dir=${dir}">
                    <button type="button" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                </a>
            </div>
            <hr/>
            </div>
        </c:forEach>
        <c:forEach items="${files}" var="file">
            <a href="/download/${file.name}?dir=${dir}" style="padding-top: 16px; display: inline-block;"><c:out value="${file.name}"/></a>
            <div class="option col-2">
                <button type="button" class="btn btn-info"><i class="fas fa-info-circle"></i></button>
                <button type="button" class="btn btn-secondary"><i class="fas fa-file-signature"></i></button>
                <a href="/space/delete/${file.name}?dir=${dir}">
                    <button type="button" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                </a>
            </div>
            <hr/>
        </c:forEach>
<%--        <c:out value="${memory}"/>--%>
    </div>
</main>
</body>
</html>
