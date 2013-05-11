<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本计划管理</title>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/nocache.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/planWork.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/cost-ctrl.css" />
	<%--
	<link type="text/css" rel="stylesheet" href="${ctx}/css/userChoose.css"/>
	 --%>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-target.css">
		
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<%--
	<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
	<script type="text/javascript" src="${ctx}/js/orgChoose.js"></script>
	 --%>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/planWorkCenter.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/plan-target.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/cost-ctrl.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/cost-check.js"></script>
	<!-- 中心内部任务成本模块 -->
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript">
var my_now_year = "${now_year}";
var my_now_month = "${now_month}";

$(function(){
	$('.singleSelect').userSelect({
		muti:false
	});
	$('.projSelect').orgSelect({
		showProjectOrg:true
	});
	var targetPageType = $('#targetPageType').val();
	$('#cost-nav li').click(function(){
		$("#centerSearchMonth").hide();
		$(this).addClass('cost-nav-click').siblings().removeClass('cost-nav-click');
		$('.cost-nav-click').css("background-color","");
		$('#cost-nav li:not(.cost-nav-click)').css("background-color","#cbcbcb");
		//$(this).siblings().css("background-color","#cbcbcb");
		var href = $(this).attr('href');
		var text = $(this).text();
		var id = $(this).attr('id');
		var pageType = $(this).attr('pageType');

		$('#btn_add_cost').hide();
		$('#main_search_div').hide();
		$('#btn_add_check').hide();
		$('#check_search_div').hide();
		$('#planMonth').hide();
		if(id == 'cost_check'){
			showCostCheckList();
			return;
		}
		
		$("#updateFlg").val("0");
		TB_showMaskLayer("正在加载...");
		showOrHideSearch(id,targetPageType);
		if(id == 'cost_task'){
			costTaskClick(this);
		}else if(id == 'cost_plan'){
			href += "&now_year="+my_now_year+"&now_month="+my_now_month+"&page.pageSize=999";
			$.get(href,function(r){
				$('#cost-container').html(r);
				TB_removeMaskLayer();
				$("#centerSearchMonth").show();
				autoHeight();
			});
		}else if(id == 'cost_stat'){
			href += "&now_year="+my_now_year+"&now_month="+my_now_month+"&page.pageSize=999";
			$.get(href,function(r){
				$('#cost-container').html(r);
				TB_removeMaskLayer();
				autoHeight();
			});
		}else{
			$('#search_pageType').val(pageType);
			if('cost_sup_li' == id){
				$('#filter_scheduleStatusCd,#filter_delayFlg,#filter_waitFlg').val('');
				$('#bid_pur_search a').css('color','#0167a2');
			}else{
				$('#sup_search a').css('color','#0072bb');
				$('#filter_isSuppCompleteFlag').val();
			}
			jumpPage(1);
		}
	});
	
	var targetId = $('#targetId').val();
	if(targetId != '' && targetPageType != ''){
		$('#cost-nav li[pageType="'+targetPageType+'"]').trigger('click');
		$('#targetId').val('');
		$('#targetPageType').val('');
		<security:authorize ifNotGranted="A_COST_TASK">
		$('#cost-nav li[pageType="'+targetPageType+'"]').hide();
		</security:authorize>
		
		var has = false; // 是否有模块权限的表示
		$.each($('#cost-nav li'), function(key, liId){
			if(liId.id == "inviteBids_li" || liId.id == "procure_li" || liId.id == "strategy_li" || liId.id == "bis_li") {
				has = true;
			}
		});
		if(has == false) {
			// no granted authorize of this module.
			TB_showMaskLayer("正在搜索...");
			var url="${ctx}/plan/cost-ctrl-bid-purc!list.action";
			var param={targetId:targetId,pageType:targetPageType};
			$.post(url,param,function(r){
				$('#cost-container').html(r);
				autoHeight();
				bindTblEv();
				TB_removeMaskLayer();
			});

			$('#result_table .mainTr[target="true"]').find('[click2expand=true]:eq(1)').trigger('click');
		}
	}else{
		$('#cost-nav li:eq(0)').trigger('click');
	}
});

