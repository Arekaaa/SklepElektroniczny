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
<h1>Panel logowania</h1>
<div id="loginForm">
    <form action="${pageContext.request.contextPath}/login" method="get">
        <div id="labele">
         Podaj swój login:
            <input type="text" name="login" id="login"/><br><br/>
         Podaj swoje hasło:
            <input type="password" name="haslo" id="haslo"/></div><br/>

        <style type="text/css">
            #login, #haslo {
                outline: <%= request.getAttribute("fieldAlert") %>;
            }
        </style>

        <input type="submit" value="Zaloguj" id="zalogujButton"><br/>
        <h2>${alert} </h2> <h3>${registerAlert}</h3>

    </form>
    <form action="register.jsp" method="get">
        <p>Nie posiadasz konta ?</p>
        <input type="submit" value="Zarejestruj się" id="rejestrujButton">
    </form>
</div>
<div id="homeButtonDiv">
    <p id="textHome">Powrót na stronę główną</p>
    <a href=index.jsp id="homeButton"> <img src="Images/home.png" alt="Przenosi na stronę główną" width="60" height="60" id="przyciskHome"></a></div>
<br/>


</body>
</html>
