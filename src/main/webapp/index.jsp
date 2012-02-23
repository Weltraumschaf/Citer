<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <title>Citer</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
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

            <a href="${pageContext.request.contextPath}/api">API</a>
            <a href="${pageContext.request.contextPath}/apitest.jsp">API-Test</a>

            <div id="menu"></div>
            <div id="form"></div>
            <div id="cite"></div>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jtemplates.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.rater.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ninjaui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.asm.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.citer.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/ejs_production.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>