
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
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/productStyle.css" type="text/css">
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
            <input type="submit" value="Wczytaj bazowe produkty" id="refreshButton"></form>
    </div><br/>

    <table  align="center" border="1" style="background-color: #a6bfd9;">
        <tr>
            <th colspan="7" scope="colgroup">Tabela Produktów</th>
        </tr>
        <tr>
            <th>ID<br><br/>
                <input type="image" src="Images/arrow-down.png" id="ascId" onclick=""  alt="Sortuje rosnąco ID">
                <input type="image" src="Images/arrow-up.png" id="descId"  onclick="" alt="Sortuje malejąco ID">
            </th>
            <th>Nazwa<br><br/>
                <input type="image" src="Images/arrow-down.png" id="ascNazwa" onclick="" alt="Sortuje rosnąco nazwę">
                <input type="image" src="Images/arrow-up.png" id="descNazwa"  onclick="" alt="Sortuje malejąco nazwę">
            </th>
            <th>Producent<br><br/>
                <input type="image" src="Images/arrow-down.png" id="ascProducent" onclick=""  alt="Sortuje rosnąco producenta">
                <input type="image" src="Images/arrow-up.png" id="descProducent"  onclick="" alt="Sortuje malejąco producenta">
            </th>
            <th>Cena(zł)<br><br/>
                <input type="image" src="Images/arrow-down.png" id="ascCena" onclick="" alt="Sortuje rosnąco cenę">
                <input type="image" src="Images/arrow-up.png" id="descCena"  onclick="" alt="Sortuje malejąco cenę">
            </th>
            <th>Ilość<br/>(w sztukach)<br/>
                <input type="image" src="Images/arrow-down.png" id="ascIlosc" onclick="" alt="Sortuje rosnąco ilość">
                <input type="image" src="Images/arrow-up.png" id="descIlosc"  onclick="" alt="Sortuje malejąco ilość">
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
