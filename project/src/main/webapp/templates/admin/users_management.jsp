<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="lab2.model.enums.Language" %>
<%@ page import="lab2.model.enums.Role" %>
<%@ page import="lab2.model.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="lab2.model.enums.Gender" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title><%=Localization.getString("a-header-users")%>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

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
        <a href="${pageContext.request.contextPath}/admin-users?lang=ua">
            <img src="templates/img/ua-01.png" alt="ua">
        </a>
        <a href="${pageContext.request.contextPath}/admin-users?lang=en">
            <img src="templates/img/us-01.png" alt="en">
        </a>
    </div>
</div>

<div class="main-wrapper">
    <h3 class="m-4" id="all-users">
        <%=Localization.getString("u-all-users")%>
    </h3>

    <table class="table admin-table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><%=Localization.getString("u-name")%>
            </th>
            <th scope="col"><%=Localization.getString("u-role")%>
            </th>
            <th scope="col"><%=Localization.getString("u-email")%>
            </th>
            <th scope="col"><%=Localization.getString("u-lang")%>
            </th>
            <th scope="col"><%=Localization.getString("u-del")%>
            </th>
            <th scope="col"><%=Localization.getString("u-change")%>
            </th>
        </tr>
        </thead>
        <tbody>
        <% List<User> users = (List<User>) request.getAttribute("entries");
            int adminId = ((User) request.getServletContext().getAttribute("user")).getId();

            for (User u : users) { %>
        <tr>
            <th scope="row"><%=u.getId()%>
            </th>
            <td><%=u.getFirstName()%>
            </td>
            <td><%=u.getRole()%>
            </td>
            <td><%=u.getEmail()%>
            </td>
            <td><%=u.getLanguage()%>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin-users?method=remove&id=<%=u.getId()%>&admin-id=<%=adminId%>#all-users">
                    <%=Localization.getString("u-del")%>
                </a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin-users?method=privilege_a&id=<%=u.getId()%>&admin-id=<%=adminId%>#all-users">
                    <%=Localization.getString("u-to-a")%>
                </a> |
                <a href="${pageContext.request.contextPath}/admin-users?method=privilege_u&id=<%=u.getId()%>&admin-id=<%=adminId%>#all-users">
                    <%=Localization.getString("u-to-u")%>
                </a>
            </td>
        </tr>
        <% } %>
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
                   href="${pageContext.request.contextPath}/admin-users?page=<%=i%>#all-users"><%=i%>
                </a>
            </li>
            <%} else {%>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin-users?page=<%=i%>#all-users"><%=i%>
                </a>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </nav>

    <h3 class="m-4" id="add-user">
        <%=Localization.getString("u-add")%>
    </h3>

    <form action="${pageContext.request.contextPath}/admin-users-update#add-user" method="post">
        <div class="form-row">
            <div class="form-group col-3">
                <label for="first-name">
                    <%=Localization.getString("u-u-first-name")%>
                </label>
                <input type="text" class="form-control" id="first-name" name="first-name">
            </div>
            <div class="form-group col-3">
                <label for="last-name">
                    <%=Localization.getString("u-u-last-name")%>
                </label>
                <input type="text" class="form-control" id="last-name" name="last-name">
            </div>

            <div class="form-group col-2">
                <label for="date-of-birth">
                    <%=Localization.getString("u-u-date-of-birth")%>
                </label>
                <input type="text" class="form-control" id="date-of-birth" name="date-of-birth"
                       value="<%=LocalDate.now()%>">
            </div>
            <div class="form-group col-2">
                <label for="gender">
                    <%=Localization.getString("u-u-gender")%>
                </label>
                <select class="browser-default custom-select" id="gender" name="gender">
                    <% Gender[] genders = Gender.values();
                        for (Gender g : genders) {%>
                    <option value="<%=g.toString()%>"><%=g.toString()%>
                    </option>
                    <% }%>
                </select>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-3">
                <label for="telephone-number">
                    <%=Localization.getString("u-u-phone")%>
                </label>
                <input type="text" class="form-control" id="telephone-number" name="telephone-number">
            </div>
            <div class="form-group col-3">
                <label for="email"><%=Localization.getString("u-email")%>
                </label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-2">
                <label for="role"><%=Localization.getString("u-role")%>
                </label>
                <select class="browser-default custom-select" id="role" name="role">
                    <% Role[] roles = Role.values();
                        for (Role r : roles) {%>
                    <option value="<%=r.toString()%>"><%=r.toString()%>
                    </option>
                    <% }%>
                </select>
            </div>
            <div class="form-group col-2">
                <label for="lang"><%=Localization.getString("u-lang")%>
                </label>
                <select class="browser-default custom-select" id="lang" name="lang">
                    <% Language[] languages = Language.values();
                        for (Language l : languages) {%>
                    <option value="<%=l.toString()%>"><%=l.toString()%>
                    </option>
                    <% }%>
                </select>
            </div>

            <div class="form-group col-3">
                <label for="password"><%=Localization.getString("u-pass")%>
                </label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
        </div>
        <button type="submit" class="btn btn-primary" id="add-request-button">
            <%=Localization.getString("user-main-req-btn")%>
        </button>
    </form>

    <a class="back-to-main-link" href="${pageContext.request.contextPath}/admin">
        <%=Localization.getString("back-to-main")%>
    </a>
</div>

<script>
    $(function () {
        $('input[name="date-of-birth"]').datepicker({
            format: 'yyyy-mm-dd',
            todayHighlight: true,
            toggleActive: true,
            opens: 'left'
        });
    });
</script>

</body>
</html>
