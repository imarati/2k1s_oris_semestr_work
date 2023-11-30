<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Players</title>
</head>
<body>
<div class="container">
    <div class="card mx-auto">
        <%
            List<User> players = (List<User>) request.getAttribute("users");
            for(int i = 0; i < players.size(); i++) {
        %>
        <div class="card-body">
            <p class="card-text">Email: <%=players.get(i).getEmail()%></p>
        </div>
        <%}%>
    </div>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
