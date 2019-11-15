<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 11.11.2019
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
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
<h1>Dodaj nowy produkt</h1>
<div id="addForm">
    <form action="${pageContext.request.contextPath}/addProduct" method="get">
        <div id="labele">
            <label for="nazwa">Nazwa:</label>
            <input type="text" name="nazwa" id="nazwa" required/><br><br/>
            <label for="producent">Producent:</label>
            <input type="text" name="producent" id="producent" required/><br><br/>
            <label for="cena">Cena(zł):</label>
            <input type="number" min=0.01 step=0.01 name="cena" id="cena" required/><br><br/>
            <label for="ilosc">Ilość(w sztukach):</label>
            <input type="number" min=1 name="ilosc" id="ilosc" required/><br><br/></div>


        <input type="submit" value="Dodaj produkt" id="dodajButton"><br/>
        <style type="text/css">
            h2{
                color:<%=request.getAttribute("alertColor") %>; }
        </style>
        <h2>${alert}</h2>
    </form>
    <p>Wróć na poprzednią stronę </p>
    <form action="${pageContext.request.contextPath}/showProduct?sortID=0" method="get">
        <input type="image"  src="Images/return.png" id="goBack" alt="Przenosi na poprzednią stronę">
    </form>

</div>
</body>
</html>