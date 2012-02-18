<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <title>Citer</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.requestURL}css/main.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.requestURL}img/favicon.ico"/>
    </head>
    <body>
        <div id="footer">
            <p><small>Gebaut vom <span class="mailadress">Weltraumschaf</span>.
            F&uuml;r Anregungen und Bug-Meldungen einfach eine <span class="mailadress">
            E-Mail</span> schreiben.</small></p>
        </div>

        <div id="centeredContainer">
            <div id="logo">
                <h1><span>Citer</span></h1>
            </div>

            <a href="${pageContext.request.requestURL}api">api</a>

            <div id="menu"></div>
            <div id="form"></div>
            <div id="cite"></div>
        </div>

        <script type="text/javascript" src="${pageContext.request.requestURL}js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/jquery.jtemplates.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/jquery.rater.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/jquery.ninjaui.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/jquery.asm.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/jquery.citer.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/ejs_production.js"></script>
        <script type="text/javascript" src="${pageContext.request.requestURL}js/main.js"></script>
    </body>
</html>