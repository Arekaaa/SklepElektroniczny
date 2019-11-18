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
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/errorStyle.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>

<body>
<c:choose>
        <c:when test="${kodBledu ==404}">
        <h3>Wystąpił błąd w aplikacji !</h3><br/>
        <strong>Kod błędu</strong>: ${kodBledu} <br/>
        <strong>Nie znaleziono pliku</strong>: ${Uri} <br/>
        </c:when>
    <c:otherwise>
        <h3>Wystąpił wyjątek w aplikacji !</h3>
        <ul><li><strong>Treść</strong>: ${tresc}</li>
        <li><strong>Nazwa wyjątku</strong>: ${nazwa}</li>
        <li><strong>Funkcja stwarzająca wyjątek</strong>: ${funkcja}</li>
        <li><strong>Klasa z wyjątkiem</strong>: ${klasa}</li>
        <li><strong>Dodatkowe wiadomości</strong>: Więcej informacji na temat wyjątku znajduje się w konsoli administratora.</li>
        </ul>
    </c:otherwise>
</c:choose>
<br><br/>
Skontaktuj się z administratorem aplikacji.<br/>
Mail:kaktus@gmail.com
<div id="homeButtonDiv">
    <p id="textHome">Powrót na stronę główną</p>
    <a href=index.jsp id="homeButton"> <img src="Images/home.png" alt="Przenosi na stronę główną" width="60" height="60" id="przyciskHome"></a></div>
<br/>
</body>
</html>