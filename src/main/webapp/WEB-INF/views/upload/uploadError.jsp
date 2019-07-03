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
    <title>Upload error</title>
</head>
<body>
You are trying to upload a file that weights <c:out value="${fileMemory}"/>, but your free space is <c:out value="${totalMemory}"/>
<a href="/space">My space</a>
</body>
</html>
