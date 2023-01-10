<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=Localization.getString("register-title")%>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <script type="text/javascript" src="templates/js/core.js"></script>
</head>
<body class="body-forms">

<div class="lang-bar">
    <div class="lang-bar-images">
        <a href="/register?lang=ua">
            <img src="templates/img/ua-01.png" alt="ua-flag">
        </a>
        <a href="/register?lang=en">
            <img src="templates/img/us-01.png" alt="us-flag">
        </a>
    </div>
</div>

<div class="container">
    <div class="title"><%=Localization.getString("registration-from-title")%>
    </div>
    <div class="content">
        <form class="register-form" action="register" method="post" novalidate>
            <div class="user-details">
                <div class="input-box">
                    <label class="details" for="first-name">
                        <%=Localization.getString("form-first-name-title")%>
                    </label>
                    <input type="text" id="first-name" name="first-name"
                           placeholder="<%=Localization.getString("form-first-name-value")%>"
                           required autofocus>
                </div>
                <div class="input-box">
                    <label class="details" for="last-name">
                        <%=Localization.getString("form-last-name-title")%>
                    </label>
                    <input type="text" id="last-name" name="last-name"
                           placeholder="<%=Localization.getString("form-last-name-value")%>" required>
                </div>

                <div class="input-box">
                    <label class="details" for="datepicker">
                        <%=Localization.getString("form-date-of-birth-title")%>
                    </label>
                    <input type="text" id="datepicker" name="date-of-birth"
                           placeholder="<%=Localization.getString("form-date-of-birth-value")%>" required>
                </div>

                <div class="input-box">
                    <input type="radio" class="gender-radio" name="gender"
                           id="dot-1" required>
                    <input type="radio" class="gender-radio" name="gender"
                           id="dot-2" required>
                    <input type="radio" class="gender-radio" name="gender"
                           id="dot-3" required>
                    <label class="details">
                        <%=Localization.getString("form-gender-title")%>
                    </label>
                    <div class="category">
                        <label for="dot-1">
                            <span class="dot one"></span>
                            <span class="gender">
                                <%=Localization.getString("form-gender-one-value")%>
                            </span>
                        </label>
                        <label for="dot-2">
                            <span class="dot two"></span>
                            <span class="gender">
                                <%=Localization.getString("form-gender-two-value")%>
                            </span>
                        </label>
                        <label for="dot-3">
                            <span class="dot three"></span>
                            <span class="gender">
                                <%=Localization.getString("form-gender-three-value")%>
                            </span>
                        </label>
                    </div>
                </div>

                <div class="input-box">
                    <label class="details" for="telephone-number">
                        <%=Localization.getString("form-telephone-title")%>
                    </label>
                    <input type="text" id="telephone-number" name="telephone-number"
                           placeholder="<%=Localization.getString("form-telephone-value")%>" required>
                </div>
                <div class="input-box">
                    <label class="details" for="email">
                        <%=Localization.getString("form-email-title")%>
                    </label>
                    <input type="text" id="email" name="email"
                           placeholder="<%=Localization.getString("form-email-value")%>" required>
                </div>

                <div class="input-box">
                    <label class="details" for="password">
                        <%=Localization.getString("form-password-title")%>
                    </label>
                    <input type="password" id="password" name="password"
                           placeholder="<%=Localization.getString("form-password-value")%>" required>
                </div>
                <div class="input-box">
                    <label class="details" for="confirm-password">
                        <%=Localization.getString("form-confirm-password-title")%>
                    </label>
                    <input type="password" id="confirm-password" name="confirm-password"
                           placeholder="<%=Localization.getString("form-confirm-password-value")%>" required>
                </div>
            </div>

            <div class="check-input">
                <span>
                    <%
                        String message = (String) request.getServletContext().getAttribute("errMessage");

                        if (!Objects.equals(message, null)) {
                            out.print(message);
                        }
                    %>
                </span>
            </div>

            <div class="button">
                <input class="submit" type="submit"
                       value="<%=Localization.getString("submit-register")%>">
            </div>
            <div class="sign-in">
                <%=Localization.getString("sign-in-text")%>
                <a href="/login">
                    <%=Localization.getString("sign-in-link")%>
                </a>
            </div>
        </form>
    </div>
</div>

</body>
</html>

