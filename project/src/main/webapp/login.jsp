<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="main_div">
    <div class="title">Login</div>
    <div class="social_icons">
        <a href="#"><i class="fab fa-google"></i><span>Google</span></a>
        <a href="#"><i class="fab fa-facebook-f"></i><span>Facebook</span></a>
        <a href="#"><i class="fab fa-twitter"></i><span>Twitter</span></a>
    </div>
    <form action="#">
        <div class="input_box">
            <label>
                <input type="email" placeholder="Email" required>
            </label>
            <div class="icon"><i class="fas fa-user"></i></div>
        </div>
        <div class="input_box">
            <label>
                <input type="password" placeholder="Password" required>
            </label>
            <div class="icon"><i class="fas fa-lock"></i></div>
        </div>
        <div class="option_div">
            <div class="check_box">
                <label>
                    <input type="checkbox">
                </label>
                <span>Remember me</span>
            </div>
            <div class="forget_div">
                <a href="#">Forgot password?</a>
            </div>
        </div>
        <div class="input_box button">
            <input type="submit" value="Login">
        </div>
        <div class="sign_up">
            Not a member? <a href="register.jsp">Sign up now</a>
        </div>
    </form>
</div>

</body>
</html>