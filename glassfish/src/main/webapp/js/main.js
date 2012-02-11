/*
 * main.js
 */
(function() {
    $(function() {
        var $btnReloadCite = $.ninja.button({
            html: "next cite"
        }).select(function() {
            $btnReloadCite.deselect();
            var $randomCite = $("#randomCite").hide();

            $.getJSON("api/randomcite", function(data, textStatus, jqXHR) {
                var cite = '<cite>' + data.cite.text + '</cite>';
                cite += '(' + data.cite.creator.name + ')';
                $randomCite.empty().html(cite).show();
            });
        });
        $("#btnReloadCite").append($btnReloadCite);
        $btnReloadCite.click();
    });
}());