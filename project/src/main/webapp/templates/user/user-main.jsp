<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="lab2.model.enums.RoomClass" %>
<%@ page import="lab2.model.entities.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% User u = (User) session.getAttribute("user");%>
<!DOCTYPE html>
<html>
<head>
    <title><%=Localization.getString("user-main-msg")%>, <%=u.getFirstName()%>!
    </title>
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

    <link rel="stylesheet" href="templates/css/style.css">
</head>
<body class="body-pages">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("u-header-welcome-msg")%> 🖖</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/user-main">
            <%=Localization.getString("u-header-new-req")%>
        </a>
        <a class="p-2 text-dark"
           href="${pageContext.request.contextPath}/user-my-requests">
            <%=Localization.getString("u-header-all-req")%>
        </a>
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/user-my-bills">
            <%=Localization.getString("u-header-all-bill")%>
        </a>
    </nav>
    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/logout">
        <%=Localization.getString("u-header-logout")%>
    </a>
</div>

<div class="lang-bar">
    <div class="lang-bar-images">
        <a href="${pageContext.request.contextPath}/user-main?lang=ua">
            <img src="templates/img/ua-01.png" alt="ua">
        </a>
        <a href="${pageContext.request.contextPath}/user-main?lang=en">
            <img src="templates/img/us-01.png" alt="en">
        </a>
    </div>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4"><%=Localization.getString("user-main-msg")%>, <%=u.getFirstName()%>!
    </h1>
    <p class="lead"><%=Localization.getString("user-main-msg-small")%>
    </p>
</div>
<div class="wrapper-custom">
    <h3 class="m-4"><%=Localization.getString("user-main-req-title")%>
    </h3>
    <form action="${pageContext.request.contextPath}/new-request" method="post" class="m-4 user-main-form">
        <div class="form-row">
            <div class="form-group col-3">
                <label for="places"><%=Localization.getString("user-main-req-places")%> (1 - 10)</label>
                <input type="number" class="form-control" id="places" name="places" min="1" max="10">
            </div>
            <div class="form-group col-3">
                <label for="class"><%=Localization.getString("user-main-req-class")%>
                </label>
                <select class="browser-default custom-select" id="class" name="class">
                    <% RoomClass[] classes = RoomClass.values();
                        for (RoomClass c : classes) {%>
                    <option value="<%=c.toString()%>"><%=c.toString()%>
                    </option>
                    <% }%>
                </select></div>
            <div class="form-group col-3">
                <label for="test"><%=Localization.getString("user-main-req-date")%>
                </label>
                <input type="text" id="test" name="date-range"
                       value="<%=LocalDate.now()%>-<%=LocalDate.now().plusDays(7)%>"/>
            </div>

        </div>
        <button type="submit" class="btn btn-primary" id="add-request-button">
            <%=Localization.getString("user-main-req-btn")%>
        </button>
    </form>
</div>
</body>
<script>
    $(function () {
        $('input[name="date-range"]').daterangepicker({
            opens: 'left'
        }, function (start, end) {
            console.log("A new date selection was made: " +
                start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        });
    });
</script>
</html>
