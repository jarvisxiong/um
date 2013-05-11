/**
 * 商业模块：选择商业项目
 *
 */
//----------------------------自动引入依赖的css 和 js文件----------------------------
function loadJs(url, callback) {
    //防止重复加载
    var i = 0;
    $('script[src]', 'head').each(function () {
        var src = $(this).attr('src');
        if (src == url) {
            i++;
        }
    });
    if (i > 0)return;
    var done = false;
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.language = 'javascript';
    script.src = url;
    script.onload = script.onreadystatechange = function () {
        if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')) {
            done = true;
            script.onload = script.onreadystatechange = null;
            if (callback) {
                callback.call(script);
            }
        }
    };
    document.getElementsByTagName("head")[0].appendChild(script);
}
;
function loadCss(url, callback) {
    var i = 0;
    $('link[src]', 'head').each(function () {
        var src = $(this).attr('src');
        if (src == url) {
            i++;
        }
    });
    if (i > 0)return;
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.media = 'screen';
    link.href = url;
    document.getElementsByTagName('head')[0].appendChild(link);
    if (callback) {
        callback.call(link);
    }
}
loadCss(_ctx + '/resources/js/jqueryplugin/jqModal/jqModal.css');
loadJs(_ctx + '/resources/js/common/common.js');
loadJs(_ctx + '/resources/js/jqueryplugin/jqModal/jqModal.js');

//----------------------------自动引入依赖的css 和 js文件----------------------------

/**
 * jquery插件形式对话框 ，选择项目
 */
