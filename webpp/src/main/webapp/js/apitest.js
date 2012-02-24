"use strict";

(function ($) {
    var serviceUrl = window.location.href.replace(/apitest.jsp/i, 'api/');

    module("API-Test");

    asyncTest("GET /api/cite", function() {
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "cite",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call!");
                start();
            }
        });
    });

    asyncTest("PUT /api/cite", function() {
        $.ajax({
            type:     "PUT",
            url:      serviceUrl + "cite",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("PUT /api/cite/{id}", function() {
        var id = "an-id";
        $.ajax({
            type:     "PUT",
            url:      serviceUrl + "cite/" + id,
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("DELETE /api/cite/{id}", function() {
        var id = "an-id";
        $.ajax({
            type:     "DELETE",
            url:      serviceUrl + "cite/" + id,
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("GET /api/cite/{id}/originator", function() {
        var id = "ab-id";
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "cite/" + id + "/originator",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("GET /api/cite/random", function() {
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "cite/random",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("GET /api/originator/", function() {
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "originator/",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("PUT /api/originator/", function() {
        $.ajax({
            type:     "PUT",
            url:      serviceUrl + "originator/",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("PUT /api/originator/{id}", function() {
        var id = "an-id";
        $.ajax({
            type:     "PUT",
            url:      serviceUrl + "originator/" + id,
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("GET /api/originator/{id}", function() {
        var id = "an-id";
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "originator/" + id,
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("DELETE /api/originator/{id}", function() {
        var id = "an-id";
        $.ajax({
            type:     "DELETE",
            url:      serviceUrl + "originator/" + id,
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

    asyncTest("GET /api/originator/{id}/cites", function() {
        var id = "an-id";
        $.ajax({
            type:     "GET",
            url:      serviceUrl + "originator/" + id + "/cites",
            dataType: 'json',
            cache:    false,
            success:   function (data, textStatus, xhr) {
                ok(true, "Service responded.");
                start();
            },
            error:     function (xhr, textStatus, errorThrown) {
                ok(false, "Can't perform Ajax call! " + textStatus + ": " + errorThrown);
                start();
            }
        });
    });

})(jQuery);