<%@ page import="models.Game" %>
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
    <title>Profile</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<div class="container" >
    <div class="card mx-auto" style="width: 50rem;">
        <div class="card-body">
            <p class="card-title">Name: <%=user.getName()%></p>
            <p class="card-text">Surname: <%=user.getSurname()%></p>
            <p class="card-text">Email: <%=user.getEmail()%></p>
        </div>
    </div>

    <%
        List<Game> games = (List<Game>) request.getAttribute("games");
        for(int i = 0; i < games.size(); i++) {
    %>

    <div class="card mx-auto" style="width: 35rem;">
        <img src="http://localhost:8080/uploaded/files?id=<%=games.get(i).getFileId()%>">
        <div class="card-body">
            <h3 class="card-text text-center"><a class="link-underline-light text-black" href="http://localhost:8080/game?id=<%=games.get(i).getId()%>"><%=games.get(i).getName()%></a></h3>
        </div>
    </div>

    <%}%>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>