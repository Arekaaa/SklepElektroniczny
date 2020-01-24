<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 06.11.2019
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<html lang="pl-PL">

<head>
    <title>Zaloguj się</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/loginStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>

<body>

<div id="container">

    <div id="logoBar">
        LOGOWANIE
    </div>
    <div id="lineBar"></div>

    <div id="loginFormBar">
        <form action="${pageContext.request.contextPath}/login" method="get">
            <div id="labelBar">
                <input type="text" name="login" id="login" placeholder="login" required/><br><br/>
                <input type="password" name="haslo" id="haslo" placeholder="password" required/>
            </div><br/>

        <input type="submit" value="Zaloguj" id="zalogujButton"><br/>
        <p>${alert} </p>
    </form>

        <form action="register.jsp" method="get">
            <p>Nie posiadasz konta ?</p>
            <input type="submit" value="Zarejestruj się" id="rejestrujButton">
        </form>
    </div>

    <div id="homeButtonDiv">
        <p id="textHome">Powrót na stronę główną</p>
        <a href=index.jsp id="homeButton"> <img src="Images/canhome1.png" alt="Przenosi na stronę główną" width="60" height="60" id="przyciskHome"></a>
    </div>
<br/>

</div>
</body>
</html>
