<%@ page import="models.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Game</title>
</head>
<body>
<%Game game = (Game) request.getAttribute("game");%>
<div class="container ">
    <div class="card mx-auto" style="width: 50rem;">
        <img src="http://localhost:8080/uploaded/files?id=<%=game.getFileId()%>" class="card-img-top">
        <div class="card-body">
            <h5 class="card-title"><%=game.getName()%></h5>
            <p class="card-text"><%=game.getReview()%></p>
            <form action="/game?id=<%=game.getId()%>" method="post">
                <input class="btn btn-primary" type="submit" value="Add to cart">
            </form>
            <a class="btn btn-primary" href="http://localhost:8080/players?id=<%=game.getId()%>">Players</a>
        </div>
    </div>
</div>>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
