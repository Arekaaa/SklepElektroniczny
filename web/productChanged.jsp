<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 13.11.2019
  Time: 15:13
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
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/productStyle.css" type="text/css">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/loginStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>
<body>
<div id="productChanged">
        <style type="text/css">
            h3{
                color:<%=request.getAttribute("alertColor") %>; }
        </style>
        <h3>${alert}</h3>
    <br/>
    <p>Powrót </p>
    <form action="${pageContext.request.contextPath}/showProduct" method="get">
        <input type="image"  src="Images/return.png" id="goBack" alt="Przenosi na poprzednią stronę">
    </form>
</div>
</body>
</html>