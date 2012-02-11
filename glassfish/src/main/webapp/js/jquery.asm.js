/*jslint browser: true */
/*global jQuery*/

/**
 *ASM = Anti Spam Mail
 */
(function($) {
    $.fn.asm = function() {
        var arg   = arguments,
            $this = $(this),
            opts  = $.extend($.fn.asm.defaults, arg[0] || {});

        $this.each(function(index, item) {
            var mail  = '',
                $item = $(item);

            if (opts.link) {
                mail += '<a href="mailto:' + opts.alias + '@' + opts.domain + '">';
            }

            if (0 === $item.text().length) {
                mail += opts.alias + '@' + opts.domain;
            } else {
                mail += $item.text();
            }

            if (opts.link) {
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