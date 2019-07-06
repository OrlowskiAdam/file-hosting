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
<div>
    <img src="/img/home.jpg" alt="aye">
    <div class="container">
        <div class="item-a">
            <a href="/upload?dir=${dir}">
                <div class="upload">
                    UPLOAD FILE<br>
                </div>
            </a>
        </div>
        <div class="item-b">
            <a href="/space">
                <i class="fas fa-fast-backward"></i>
            </a>
            <a href="/space/lastPath?dir=${dir}">
                <i class="fas fa-step-backward"></i>
            </a>
            <a href="/folder?dir=${dir}">
                <i class="fas fa-folder-plus"></i>
            </a>
            <div class="directory">
                <c:out value="${dir}"/>
            </div>
        </div>
        <div class="item-d">
            <a href="/logout">
                <i class="fas fa-sign-out-alt"></i>
            </a>
            <a href="/settings">
                <i class="fas fa-cog"></i>
            </a>
        </div>

        <div class="item-c">
            <c:forEach items="${directories}" var="directory">
                <a href="/space?dir=${dir}/${directory.toString()}">
                    <i class="fas fa-folder"></i>&nbsp &nbsp<c:out value="${directory.toString()}"/></a>
                <div class="control-icons">

                    <button type="button" class="btn btn-info"><i class="fas fa-info-circle"></i></button>
                    <button type="button" class="btn btn-secondary"><i class="fas fa-file-signature"></i></button>

                    <a href="/space/delete/${directory.toString()}?dir=${dir}">
                        <button type="button" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                    </a>
                </div>
                <hr/>
            </c:forEach>
            <c:forEach items="${files}" var="file">
                <div class="control-icons">
                    <a href="/download/${file.name}?dir=${dir}"><c:out
                            value="${file.name}"/></a>
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
    </div>
</div>
</body>
</html>
