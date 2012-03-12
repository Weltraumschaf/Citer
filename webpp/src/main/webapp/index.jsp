<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <title>Citer</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.fancybox.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.rater.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    </head>
    <body>
        <div id="footer">
            <p><small>Gebaut vom <a href="http://www.weltraumschaf.de">Weltraumschaf</a>.
            F&uuml;r Anregungen und Bug-Meldungen einfach eine
            <a id="mailadress" title="E-Mail">ich[at]weltraum+NOSPAM+schaf[dot]de</a>
            schreiben.</small></p>
        </div>

        <div id="centeredContainer">
            <div id="logo">
                <h1><span>Citer</span></h1>
            </div>

            <p>
                <a id="nextCite" href="javascript:;">nächstes</a> &ndash;
                <a id="submitCite" href="#citeForm">eintragen</a> &ndash;
                <a href="${pageContext.request.contextPath}/api">API</a> &ndash;
                <a href="${pageContext.request.contextPath}/apitest.jsp">API-Test</a>
            </p>

            <div id="content"></div>
        </div>

        <div id="fancy">
            <div id="citeForm">
                <h2>Zitat eintragen</h2>
                <form>
                    <p>
                        <label for="name">Urheber:</label>
                        <input id="name" type="text"/>
                    </p>
                    <p>
                        <label for="text">Zitat:</label>
                        <textarea id="text"></textarea>
                    </p>
                    <p>
                        <input type="submit"/>
                    </p>
                </form>
            </div>
        </div>

        <script id="citeTpl" type="text/x-handlebars-template">
            <h2>zufülliges Zitat</h2>
            <p>
                <strong>{{originator.name}}</strong><br />
                <cite>{{text}}</cite><br />
                <small>(einetragen am: {{date dateCreated}}</small>
            </p>
        </script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jtemplates.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.rater.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.amail.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easing.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.fancybox.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/handlebars.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>