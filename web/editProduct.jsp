<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 13.11.2019
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<html lang="pl-PL">
<head>
    <title>Dodaj produkt</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/loginStyle.css" type="text/css">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/productStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>
<body>
<h1>Edytuj produkt</h1>
<div id="addForm">
    <form action="${pageContext.request.contextPath}/editProduct" method="get">
        <div id="labele">
            <c:forEach items="${produktById}" var="produkt">
            <input type="hidden" name="ID" value="${produkt.id}" />
            <label for="nazwa">Nazwa:</label>
            <input type="text" name="nazwa" id="nazwa" value="${produkt.nazwa}" required/><br><br/>
            <label for="producent">Producent:</label>
            <input type="text" name="producent" id="producent" value="${produkt.producent}" required/><br><br/>
            <label for="cena">Cena(zł):</label>
            <input type="number" min=0.01 step=0.01 name="cena" id="cena" value="${produkt.cena}"  required/><br><br/>
            <label for="ilosc">Ilość(w sztukach):</label>
            <input type="number" min=1 name="ilosc" id="ilosc" value="${produkt.ilosc}" required/><br><br/></div>
            </c:forEach>
        <input type="submit" value="Edytuj Produkt" id="dodajButton"><br/>
        <style type="text/css">
            h2{
                color:<%=request.getAttribute("alertColor") %>; }
        </style>
        <h2>${alert}</h2>
    </form>
    <p>Wróć na poprzednią stronę </p>
        <input type="image"  src="Images/return.png" id="goBack" onclick="history.back()" alt="Przenosi na poprzednią stronę">
</div>
</body>
</html>