"use strict";

(function ($) {
    var serviceUrl = window.location.href.replace(/apitest.jsp/i, 'api/');

    module("API-Test");

    test("GET /api/cite", function() {
        ok(false, "Not implemented yet!");
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "cite",
            dataType: 'json',
            cache:    false,
            success:   function (XMLHttpRequest, textStatus, errorThrown) {
            },
            error:     function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });

    test("PUT /api/cite", function() {
        ok(false, "Not implemented yet!");
        $.ajax({
            type:     "PUT",
            url:      serviceUrl + "cite",
            dataType: 'json',
            cache:    false,
            success:   function (XMLHttpRequest, textStatus, errorThrown) {
            },
            error:     function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });

    test("PUT /api/cite/{id}", function() {
        ok(false, "Not implemented yet!");
        $.ajax({
            type:     "PUT",
            url:      serviceUrl + "cite/" + id,
            dataType: 'json',
            cache:    false,
            success:   function (XMLHttpRequest, textStatus, errorThrown) {
            },
            error:     function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });

    test("DELETE /api/cite/{id}", function() {
        ok(true);
    });

    test("GET /api/cite/{id}/originator", function() {
        ok(true);
    });

    test("GET /api/cite/random", function() {
        ok(true);
    });

    test("GET /api/originator/", function() {
        ok(true);
    });

    test("PUT /api/originator/", function() {
        ok(true);
    });

    test("PUT /api/originator/{id}", function() {
        ok(true);
    });

    test("GET /api/originator/{id}", function() {
        ok(true);
    });

    test("DELETE /api/originator/{id}", function() {
        ok(true);
    });

    test("GET /api/originator/{id}/cites", function() {
        ok(true);
    });

})(jQuery);