//月份的点击
function centerMonthClick(toYear,toMonth){
	if(null==toMonth && toYear==my_now_year){
		return;
	}
	if(null==toYear && toMonth==my_now_month){
		return;
	}
	if(null!=toYear && 0!= toYear){
		my_now_year = toYear;
	}
	if(null!=toMonth && 0!= toMonth){
		my_now_month = toMonth;
	}
	try{
	for(var i=1;i<13;i++){
		$("#month"+i).removeClass("month_big");
		$("#month"+i).removeClass("color_red");
		$("#month"+i).addClass("month_small");
	}
	$("#month"+my_now_month).removeClass("month_small");
	$("#month"+my_now_month).addClass("month_big");
	$("#month"+my_now_month).addClass("color_red");
	}catch(e){}
	
	var loading="<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>";
	var planWorkCenterParam = {
			if_in_attention : false,
			search_orgCd : '17',
			now_year : my_now_year,
			now_month : my_now_month,
			now_month_of_year : my_now_month,
			"page.pageNo" : 1,
			"page.pageSize" : 999,
			centerCd : ''
	};
	$("#cost-container").html(loading).load("${ctx}/plan/plan-work-center!list.action?costModule=costCtrl&"+decodeURIComponent($.param(planWorkCenterParam)),function(){
		autoHeight();
	});
}
</script>
</head>
<body>
	<input type="hidden" id="updateFlg" value="0"/>
	<div>
		<div class="title_bar" style="font-weight:bold;padding-left:5px;font-size:14px;">成本工作管理</div>
		
		<!-- 搜索条件 -->
		<div style="padding:5px 5px 0px 10px;font-size:12px;line-height:30px;">
			<!-- 北方/南方/上海/合同 -->
			<div id="main_search_div" style="display: none;">
				<form action="${ctx}/plan/cost-ctrl-bid-purc!list.action" id="main_search_form">
					<input type="hidden" id="targetId" name="targetId" value="${targetId}"/>
					<input type="hidden" id="targetPageType" value="${targetPageType}"/>
					<input type="hidden" name="pageType" id="search_pageType"/>
					<input type="hidden" name="page.pageNo" id="search_pageNo"/>
					<div style="float:left;">
						计划
						<select name="filter_EQS_planTypeCd" class="select_box"> 
							<option></option>
							<option value="0">计划内</option>
							<option value="1">计划外</option>
							<option value="2">半年计划</option>
							<option value="3">执行计划</option>
						</select>
						项目
						<input type="text"   id="filter_projectNameId" style="width:100px;cursor:pointer;" class="projSelect"/>
						<input type="hidden" id="filter_projectCdId" name="filter_EQS_projectCd"/>
						负责人
						<input type="text"   id="filter_UserNames" style="width:80px;cursor:pointer;" class="singleSelect"/>
						<input type="hidden" id="filter_UserCds" name="filter_LIKES_ownerCds"/>
						
						定标
						<input type="text" id="matchBidDate" name="matchBidDate" onfocus='WdatePicker({dateFmt:"yyyy-MM"})' class="Wdate select_box" style="width:70px"/>
						
						<!-- 新增、进行中、已完成 -->
						<input type="hidden" id="filter_scheduleStatusCd" name="filter_EQS_scheduleStatusCd"/>
						<!-- 过期 -->
						<input type="hidden" id="filter_delayFlg" name="delayFlg"/>
						<!-- 等待 -->
						<input type="hidden" id="filter_waitFlg" name="waitFlg"/>
						<!-- 供应商状态 -->
						<input type="hidden" id="filter_isSuppCompleteFlag" name="filter_EQS_isSuppCompleteFlag"/>
						
					</div>
				    <div style="float: left;padding-left:5px;">
				      <input type="button" class="btn_blue" value="搜索" onclick="submitSearch(0);"/>
					  <input type="button" class="btn_red" value="清空" onclick="resetSearch();"/>
				    </div>
					<!-- 搜索招采 -->
					<div style="float:left;display:none;margin-left:5px;" id="bid_pur_search" title="快速搜索">
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearch(this,'filter_delayFlg')">过期</a>
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearch(this,'filter_scheduleStatusCd','0')">新增</a>
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearch(this,'filter_scheduleStatusCd','1')">进行中</a>
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearch(this,'filter_scheduleStatusCd','2')">已完成</a>
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearch(this,'filter_waitFlg')">等待</a>
					</div>
					
					<!-- 搜索供应商 -->
					<div style="float:left;display:none;margin-left:5px;" id="sup_search">
						<span style="color:#464646">快速搜索：</span>
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchSup(this,'1')">进行中</a>
						<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchSup(this,'2')">已完成</a>
					</div>
				</form>
			</div>
			
			<!-- 标后核对 -->
			<div id="check_search_div" style="display: none;">
				<form action="${ctx}/plan/cost-check!list.action" id="main_search_form_check">
					<input type="hidden" id="search_pageNo_check" name="page.pageNo"/>
					<input type="hidden" id="search_isUpdateFlg" name="isUpdateFlg" value="${isUpdateFlg}"/>
					
					<div style="float:left;">
					  <div style="float: left;">
						<%--
						数据来源
						<select name="filter_EQS_dataSrcTypeCd">
							<option></option>
							<option value="0">新增</option>
							<option value="1">招采</option>
						</select>
						 --%>
						项目
						<input type="text"   id="filter_projectNameId_check" style="width:100px;cursor:pointer;" class="projSelect"/>
						<input type="hidden" id="filter_projectCdId_check" name="filter_EQS_projectCd"/>
						负责人
						<input type="text"   id="filter_UserNames_check" style="width:80px;cursor:pointer;" class="singleSelect"/>
						<input type="hidden" id="filter_UserCds_check" name="filter_LIKES_ownerCds"/>
						
						审核
						<input type="text"   id="matchBidDate_check" name="matchBidDate" onfocus='WdatePicker({dateFmt:"yyyy-MM"})' class="Wdate" style="width:70px"/>
						 
						<!-- 新增、进行中、已完成 -->
						<input type="hidden" id="filter_scheduleStatusCd_check" name="filter_EQS_scheduleStatusCd" value="${filter_EQS_scheduleStatusCd}"/>
						<!-- 过期 -->
						<input type="hidden" id="filter_delayFlg_check" name="delayFlg" value="${delayFlg}"/>
						<!-- 等待 -->
						<input type="hidden" id="filter_waitFlg_check" name="waitFlg" value="${waitFlg}"/>
						</div>
						<div style="float: left;">
						<input type="button" class="btn_blue" value="搜索" onclick="searchCostCheck();"/>
					  <input type="button" class="btn_red" value="清空" onclick="resetSearchCheck();"/>
					</div>
					</div>
				
					<!-- 搜索标后核对 -->
					<div style="float:left;margin-left:5px;" id="bid_pur_search_check">
						<span title="快速搜索">
							<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchCheck(this,'filter_delayFlg_check','1')">过期</a>
							<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchCheck(this,'filter_scheduleStatusCd_check','0')">新增</a>
							<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchCheck(this,'filter_scheduleStatusCd_check','1')">进行中</a>
							<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchCheck(this,'filter_scheduleStatusCd_check','2')">已完成</a>
							<a style="color:#0167A2;" href="javascript:void(0)" onclick="quickSearchCheck(this,'filter_waitFlg_check','1')">等待</a>
						</span>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 标题切换 -->
		<div style="clear: left;">
			<ul style="float:left;margin-left:5px;min-width:300px;" class="cost-nav" id="cost-nav">
			
				<%-- 成本任务 --%>
			  	<security:authorize ifAnyGranted="A_COST_TASK">
				<li id="cost_task" class="cost-task">成本任务</li>
			  	<li id="cost_plan" href="${ctx}/plan/plan-work-center!list.action?search_orgCd=17&costModule=costCtrl">中心内部任务</li>
			  	</security:authorize>

				<%-- 招标 --%>
				<security:authorize ifAnyGranted="A_N_BP_SEARCH,A_N_BP_CONFIRM,A_N_BP_EDIT,A_N_BP_SCHE,A_BP_ADD">
				<li id="inviteBids_li" pageType="inviteBids" href="${ctx}/plan/cost-ctrl-bid-purc!listByUpdate.action?pageType=inviteBids">招标</li>
				</security:authorize>
				
				<%-- 采购 --%>
				<security:authorize ifAnyGranted="A_S_BP_SEARCH,A_S_BP_CONFIRM,A_S_BP_EDIT,A_S_BP_SCHE,A_BP_ADD">
				<li id="procure_li" pageType="procure" href="${ctx}/plan/cost-ctrl-bid-purc!listByUpdate.action?pageType=procure">采购</li>
				</security:authorize>
				
				<%-- 战略 --%>
				<security:authorize ifAnyGranted="A_S_BP_SEARCH,A_S_BP_CONFIRM,A_S_BP_EDIT,A_S_BP_SCHE,A_BP_ADD">
				<li id="strategy_li" pageType="strategy" href="${ctx}/plan/cost-ctrl-bid-purc!listByUpdate.action?pageType=strategy">战略</li>
				</security:authorize>
				 			
				<!-- 商业招采 -->
				<security:authorize ifAnyGranted="A_SH_BP_SEARCH,A_SH_BP_CONFIRM,A_SH_BP_EDIT,A_SH_BP_SCHE,A_BP_ADD">
				<li id="bis_li" pageType="bis" href="${ctx}/plan/cost-ctrl-bid-purc!listByUpdate.action?pageType=bis">商业招采</li>
				</security:authorize>
				<%--酒店招采 
				<security:authorize ifAnyGranted="A_SH_BP_SEARCH,A_SH_BP_CONFIRM,A_SH_BP_EDIT,A_SH_BP_SCHE,A_BP_ADD">
				<li id="hotel_li" pageType="hotel" href="${ctx}/plan/cost-ctrl-bid-purc!listByUpdate.action?pageType=hotel">酒店招采</li>
				</security:authorize>
				--%>
				<%-- 合同管理 --%>
				<security:authorize ifAnyGranted="A_CON_SEARCH,A_CON_CONFIRM">
				<li pageType="con" href="${ctx}/plan/cost-ctrl-bid-purc!list.action?pageType=con">合同管理</li>
				</security:authorize>
				
				
				<%-- 标后核对 --%>
				<security:authorize ifAnyGranted="A_CCHECK_SCHE,A_CCHECK_EDIT,A_CCHECK_SEARCH,A_CCHECK_CONFIRM,A_CCHECK_DELETE,A_CCHECK_ADD">
				<li id="cost_check" pageType="check" href="${ctx}/plan/cost-check!list.action?pageType=check">标后核对</li>
				</security:authorize>
				
				
				<%-- 
				<!-- 供应商管理 -->
				<security:authorize ifAnyGranted="A_COST_SUPQUERY,A_COST_SUPCOMP">
				<li  id="cost_sup_li" pageType="sup" href="${ctx}/plan/cost-ctrl-bid-purc!list.action?pageType=sup">供应商管理</li>
				</security:authorize>
				<security:authorize ifAnyGranted="A_COST_YQUERY,A_COST_YTRAN">
				<li id="cost_plan" href="${ctx}/plan/cost-exec-plan!getCostCtrlPlanRel.action">半年计划</li>
			  	</security:authorize>
			  	--%>
			  	
			  	
				<%-- 报表统计	--%>
			  	<security:authorize ifAnyGranted="A_COST_STAT">
			  	<li id="cost_stat" href="${ctx}/plan/cost-ctrl-bid-purc!statZc.action?" >报表统计</li>
			  	</security:authorize>
			  
			</ul>
			
			<div style="float:left;display:none;padding-left:5px;" id="btn_add_cost">
			   <input type="button" class="btn_blue"onclick="addCost();" title="新增招采" value="新增" />
				
			</div>
			<div style="float:left;display:none;padding:3px 0 0 5px" id="btn_add_check">
				<security:authorize ifAnyGranted="A_CCHECK_ADD,A_CCHECK_EDIT">
				 <input type="button" class="button_blue"onclick="showCostCheckPanel();" title="新增标后核对" value="新增" />
				</security:authorize>
			</div>
			
			<!-- 显示操作结果提示 -->
			<div style="float:left;display: none;padding:3px 0 0 5px;color:red;" id="succInfoId"></div>
		</div>
	</div>
