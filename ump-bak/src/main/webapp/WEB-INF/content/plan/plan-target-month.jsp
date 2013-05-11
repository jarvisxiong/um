<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.core.utils.RandomUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宝龙管理平台</title>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-target.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/plan-target.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
</head>
<body id="mainBody">
<div id="main">
	<div id="top">
		<div style="float:left;">
		<label style="font-weight: bold; font-size: 14px;float:left;margin-top:3px;">正在查看：</label><input id="centerName" class="inputBtn"
		<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VIEW,A_PLAN_WORK2_CENTER">
		 onmouseover="centerShow();"
		 </security:authorize>
		 <s:if test="bisFlg==1">onmouseover="centerByBisShow();"</s:if>
		  readonly="readonly" style="color:#F46614;font-weight: bold;float:left;"/>
		<div id="selectCenter" style="display: none;">
			<div class="centerTitle">地产</div>
			<s:iterator value="MapEstateCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index%5==0">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
			<div class="centerTitle">商业</div>
			<s:iterator value="MapBusinessCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index==5">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
			<div class="centerTitle">其它</div>
			<s:iterator value="MapOtherCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index==5">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
		</div>
		<div id="selectCenterBis" style="display: none;">
			<div class="centerTitle">商业</div>
			<s:iterator value="MapBusinessCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index==5">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
		</div>
		<span style="float:left;margin-left: 10px;"><input id="year" class="year"/>年
			<div id="selectYear" style="display: none;">
				<div class="yearDiv">请选择年份</div>
				<input type="button" class="btnCancel" value="2010" key="2010" onclick="selectYear(2010);"/>
				<input type="button" class="btnCancel" value="2011" key="2011" onclick="selectYear(2011);"/>
				<input type="button" class="btnCancel" value="2012" key="2012" onclick="selectYear(2012);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="2013" key="2013" onclick="selectYear(2013);"/>
				<input type="button" class="btnCancel" value="2014" key="2014" onclick="selectYear(2014);"/>
				<input type="button" class="btnCancel" value="2015" key="2015" onclick="selectYear(2015);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="2016" key="2016" onclick="selectYear(2016);"/>
				<input type="button" class="btnCancel" value="2017" key="2017" onclick="selectYear(2017);"/>
				<input type="button" class="btnCancel" value="2018" key="2018" onclick="selectYear(2018);"/>
			</div>
			<input id="month" class="month" readonly="readonly"/>月
			<div id="selectMonth" style="display: none;">
				<div class="monthDiv">请选择月份</div>
				<input type="button" class="btnCancel" value="01" key="01" onclick="selectMonth(01);"/>
				<input type="button" class="btnCancel" value="02" key="02" onclick="selectMonth(02);"/>
				<input type="button" class="btnCancel" value="03" key="03" onclick="selectMonth(03);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="04" key="04" onclick="selectMonth(04);"/>
				<input type="button" class="btnCancel" value="05" key="05" onclick="selectMonth(05);"/>
				<input type="button" class="btnCancel" value="06" key="06" onclick="selectMonth(06);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="07" key="07" onclick="selectMonth(07);"/>
				<input type="button" class="btnCancel" value="08" key="08" onclick="selectMonth(08);"/>
				<input type="button" class="btnCancel" value="09" key="09" onclick="selectMonth(09);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="10" key="10" onclick="selectMonth(10);"/>
				<input type="button" class="btnCancel" value="11" key="11" onclick="selectMonth(11);"/>
				<input type="button" class="btnCancel" value="12" key="12" onclick="selectMonth(12);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="全部" onclick="selectMonth(0);"/>
			</div>
			</span>
			</div>
			
			<div class="titleBarRight" style="float:right;">
			 <security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN">
			    <input class="btn_Green" type="button" onclick="exportExcel();" value="导出"/>
			 </security:authorize>
				<input class="btn_Green" type="button" onclick="jumpPage('1');" value="刷新"/>
				<input class="btn_Green" type="button" onclick="fullScreen();" value="全屏"/>
			</div>
	</div>
	<div id="mainContent">
		<form action="${ctx}/plan/plan-target!month.action" method="post" id="advanceSearch">
			<input type="hidden" id="queryPageNo" name="page.pageNo"/>
			<input type="hidden" id="centerCd" name="center" value="${currentCenterCd}"/>
			<input type="hidden" name="lockFlg" id="lockFlg" />
			<input type="hidden" id="currentCenterCd" name="currentCenterCd" value="${currentCenterCd}"/>
			<input type="hidden" id="currentPlanMonth" name="currentPlanMonth" value="${currentPlanMonth}"/>
			<input type="hidden" id="currentPlanYear" name="currentPlanYear" value="${currentPlanYear}"/>
			<input type="hidden" id="orderBy" name="orderBy">
			<input type="hidden" id="order" name="order">
			<input type="hidden" id="orderColMark">
			<input type="hidden" id="orderMark">
			<input type="hidden" id="targetYear" name="targetYear" value="">
			<input type="hidden" id="targetMonth" name="targetMonth" value="">
		<!-- 搜索条件模块 -->
		<div id="searchPanel" style="display: none;">
		<table>
			<tr>
				<td align="right" width="80px;">类型：</td>
				<td width="100px;"><select id="planType_s" name="planType" style="width: 100%;">
						<option value="">--全部--</option>
						<option value="1">年计划</option>
						<option value="2">月会工作</option>
						<option value="3">项目管理</option>
						<option value="4">指令单</option>
						<option value="5">综合</option>
						<option value="6">季度KPI</option>
						<option value="7">四级计划</option>
						<option value="8">中心工作</option>
					</select></td>
				<td align="right" width="100px;">状态：</td>
				<td width="100px;"><select id="status_s" name="status" style="width: 100%;">
						<option value="">--全部--</option>
						<option value="1">执行</option>
						<option value="2">预完成</option>
						<option value="3">完成</option>
						<option value="4">完成但曾经过期</option>
						<option value="5">过期</option>
						<option value="6">非考核性过期</option>
						<option value="7">非本月</option>
						<option value="8">隐藏</option>
						<option value="9">申请删除</option>
						<option value="10">删除</option>
					</select></td>
				<td width="300px;"></td>
			</tr>
			<tr>
				<td align="right">工作内容：</td>
				<td colspan="3"><input id="content_s" name="content" style="width: 100%;"/></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<input type="button" class="btnSearch btn_Green" onclick="jumpPage('1')" value="搜索" />
					<input type="button" class="btnCancel btn_Green" onclick="hideOrShow_Search();" value="返回"/>
				</td>
			</tr>
		</table>
		</div>
		</form>

		<div class="actionTab" style="float:left">
		<input type="button" class="btn_Gray inputBtn" style="margin-left: 5px;" onclick="hideOrShow_Search();" value="高级搜索"/>
		<input type="button" class="btn_Blue inputBtn" onclick="openPlanTargetYear();" value="年计划"/>
		</div>

	<s:set var="entityTmpId_New" ><%=RandomUtils.generateTmpEntityId() %></s:set>
	<div id="contentPanel">
	</div>
	</div>
