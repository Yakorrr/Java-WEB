<%@ page import="lab2.controller.dao.BillDAOProxy" %>
<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="lab2.model.entities.Bill" %>
<%@ page import="lab2.model.entities.Request" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title><%=Localization.getString("user-req-title")%>
    </title>
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

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
        <a href="${pageContext.request.contextPath}/user-my-requests?lang=ua">
            <img src="templates/img/ua-01.png" alt="ua">
        </a>
        <a href="${pageContext.request.contextPath}/user-my-requests?lang=en">
            <img src="templates/img/us-01.png" alt="en">
        </a>
    </div>
</div>

<div class="main-wrapper">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4"><%=Localization.getString("user-req-table-title")%>
        </h1>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><%=Localization.getString("user-req-table-plc")%>
            </th>
            <th scope="col"><%=Localization.getString("user-req-table-start-date")%>
            </th>
            <th scope="col"><%=Localization.getString("user-req-table-end-date")%>
            </th>
            <th scope="col"><%=Localization.getString("user-req-table-app")%>
            </th>
            <th scope="col"><%=Localization.getString("user-req-table-cancel")%>
            </th>
            <th scope="col"><%=Localization.getString("user-req-table-pay")%>
            </th>
        </tr>
        </thead>
        <tbody>
        <% List<Request> requests = (List<Request>) request.getAttribute("entries");
            for (Request r : requests) {%>
        <tr>
            <th scope="row"><%=r.getId()%>
            </th>
            <td><%=r.getPlaces()%>
            </td>
            <td><%=r.getStartDate()%>
            </td>
            <td><%=r.getEndDate()%>
            </td>
            <td><%=r.isApproved()%>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/user-my-requests?method=cancel&id=<%=r.getId()%>">
                    <%=Localization.getString("user-req-table-cancel")%>
                </a>
            </td>
            <td>
                <% if (r.isApproved()) {
                    Bill b = new BillDAOProxy().findBillByRequestId(r.getId());
                    if (b != null && b.isPaid()) { %>
                <%=Localization.getString("user-req-paid")%>
                <%} else { %>
                <a href="${pageContext.request.contextPath}/user-my-bills">
                    <%=Localization.getString("user-req-table-pay")%>
                </a>
                <%}%>
                <%} else {%>
                <%=Localization.getString("user-req-table-cant-pay")%>
                <%}%>
            </td>
        </tr>
        <%} %>
        </tbody>
    </table>

    <nav aria-label="my-nav">
        <ul class="pagination">
            <% Integer pageCount = (Integer) request.getAttribute("page-count");
                Integer active = (Integer) request.getAttribute("active-page");
                for (int i = 1; i <= pageCount; i++) {
                    if (i == active) {%>
            <li class="page-item active">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/user-my-requests?page=<%=i%>"><%=i%>
                </a>
            </li>
            <%} else {%>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/user-my-requests?page=<%=i%>"><%=i%>
                </a>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </nav>
    <a class="back-to-main-link" href="${pageContext.request.contextPath}/user-main">
        <%=Localization.getString("back-to-main")%>
    </a>
</div>

</body>
</html>