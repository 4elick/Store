<%@ page import="entities.Item" %><%--
  Created by IntelliJ IDEA.
  User: klima
  Date: 15.03.2023
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/forms.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/night-style.css">

<head>
    <title>Adding items</title>
</head>
<body>

<form action = "/servlets/add-item" method = "POST" enctype = "multipart/form-data">
    Item's name: <input type = "text" name = "item_name" >
    <br />
    Item's description: <input type = "text" name = "item_description" />
    <br />
    Item's price: <input type = "number" name = "item_price" />
    <br />
    Upload item's picture <input type="file" name="item_picture" >
    <input type = "submit" value = "Submit" />

</form>

</body>
</html>
