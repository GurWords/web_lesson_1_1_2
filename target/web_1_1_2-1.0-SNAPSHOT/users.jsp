<%@ page import="DAOUser.DAOuser" %>
<%@ page import="Service.UsersService" %><%--
  Created by IntelliJ IDEA.
  User: GEORGY
  Date: 24.04.2020
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>Добавить пользователя:</h4>
<form action="/users" method="post">
    Name:
    <input name="name" type="text">
    Age:
    <input name="age" type="number">
    <input type="submit">
</form>
<h4>Изменить ползователя:</h4>
<form name="updateUser" action="/users" method="post">
    Name old:
    <input name="name_old" type="text">
    Age old:
    <input name="age_old" type="number">
    Name new:
    <input name="name_new" type="text">
    Age new:
    <input name="age_new" type="number">
    <input type="submit" name="updateForm">
</form>
    <h4>Удалить пользователя</h4>
<form action="/users" method="post">
    Name:
    <input name="nameDelete" type="text">
    Age:
    <input name="ageDelete" type="number">
    <input type="submit">
</form>
<% response.getWriter().println(UsersService.selectAllUsers()); %>
</body>
</html>
