<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<!DOCTYPE HTML>
<html  style="overflow: auto;">
<head>
    <meta http-equiv="content-Type" content="text/html; charset=utf-8"/>
    <title>经营报表</title>
    <link href="${url.ctx}/resources/css/bis/bis-manage-day.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/js/prompt/skin/custom_1/ymPrompt.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/js/jqueryplugin/jqModal/jqModal.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var _ctx = "${url.ctx}";
    </script>
    <script src="${url.ctx}/resources/js/jquery/jquery.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/datePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/mes/jquery-ump.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/bis/bis.project.select.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/jqueryplugin/chilltip-bisManageDay.js" type="text/javascript"></script>
    <script src="${url.ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
    
</head>
<body>
<div id="warp">
    <div id="header">
        <div class="title1  none">
            <div style="float: left;"><h2 style="width: auto;margin-right: 10px;">经营报表</h2><span style="height:45px;line-height: 40px;" id="scount"></span></div>
           
            <div style="float: right;margin-right: 8px;margin-top: 8px;">

		            <button class="green qiehuan" type="button"
		            	<@security.authorize ifAllGranted="A_BIS_MAIN_QUERY"> style='width: 120px;' </@security.authorize>
		           		<@security.authorize ifNotGranted="A_BIS_MAIN_QUERY"> style='width: 120px; display: none;' </@security.authorize>
		           
		               >主力店招商周报
		            </button>
            </div>
            
            <div style="float: right;margin-right: 8px;margin-top: 8px;">
	            <@security.authorize ifAllGranted="A_RENTAL_C_RATE_VIEW">
	            	<button class="green zufei" style="width:100px;" type="button">租费收缴率</button>
	            </@security.authorize>
            </div>
            <div style="float: right;margin-right: 8px;margin-top: 8px;"><button class="green qiehuan" type="button"
                    style=" width: 100px;">项目列表
            </button>
            </div>
        </div>
        <div class="form_body condition_panel clearfix" style="background:#FFF;border-bottom: none;padding-left: 0px;">
            <form method="post" id="query">
                <input type="hidden" name="startDate" value="${helper.currentDate?string("yyyy-MM")}"/>
                <span style="float:left;height:25px;line-height: 25px;font-weight: bold;margin-right: 10px;color: #303030">正在查看: </span>
                <label class="none">项目：</label> <input type="text" class="text " id="bisProjectName" readonly="true"
                                          value="${project.projectName!}"
                                          style="padding-left: 5px;width: 120px;"/><input type="hidden"
                                                                                          name="bisProjectId"
                                                                                          id="bisProjectId"
                                                                                          value="${project.bisProjectId!}"/>
                <label class="none">日期：</label>
                <input type="text " class="text none" readonly="true" id="lzsb1" style="padding-left: 5px;width: 120px;"
                       onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:pickedFunc})"
                       value="${helper.currentDate?string("yyyy-MM")}"/>
                <input type="text" class="text " readonly="true" id="lzsb2" style="padding-left: 5px;width: 120px;"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickedFunc})"
                       value="${helper.currentDate?string("yyyy-MM-dd")}"/>
                <button class="red min" type="button" style="border-color: #666">清空</button>
            <#--<button class="green qiehuan" type="button">项目切换</button>-->
            <#--A_BIS_MANAGE_DAY_REPORT-->
                <span class="type">报表类型：<#--<a href="javascript://" class="selected">全报</a> / --><a
                        href="javascript://">月报</a>  <a
                        href="javascript://" class="selected">日报</a></span>
                <span style="float: right; line-height: 25px">单位：万元</span>
            </form>
        </div>
        <div class="advanced_condition_panel none">暂无～～</div>
    </div>
    <div id="body" style="margin:0px;">
        <div id="results" class="results"></div>
        <div id="publish"></div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $.ajaxSetup({cache:false});
        <#if !bisProjectId?exists>
            $("#bisProjectName").onSelect({muti:false, callback:function ($data) {
                $("#bisProjectName").val($data.projectName).next().val($data.bisProjectId);
                gotoPager({});
            }});
        </#if>
        //列表加载方法/分页方法
        window.gotoPager = function ($input) {
            if (!$input)$input = {};
            TB_showMaskLayer("正在搜索请稍后...", 5000);
            var params = $("form#query").serializeArray();
            if ($input.bar || $input.bar == 0) {
                params[params.length] = {name:"bar", value:$input.bar}
            }
            $("#results").load("bis-manage-day!reportBody.action", $.param(params), function () {
                TB_removeMaskLayer();
            });
        }
        //日期选择
        window.pickedFunc = function ($date) {
            $(".condition_panel input[name=startDate]").val($date.cal.getDateStr());
            gotoPager({});
        }

        $(".condition_panel select").change(function () {
            gotoPager({});
        });
        $(".condition_panel button:contains(清空)").click(function () {
            document.getElementById("query").reset();
            types.removeClass("selected");
            gotoPager({});
        });

        $("#195_tab", parent.document).find(".tab_title").html("经营日报表").attr("title", "经营日报表");
        //项目切换
        var qiehuans = $("button.qiehuan").click(function () {

            var index = qiehuans.index(this);
            var tab = parent.TabUtils;
            if (index == 1) {
                var url = "${url.ctx}/bis/bis-manage.action?report=false";
                if (tab == null) {
                    window.open(url);
                } else {
                    if($("#195_tab",parent.document).size()!=0){
                        tab.closeTab({data:{tabId:"195"}});
                    }
                    tab.newTab("195", "项目列表", url, true);
                }
            } else {
                var url = "${url.ctx}/bis/bis-main-shop-report.action";
                if (tab == null)
                    window.open(url);
                else {
                    if($("#195_tab",parent.document).size()!=0){
                        tab.closeTab({data:{tabId:"195"}});
                    }
                    tab.newTab("195", "主力店招商周报", url, true);
                }
            }
        });
        $("button.zufei").bind("click",function(){
        	var tab = parent.TabUtils;
        	var url = "${url.ctx}/bis/bis-rental-collection-rate.action";
			if (tab == null) {
                window.open(url);
            } else {
                if($("#195_tab",parent.document).size()!=0){
                    tab.closeTab({data:{tabId:"195"}});
                }
                tab.newTab("195", "租费收缴率", url, true);
            }
        });
        var queryInput = $(".condition_panel input:gt(2)");
        var falg = true;
        var types = $("#header .type a").click(function () {
            types.removeClass("selected");
            $(this).addClass("selected");
            queryInput.hide();
            //Show 日报/月报
            var index = types.index(this);
            queryInput.eq(index).show();
            if (index == 0) {
                queryInput.eq(index).val("${helper.currentDate?string("yyyy-MM")}");
            } else {
                falg = true;
                queryInput.eq(index).val("${helper.currentDate?string("yyyy-MM-dd")}");
            }
            $(".condition_panel input[name=startDate]").val(queryInput.eq(index).val());
            if (falg) {
                gotoPager({bar:0});
                falg = false;
            } else {
                gotoPager({});
            }
        });
        types.eq(1).trigger("click");
    });
</script>
</body>
</html>
