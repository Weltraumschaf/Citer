/*
 * jQuery aMail plug-in 1.0
 * (c) 2009 Max Ya  ( http://iHackWeb.com )
 * Inspired by epemail plugin/
 *
 * http://ihackweb.com/archives/124
 *
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Example:
 * $('#email').amail();
 *
 * Using Example: <a class="amail" title="my email">lim[at]from+NOSPAM+ru[dot]com</a>
 * will become:   <a title="my email" class="amail" href="mailto:lim@fromru.com">my email</a>
 *
 * example: $('#email').amail('<at>','<dot>','FOOBAR');
 *
 * Using Example: <a class="amail" title="my email">lim<at>fromFOOBARru<dot>com</a>
 * will become:   <a title="my email" class="amail" href="mailto:lim@fromru.com">my email</a>
 */
;
(function($) {
    $.fn.amail = function(sAt, sDot, sRepl) {

        if (!sAt) {
            sAt = '[at]';
        }

        if (!sDot) {
            sDot = '[dot]';
        }

        if (!sRepl) {
            sRepl = '+NOSPAM+';
        }

        return this.each(function() {
            var el   = $(this),
                mail = el.text()
                         .replace(sAt, '@')
                         .replace(sDot, '.')
                         .replace(sRepl, '');

            el.each(function() {
                el.attr('href', 'mailto:' + mail);

                if (el.attr('title')) {
                    el.html(el.attr('title'));
                } else {
                    el.html(mail);
                }
            });
        });
    };
})(jQuery);