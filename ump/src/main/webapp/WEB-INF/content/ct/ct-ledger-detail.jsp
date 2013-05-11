<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html >
<head>
<title>目标成本台账</title>

<meta http-equiv="Content-Type" content="text/html" />
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/icon.css"></link>
<link type="text/css" rel="stylesheet"	href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet"	href="${ctx}/resources/css/common/common.css" />

<link type="text/css" rel="stylesheet"	href="${ctx}/resources/css/common/thickbox.css" />
<link type="text/css" rel="stylesheet"	href="${ctx}/resources/css/ct/ct.css" />
<link type="text/css" rel="stylesheet"	href="${ctx}/resources/js/jquery-easyui/themes/gray/tabs.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<script type="text/javascript"	src="${ctx}/resources/js/common/common.js"></script>

<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript"	src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript"	src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ct/ct-ledger-detail.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
</head>
<body>
<%
	String ctPlanId = (String)request.getAttribute("ctPlanId");
%>
<div id="bodyHead" class="bodyHead">
	<div class="headTitle" style="margin-top: 0px;width: 100%" >
	<div style="float:left; font-weight: bolder;font-size:14px;color:#333333; padding-right: 10px;">台账详情</div>
	<div style="float:left; margin-left: 10px;">
	下载:
					<s:url id="ct" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">ctLedgerdownTemplet1.doc</s:param>
				  	  <s:param name="realFileName">项目成本分类作业指引.doc</s:param>
				  	  <s:param name="bizModuleCd">ctDownload</s:param>
					</s:url>
	               <a href="${ct}" style="color: #0167A2; ">项目成本分类作业指引</a> &nbsp;
					<s:url id="ct" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">ctLedgerdownTemplet2.xls</s:param>
				  	  <s:param name="realFileName">合约规划作业指引.xls</s:param>
				  	  <s:param name="bizModuleCd">ctDownload</s:param>
					</s:url>
	<a href="${ct}" style="color: #0167A2;">合约规划作业指引</a>
	</div>
		<div style="float: right;margin-right: 14px;margin-top: 5px;" >
			<security:authorize ifAnyGranted="A_CT_RESOLVE">
			<input class="btn_blue" style="width: 120px;height:20px;line-height: 20px;" type="button" onclick=buildCostItem(); value="生成目标成本科目" id="btn_buildCostItem" />
			</security:authorize>
		</div>
	</div>
</div>
<div id="showOperation" style="width: 400px; height: 300px; text-align: center; vertical-align: center; background-color: #fff; display: none;">
<table>
	<tr>
		<td style="width: 15px"></td>
		<td style="text-align: center;">
			<div class="selectorItem" style="width: 180px">
				<div style="width: 170px; margin-left: 10px;">
					<div class="itemTitle"><span class="txtRed">*</span>地块</div>
					<div style="text-align: left"><input type="text" class="selectInput" id="landName" style="width: 96px" /> 
						<input type="hidden" id="txtlandId" /> 
						<input type="button" class="btn_orange" value="新增" id="btAddLand" />
					</div>
					<div id="landLoading" class="txtRed"><img src="${ctx}/resources/images/loading16.gif" />请稍等，载入数据中...</div>
					<div class="subSelectorItem">
						<ul class="ctslt-nav" id="landDiv">
						</ul>
					</div>
				</div>
			</div>
		</td>
		<td style="width: 20px"></td>
		<td style="text-align: center;">
			<div class="selectorItem" style="width: 200px">
				<div style="width: 170px; margin-left: 20px;">
					<div class="itemTitle"><span class="txtRed">*</span>业态(可多选)</div>
					<div style="text-align: left; display: none;" id="addOperDiv" ctr='operation'>
						<input type="text" class="selectInput" id="operTypeName" style="width: 96px" /> 
						<input type="button" class="btn_orange" value="新增" id="btAddOper" /> 
						<input type="hidden" id="operationId" /> 
						<input type="hidden" id="isAutoExpand" />
					</div>
					<div id="operationLoading" class="txtRed"><img
						src="${ctx}/resources/images/loading16.gif" />请稍等，载入数据中...</div>
					<div class="subSelectorItem" id="operDiv" ctr='operation'></div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
</div>
<div class="bodyContent" style="width: 100%;">
<div class="contentTitle " style="width: 99%;">项目目标成本信息简要&nbsp;&nbsp;&nbsp;&nbsp;
项目：<font color="red" size="6">${entity.projectName}</font>&nbsp;&nbsp;&nbsp; 
期数：<font color="red" size="6">${entity.periods}期</font>&nbsp;&nbsp;&nbsp;&nbsp;
	<security:authorize ifAnyGranted="A_CT_RESOLVE">
		<input type="button" value="目标成本" class="btn_green" onclick="ctCostItemShow();isHasBuildCostItem();" />&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="合约规划" class="btn_green" onclick="ctContPlanShow('${entity.ctLedgerId}','<%=ctPlanId %>');" />
    </security:authorize>
    <security:authorize ifAnyGranted="A_CT_RESOLVE,A_CT_VERI"  >
		<input type="button" value="查看调整记录" class="btn_green" style="width:100px;" id="btn_seeHisAdjustRecord"/>
   </security:authorize>
