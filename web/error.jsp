<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 18.11.2019
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<html lang="pl-PL">

<head>
    <title>Szczegóły błędu/wyjątku</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/errorStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>

<body>

<div id="container">
    <div id="errorPanel">ERROR PAGE</div>
    <div id="contentError">
<c:choose>
        <c:when test="${kodBledu ==404}">
        <h3>Wystąpił błąd w aplikacji !</h3><br/>
        <strong>Kod błędu</strong>: ${kodBledu} <br/>
        <strong>Nie znaleziono pliku</strong>: ${Uri} <br/>
        </c:when>
    <c:otherwise>
        <h3>Wystąpił wyjątek w aplikacji !</h3>
        <ul><li><strong>Treść</strong>:</br> ${tresc}</li>
        <li><strong>Nazwa wyjątku</strong>:</br> ${nazwa}</li>
        <li><strong>Funkcja stwarzająca wyjątek</strong>:</br> ${funkcja}</li>
        <li><strong>Klasa z wyjątkiem</strong>:</br> ${klasa}</li>
        <li><strong>Dodatkowe wiadomości</strong>:</br> Więcej informacji na temat wyjątku znajduje się w konsoli administratora.</li>
        </ul>
    </c:otherwise>
</c:choose>
    </div>
    <div id="adresMail">
        Skontaktuj się z administratorem aplikacji.<br/>
        MAIL:
        <h1>admin.elektwin@gmail.com</h1>
    </div>
    <div id="homeButtonDiv">
        <p id="textHome">Powrót na stronę główną</p>
        <a href=index.jsp id="homeButton"> <img src="Images/canhome1.png" alt="Przenosi na stronę główną" width="70" height="70" id="przyciskHome"></a>
    </div>
<br/>
</div>
</body>
</html>