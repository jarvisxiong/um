<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="content-Type" content="text/html; charset=utf-8"/>
    <title>经营日数据录入</title>
    <link href="${url.ctx}/resources/css/common/common.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/css/bis/bis-manage-day.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/js/prompt/skin/custom_1/ymPrompt.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/js/jqueryplugin/jqModal/jqModal.css" media="screen" rel="stylesheet"
          type="text/css"/>
    <link href="${url.ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var _ctx = "${url.ctx}";
    </script>
    <script src="${url.ctx}/resources/js/jquery/jquery.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/datePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/mes/jquery-ump.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/bis/bis.project.select.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/datePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/jqueryplugin/chilltip.js" type="text/javascript"></script>
    <script src="${url.ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
</head>
<body>
<div id="warp">
    <div id="header">
        <div class="title1 clearfix">
            <h2>经营日数据录入</h2>

            <div class="btns none">
                <button class="searcher blue" type="button"></button>
            </div>
        </div>
        <div class="form_body condition_panel clearfix">
            <form method="post" id="query">
                <label>项目：</label> <input type="text" class="text" id="bisProjectName" readonly="true"
                                          value="${project.projectName!}"      <#if helper.isEveryChildCompany()>
                                          disabled="true" </#if>
                                          style="padding-left: 5px;width: 120px;"/><input type="hidden"


                                                                                          name="bisProjectId"
                                                                                          id="bisProjectId"
                                                                                          value="${project.bisProjectId!}"/>
                <label>年份：</label>
                <select class="box" name="startYear">
                    <option value="">请选择</option>
                <#list 2008 .. helper.currentDate?string("yyyy")?number as year>
                    <option value="${year?string("00")}">${year?string("00")}年</option>
                </#list>
                </select>
                <label>月份：</label>
                <select class="box" name="startMonth">
                    <option value="">请选择</option>
                <#list 1 ..12 as month>
                    <option value="${month?string("00")}">${month?string("00")}月</option>
                </#list>
                </select>
                <button class="blue min" type="button">搜索</button>
            <@security.authorize ifAnyGranted="A_BIS_DAY_WRITE">
                <button class="blue min" type="button" style="margin-left:-5px;">新增</button>
            </@security.authorize>
            <#if !helper.isEveryChildCompany()>
                <button class="red min" type="button" style="margin-left:-5px;background-color: #ac2727;">清空</button>
            </#if>
                <span style="float: right; line-height: 25px">单位：万元</span>
            </form>
        </div>
        <div class="advanced_condition_panel none">暂无～～</div>
    </div>
    <div id="body" style="margin:10px;">
        <div id="results" class="results"></div>
        <div id="publish"></div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $.ajaxSetup({cache:false});
    <#if !helper.isEveryChildCompany()>
        $("#bisProjectName").onSelect({muti:false, callback:function ($data) {
            $("#bisProjectName").val($data.projectName).next().val($data.bisProjectId == "1" ? "" : $data.bisProjectId);
            gotoPager({});
        }});
    </#if>
        //列表加载方法/分页方法
        window.gotoPager = function ($input) {
            TB_showMaskLayer("正在搜索请稍后...", 5000);
            var params = $("form#query").serializeArray();
            if ($input.index) {
                params[params.length] = {name:"page.pageNo", value:$input.index}
            }
            $("#results").load("bis-manage-day!list.action", $.param(params), function () {
                TB_removeMaskLayer();
            });
        }

        var btns = $("#header button").click(function () {
            var text = $(this).text();
            if (text == "") {//高级擦搜索
                if ($(this).hasClass("selected")) {
                    $(this).removeClass("selected")
                } else {
                    $(this).addClass("selected")
                }
                $(".advanced_condition_panel").toggle();
            } else if (text == "搜索") {
                gotoPager({})
            } else if (text == "清空") {
                $(".condition_panel input,.condition_panel select").each(function () {
                    var element = $(this).val("");
                    if (element.is("select")) {
                        element.attr("selectedIndex", 0);
                    }
                });
                gotoPager({});
            } else if (text == "新增") {
                var project = $("form#query input#bisProjectId");

                if (ump.helper.trim(project.val()) == "") {
                    ymPrompt.alert({
                        message:"请选择项目！", width:350, height:150, title:"消息提示！", closeBtn:true
                    });
                    project.trigger("focus");
                    return;
                }
                $("#publish").load("bis-manage-day!publish.action", {bisProjectId:project.val()}, function ($html) {
                    var containerHeight = ($.browser.msie ? $("html") : $(window)).height();
                    containerHeight = (containerHeight - $(".publish").height()) / 2;
                    $(".publish").jqm().jqmShow().css("top", containerHeight).jqDrag(".title");
                });
            }
        });
        gotoPager({});
    });
</script>
</body>
</html>