</div>
</div>
<div style="margin-left: 5px; margin-right: 5px;">
<table class="shop-table">
	<input type="hidden" value="${entity.projectCd}" name="projectCd" />
	<col style="width: 200px;" />
	<col />
	<col style="width: 200px;" />
	<col />
	<tr>
		<td class='itemTxtTitle'>审批权限</td>
		<td class='itemTxt'>
		 与酒店有关
		<s:if test="entity.approvePrivFlg==1">
			<input  type="checkbox" checked="true" disabled="disabled">
		</s:if>
		<s:else>
			<input  type="checkbox" disabled="disabled">
		</s:else>
		<input type="hidden" id="ctLdId" value="${entity.ctLedgerId}" />
		<input type="hidden" id="ctLandIdHide" />
		<input type="hidden" id="ctOperationIdHide"/>
		<input type="hidden" id="landNameHide" />
		<input type="hidden"id="operNameHide" />
		<input type="hidden" id="ctVersion" />
		<input type="hidden" id="ctLedgerStatus" value="${entity.ledgerStatus}" />
		<input type="hidden" id="isHasBuild"/>
		</td>
		<td class='itemTxtTitle'>是否可研</td>
		<td class='itemTxt'><s:if test="entity.searchFlg==0">否</s:if><s:else>是</s:else></td>
	</tr>
	<tr>
		<td class='itemTxtTitle'>开工时间</td>
		<td><s:date name="entity.startDate" format="yyyy-MM-dd" /></td>
		<td class='itemTxtTitle'>交房时间</td>
		<td class='itemTxt'><s:date name="entity.handDate" format="yyyy-MM-dd" /></td>
	</tr>

	<tr>
		<td class='itemTxtTitle'>总建筑面积(㎡)</td>
		<td  class='itemTxt'>${entity.totalConsArea}</td>
		<td class='itemTxtTitle'>计入容积率建筑面积(㎡)</td>
		<td class='itemTxt'>${entity.plotRateArea}</td>
	</tr>
	<tr>
		<td class='itemTxtTitle'>目标成本总额(元)</td>
		<td id='ctCostTargetTotalAmt' class='itemTxt'>${entity.costTargetTotalAmt}</td>
		<td class='itemTxtTitle'>容积率面积单方造价(元/㎡)</td>
		<td class='itemTxt'>${entity.plotRateUnitAmt}</td>
	</tr>
	<tr>
		<td class='itemTxtTitle'>主要内容及说明</td>
		<td class='itemTxt'>${entity.mainContentDesc}</td>
		<td class='itemTxtTitle' class='itemTxt'>网批来源</td>
		<td class='itemTxt'>
			<s:if test="entity.resApproveInfoId != null">
				<div style="cursor: pointer;"  onclick="openResTask('${entity.resApproveInfoId}');">来源于网批</div>
			</s:if>
			<s:else>
				无
			</s:else>
		</td>
	</tr>
</table>
</div>

<br />

<div id="targetCostDiv">
	<div id="ctLandNameDiv"></div>
	<div id="ctPlanShow"></div>
	<div id="ctLandToOperationDivs" 	class="costItemDiv" oncontextmenu="return false"></div>
	<security:authorize ifAnyGranted="A_CT_RESOLVE">
	<div id="divButton" class="costItemDiv">
		<span id="divButtonLeft"> 
			<input type="button" value="新增" id="btn_addItem" class="btn_green"></input>
			<input type="button" value="删除" id="btn_delItem" class="btn_green"></input>
			<input type="button" value="修改" id="btn_modItem" class="btn_green"></input>
			<input type="button" value="升序" id="btn_AscItem" class="btn_green"></input>
			<input type="button" value="降序" id="btn_DescItem" class="btn_green"></input> 
			<input type="button" value="提交" id="btn_publishNewVerison" class="btn_green"></input>
		</span>
		<span id="divButtonCenter"> 
			<input type="button" value="取消" id="btn_cancelItem" class="btn_green"></input> 
		</span> 
		<input type="button" value="分解目标成本" style="width: 90px;" id="btn_dismantleCost" class="btn_green" isShowCheck="false"></input>
	</div>
	</security:authorize>
	<security:authorize ifAnyGranted="A_CT_VERI">
	<div id="reviewBtnDiv" class="costItemDiv">
	
       <s:if test="entity.ledgerStatus==1 || entity.ledgerStatus== 9">
		<input type="button" value="审核" id="btn_approve" class="btn_green"></input> 
		
		<input type="button" value="驳回" id="btn_overrule" class="btn_green"></input> 
	</s:if>
	</div>
	</security:authorize>
	<security:authorize ifAnyGranted="A_CT_RESOLVE">
	<div id="modificationBtnDiv" class="costItemDiv">
	<s:if test="entity.ledgerStatus==2">
	<input type="button" value="调整" id="btn_modification" class="btn_green"></input> 
	</s:if>
	</div>
	</security:authorize>
	
	
	<div id="hisRemarkDiv" style="display:none">
		<table class="content_table">		
			<thead>
			<tr class="header">
				<th style="background: none;" width="30px;">次数</th>			
				<th class="pd-chill-tip" width="60px">
				<div class="ellipsisDiv_full">历史版本</div>
				</th>
				<th class="pd-chill-tip" >
				<div class="ellipsisDiv_full">调整内容描述</div>
				</th>				
			 </tr>
			</thead>
			 <s:iterator value="hisRemarkList" status="stat">		
				<tr class="mainTr"  ctr='remark' hid='${ctLedgerHismarkId}'>
					<td>${stat.index+1}</td>			
					<td>V${sequenceNo}.0</td>
				    <td class="pd-chill-tip"  title="${remark}" ><div class="ellipsisDiv_full">${remark}</div></td>
				</tr>				
		</s:iterator>
		</table>
	</div>
</div>
<script type="text/javascript">
/**
 * 打开网批
 */
function openResTask(id){
	var url = '${ctx}/res/res-approve-info.action?id='+id;
	openWindow('resApprove', '网上审批', url);
}

function openWindow(tabid, title, url) {
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab(tabid,title,url,true);
}
</script> 

</body>

</html>
