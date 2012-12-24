/**
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich@weltraumschaf.de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 * @author    Weltraumschaf
 * @copyright Copyright (c) 01.12.2010, Sven Strittmatter.
 * @version   0.2
 * @license   http://www.weltraumschaf.de/the-beer-ware-license.txt
*/

(function($) {

    $(function() {
        $("a.delete").bind("click", function(event) {
            $.ajax({
               url: event.target.href,
               type: "DELETE",
               success: function(data, textStatus, jqXHR) {
                   window.alert("Cite deleted.");
                   window.location.href = data;
               },
               error: function(jqXHR, textStatus, errorThrown) {
                   window.alert(errorThrown);
               }
            });
            return false;
        });
    });
}(jQuery));