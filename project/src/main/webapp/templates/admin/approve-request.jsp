<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="lab2.model.entities.Request" %>
<%@ page import="lab2.model.entities.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title><%=Localization.getString("approve-req-title")%>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="templates/css/style.css">
</head>

<body class="body-pages">

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("a-header-welcome")%>
    </h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/admin">
            <%=Localization.getString("a-header-main")%>
        </a>
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/admin-users">
            <%=Localization.getString("a-header-users")%>
        </a>
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/admin-tables">
            <%=Localization.getString("a-header-tables")%>
        </a>
    </nav>
    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/logout">
        <%=Localization.getString("u-header-logout")%>
    </a>
</div>

<div class="lang-bar">
    <div class="lang-bar-images">
        <a href="${pageContext.request.contextPath}/approve?lang=ua">
            <img src="templates/img/ua-01.png" alt="ua">
        </a>
        <a href="${pageContext.request.contextPath}/approve?lang=en">
            <img src="templates/img/us-01.png" alt="en">
        </a>
    </div>
</div>

<div class="main-wrapper">
    <h3 class="m-4"><%=Localization.getString("new-req-title")%>
    </h3>

    <% Request req = (Request) request.getAttribute("selected-request");%>
    <form action="${pageContext.request.contextPath}/approve" method="post" class="m-4">
        <div class="form-row">
            <div class="form-group col-1">
                <label for="id">#</label>
                <input type="number" value="<%=req.getId()%>" class="form-control" id="id" name="id"
                       min="<%=req.getId()%>"
                       max="<%=req.getId()%>">
            </div>
            <div class="form-group col-1">
                <label for="places"><%=Localization.getString("approve-req-form-places")%>
                </label>
                <input type="number" value="<%=req.getPlaces()%>" class="form-control" id="places" name="places"
                       disabled>
            </div>
            <div class="form-group col-2">
                <label for="class"><%=Localization.getString("approve-req-form-class")%>
                </label>
                <input type="text" id="class" class="form-control" value="<%=req.getRoomClass()%>" name="class"
                       disabled>
            </div>
            <div class="form-group col-2">
                <label for="start-date"><%=Localization.getString("approve-req-form-start-d")%>
                </label>
                <input type="text" id="start-date" class="form-control" value="<%=req.getStartDate()%>"
                       name="start-date"
                       disabled/>
            </div>
            <div class="form-group col-2">
                <label for="end-date"><%=Localization.getString("approve-req-form-end-d")%>
                </label>
                <input type="text" id="end-date" class="form-control" value="<%=req.getEndDate()%>" name="end-date"
                       disabled/>
            </div>
            <div class="form-group col-2">
                <label for="room-select"><%=Localization.getString("approve-req-form-room-id")%>
                </label>
                <select class="browser-default custom-select" id="room-select" name="room-select">
                    <% List<Room> matchingRooms = (List<Room>) request.getAttribute("entries");
                        for (Room matchingRoom : matchingRooms) {%>
                    <option value="<%=matchingRoom.getId()%>"><%=matchingRoom.getId()%>
                    </option>
                    <%}%>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" id="add-request-button">
            <%=Localization.getString("approve-btn")%>
        </button>
    </form>

    <h3 class="m-4"><%=Localization.getString("room-title")%>
    </h3>

    <table class="table admin-table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><%=Localization.getString("room-pic")%>
            </th>
            <th scope="col"><%=Localization.getString("room-place")%>
            </th>
            <th scope="col"><%=Localization.getString("room-room-c")%>
            </th>
            <th scope="col"><%=Localization.getString("room-price")%>
            </th>
        </tr>
        </thead>
        <tbody>
        <% for (Room r : matchingRooms) {%>
        <tr>
            <th scope="row"><%=r.getId()%>
            </th>
            <td><img height="120px" src="<%=r.getPicURL()%>" alt="room"></td>
            <td><%=r.getPlaces()%>
            </td>
            <td><%=r.getRoomClass().name()%>
            </td>
            <td><%=r.getPrice()%>
            </td>
        </tr>
        <%
            } %>
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
                   href="${pageContext.request.contextPath}/approve?page=
                    <%=i%>&method=approve&id=<%=req.getId()%>"><%=i%>
                </a>
            </li>
            <%} else {%>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/approve?page=
                    <%=i%>&method=approve&id=<%=req.getId()%>"><%=i%>
                </a>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </nav>

    <a class="back-to-main-link" href="${pageContext.request.contextPath}/admin">
        <%=Localization.getString("back-to-main")%>
    </a>
</div>

</body>
</html>