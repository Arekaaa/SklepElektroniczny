<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 07.11.2019
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<html lang="pl-PL">
<head>
    <title>Zarejestruj się</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/registerStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>
<body>
<div id="container">

    <div id="panelBar">REJESTRACJA</div>
    <div id="lineLogBar"></div>


    <div id="registerForm">
        <form action="${pageContext.request.contextPath}/register" method="get">

            <div id="labelBar">
                <input type="text" name="rLogin" class="field" placeholder="login" required/><br><br/>
                <input type="password" name="rHaslo" class="field" placeholder="password" required/><br><br/>
                <input type="password" name="rHasloRepeat" class="field" placeholder="repeat password" required/><br><br/>
                <input type="text" name="rImie" class="field" placeholder="name" required/><br><br/>
                <input type="text" name="rNazwisko" class="field" placeholder="surname" required/><br><br/>
                <input type="email" name="rMail" class="field" placeholder="e-mail" required/>
            </div>
            <br/>

            <div id="buttony">
                <div id="rejestruj">
                <input type="submit" value="Zarejestruj" id="rejestrujButton"><br/>
                </div>

                <div id="wroc">
                    <a href=login.jsp> <input type="button" value="Powrót" id="wrocLog"> </a>
                </div>
            </div>
        <style type="text/css">
            h2{
            color:<%=request.getAttribute("alertColor") %>; }
        </style>
          <h2>${alertMail}
              ${alert}
              ${alert1}
              ${daneLogowania}
              ${daneLogowania1}</h2><%-- <h3>${passwordAlert} </h3><br/> --%>

    </form>

    <div id="panelBottomLine"></div>
    </div>
    <div id="fieldEmpty"></div>

</div>
</div>
</body>
<script>
    var password = document.getElementById("hasloR"), confirm_password = document.getElementById("hasloRepeat");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Wpisane hasło rózni się!");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</html>
