/*
 * main.js
 */
(function($) {
    $(function() {
        var $randomCite = $("#randomCite").hide(),
            $btnReloadCite = $.ninja.button({
                html: "next cite"
            }),
            $btnAddCite = $.ninja.button({
                html: "add new cite"
            }),
            $dlgCiteForm = $.ninja.dialog({
                // http://embeddedjs.com/
                html: new EJS({url: 'ejs/citeform.ejs'}).render()
            });

        $btnReloadCite.select(function() {
            $randomCite .fadeOut();
            $btnReloadCite.deselect();

            $.getJSON("api/randomcite", function(data, textStatus, jqXHR) {
                var cite = '<cite>' + data.cite.text + '</cite>';
                cite += '(' + data.cite.creator.name + ')';
                $randomCite.stop(true, true)
                           .empty()
                           .html(cite)
                           .fadeIn();
            });
        });

        $("#dlgCiteForm").append($dlgCiteForm).hide();
        $("#btnReloadCite").append($btnReloadCite.click());
        $("#btnAddCite").append($btnAddCite);
        $btnAddCite.select(function() { $dlgCiteForm.attach(); });
        $dlgCiteForm.detach(function() { $btnAddCite.deselect(); });
    });
}(jQuery));