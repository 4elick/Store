<%--
  Created by IntelliJ IDEA.
  User: klima
  Date: 25.03.2023
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/forms.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/night-style.css">

<head>
    <title>Log in</title>
</head>
<body>
<form action = "/servlets/login" method = "POST">
  Your email: <input type = "email" name = "user_email" >
  <br />
  Your password: <input type = "password" name = "user_password" />
  <br />
  <input type = "submit" value = "Submit" />
</form>
</body>
</html>
