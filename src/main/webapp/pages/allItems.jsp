<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.Item" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/night-style.css">

<head>
    <title>Items</title>
</head>
<body>
<jsp:useBean id="items" scope="request" type="java.util.List"/>
<c:forEach var="item" items="${items}">

    <a href="items?id=${item.getId()}">${item.getName()}</a>
    <br/>
</c:forEach>
</body>
</html>