<div id="planMonth" class="title_bar" style="clear: both; display:none;">
	<div class="title_bar_h">日期&nbsp;&nbsp;
		<span class="color_black">
		<select id="planSelect">
			<option value="2011" <s:if test="now_year==2011"> selected="selected"</s:if>>2011</option>
			<option value="2012" <s:if test="now_year==2012"> selected="selected"</s:if>>2012</option>
			<option value="2013" <s:if test="now_year==2013"> selected="selected"</s:if>>2013</option>
			<option value="2014" <s:if test="now_year==2014"> selected="selected"</s:if>>2014</option>
			<option value="2015" <s:if test="now_year==2015"> selected="selected"</s:if>>2015</option>
			<option value="2016" <s:if test="now_year==2016"> selected="selected"</s:if>>2016</option>
			<option value="2017" <s:if test="now_year==2017"> selected="selected"</s:if>>2017</option>
			<option value="2018" <s:if test="now_year==2018"> selected="selected"</s:if>>2018</option>
			<option value="2019" <s:if test="now_year==2019"> selected="selected"</s:if>>2019</option>
			<option value="2020" <s:if test="now_year==2020"> selected="selected"</s:if>>2020</option>
		</select>
		年
		<button onClick="loadPage(1,this)"  <s:if test="now_month==1">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>1</button>
		<button onClick="loadPage(2,this)"  <s:if test="now_month==2">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>2</button>
		<button onClick="loadPage(3,this)"  <s:if test="now_month==3">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>3</button>
		<button onClick="loadPage(4,this)"  <s:if test="now_month==4">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>4</button>
		<button onClick="loadPage(5,this)"  <s:if test="now_month==5">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>5</button>
		<button onClick="loadPage(6,this)"  <s:if test="now_month==6">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>6</button>
		<button onClick="loadPage(7,this)"  <s:if test="now_month==7">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>7</button>
		<button onClick="loadPage(8,this)"  <s:if test="now_month==8">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>8</button>
		<button onClick="loadPage(9,this)"  <s:if test="now_month==9">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>9</button>
		<button onClick="loadPage(10,this)" <s:if test="now_month==10">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>10</button>
		<button onClick="loadPage(11,this)" <s:if test="now_month==11">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>11</button>
		<button onClick="loadPage(12,this)" <s:if test="now_month==12">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>12</button>
		月内部任务
		</span>
	</div>
