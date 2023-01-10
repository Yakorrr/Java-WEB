<%@ page import="lab2.controller.util.Localization" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><%=Localization.getString("access-den-title")%>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">

    <link rel="stylesheet" href="/templates/css/style.css">
</head>
<body class="body-pages">
<div class="error">
    <h1 class="display-4"><%=Localization.getString("access-den-err")%>
    </h1>

    <p class="lead"><%=Localization.getString("access-den-err-text")%>
    </p>

    <a href="/">
        <%=Localization.getString("access-den-back")%>
    </a>
</div>

</body>
</html>
