<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lab2.controller.util.Localization" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=Localization.getString("login-title")%>
    </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

    <link rel="stylesheet" href="templates/css/style.css">
</head>
<body class="body-forms">

<div class="lang-bar">
    <div class="lang-bar-images">
        <a href="<c:url value="/login?lang=ua"/>">
            <img src="templates/img/ua-01.png" alt="ua-flag">
        </a>
        <a href="<c:url value="/login?lang=en"/>">
            <img src="templates/img/us-01.png" alt="us-flag">
        </a>
    </div>
</div>

<div class="main_div">
    <div class="title"><%=Localization.getString("login-form-title")%>
    </div>
    <div class="social_icons">
        <a href="#"><i class="fab fa-google"></i><span>Google</span></a>
        <a href="#"><i class="fab fa-facebook-f"></i><span>Facebook</span></a>
        <a href="#"><i class="fab fa-twitter"></i><span>Twitter</span></a>
    </div>
    <form class="login-form" action="login" method="post" novalidate>
        <div class="input_box">
            <label>
                <input type="text" name="email"
                       placeholder="<%=Localization.getString("login-placeholder")%>" required autofocus>
            </label>
            <div class="icon"><i class="fas fa-user"></i></div>
        </div>
        <div class="input_box">
            <label>
                <input type="password" name="password" placeholder="<%=Localization.getString("password-placeholder")%>"
                       required>
            </label>
            <div class="icon"><i class="fas fa-lock"></i></div>
        </div>
        <div class="option_div">
            <div class="check_box">
                <label>
                    <input type="checkbox">
                </label>
                <span><%=Localization.getString("remember-me")%></span>
            </div>
            <div class="forget_div">
                <a href="#"><%=Localization.getString("forgot-password")%>
                </a>
            </div>
        </div>

        <div class="check-input">
                <span>
                    <%
                        String message = (String) request.getServletContext().getAttribute("errMessage");

                        if (!Objects.equals(message, null)) {
                            out.print(message);
                        } else request.getServletContext().removeAttribute("errMessage");
                    %>
                </span>
        </div>

        <div class="input_box button">
            <input type="submit" value="<%=Localization.getString("submit-value")%>">
        </div>
        <div class="sign_up"><%=Localization.getString("sign-up-text")%>
            <a href="<c:url value="/register"/>">
                <%=Localization.getString("sign-up-link")%>
            </a>
        </div>
    </form>
</div>

</body>
</html>