(function ($) {
    $.extend({
        onSelect:function (dom, opts) {
            var _this = dom;
            //项目容器
            var _projectMap = {};
            var defaultOpt = {
                width:'500',
                height:'400',
                title:'请选择项目',
                zIndex:1000, //窗口层叠值
                opacity:30, //遮罩透明度
                ctx:_ctx,
                waitingHtml:'<p>Please wait..</p>',
                muti:true, //项目多选，默认为true，设置false：单选
                maxSelectedProject:5,
                nameField:'projectName', //实体属性，回显文本对应的字段，默认为projectName
                cdField:'bisProjectId', //实体属性，回显cd对应的字段，默认bisProjectId
                orgCd:'orgCd', //实体属性，回显cd对应的字段，默认bisProjectId
                cdFields:''//实体属性，回显cd对应的字段，默认bisProjectIds
            };
            //初始化参数配置
            var option = $.extend(defaultOpt, opts);
            //先清空也米昂上可能已渲染过的弹出框，避免重复
            var id = $(_this).attr('id');
            //$('#jqmDialog_'+id).remove(); 保留渲染过的弹出框
            //初始化弹出窗口容器
            var jqmDlg;
            if (option.muti) {
                jqmDlg = $('<div class="jqmDialog" id="jqmDialog_' + id + '">' +
                    '<div class="projectHeader">' +
                    '<div class="jqmClose">X</div>' +
                    '<div class="bisTitle">' + option.title + '</div>' +
                    '</div>' +
                    '<div class="jqmBody">' +
                    option.waitingHtml +
                    '</div>' +
                    '<div align=center class="jqmBottom">' +
                    '<button class="bisConfirmBtn">确定</button>' +
                    '<button class="bisCancelBtn">取消</button>' +
                    '</div>' +
                    '</div>');
            } else {
                jqmDlg = $('<div class="jqmDialog" id="jqmDialog_' + id + '">' +
                    '<div class="projectHeader">' +
                    '<div class="jqmClose">X</div>' +
                    '<div class="bisTitle">' + option.title + '</div>' +
                    '</div>' +
                    '<div class="jqmBody">' + option.waitingHtml + '</div>' +
                    '</div');
            }
            $('div.jqmBody', jqmDlg).height(option.height + "px");
            jqmDlg.css({'width':option.width + 'px', 'marginLeft':(0 - option.width / 2) + 'px'});
            $('body').append(jqmDlg);
            initDlgEvents();
            var t = $('div.jqmBody', jqmDlg);


            //初始化项目列表，并绑定点击忽而选择事件 -- 显示以选项目
            function initProjects() {
                var map = $(_this).data('projectMap');
                if (typeof (map) === 'object') {
                    _projectMap = $.extend(true, {}, map);
                    var emptyFlg = true;
                    for (var k in _projectMap) {
                        emptyFlg = false;
                        break;
                    }
                }
                if (typeof(map) === 'undefined') {
                    var name = option.nameEId ? $('#' + option.nameEId).val() : $('#' + option.nameEId).val();
                    var cd = option.cdFields;
                    var nameArray = $.trim(name).split(';');
                    var cdArray = $.trim(cd).split(',');
                    _projectMap = {};
                    var k = 0;
                    $.each(cdArray, function (i, n) {
                        if (n != '') {
                            var o = {
                                projectName:n,
                                bisProjectId:cdArray[i]
                            };
                            _projectMap[n] = o;
                            k++;
                        }
                    });

                }
            }

            ;
            //初始化部分元素的绑定事件：
            function initEvents() {
                //初始显示项目toggle事件绑定
                $('.projectDisplay li').each(function () {
                    var obj = {};
                    obj.bisProjectId = $(this).attr('id');
                    obj.projectName = $(this).attr('projectName');
                    obj.orgCd = $(this).attr('orgCd');
                    addAndBindProject(obj, false);
                });
            }

            //初始化窗口的确定和取消事件
            function initDlgEvents() {
                //确定
                $('.bisConfirmBtn', jqmDlg).click(function () {
                    projectCallBack();
                });
                $('.bisCancelBtn').click(function () {
                    //_projectMap = {};
                    $(jqmDlg).jqmHide();
                });
            }

            //项目选择框确定事件
            function projectCallBack() {
                var check = true;
                if (option.maxSelectedProject > 0) {
                    var i = 0;
                    for (var k in _projectMap) {
                        i++;
                        if (i > option.maxSelectedProject) {
                            alert('对不起，最多选择' + option.maxSelectedProject + '个项目');
                            check = false;
                            break;
                        }
                    }
                    ;
                }
                if (!check)return;
                var data = $.extend(true, {}, _projectMap);
                $(_this).data('projectMap', data);
                var names = [];
                var cds = [];
                var nameStr = '';
                var cdStr = '';
                for (var k in _projectMap) {
                    var obj = _projectMap[k];
                    if (obj) {
                        names.push(obj[option.nameField]);
                        cds.push(obj[option.cdField]);
                    }
                }
                if (names.length > 0) {
                    nameStr = names.join(';');
                    cdStr = cds.join(';');
                    if (option.muti) {
                        nameStr += ';';
                        cdStr += ';';

                    }

                }
                // if(option.nameEId){ $('#'+option.nameEId).val(nameStr); $('#'+option.cdEId).val(cdStr);	}else{	 $(_this).val(nameStr).next().val(cdStr);	 }
                if ($.isFunction(option.callback)) {
                    option.callback(_projectMap);
                }
                $(jqmDlg).jqmHide();
            }

            //根据项目信息添加并绑定项目事件
            function addAndBindProject(objects, addFlag, checked) {
                if ($.isArray(objects)) {
                    //记录长度
                    var s_start = 0;
                    //统计处理条数
                    var s_count = 1;
                    //数据的长度
                    var len = objects.length;
                    for (var i = s_start; i < len; i++) {
                        if (i < len) {
                            //逻辑处理
                            if (objects[i])
                                bind(objects[i], addFlag, checked);

                        } else {
                            return;
                        }
                    }
                } else {
                    bind(objects, addFlag);
                }
                //绑定单选多选 的事件：单击、鼠标滑过
                function bind(obj, flg, cFlg) {
                    if (obj.bisProjectId == '')return;
                    if (flg) {
                        var strList = new Array();
                        strList.push('<li class = "projectUnSelected" id="');
                        strList.push(obj.bisProjectId);
                        strList.push('"><div class=""');
                        strList.push(obj.projectName);
                        strList.push('</div></li>');
                        $('.projectDisplay').append(strList.join(''));
                    }
                    var objProject = $('#' + obj.bisProjectId);
                    //绑定鼠标滑过事件
                    //绑定单击事件
                    if (!option.muti) {
                        objProject.bind('mousemove',
                            function () {
                                $(this).addClass('projectSelected');
                            }
                        ).bind('mouseout',
                            function () {
                                $(this).removeClass('projectSelected');
                                $(this).addClass('projectUnSelected');

                            }).bind('click', function () {
                                //单选
                                $('.projectDisplay li.projectSelected').attr('className', 'projectUnSelected');
                                _projectMap = {};
                                //选中 执行回掉函数
                                $(this).attr('className', 'projectSelected');
                                _projectMap[obj.bisProjectId] = obj;
                                if ($.isFunction(option.callback)) {
                                    option.callback(obj);
                                } else {//若未定义回掉函数，在将选中项目赋予下一控件默认将选中的值赋予this
                                    option.nameField = obj.bisProjectId;
                                    option.cdField = obj.projectName;
                                    $(_this).val(option.cdField).next().val(option.nameField);
                                }
                                $(jqmDlg).jqmHide();
                            });
                    } else {
                        objProject.click(function () {
                            var className = $(this).attr('className');
                            if (className == 'projectSelected') {
                                $(this).attr('className', 'projectUnSelected');
                                delete _projectMap[obj.bisProjectId];
                            } else if (className == 'projectUnSelected') {
                                $(this).attr('className', 'projectSelected');
                                _projectMap[obj.bisProjectId] = obj;

                            }
                        });
                    }


                    var projectObj = _projectMap[obj.bisProjectId];
                    if (projectObj) {
                        objProject.attr('className', 'projectSelected');
                    }
                    if (cFlg == '0') {
                        if (projectObj) {
                            objProject.trigger('click');
                        }
                    } else if (cFlg == '1') {
                        if (!projectObj) {
                            objProject.trigger('click');
                        }
                    }
                }
            }

            //点击时弹出项目选择框
            $(_this).click(function () {
                var url = option.ctx + '/bis/bis-project-select.action';
                $(jqmDlg).jqm({
                    trigger:false,
                    ajax:url,
                    ajaxText:option.waitingHtml,
                    target:t,
                    modal:true,
                    zIndex:option.zIndex,
                    overlay:option.opacity,
                    onHide:function (h) {
                        $('div.jqmBody', jqmDlg).empty();
                        //h.w.slideUp(200);
                        //添加动画效果
                        var wE = h.w;
                        var width = wE.width();
                        var height = wE.height();
                        wE.animate({
                            height:20, width:20
                        }, 300, function () {
                            wE.hide();
                            wE.css({'width':width, 'height':height});
                        });
                        h.o.remove();
                    },
                    onLoad:function (r) {
                        initProjects();
                        initEvents();
                        $('div.jqmBody', jqmDlg).css('overflow', 'auto');
                    }
                }).jqDrag('.projectHeader');

                var containerHeight = ($.browser.msie ? $("html") : $(window)).height();
                containerHeight = (containerHeight - $(jqmDlg).height()) / 2;

                if (option.top){
                    containerHeight = option.top;
                }
                $(jqmDlg).jqmShow().css("top", containerHeight);
            });
        }
    });
    $.fn.onSelect = function (opts) {
        return this.each(function () {
            $.onSelect(this, opts || {});
        });
    };
})(jQuery);