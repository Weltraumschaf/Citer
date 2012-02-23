/*
 * main.js
 */
(function($) {
//    $(function() {
//        var $randomCite = $("#randomCite").hide(),
//            $btnReloadCite = $.ninja.button({
//                html: "next cite"
//            }),
//            $btnAddCite = $.ninja.button({
//                html: "add new cite"
//            }),
//            $dlgCiteForm = $.ninja.dialog({
//                // http://embeddedjs.com/
//                html: new EJS({url: 'ejs/citeform.ejs'}).render()
//            });
//
//        $btnReloadCite.select(function() {
//            $randomCite .fadeOut();
//            $btnReloadCite.deselect();
//
//            $.getJSON("api/randomcite", function(data, textStatus, jqXHR) {
//                var cite = '<cite>' + data.cite.text + '</cite>';
//                cite += '(' + data.cite.creator.name + ')';
//                $randomCite.stop(true, true)
//                           .empty()
//                           .html(cite)
//                           .fadeIn();
//            });
//        });
//
//        $("#dlgCiteForm").append($dlgCiteForm).hide();
//        $("#btnReloadCite").append($btnReloadCite.click());
//        $("#btnAddCite").append($btnAddCite);
//        $btnAddCite.select(function() { $dlgCiteForm.attach(); });
//        $dlgCiteForm.detach(function() { $btnAddCite.deselect(); });
//    });

    var serviceUrl = window.location.href.replace(/index.jsp/i, "") + "api/";
console.log(serviceUrl);
    $(function() {
        var $cite = $('#cite');
        $.citer({serviceUrl: serviceUrl});
        $cite.citer('random');
        $('#form').citer('submit');
        $('#menu').append($.ninja.tabs({
            choices: [{
                html: 'zuf&auml;lliges Zitat',
                select: function () { $cite.citer('random'); }
            }, {
                html: 'bestes Zitat',
                select: function () { $cite.citer('best'); }
            }, {
                html: 'neustes Zitat',
                select: function () { $cite.citer('latest'); }
            }, {
                html: 'alle Zitate',
                select: function () { $cite.citer('all'); }
            }],
            choice: 0
        }));
        $('.mailadress').asm({
            alias: 'ich',
            domain: 'weltraumschaf.de'
        });
    });
}(jQuery));