<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Register page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/css/bootstrapvalidator.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js">

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="registration-title"><h1>Registration Form</h1></div>
<div class="registration-form">
    <form class="col g-3 needs-validation" name="form" action="RegistrationServlet"
          method="post" novalidate>
        <div class="col-md-12">
            <div class="col-md-4">
                <label for="firstName" class="form-label">First name</label>
                <input type="text" class="form-control" id="firstName" name="first-name"
                       placeholder="John" required>

                <div class="valid-feedback">
                    That's right!
                </div>
                <div class="invalid-feedback">
                    Please enter your first name.
                </div>
            </div>

            <div class="col-md-4">
                <label for="lastName" class="form-label">Last name</label>
                <input type="text" class="form-control" id="lastName" name="last-name"
                       placeholder="Smith" required>
                <div class="valid-feedback">
                    That's right!
                </div>
                <div class="invalid-feedback">
                    Please enter your last name.
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="col-md-4">
                <label for="datepicker" class="form-label">Date of birth</label>
                <input type="text" class="form-control input-group date" id="datepicker"
                       name="date-of-birth" aria-describedby="inputGroupPrepend"
                       placeholder="Select date" required>
                <div class="valid-feedback">
                    You look so good!
                </div>
                <div class="invalid-feedback">
                    Please enter your birthday!
                </div>
            </div>

            <div class="col-md-4">
                <label class="form-label">Gender: </label>

                <input type="radio" class="btn-check" name="gender" id="male" autocomplete="off" required>
                <label class="btn btn-outline-secondary" for="male">Male</label>

                <input type="radio" class="btn-check" name="gender" id="female" autocomplete="off" required>
                <label class="btn btn-outline-secondary" for="female">Female</label>

                <input type="radio" class="btn-check" name="gender" id="secret" autocomplete="off" required>
                <label class="btn btn-outline-secondary" for="secret">Secret</label>

                <div class="valid-feedback">Well done!</div>
                <div class="invalid-feedback">Please select a gender!</div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="col-md-4">
                <label for="telephone" class="form-label">Telephone number</label>

                <span class="input-group-text" id="telephoneStartSign">+</span>
                <input type="text" class="form-control" id="telephone" name="email"
                       placeholder="123-(123)-xxx-xx-xx" required>

                <div class="valid-feedback">That's right!</div>
                <div class="invalid-feedback">
                    Please enter your telephone number!
                </div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="col-md-4">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       placeholder="name@example.com" required>

                <div class="valid-feedback">That's right!</div>
                <div class="invalid-feedback">
                    Please enter your email!
                </div>
            </div>
        </div>


<%--        <div class="col-md-3">--%>
<%--            <label for="validationCustom04" class="form-label">State</label>--%>
<%--            <select class="form-select" id="validationCustom04" required>--%>
<%--                <option selected disabled value="">Choose...</option>--%>
<%--                <option>...</option>--%>
<%--            </select>--%>
<%--            <div class="invalid-feedback">--%>
<%--                Please select a valid state.--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-md-3">--%>
<%--            <label for="validationCustom05" class="form-label">Zip</label>--%>
<%--            <input type="text" class="form-control" id="validationCustom05" required>--%>
<%--            <div class="invalid-feedback">--%>
<%--                Please provide a valid zip.--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-12">--%>
<%--            <div class="form-check">--%>
<%--                <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>--%>
<%--                <label class="form-check-label" for="invalidCheck">--%>
<%--                    Agree to terms and conditions--%>
<%--                </label>--%>
<%--                <div class="invalid-feedback">--%>
<%--                    You must agree before submitting.--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="col-12">
            <button class="btn btn-primary" id="submitButton" type="submit">Submit form</button>
            <button class="btn btn-info" id="resetButton" type="reset">Reset</button>
        </div>
    </form>
</div>

<%--<form name="form" action="RegistrationServlet" method="post" onsubmit="return validate()">--%>
<%--    <table align="center">--%>
<%--        <tr>--%>
<%--            <td>First Name</td>--%>
<%--            <td><label>--%>
<%--                <input type="text" name="first-name" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Last Name</td>--%>
<%--            <td><label>--%>
<%--                <input type="text" name="last-name" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Date of birth</td>--%>
<%--            <td><label>--%>
<%--                <input type="date" name="date-of-birth" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Gender</td>--%>
<%--            <td>--%>
<%--                <input type="radio" id="male-radio" name="gender" value="male" />--%>
<%--                <label for="male-radio">Male</label>--%>
<%--                <input type="radio" id="female-radio" name="gender" value="female" />--%>
<%--                <label for="female-radio">Female</label>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Telephone number</td>--%>
<%--            <td><label>--%>
<%--                <input type="text" name="telephone-number" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Email</td>--%>
<%--            <td><label>--%>
<%--                <input type="email" name="email" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Password</td>--%>
<%--            <td><label>--%>
<%--                <input type="password" name="password" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Confirm password</td>--%>
<%--            <td><label>--%>
<%--                <input type="password" name="confirm-password" />--%>
<%--            </label></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><%=(request.getAttribute("errMessage") == null) ? ""--%>
<%--                    : request.getAttribute("errMessage")%></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td>--%>
<%--                <input type="submit" value="Register">--%>
<%--                <input type="reset" value="Reset"></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>

<script type="text/javascript">
    $(function() {
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            startDate: "-150y",
            endDate: new Date(),
            todayHighlight: true,
            toggleActive: true
        });

        $(document).ready(function() {
            $("#firstName").focus();
        });
    });

    $("#resetButton").click(function() {
        $("#firstName").focus();
    });

    $("#submitButton").click(function () {
        // const datepicker = $("#datepicker");
        // datepicker.prop('readonly', false);
        'use strict'

        const forms = document.querySelectorAll(".needs-validation");
        Array.from(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });

        // datepicker.prop('readonly', true);
    })();






    // $("#datepicker .datepicker-switch").click(function() {
    //     console.log($("#datepicker .datepicker-switch").css("visibility"));
    //     $(".next .disabled").css({
    //         "background-color": "#2130ab !important"
    //     });
    //     $(".prev .disabled").css({
    //         "background-color": "#2130ab !important"
    //     });
    //     // $(".datepicker-switch").css({
    //     //     paddingRight: "40px"
    //     // });
    // });

    // if ($(".next .disabled").css("visibility") === "hidden")
    //     console.log(true);
    //     $(function() {
    //         $(".next .disabled").css({
    //             "background-color": "#2130ab !important"
    //         });
    //         // $(".datepicker-switch").css({
    //         //     paddingRight: "40px"
    //         // });
    //     });
</script>
</body>
</html>
