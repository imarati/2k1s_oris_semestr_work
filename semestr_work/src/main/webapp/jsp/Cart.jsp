<%@ page import="models.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Cart</title>
</head>
<body>
<div class="container">
    <div class="card mx-auto">
        <div class="card-body">
            <ul class="list-group">
                <%
                    List<Game> games = (List<Game>) request.getAttribute("games");
                    for(int i = 0; i < games.size(); i++) {
                %>

                <li class="list-group-item"><%=games.get(i).getName()%></li>

                <%}%>
            </ul>
            <form action="/cart" method="post">
                <input class="btn btn-primary" type="submit" value="Buy">
            </form>
        </div>
    </div>
</div>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
