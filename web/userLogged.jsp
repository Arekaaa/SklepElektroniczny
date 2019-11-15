<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 07.11.2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<body>
<br>
<div id="loginButtonDiv">
<p>Witaj: ${powitanie}</p>
<br/>
<a href =index.jsp id="loginButton"> Wyloguj się</a> </div>
<img src="Images/logoSklep.png" alt="Logo sklepu elektronicznego" width="850" height="200" id="logo">
<br><br><br/>

<h1>Spis produktów sklepu: </h1>
<p>Sklep posiada: ${iloscProduktow} produkty/ów, które są warte: ${kwotaProduktow} zł.</p>
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
    <div id="refreshForm">
        <form action="${pageContext.request.contextPath}/showProduct" method="get">
            <input type="submit" value="Wczytaj wszystkie produkty" id="refreshButton"></form>
    </div><br/>

    <table  align="center" border="1" style="background-color: #a6bfd9;">
        <tr>
            <th colspan="7" scope="colgroup">Tabela Produktów</th>
        </tr>
            <tr>
                <th>ID<br><br/>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Id&descOrAsc=ASC" >
                    <img src="Images/arrow-up.png" id="ascId" alt="Sortuje rosnąco ID"></a>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Id&descOrAsc=DESC" >
                    <img src="Images/arrow-down.png" id="descId" alt="Sortuje malejąco ID"></a>
                </th>
                <th>Nazwa<br><br/>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Id&descOrAsc=ASC" >
                    <img src="Images/arrow-up.png" id="ascNazwa" alt="Sortuje rosnąco nazwę"></a>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Nazwa&descOrAsc=DESC" >
                    <img src="Images/arrow-down.png" id="descNazwa" alt="Sortuje malejąco nazwę"></a>
                </th>
                <th>Producent<br><br/>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Id&descOrAsc=ASC" >
                    <img src="Images/arrow-up.png" id="ascProducent" alt="Sortuje rosnąco producenta"></a>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Producent&descOrAsc=DESC" >
                    <img src="Images/arrow-down.png" id="descProducent" alt="Sortuje malejąco producenta"></a>
                </th>
                <th>Cena(zł)<br><br/>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Id&descOrAsc=ASC" >
                    <img src="Images/arrow-up.png" id="ascCena" alt="Sortuje rosnąco cenę"></a>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Cena&descOrAsc=DESC" >
                    <img src="Images/arrow-down.png" id="descCena" alt="Sortuje malejąco cenę"></a>
                </th>
                <th>Ilość<br/>(w sztukach)<br/>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Id&descOrAsc=ASC" >
                    <img src="Images/arrow-up.png" id="ascIlosc" alt="Sortuje rosnąco ilością"></a>
                    <a href="${pageContext.request.contextPath}/sortProduct?typ=<c:out value='${typWyszukiwania}'/>&wartosc=<c:out value='${wprowadzonaWartosc}'/>&metoda=<c:out value='${metoda}'/>&sortID=Ilosc&descOrAsc=DESC" >
                    <img src="Images/arrow-down.png" id="descIlosc" alt="Sortuje malejąco ilością"></a>
                </th>
                <th colspan="2"></th>
            </tr>
        <c:forEach items="${listaProduktow}" var="produkt">
            <tr>
            <td align="center">${produkt.id}</td>
            <td align="center">${produkt.nazwa}</td>
            <td align="center">${produkt.producent}</td>
            <td align="center">${produkt.cena}</td>
            <td align="center">${produkt.ilosc}</td>
            <td><a href="${pageContext.request.contextPath}/showByID?productID=<c:out value='${produkt.id}'/>" id="edytuj">Edytuj</a></td>
            <td> <a href="${pageContext.request.contextPath}/removeProduct?productID=<c:out value='${produkt.id}'/>" id="usun" onclick="return confirm('Usunąć produkt z bazy?')">Usuń</a> </td>
            </tr>
        </c:forEach>
    </table>
    <p>${pustyWynik}</p>
</div>
</body>
</html>
