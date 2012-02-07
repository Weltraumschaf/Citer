/*
 * main.js
 */
(function() {
    var $tabs = $.ninja.tabs({
        values: [
        {
            html: 'random cite',
            select: function () {
                console.log("random cite");
            }
        },
        {
            html: 'best cite',
            select: function () {
                console.log("best cite");
            }
        },
        {
            html: 'newest cite',
            select: function () {
                console.log("newest cite");
            }
        },
        {
            html: 'all cites',
            select: function () {
                console.log("all cites");
            }
        }
        ]
    });
    $("nav").append($tabs);
}());