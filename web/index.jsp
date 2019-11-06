<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 06.11.2019
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<html lang="pl-PL">

  <head>
    <title>Sklep Elektroniczny</title>
    <meta name="author" content="Arkadiusz Pajor, Mateusz Ornat">
    <meta name="description" content="Projekt studencki dotyczący aplikacji webowej wspierającej sklep elektroniczny">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="Stylesheet" href="CSS/mainStyle.css" type="text/css">
  </head>

  <body>
  <div id="loginButtonDiv">
    <a href =login.jsp id="loginButton"> Zaloguj się</a> </div>
 <img src="Images/logoSklep.png" alt="Logo sklepu elektronicznego" width="850" height="200" id="logo">
  <br><br><br/>
  <h1>Witaj na stronie głównej aplikacji wspomagającej sklep elektroniczny.<br/>
  Zaloguj się aby rozpocząć.
  </h1>
  <%--<a href="${pageContext.request.contextPath}/login"> Zaloguj Się</a>  Do odwołania się do servletu --%>

<div id="footer">
    Autorzy aplikacji:<br></br>
        Arkadiusz Pajor<br>
        Mateusz Ornat
    </div>
  </body>
</html>
