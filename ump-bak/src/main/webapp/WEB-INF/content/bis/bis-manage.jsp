<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <title>商业管理系统</title>
</head>

<body>

<div id="warp">
    <div id="header">
        <div class="title1 clearfix">

                <h2 style="width: auto;margin-right: 10px; float: left;">商业管理系统</h2>

                <div style="float:left; padding-top: 13px;">
                    <%@ include file="bis-manage-version.jsp" %>
                </div>

                <div class="btns">
                    <button class="green" type="button" onclick="openReports()">报表首页</button>
                    <!--button class="blue" type="button">支出明细</button>
                    <button class="blue" type="button">收入明细</button>
                    <button class="blue" type="button">合同台帐</button>
                    <button class="blue" type="button">商家库</button-->
                </div>

        </div>
    </div>
    <div id="body" style="margin:10px;">
        <div id="projectNorthDiv" style="float: left; padding-right:10px; width: 30%;">
            <ul id="projectNorth" class="project-nav">
                <li class="project-nav-title">北方区域公司</li>
                <s:iterator value="projectNorth">
                    <li id="btli_${bisProjectId}" value="${bisProjectId}" class="project-nav-li"
                        onclick="viewProject('${bisProjectId}');">${projectName}</li>
                </s:iterator>
            </ul>
        </div>
        <div id="projectSouthDiv" style="float: left;  width: 30%;">
            <ul id="projectSouth" class="project-nav">
                <li class="project-nav-title">南方区域公司</li>
                <s:iterator value="projectSouth">
                    <li id="btli_${bisProjectId}" value="${bisProjectId}" class="project-nav-li"
                        onclick="viewProject('${bisProjectId}');">${projectName}</li>
                </s:iterator>
            </ul>
        </div>
    </div>
	<security:authorize ifAllGranted="A_RENTAL_C_RATE_VIEW">
		<div style="float:left; padding-left:10px;display:none;">
			<input type="button" class="btn_blue" style="height:26px;width:100px;" onclick="toRentalCollectionRate()" value="租费收缴率"/>
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
        else {
            tab.closeTab({data:{tabId:"195"}});
            tab.newTab("195", "经营日报表", url, true);
        }
    }
    function toBisMainShopWeekly() {
        var tab = parent.TabUtils;
        var url = "${ctx}/bis/bis-main-shop-report.action";
        if (parent.TabUtils == null)
            window.open(url);
        else {
            tab.closeTab({data:{tabId:"195"}});
            tab.newTab("195", "主力店招商周报", url, true);
        }
    }
    function toRentalCollectionRate(){
    	var tab = parent.TabUtils;
    	var url = "${ctx}/bis/bis-rental-collection-rate.action";
    	if(parent.TabUtils==null)
    		window.open(url);
    	else{
            tab.closeTab({data:{tabId:"195"}});
            tab.newTab("195","租费收缴率",url,true);
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
     function openReports(){
         var tab = parent.TabUtils;
         var url = "${ctx}/bis/bis-manage!reports.action";
         if (parent.TabUtils == null)
             window.open(url);
         else {
             //parent.TabUtils.newTab("19870422", "报表首页", url, true);
             //tab.closeTab({data:{tabId:"195"}});
             tab.newTab("195", "报表首页", url, true);
         }
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