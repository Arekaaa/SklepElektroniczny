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
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/productStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>
<body>
    <div id="headline">
        NOWY PRODUKT
    </div>

    <div id="addForm">
        <form action="${pageContext.request.contextPath}/addProduct" method="get">
        <div id="labele">
            <input type="text" name="nazwa" class="fieldLabel" placeholder="nazwa towaru" required/><br>
            <input type="text" name="producent" class="fieldLabel" placeholder="producent" required/><br>
            <input type="number" min=0.01 step=0.01 name="cena" class="fieldLabel" placeholder="cena" required/><br>
            <input type="number" min=1 name="ilosc" class="fieldLabel" placeholder="ilosc" required/><br>
        </div>

        <div id="addButton">
        <input type="submit" value="Dodaj produkt" id="dodajButton"><br/>
        <style type="text/css">
            h2{
                color:<%=request.getAttribute("alertColor") %>; }
        </style>
        <h2>${alert}</h2>
        </div>
    </form>

        <div id="bottomLine">
            <p>Wróć na poprzednią stronę </p>
            <form action="${pageContext.request.contextPath}/showProduct?sortID=0" method="get">
                <input type="image"  src="Images/return.png" id="goBack" alt="Przenosi na poprzednią stronę" width="50" height="50">
            </form>
        </div>

</div>

</body>
</html>