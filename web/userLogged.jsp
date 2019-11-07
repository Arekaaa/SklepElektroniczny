<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 07.11.2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
</head>

<body>
<div id="loginButtonDiv">
<p>Witaj: ${powitanie}</p>
<br/>
<a href =index.jsp id="loginButton"> Wyloguj się</a> </div>
<img src="Images/logoSklep.png" alt="Logo sklepu elektronicznego" width="850" height="200" id="logo">
<br><br><br/>

<h1>Tu będzie możliwa edycja produktów sklepu...</h1>
<%--<a href="${pageContext.request.contextPath}/login"> Zaloguj Się</a>  Do odwołania się do servletu --%>
</body>
</html>
