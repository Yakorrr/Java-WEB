<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
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

    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<div class="container">
    <div class="title">Registration</div>
    <div class="content">
        <form action="RegistrationServlet" method="post" novalidate>
            <div class="user-details">
                <div class="input-box">
                    <label class="details" for="first-name">First Name</label>
                    <input type="text" id="first-name" name="first-name"
                           placeholder="Enter your first name" required>
                </div>
                <div class="input-box">
                    <label class="details" for="last-name">Last Name</label>
                    <input type="text" id="last-name" name="last-name"
                           placeholder="Enter your last name" required>
                </div>

                <div class="input-box">
                    <label class="details" for="telephone-number">Phone Number</label>
                    <input type="text" id="telephone-number" name="telephone-number"
                           placeholder="Enter your telephone number" required>
                </div>
                <div class="input-box">
                    <label class="details" for="email">Email</label>
                    <input type="email" id="email" name="email"
                           placeholder="Enter your email" required>
                </div>

                <div class="input-box">
                    <label class="details" for="password">Password</label>
                    <input type="password" id="password" name="password"
                           placeholder="Enter your password" required>
                </div>
                <div class="input-box">
                    <label class="details" for="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" name="confirm-password"
                           placeholder="Confirm your password" required>
                </div>

                <div class="input-box">
                    <label class="details" for="datepicker">Date of birth</label>
                    <input type="text" id="datepicker" name="date-of-birth"
                           placeholder="Enter your date of birth" required>
                </div>
            </div>

            <div class="gender-details">
                <input type="radio" class="gender-radio" name="gender" id="dot-1" required>
                <input type="radio" class="gender-radio" name="gender" id="dot-2" required>
                <input type="radio" class="gender-radio" name="gender" id="dot-3" required>
                <span class="gender-title">Gender</span>
                <div class="category">
                    <label for="dot-1">
                        <span class="dot one"></span>
                        <span class="gender">Male</span>
                    </label>
                    <label for="dot-2">
                        <span class="dot two"></span>
                        <span class="gender">Female</span>
                    </label>
                    <label for="dot-3">
                        <span class="dot three"></span>
                        <span class="gender">Other</span>
                    </label>
                </div>
            </div>

            <div class="check-gender">
                <%--                <span><%= request.getAttribute("errMessage") %></span>--%>
                <span>yfyufviylb;ou</span>
            </div>

            <div class="button">
                <input class="submit" type="submit" value="Register">
            </div>
            <div class="sign-in">
                Already have an account? <a href="login.jsp">Sign in now</a>
            </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            startDate: "-150y",
            endDate: new Date(),
            todayHighlight: true,
            toggleActive: true
        });

        $(document).ready(function () {
            $("#first-name").focus();
        });
    });

    // $(".submit").click(function () {
    //     if ($(".gender-radio").not(':checked')) {
    //         $(".check-gender").css({
    //             "display": "flex",
    //             "justify-content": "center",
    //             "width": "100%",
    //             "color": "red"
    //         });
    //     }
    // });
</script>

</body>
</html>
