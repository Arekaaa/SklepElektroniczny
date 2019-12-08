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
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/CSS/mainStyl.css" type="text/css">
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
  </head>

  <body>
  <div id="container">
      <div id="panelBar">
          ELEKTWIN
      </div>

      <div id="content">
          <div id="topBar">
              <img src="Images/logocanva2icon.png" alt="Logo sklepu elektronicznego">
          </div>
          <div id="lineBar"></div>
          <div id="bottomBar">
              <h1>Witaj na stronie głównej aplikacji wspomagającej sklep elektroniczny.<br/>
                  Zaloguj się aby rozpocząć.
              </h1>
          </div>
          <div style="clear:both;"></div>

         <div id="loginButtonDiv">
            <a href =login.jsp id="loginButton"> Zaloguj się</a>
         </div>
      </div>

        <div id="footer">
            This page was created by @ArkadiuszPajor and @MateuszOrnat.<br> &copy All rights reserved.
        </div>

  </div>
  </body>
</html>
