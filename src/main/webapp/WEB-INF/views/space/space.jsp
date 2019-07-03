<%--
  Created by IntelliJ IDEA.
  User: feruu
  Date: 03.07.2019
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My space</title>
</head>
<body>

<a href="/upload">UPLOAD FILE</a><br>

<c:forEach items="${files}" var="file">
    <c:out value="${file.name}"/> <br>
</c:forEach>
</body>
</html>
