<%--
  Created by IntelliJ IDEA.
  User: klima
  Date: 19.03.2023
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/forms.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/pages/styles/night-style.css">
<head>
    <title>Registration</title>
</head>
<body>
<form action = "/servlets/registration" method = "POST">
  Your email: <input type = "email" name = "user_email" >
  <br />
  Your name: <input type = "text" name = "user_name" />
  <br />
  Your password: <input type = "password" name = "user_password" />
  <br />
  <label for="role">Chose who are you:</label>
  <select id="role" name="role">
    <option value="PRODUCER">Producer</option>
    <option value="COSTUMER">Costumer</option>
  </select>
  <input type = "submit" value = "Submit" />
</form>

</body>
</html>
