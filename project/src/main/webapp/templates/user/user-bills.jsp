<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="lab2.model.entities.Bill" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><%=Localization.getString("u-header-all-bill")%>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="templates/css/style.css">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("u-header-welcome-msg")%> 🖖</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="<c:url value="/user-main"/>"><%=Localization.getString("u-header-new-req")%>
        </a>
        <a class="p-2 text-dark"
           href="<c:url value="/user-my-requests"/>"><%=Localization.getString("u-header-all-req")%>
        </a>
        <a class="p-2 text-dark" href="<c:url value="/user-my-bills"/>"><%=Localization.getString("u-header-all-bill")%>
        </a>
    </nav>
    <a class="btn btn-outline-primary" href="<c:url value="/logout"/>"><%=Localization.getString("u-header-logout")%>
    </a>
</div>
<br>
<div class="lang-bar">
    <div class="lang-bar-images">
        <a href="<c:url value="/user-my-bills?lang=ua"/>">
            <img src="templates/img/ua-01.png" alt="ua">
        </a>
        <a href="<c:url value="/user-my-bills?lang=en"/>">
            <img src="templates/img/us-01.png" alt="en">
        </a>
    </div>
</div>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4"><%=Localization.getString("user-bills-table-title")%>
    </h1>
</div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><%=Localization.getString("user-bills-table-sum")%>
        </th>
        <th scope="col"><%=Localization.getString("user-bills-table-req-id")%>
        </th>
        <th scope="col"><%=Localization.getString("user-bills-table-room-id")%>
        </th>
        <th scope="col"><%=Localization.getString("user-bills-table-paid")%>
        </th>
        <th scope="col"><%=Localization.getString("user-req-table-pay")%>
        </th>
    </tr>
    </thead>
    <tbody>
    <% List<Bill> bills = (List<Bill>) request.getAttribute("entries");
        for (Bill b : bills) { %>
    <tr <% if (b.isPaid()) {%>  <%= "style=\"background: #7eb775;\""%>  <%}%>>
        <th scope="row"><%=b.getId()%>
        </th>
        <td><%=b.getSum()%>
        </td>
        <td><%=b.getRequest().getId()%>
        </td>
        <td><%=b.getRoom().getId()%>
        </td>
        <td><%=b.isPaid()%>
        </td>
        <% if (!b.isPaid()) {%>
        <td>
            <a href="<c:url value="/user-my-bills?method=pay&id=<%=b.getId()%>"/>"><%=Localization.getString("user-req-table-pay")%>
            </a></td>
        <%}%>
    </tr>
    <%}%>
    </tbody>
</table>
<nav aria-label="my-nav">
    <ul class="pagination">
        <% Integer pageCount = (Integer) request.getAttribute("page-count");
            Integer active = (Integer) request.getAttribute("active-page");
            for (int i = 1; i <= pageCount; i++) {
                if (i == active) {%>
        <li class="page-item active"><a class="page-link" href="<c:url value="/user-my-bills?page=<%=i%>"/>"><%=i%>
        </a></li>
        <%} else {%>
        <li class="page-item"><a class="page-link" href="<c:url value="/user-my-bills?page=<%=i%>"/>"><%=i%>
        </a></li>
        <%
                }
            }
        %>
    </ul>
</nav>
<br>
<a href="<c:url value="/user-main"/>"><%=Localization.getString("back-to-main")%>
</a>
</body>
</html>
