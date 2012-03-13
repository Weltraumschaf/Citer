/*
 * main.js
 */
(function($) {
    var serviceUrl = window.location.href.replace(/index.jsp/i, "") + "api/",
        citeTpl    = Handlebars.compile($("#citeTpl").html());

    $.ajaxSetup({
        cache:       false,
        processData: false,
        dataType:    "json",
        contentType: "application/json"
    });

    Handlebars.registerHelper('date', function(timestamp) {
        var date = new Date(parseInt(timestamp, 10));

        return date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
    });

    function randomCite(event) {
        $.ajax({
            type:   'GET',
            url:    serviceUrl + 'cite/random',
            success: function(response) {
                response.uri = serviceUrl + 'cite/' + response.id;
                $("#content").html(citeTpl(response));
            },
            error:   function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });
        event.preventDefault();
        event.stopPropagation();
    }

    function submitCite(event) {
        $.ajax({
            type:   'PUT',
            url:    serviceUrl + 'cite',
            data:   JSON.stringify({
                name: $('#name').val(),
                text: $('#text').val()
            }),
            success: function(response) {
                $("#content").html(citeTpl(response));
                $.fancybox.close();
            },
            error:   function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });
        event.preventDefault();
        event.stopPropagation();
    }

    function deleteCite(event) {
        $.ajax({
            type:   'DELETE',
            url:    this.href,
            success: function(response) {},
            error:   function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });
        event.preventDefault();
        event.stopPropagation();
    }

    $(function() {
        $('#fancy').hide();
        $('#mailadress').amail();
        $('#nextCite').click(randomCite).click();
        $('#deleteCite').click(deleteCite);
        $('#submitCite').fancybox({
            'type':          'inline',
            'autoScale':          true,
            'autoDimensions':     false,
            'showCloseButton':    true,
            'transitionOut':      'none',
            'hideOnOverlayClick': false,
            'hideOnContentClick': false
        });
        $("form").submit(submitCite);
    });
}(jQuery));