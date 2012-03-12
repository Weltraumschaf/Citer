/*
 * main.js
 */
(function($) {
    var serviceUrl = window.location.href.replace(/index.jsp/i, "") + "api/",
        citeTpl = Handlebars.compile($("#citeTpl").html());

    function randomCite() {
        $.ajax({
            type:     'GET',
            url:      serviceUrl + 'cite/random',
            data:     {},
            dataType: 'json',
            cache:    false,
            success:   function(response) {
                console.debug(response);
                $("#content").html(citeTpl(response));
            },
            error:     function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });
    }

    $(function() {
        $('#mailadress').amail();
        $('#nextCite').click(randomCite).click();
    });
}(jQuery));