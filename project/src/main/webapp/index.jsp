<%@ page import="lab2.controller.util.Localization" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><%=Localization.getString("welcome-title")%>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="/templates/css/style.css">
</head>
<body class="body-main">

<div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-light">
        <a class="p-2 link-dark" href="/login">
            <%=Localization.getString("submit-value")%>
        </a>
        <a class="p-2 link-dark" href="/register">
            <%=Localization.getString("register-link-text")%>
        </a>
    </div>
</div>
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<div class="lang-bar">
    <div class="lang-bar-images">
        <a href="/main?lang=ua">
            <img src="templates/img/ua-01.png" alt="ua">
        </a>
        <a href="/main?lang=en">
            <img src="templates/img/us-01.png" alt="en">
        </a>
    </div>
</div>

<div class="welcome">
    <h1 class="welcome-message"><%=Localization.getString("welcome-message1")%>
        <br>
        <%=Localization.getString("welcome-message2")%> 🏨</h1>
</div>
<div class="br-line"></div>
<img class="index-image"
     src="/templates/img/hotel/hotel.jpg"
     alt="hotel image">
</body>
</html>