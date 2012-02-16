/*jslint browser: true */
/*global jQuery*/

/**
 *ASM = Anti Spam Mail
 */
(function($) {
    $.fn.asm = function() {
        var $this   = $(this),
            options = $.extend({}, $.fn.asm.defaults, arguments[0] || {});

        $this.each(function(index, item) {
            var mail  = '',
                $item = $(item);

            if (options.link) {
                mail += '<a href="mailto:' + options.alias + '@' + options.domain + '">';
            }

            if (0 === $item.text().length) {
                mail += options.alias + '@' + options.domain;
            } else {
                mail += $item.text();
            }

            if (options.link) {
                mail += '</a>';
            }

            $item.html(mail);
        });

        return $this;
    };

    $.fn.asm.defaults = {
        alias:  'foo',
        domain: 'bar.com',
        link:   true
    };
})(jQuery);