</div>
</body>
</html>

<script language="javascript">
	var currentPlanYear = "${currentPlanYear}";
	var currentPlanMonth = "${currentPlanMonth}";
	var opened_entityid = "${opened_entityid}";
	var bisFlg="${bisFlg}";
	$(function() {
		init();
		centerHide();
		monthHide();
		yearHide();
		if(""==$("#currentCenterCd").val()){
			$("#currentCenterCd").val("147");
		}
		$("#centerName").val(CENTER_NAMES[$("#currentCenterCd").val()]);
		jumpPage("1",true);
	});
	// 打开要编辑的工作计划--与商业同用，商业有自己特殊需求
	function openEditPlanTarget(id) {
		if(lastPlanTargetId) {
			lastDisplay = $("#edit_monthPlan_" + lastPlanTargetId).css("display");
			if(lastDisplay == "block" && lastPlanTargetId != id){
				$("#edit_monthPlan_" + lastPlanTargetId).hide();
			}
		}
		var display = $("#edit_monthPlan_" + id).css("display");
		if(display == "none") {
			try{
				if("inline"==$("#attention_unread_img_"+id).css("display")){
					setAttentionRead("planTarget",id);
				}
			}catch(e){}
			if("7" == $("#editStatusVal_" + id).val()) { // 当前状态为被本月
				s_h_other(id, "none"); // 隐藏除退回外的其它按钮
			}
			
			// 计划类型ID编号转换为文本
			$("#td_edit_pt_" + id + " input[class=editPlanType]").val($.trim($("#td_pt_" + id).html()));
			// 获取附件列表
			//fileList(id);
			var selCen =$("#centerCd").val();
			var curCen=",153,155,156,154,157,869,439,453,";
			if(curCen.indexOf(","+selCen+",")>0){
				//商业
			<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK_BISCEO">
				fileList(id);
			</security:authorize>
			<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK_STAFF,A_PLAN_WORK2_DEPT,A_PLAN_WORK2_VIEW">
			    fileList(id,1);
			</security:authorize>
			}else{
				fileList(id);
			}
			$("#edit_monthPlan_" + id).show();
			editPlanNumber = $("#editPlanNumber_" + id).val();
			editPlanType = $("#td_edit_pt_" + id + " input[class=editPlanType]").val();
			editContent = $("#td_edit_ct_" + id + " textarea").val();
			editTargetDate = $("#td_edit_td_" + id + " input").val();
			$("#tr_" + id).css({"background-color": "#D9DEE6"});
		} else {
			$("#edit_monthPlan_" + id).hide();
			$("#tr_" + id).css("background-color", "#FFF");
		}
		lastPlanTargetId = id;
		autoHeightTarget("contentTable");
	}
	//上传附件
	function openAttachment(title,entityId){
		cur_entityId = entityId;
		ymPrompt.win({
			message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=planTarget&filterType=image|office&bizEntityName=PlanTarget",
			width:500,
			height:300,
			title:title,
			iframe:true,
			handler : attachRefresh
			});
	}
	var cur_entityId = null;	//附件的临时存储变量
	function attachRefresh(){
		var url = _ctx+"/plan/plan-target!checkStatus.action?targetId=" + cur_entityId;
		$("#editPlanTarget_" + cur_entityId).attr("action", url).ajaxSubmit(function(result){
			if(result == "is") {
				$("#edit_atta_imgSpan_" + cur_entityId).html("<img src=" + _ctx + "/resources/images/bid/atta.gif />");
			} else if(result == "not") {
				$("#edit_atta_imgSpan_" + cur_entityId).html("");
			}
			var selCen =$("#centerCd").val();
			var curCen=",153,155,156,154,157,869,439,453,";
			if(curCen.indexOf(","+selCen+",")>0){
				//商业
			<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK_BISCEO">
				fileList(cur_entityId);
			</security:authorize>
			<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER">
			    fileList(cur_entityId,1);
			</security:authorize>
			}else{
				fileList(cur_entityId);
			}
		});
	}
	/***
	 * 解锁计划
	 */
	function unlock() {
		var flg=confirm("您要解锁计划吗？");
		if(flg){
			 $("#lockFlg").val("0"); // 解锁计划
			var url = "" +_ctx+ "/plan/plan-target!lockOperation.action";
			$("#advanceSearch").attr("action", url).submit();
		}
	}
</script>