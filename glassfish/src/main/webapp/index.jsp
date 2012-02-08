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

        <nav></nav>

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

        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.ninjaui.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>