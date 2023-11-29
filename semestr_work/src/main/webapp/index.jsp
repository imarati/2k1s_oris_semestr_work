<%@ page import="models.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Index</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand text-white" href="#">MG</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="#">Link</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <%
        List<Game> games = (List<Game>) request.getAttribute("games");
        for(int i = 0; i < games.size(); i++) {
    %>

    <div class="card mx-auto" style="width: 45rem;">
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