</div>	
<div id="centerSearchMonth" class="title_bar" style="clear: both; display:none;">
	<div class="title_bar_h">日期&nbsp;&nbsp;
		<span class="color_black">
		<select id="select_now_year" onchange="centerMonthClick($(this).val(),null);">
			<option value="2011" <s:if test="now_year==2011"> selected="selected"</s:if>>2011</option>
			<option value="2012" <s:if test="now_year==2012"> selected="selected"</s:if>>2012</option>
			<option value="2013" <s:if test="now_year==2013"> selected="selected"</s:if>>2013</option>
			<option value="2014" <s:if test="now_year==2014"> selected="selected"</s:if>>2014</option>
			<option value="2015" <s:if test="now_year==2015"> selected="selected"</s:if>>2015</option>
			<option value="2016" <s:if test="now_year==2016"> selected="selected"</s:if>>2016</option>
			<option value="2017" <s:if test="now_year==2017"> selected="selected"</s:if>>2017</option>
			<option value="2018" <s:if test="now_year==2018"> selected="selected"</s:if>>2018</option>
			<option value="2019" <s:if test="now_year==2019"> selected="selected"</s:if>>2019</option>
			<option value="2020" <s:if test="now_year==2020"> selected="selected"</s:if>>2020</option>
		</select>
		年
		<button id="month1"  onClick="centerMonthClick(null,1)"  <s:if test="now_month==1">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>1</button>
		<button id="month2"  onClick="centerMonthClick(null,2)"  <s:if test="now_month==2">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>2</button>
		<button id="month3"  onClick="centerMonthClick(null,3)"  <s:if test="now_month==3">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>3</button>
		<button id="month4"  onClick="centerMonthClick(null,4)"  <s:if test="now_month==4">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>4</button>
		<button id="month5"  onClick="centerMonthClick(null,5)"  <s:if test="now_month==5">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>5</button>
		<button id="month6"  onClick="centerMonthClick(null,6)"  <s:if test="now_month==6">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>6</button>
		<button id="month7"  onClick="centerMonthClick(null,7)"  <s:if test="now_month==7">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>7</button>
		<button id="month8"  onClick="centerMonthClick(null,8)"  <s:if test="now_month==8">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>8</button>
		<button id="month9"  onClick="centerMonthClick(null,9)"  <s:if test="now_month==9">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>9</button>
		<button id="month10" onClick="centerMonthClick(null,10)" <s:if test="now_month==10">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>10</button>
		<button id="month11" onClick="centerMonthClick(null,11)" <s:if test="now_month==11">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>11</button>
		<button id="month12" onClick="centerMonthClick(null,12)" <s:if test="now_month==12">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>12</button>
		月内部任务
		</span>
	</div>
</div>
	<div id="cost-container" style="clear:both;"></div>
</body>
</html>
