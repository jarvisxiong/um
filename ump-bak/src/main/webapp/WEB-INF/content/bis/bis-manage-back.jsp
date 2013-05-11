<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>

    <%--
    <link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"  />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"  />
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
    --%>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>


    <title>商业管理系统</title>
</head>

<body>

<div class="title_bar">
    <div id="div_bis" style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">商业管理系统
    </div>
    <%@ include file="bis-manage-version.jsp" %>
    
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>

<div style="padding:20px 8px;" id="projectDiv">

    <div id="projectNorthDiv" style="float: left; padding-right:2px; width: 30%;">
        <ul id="projectNorth" class="project-nav">
            <li class="project-nav-title">北方区域公司</li>
            <s:iterator value="projectNorth">
                <li id="btli_${bisProjectId}" value="${bisProjectId}" class="project-nav-li"
                    onclick="viewProject('${bisProjectId}');">${projectName}</li>
            </s:iterator>
        </ul>
    </div>
    <div id="projectSouthDiv" style="float: left; padding-right:2px; width: 30%;">
        <ul id="projectSouth" class="project-nav">
            <li class="project-nav-title">南方区域公司</li>
            <s:iterator value="projectSouth">
                <li id="btli_${bisProjectId}" value="${bisProjectId}" class="project-nav-li"
                    onclick="viewProject('${bisProjectId}');">${projectName}</li>
            </s:iterator>
        </ul>
    </div>

</div>

<div style="position:absolute; bottom:10px; width:100%;">
    <security:authorize ifAnyGranted="A_REPO_GROUP_QUERY">
        <div style="float:left; padding-left:8px;">
            <input type="button" class="btn_blue" style="width:75px; height:26px;" onclick="viewReport(1);"
                   value="集团报表"/>
        </div>
    </security:authorize>
    <div style="float:left; padding-left:10px;">
        <input type="button" class="btn_blue" style="height:26px;width:75px;" onclick="toBisShop();" value="商家库"/>
    </div>
    <div style="float:left; padding-left:10px;">
        <input type="button" class="btn_blue" style="height:26px;width:85px;" onclick="toBisManageDay();" value="经营日报表"/>
    </div>
	<security:authorize ifAnyGranted="A_BIS_MAIN_QUERY">
	    <div style="float:left; padding-left:10px;">
			<input type="button" class="btn_blue" style="height:26px;width:100px;" onclick="toBisMainShopWeekly();" value="主力店招商周报"/>
		</div>
	</security:authorize>
</div>

<script type="text/javascript">
    function viewProject(id) {
        var url = "${ctx}/bis/bis-manage!toProjectOverview.action?bisProjectId=" + id;
        if (parent.TabUtils == null)
            window.open(url);
        else
            parent.TabUtils.newTab('projectOverview', '项目总况', url, true);
    }

    function viewReport(module) {

        var url = "${ctx}/bis/bis-manage!layout.action?module=" + module;
        var tabname;
        var title;
        if (module == 1) {
            tabname = "bisGroupOperate";
            title = "集团报表";
        } else if (module == 2) {
            tabname = "bisGroupCash";
            title = "集团资金流量表";
        }

        if (parent.TabUtils == null)
            window.open(url);
        else
            parent.TabUtils.newTab(tabname, title, url, true);
    }

    function toBisShop() {
        var url = "${ctx}/bis/bis-shop!main.action";
        if (parent.TabUtils == null)
            window.open(url);
        else
            parent.TabUtils.newTab("bisShop", "商家库", url, true);
    }
    function toBisManageDay() {
        var tab = parent.TabUtils;
        var url = "${ctx}/bis/bis-manage-day!report.action";
        if (parent.TabUtils == null)
            window.open(url);
        else{
            tab.closeTab({data:{tabId:"195"}});
            tab.newTab("195", "经营日报表", url, true);
        }
    }
    function toBisMainShopWeekly() {
        var tab = parent.TabUtils;
    	var url = "${ctx}/bis/bis-main-shop-report.action";
    	if(parent.TabUtils==null)
    		window.open(url);
    	else{
            tab.closeTab({data:{tabId:"195"}});
            tab.newTab("195","主力店招商周报",url,true);
        }
    }

    /**
     * 选择项目业态图，跳转mall楼层展示页面
     */
    function goFloor(bisProjectId) {
        var url = '${ctx}/bis/bis-project!projectOperate.action?bisProjectId=' + bisProjectId;
        $('#layoutPanel').hide();
        if (parent.TabUtils == null)
            window.open(url);
        else
            parent.TabUtils.newTab("bisProjectMenu", "项目业态图", url, true);
    }

    $(function () {

        $('#projectDiv').height($(window).height() - 103 + "px");

        $('#btnLayOut').onSelect({
            muti:false,
            callback:function (project) {
                goFloor(project.bisProjectId);
            }
        });
    });

</script>
</body>
</html>