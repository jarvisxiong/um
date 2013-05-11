<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>月度资金预算</title>	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>	
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>		
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/cost/cost-budget.js"></script>	
	
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<%--
	<link type="text/css" rel="stylesheet" href="${ctx}/js/loadMask/jquery.loadmask.css"  />
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	 --%>

</head>
<body>
<div id="costContainer">
	<%--抬头标题栏目 --%>
	<div id="bodyHead" class="bodyHead">
		<div class="headTitle">
			<font style="font-size: 14px;float: left;">资金预算</font>			
		</div>
		<%--功能按钮 --%>
		<div id="lefTNav" class="leftNav" style="margin-left: 100px;">
			<ul id="btn_nav" class="tab">
				<security:authorize ifAnyGranted="A_COST_BUD_VIEW_N">
					<li class="tabClick" item="searchYear">搜索年度</li>
				</security:authorize>
				
				<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
					<li class="tabClick" item="searchMonth">搜索月度</li>
				</security:authorize>
				
				<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N">
					<li item="newYear">新增年度</li> 
				</security:authorize>
				
				<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
					<li item="newMonth">新增月度</li>
				</security:authorize>
				
				<security:authorize ifAnyGranted="A_COST_BUD_EDIT_STAT,A_COST_BUD_VIEW_STAT">
					<li item="monthStat" style="width: 60px;">月度汇总</li>
				</security:authorize>
				
				<security:authorize ifAnyGranted="A_COST_BUD_ADMIN">
					<li item="projectSet">项目设置</li>
				</security:authorize>
				
				<security:authorize ifAnyGranted="A_COST_BUD_ADMIN">
					<li item="projectAuth">授权项目</li>
				</security:authorize>
			</ul>
		</div>
		<input type="button" class="fRight btn_new btn_refresh_new" onclick="window.location.href=location.href;" value="刷新"/>
	</div>
	
	
	<%--第二部分 --%>
	<div style="clear: left;" style="width:100%; height:100%;">
		<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
		<tr>
			<td id="leftPanel" style="width:100px;border-bottom:1px solid #8C8F94; border-right:1px solid #8c8f94;background-color: #E4E7EC;" valign="top">
				<table style="width:100%;">
					<tr>
						<td >
							<div id="itemDiv" style="padding-top: 5px; float: left; min-width:200px;width: 100%; height:450px;overflow-y: auto;overflow-x: hidden; border: none;">							
							</div>
						</td>
						<td style="width: 5px;">
							<div id="noteResizeHandler" 
								 class="pd-chill-tip" title="您可以拖动,调整宽度" 
								 style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;
							</div>
						</td>
					</tr>
				</table>
			</td>
			<td  align="center" valign="top">
				<div id="rightPanel" style="height: 100%;line-height: 100%;text-align: center;">
					 
					 <%-- 搜索 --%>
					<div  id="resultPanel">
						
					</div>
				</div>	
			</td>
		</tr>
		</table>
	</div>
</div>

<script type="text/javascript">
//项目树节点
var arrCheck;
var gProjectTree;

$(function(){

	//注册左右拖动
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 200, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {}
    });

	//加载项目树
	loadProjectTree();	
	
	//注册标签事件
	$("#btn_nav li").click(function(i,dom){
		if($(this).hasClass('tabClick')){
			$(this).addClass('li-click').siblings().removeClass('li-click');
		}
		var item = $(this).attr('item');
		switch(item){
			//搜索年度
			case 'searchYear': 
				loadYearList();
				break;
			//搜索月度	
			case 'searchMonth': 
				loadMonthList();
				break;
			//年份
			case 'newYear':
				var url = _ctx + '/cost/cost-budget-year!input.action';
				openWindow('newBudgetYear', '新增年度资金预算', url);
				break;
			//月份
			case 'newMonth':
				var url = _ctx + '/cost/cost-budget-month!input.action';
				openWindow('newBudgetMonth', '新增月度资金预算', url);
				break;
			//汇总
			case 'monthStat':
				var url = _ctx + '/cost/cost-budget-month!monthStat.action';
				openWindow('monthBudgetStat', '月度汇总', url);
				break;
			//设置
			case 'projectSet':
				var url = _ctx + "/cost/cost-project-section!main.action";
				openWindow('projectSet', '项目设置', url);
				break;
			//授权
			case 'projectAuth':
				var url = _ctx + "/cost/cost-budget-auth!main.action";
				openWindow('projectAuth', '授权项目', url);
				break;
			
			
			default: ;
		}
	});
	
	//点击第一个tab点击事件
	$('#btn_nav li.tabClick').eq(0).addClass('li-click').trigger('click');
    
});

//加载项目树
function loadProjectTree(){
	var url = _ctx + '/cost/cost-budget!loadAuthProjectTree.action';
	$.post(url, {}, function(result){
		gProjectTree = new TreePanel({
			renderTo:'itemDiv',
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		gProjectTree.addListener("check",function(node){
			arrCheck = gProjectTree.getCheckedId();
		});
		gProjectTree.render();
		gProjectTree.isExpendSelect = true;
		gProjectTree.on(function(node){
			var nodeType = node.attributes.nodeType;
			//若非根节点 
			if("0" != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
			}
		}); 
	});
}
//获取选中的项目IDs
function getSectionIds(){
	//2-项目
	var arrProjects = (gProjectTree.getModifiedLeafNodes('2'))[0];
	var buf = new Array();
	var size = arrProjects.length;
	for(var i=0; i<arrProjects.length; i++){
		buf.push(arrProjects[i]);
		if(i < (size-1)){
			buf.push(',');
		}
	}
	return buf.join('');
}

//打开窗口
function openWindow(id, desc, url) {
	if (window.parent && window.parent.parent.TabUtils) {
		window.parent.parent.TabUtils.newTab(id, desc, url);
	} else {
		window.open(url);
	}
}

//搜索年度资金计划
function loadYearList(){
	TB_showMaskLayer("正在载入...");
	var url = _ctx + '/cost/cost-budget-year!loadYearList.action';
	$.post(url, {}, function(result){
		TB_removeMaskLayer();
		$('#resultPanel').html(result);
	});
}
//搜索月度资金计划
function loadMonthList(){
	TB_showMaskLayer("正在载入...");
	var url = _ctx + '/cost/cost-budget-month!loadMonthList.action';
	$.post(url, {}, function(result){
		TB_removeMaskLayer();
		$('#resultPanel').html(result);
	});
}
//刷新页面
function refreshEnterWin(){
	window.location.href = _ctx + '/cost/cost-budget!load.action';
}
</script>	
</body>
</html>