(function (container) {
    var powerdesk = window.powerdesk = window.powerlong = window.pd = window.pl = {};

    //========================================== Start    ================================================
    //========================================== 界面展示 ================================================
    powerdesk.helper = {
        trim:function (value) {
            return value.replace(/(^[\s]*)|([\s]*$)/g, "");
        }, validation:function ($element) {/*验证函数*/
            var errorMessage;
            $($element).each(function () {
                var element = $(this);
                if (!errorMessage) {
                    if ($.trim(element.val()) == "") {
                        if ($.trim(element.attr("requiredMsg")) != "") {
                            errorMessage = element.attr("requiredMsg");
                        } else {
                            if (element.get(0).tagName.toUpperCase() == "SELECT") {
                                errorMessage = element.prev().text() + "必须选择！";
                            } else {
                                errorMessage = element.prev().text() + "必须填写！";
                            }
                        }
                        element.trigger("focus");
                    } else if (element.attr("regex") != "" && !new RegExp(element.attr("regex")).test(element.val())) {
                        errorMessage = element.attr("regexMsg");
                        element.trigger("focus");
                    } else if (element.hasClass("number") && !/^\-?\d+(\.\d{0,2})?$/.test(element.val())) {
                        if (element.attr("numberMsg") && element.attr("numberMsg") != "") {
                            errorMessage = element.attr("numberMsg");
                        } else {
                            errorMessage = element.prev().text() + "格式错误,且小数只能保留两位！"
                        }
                        element.trigger("focus");
                    }
                }
            });
            return errorMessage;
        }
    }
    //========================================== Start    ================================================
    //========================================== 界面展示 ================================================
    powerdesk.ui = {
        init:function () {
        }, effect:{
            fade:function (option) {
                $(option.id).fadeOut(function () {
                    $(this).fadeIn();
                });
            }
        }, prompt:{
            show:function (options) {
                options = $.extend({"id":".informations"}, options);
                var prompt = $(options.id).empty().html("<p class='" + options["class"] + "'>" + options.message + "</p>").show();
                if (options.time) {
                    setTimeout(function () {
                        prompt.slideUp();
                    }, options.time)
                }
            }, error:function (options) {
                powerdesk.ui.prompt.show($.extend(options, {"class":"error" }));
            }, success:function (options) {
                powerdesk.ui.prompt.show($.extend({"class":"success"}, options));
            }, warning:function (options) {
                powerdesk.ui.prompt.show($.extend(options, {"class":"warning"}));
            }
        }
    }
})(window);