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
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/loginStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>
<body>
<h1>Panel rejestracji</h1>
<div id="registerForm">
    <form action="${pageContext.request.contextPath}/register" method="get">
        <div id="labele">
            <label for="loginR">Login:</label>
            <input type="text" name="rLogin" id="loginR" required/><br><br/>
            <label for="hasloR">Hasło:</label>
            <input type="password" name="rHaslo" id="hasloR" required/><br><br/>
            <label for="hasloRepeat">Powtórz hasło:</label>
            <input type="password" name="rHasloRepeat" id="hasloRepeat" required/><br><br/>
            <label for="imie">Imię:</label>
            <input type="text" name="rImie" id="imie" required/><br><br/>
            <label for="nazwisko">Nazwisko:</label>
            <input type="text" name="rNazwisko" id="nazwisko" required/><br><br/>
            <label for="mail">Adres e-mail:</label>
            <input type="email" name="rMail" id="mail" required/></div><br/>
            <style type="text/css">
            #loginR, #hasloR,#hasloRepeat,#imie,#nazwisko,#mail {
                outline: <%= request.getAttribute("fieldAlert") %>; <%--//pola nie moga byc puste--%>
            }
            #hasloR,#hasloRepeat{
                outline: <%= request.getAttribute("passwordAlertField") %>; <%-- hasla musza byc takie same--%>
            }
            #mail{
                outline: <%= request.getAttribute("mailAlertField") %>; <%-- mail nie poprawny--%>
            }
            </style>

            <input type="submit" value="Zarejestruj" id="rejestrujButton"><br/>
        <style type="text/css">
            h2{
            color:<%=request.getAttribute("alertColor") %>; }
        </style>
          <h2>${alertMail}<br/>${alert}<br/> ${alert1}<br/> ${daneLogowania} <br/> ${daneLogowania1}</h2><%-- <h3>${passwordAlert} </h3><br/> --%>

    </form>

        <p>Wróć na stronę logowania </p>
<a href=login.jsp> <img src="Images/return.png" alt="Przenosi na poprzednią stronę" width="60" height="60" id="returnImage"></a>

</div>
<div id="homeButtonDiv">
    <p id="textHome">Powrót na stronę główną</p>
    <a href=index.jsp id="homeButton"> <img src="Images/home.png" alt="Przenosi na stronę główną" width="60" height="60" id="przyciskHome"></a></div>
<br/>
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
