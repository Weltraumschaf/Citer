<%@page import="java.util.Enumeration"%>
<%@page import="de.weltraumschaf.citer.domain.Cite"%>
<%@page import="de.weltraumschaf.citer.domain.Factory"%>
<%@page import="de.weltraumschaf.citer.domain.Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
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

<!--        <section>
            <form>
                <label for="name">name:</label> <input id="name" type="text"/><br/>
                <label for="cite">cite:</label> <textarea id="cite">type here</textarea><br/>
                <input type="submit"/>
            </form>
        </section>-->

<article>
    <%! Data model = Factory.getModel();%>
    <%! Cite cite = model.getRandomCite();%>
    <cite><%= cite.getText()%></cite> (<%= cite.getCrator().getName()%>)
</article>

<footer>
    Presented by Weltraumschaf
</footer>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.ninjaui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</body>
</html>