
<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 07.11.2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="beans.UserBean"
%>

<!DOCTYPE html>
<html>
<html lang="pl-PL">

<head>
    <title>Sklep Elektroniczny</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/mainStyle.css" type="text/css">
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/productStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>

<br>
<div id="loginButtonDiv">
<p>Witaj: ${powitanie}</p>
<br/>
<a href =index.jsp id="loginButton"> Wyloguj się</a> </div>
<img src="Images/logoSklep.png" alt="Logo sklepu elektronicznego" width="850" height="200" id="logo">
<br><br><br/>

<h1>Spis produktów sklepu: </h1>
<p>Sklep posiada: ${iloscProduktow} produkty/ów</p>
<div id="spisForm">
    <form action="${pageContext.request.contextPath}/addProduct.jsp" method="get">
        <input type="submit" value="Dodaj produkt" id="addProductButton"></form>

    <p>Wyszukaj produkt:</p>
    <form action="${pageContext.request.contextPath}/searchProduct" method="get">
    <label for="typWyszukiwania" id="labelTypWyszukiwania">Kategoria wyszukiwania: </label>
    <select name="typWyszukiwania" id="typWyszukiwania" size="2" required>
        <option value ="Nazwa">Nazwa produktu</option>
        <option value ="Producent">Producent</option>
    </select>
    <input type="submit" value="Wyszukaj" id="szukajButton">
    <label for="szukaj" id="labelSzukaj">Wprowadź wyszukiwaną frazę: </label>
    <input type="text" name="szukaj" id="szukaj" required>
    </form>
    <p></p>

    <table  align="center" border="1" style="background-color: #a6bfd9;">
        <div id="refreshForm">
        <form action="${pageContext.request.contextPath}/showProduct" method="get">
        <input type="submit" value="Wczytaj bazowe produkty" id="refreshButton"></form></div>
        <tr>
            <th>ID</th>
            <th>Nazwa</th>
            <th>Producent</th>
            <th>Cena(zł)</th>
            <th>Ilość<br/>(w sztukach)</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${listaProduktow}" var="produkt">
            <tr>
            <td align="center">${produkt.id}</td>
            <td align="center">${produkt.nazwa}</td>
            <td align="center">${produkt.producent}</td>
            <td align="center">${produkt.cena}</td>
            <td align="center">${produkt.ilosc}</td>
            <td><a href="${pageContext.request.contextPath}/editProduct?productID=<c:out value='${produkt.id}'/>" id="edytuj">Edytuj</a></td>
            <td> <a href="${pageContext.request.contextPath}/removeProduct?productID=<c:out value='${produkt.id}'/>" id="usun" onclick="return confirm('Usunąć produkt z bazy?')">Usuń</a> </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
