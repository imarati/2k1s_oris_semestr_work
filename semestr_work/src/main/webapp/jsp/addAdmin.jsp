<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add admin</title>
</head>
<body>
<div class="container">
    <form action="/addAdmin" method="post">
        <input type="text" name="name" placeholder="Your name">
        <input type="text" name="surname" placeholder="Your surname">
        <input type="text" name="email" placeholder="email">
        <input type="text" name="password" placeholder="Password">
        <input type="submit" value="Send">
    </form>
    <div class="card mx-auto">
        <%
            List<User> admins = (List<User>) request.getAttribute("admins");
            for(int i = 0; i < admins.size(); i++) {
        %>
        <div class="card-body">
            <p class="card-title">Name: <%=admins.get(i).getName()%></p>
            <p class="card-text">Surname: <%=admins.get(i).getSurname()%></p>
            <p class="card-text">Email: <%=admins.get(i).getEmail()%></p>
        </div>
        <%}%>
    </div>
</div>
</body>
</html>
