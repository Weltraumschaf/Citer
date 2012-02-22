/*global window, $, jQuery */
/*jslint nomen: false, debug: true, evil: false, onevar: false,
 		 bitwise: true, browser: true, passfail: false */
(function($) {
    var __DEBUG__ = true,
        __FILE__ = 'jquery.citer.js',
        options,

    /**
     * @access private
     */
    callService = function (action, callback, opts) {
        $.ajax({
            type:       'GET',
            url:        options.serviceUrl + action,
            data:       opts || {},
            dataType:   'json',
            cache:      false,
            success:    callback,
            error:      function (XMLHttpRequest, textStatus, errorThrown) {
                throw new Error('Cant do AJAX request: ' + textStatus);
            }
        });
    },

    /**
     * If no option with a template url is given this function trys
     * to guess where to load the template. It searches for the
     * script src URL of this plugin and changes the file extension from
     * js to html. By default the template should reside in the same
     * directory as the plugin file.
     *
     * @access private
     * @return {String}
     */
    getTemplateUrl = function() {
        var errorMessage,
            $script = $('script[src$="' + __FILE__ + '"]');

        if ($script.size() === 0) {
            errorMessage  = 'The plugin script ' +__FILE__ + ' is ';
            errorMessage +=  'not found in current document!';
            throw Error(errorMessage, __FILE__);
        }

        return $script.attr('src').replace(/\.js/, '.html');
    };

    /**
     * @access public
     * @var {Function}
     */
    $.citer = function(opts) {
        var errorMessage;
        options = $.extend($.citer.defaults, opts || {});

        if (!options.serviceUrl) {
            throw new Error('Please set url in options!');
        }

        if(!$.createTemplate) {
            errorMessage  = 'The plugin jTemplates is missing! ';
            errorMessage +=  'You ca ndownload it from http://jtemplates.tpython.com/';
            throw Error(errorMessage, __FILE__);
        }
        $('body').append($.ajax({
                type:     'GET',
                url:      options.templateUrl,
                async:    false,
                dataType: 'html',
                cache:    !__DEBUG__,
                error:    function() {
                    var errorMessage  = 'Cant load template from url: ';
                        errorMessage += options.templateUrl;
                    throw Error(errorMessage, __FILE__);
                }
            }).responseText);
    };

    /**
     * Default options for the util function $.stylefinder().
     *
     * @access public
     * @var {Object}
     */
    $.citer.defaults = {
        serviceUrl:  false,
        templateUrl: getTemplateUrl()
    };

    $.fn.citer = function(action) {
        var $this = $(this);

        if ('random' === action) {
            $.fn.citer.random($this);
        } else if ('submit' === action) {
            $.fn.citer.submit($this);
        } else if ('best' === action) {
            $.fn.citer.best($this);
        } else if ('latest' === action) {
            $.fn.citer.latest($this);
        } else if ('all' === action) {
            $.fn.citer.all($this);
        }

        return $this;
    };

    $.fn.citer.random = function($this) {
        callService('cite/random', function(result) {
            result.rating = {average: 0, count: 0};
            $this.setTemplate($('#citer_tpl_random').html());
            $this.processTemplate(result);
//            $this.find('#rating').rater({
//                id:         result.message.id,
//                rating:     result.message.rating.average,
//                postHref:   'api/?action=rate'
//            });
        });
    };

    $.fn.citer.submit = function($this) {
        $this.setTemplate($('#citer_tpl_submit').html());
        $this.processTemplate();
        $this.find('#citer_submitForm_submit').bind('click', function(event) {
            if (0 === $('#citer_submitForm_name').val().length ||
                0 === $('#citer_submitForm_text').val().length) {
                $('#citer_submitForm_status').text('Du musst schon Namen und Zitat eingeben...');
                return;
            }

            callService('submit', function(result) {
                $('#citer_submitForm_status').text(result.message);
                $('#citer_submitForm_name').val(''),
                $('#citer_submitForm_text').val('')
            }, {
                name: $('#citer_submitForm_name').val(),
                text: $('#citer_submitForm_text').val()
            });
        });
    };

    $.fn.citer.best = function($this) {
        callService('best', function(result) {
            $this.setTemplate($('#citer_tpl_best').html());
            $this.processTemplate(result.message);
        });
    };

    $.fn.citer.latest = function($this) {
        callService('latest', function(result) {
            $this.setTemplate($('#citer_tpl_latest').html());
            $this.processTemplate(result.message);
        });
    };

    $.fn.citer.all = function($this) {
        callService('all', function(result) {
            $this.setTemplate($('#citer_tpl_all').html());
            $this.processTemplate(result.message);
        });
    };
})(jQuery);