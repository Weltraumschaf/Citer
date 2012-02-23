<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>API Test</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/qunit.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
        <style type="text/css">
            html {
                overflow-y: scroll;
            }

            body {
                font: 14px/1.231 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif;
                margin: 1em auto;
                padding: 0 1em;
                max-width: 800px;
                min-width: 640px;
            }

            h1, h2, h3, h4, h5, h6 {
                font-family: 'Myriad Pro', Verdana, Arial, Helvetica, sans-serif;
                margin: 1em 0 0 0;
            }
        </style>
    </head>
    <body>
        <!-- dependencies -->
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>

        <h1 id="qunit-header" style="margin-top: 1em;">API-Tests</h1>
        <h2 id="qunit-banner"></h2>

        <div id="qunit-testrunner-toolbar"></div>
        <ol id="qunit-tests"></ol>
        <div id="qunit-fixture">test markup, will be hidden</div>

		<!-- test framework -->
        <script src="${pageContext.request.contextPath}/js/qunit.js"></script>
		<!-- the tests itself -->
        <script src="${pageContext.request.contextPath}/js/apitest.js"></script>
    </body>
</html>
