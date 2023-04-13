<%@ page import="entities.Item" %><%--
  Created by IntelliJ IDEA.
  User: klima
  Date: 13.03.2023
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/night-style.css">

<head>
    <title>Item</title>
</head>
<body>
<%
    Item item = (Item)request.getAttribute("item");
%>
<p><%= item.getName() %></p>
<p><%= item.getDescription()%></p>
<p><%= item.getPrice()%></p>
<img src="data:image/png;base64,${requestScope['item_picture']}" width="500" height="500"/>
<button type="submit" formaction="/servlets/order" formmethod="post"> Add to basket</button>


</body>
</html>
