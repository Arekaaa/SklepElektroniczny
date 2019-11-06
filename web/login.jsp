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
    <link rel="Stylesheet" href="CSS/loginStyle.css" type="text/css">
</head>

<body>
<h1>Panel logowania</h1>
<div id="loginForm">
<form action="${pageContext.request.contextPath}/login">

    Podaj swój login:
    <input type="text" name="login" id="login"/><br><br/>
    Podaj swoje hasło:
    <input type="password" name="haslo" id="haslo"/><br/>
    <input type="submit" value="Zaloguj" id="zalogujButton"><br><br/>
</form>
    <form action="RegisterController">
        <p>Nie masz konta ?</p>
        <input type="submit" value="Zarejestruj się" id="rejestrujButton">
    </form>
</div>
<div id="homeButtonDiv">
    <p id="textHome">Powrót na stronę główną</p>
    <a href=index.jsp id="homeButton"> <img src="Images/home.png" alt="Przenosi na stronę główną" width="60" height="60" id="przyciskHome"></a> </div>
<br/>



</body>
</html>
