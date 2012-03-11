/*
 * main.js
 */
(function($) {
    var serviceUrl = window.location.href.replace(/index.jsp/i, "") + "api/";

    $(function() {
        var $cite = $('#cite');
        $.citer({serviceUrl: serviceUrl});
        $cite.citer('random');
        $('#form').citer('submit');
        $('#mailadress').amail();
    });
}(jQuery));