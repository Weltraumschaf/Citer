
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns="http://www.w3.org/1999/xhtml"
        version="2.0">
    <jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
    <jsp:output omit-xml-declaration="true" />
    <jsp:output doctype-root-element="HTML" doctype-system="about:legacy-compat" />

    <html lang="en">
        <head>
            <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
            <title>Citer</title>
            <link rel="stylesheet" type="text/css" href="css/main.css"/>
        </head>
        <body>
            <header>
                <h1>Citer</h1>
            </header>

            <nav>
                <a href="javascript:;">random cite</a> -
                <a href="javascript:;">best cite</a> -
                <a href="javascript:;">newest cite</a> -
                <a href="javascript:;">all cites</a>
            </nav>

            <section>
                <form>
                    <label for="name">name:</label> <input id="name" type="text"/><br/>
                    <label for="cite">cite:</label> <textarea id="cite">type here</textarea><br/>
                    <input type="submit"/>
                </form>
            </section>

            <article>
                Quisque tempor interdum purus, ut condimentum sem facilisis sit amet. Aenean imperdiet,
                ligula ut molestie volutpat, risus purus vestibulum velit.
            </article>

            <footer>
                Presented by Weltraumschaf
            </footer>

            <script lang="text/javascript" src="js/jquery.js"></script>
            <script lang="text/javascript" src="js/main.js"></script>
        </body>
    </html>
</jsp:root>