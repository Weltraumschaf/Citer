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
                var html = "<h2>Zufülliges Zitat</h2>";

                response.uri = serviceUrl + 'cite/' + response.id;
                html += citeTpl(response);
                $("#content").html(html);
            },
            error:   function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });

        if (event) {
            event.preventDefault();
            event.stopPropagation();
        }
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
                var html = "<h2>Neues Zitat</h2>";
                html += citeTpl(response);
                $("#content").html(html);
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
            success: randomCite,
            error:   function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });
        event.preventDefault();
        event.stopPropagation();
    }

    function allCites(event) {
        $.ajax({
            type:   'GET',
            url:    serviceUrl + 'cite',
            success: function(response) {
                var i, l, html = "<h2>Alle Zitate</h2>";

                for (i = 0, l = response.length; i < l; ++i) {
                    response[i].uri = serviceUrl + 'cite/' + response[i].id;
                    html += citeTpl(response[i]);
                }

                $("#content").html(html);
            },
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
        $('#allCites').click(allCites);
        $('#centeredContainer').delegate('a.deleteCite', 'click', deleteCite);
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