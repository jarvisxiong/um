<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link rel="stylesheet" href="${ctx}/resources/css/sc/sc.css" type="text/css" />
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/global.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>

<s:if test="scContractPrintList!=null && scContractPrintList.size()>0">

<div class="list_header2" style="height:24px;line-height:24px;text-align:left;">
<div style="float:left;padding:7px 5px 0 5px ;cursor:pointer;"  title="点击折叠" curStatusCd="1" onclick="showPrint(this,'flow-printHisList')">
<img src="${ctx}/resources/images/common/down_black.gif" id="flow-printHisList1"></img>

<img src="${ctx}/resources/images/common/left_black.gif" id="flow-printHisList0" style="display:none"></img>
</div>
<span class="list_header2" style="height:24px;line-height:24px;text-align:left;font-weight:bold">打印历史记录</span>
</div>
<div style="background-color: #ffffee;padding:0px 5px;margin:2px;" id="flow-printHisList">
	<table class="content_table"  width="100%" style="font-size:12px;"  cellpadding="0" cellspacing="0">	
		<s:iterator value="scContractPrintList" var="ra">
			<tr class="mainTr" >
				<td style="padding:1px 0px;height:100%;">
					<div style="float:left;margin-right:10px;">
						流水号：${contractPrintNo}
					</div>
					<div style="margin-right: 10px;float:left;"> 
						打印人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>
					</div>
					<div style="float:left;margin-right: 10px;">
					打印时间：<s:date name="createdDate" format="yyyy-MM-dd HH:mm" />
					</div>
					
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</s:if>


<s:if test="scContractApproveHisList!=null && scContractApproveHisList.size()>0">

<div class="list_header2" style="height:24px;line-height:24px;text-align:left;">
<div style="float:left;padding:7px 5px 0 5px ;cursor:pointer;"  title="点击折叠" curStatusCd="1" onclick="showPrint(this,'flow-operationList')">
<img src="${ctx}/resources/images/common/down_black.gif" id="flow-operationList1"></img>

<img src="${ctx}/resources/images/common/left_black.gif" id="flow-operationList0" style="display:none"></img>
</div>
<span class="list_header2" style="height:24px;line-height:24px;text-align:left;font-weight:bold">合同操作记录</span>
</div>
<div style="background-color: #ffffee;padding:0px 5px;margin:2px;" id="flow-operationList">
	<table class="content_table" id="showTableApproveRec" width="100%" style="font-size:12px;">
	
		<s:iterator value="scContractApproveHisList" var="ra">
			<tr class="mainTr" >
				<td style="padding:1px 0px;height:100%;">
					<div style="float:left;margin-right:10px;">
						操作人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>
					</div>
					<div style="margin-right: 10px;float:left;"> 
						操作内容：<s:if test="recordTypeCd == 40">审核通过</s:if>
						          <s:elseif test="recordTypeCd == 70">合同编写完成</s:elseif>
						           <s:elseif test="recordTypeCd == 80">合同签署完成</s:elseif>
					</div>
					<div style="float:left;margin-right: 10px;">
					操作时间：<s:date name="createdDate" format="yyyy-MM-dd HH:mm" />
					</div>
					
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</s:if>

<div class="list_header2" style="height:24px;line-height:24px;text-align:left;">
<div style="float:left;padding:7px 5px 0 5px ;cursor:pointer;"  title="点击折叠" curStatusCd="1" onclick="showPrint(this,'flow-flowList')">
<img src="${ctx}/resources/images/common/down_black.gif" id="flow-flowList1"></img>
<img src="${ctx}/resources/images/common/left_black.gif" id="flow-flowList0" style="display:none"></img>
</div>
<span style="font-size:14px;text-align:left;font-weight:bold;">历史版本<s:if test="resApproveInfoList.size==0">&nbsp;&nbsp;(无记录)</s:if></span>
</div>

<div style="background-color: #ffffee;padding:0px 5px;margin:2px;" id="flow-flowList">
	<table class="content_table" id="showTableApproveRec" width="100%" style="font-size:12px;">
		<s:iterator value="resApproveInfoList" var="ra">
			<tr class="mainTr" onclick="openDetail('${resApproveInfoId}')">
				<td style="padding:1px 0px;height:100%;">
					<div style="float:left;margin-right:10px;">
						编号：${approveCd}${serialNo }
					</div>
					<div style="margin-right: 10px;float:left;"> 
						发起人：${userName}
					</div>
					<div style="float:left;margin-right: 10px;">
						发起时间：<s:date name="startDate" format="yyyy-MM-dd HH:mm" />
					</div>
					<div style="float:left;">
						标题：${titleName }
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<script>
function showPrint(ele,tagName){
	$("#"+tagName+$(ele).attr("curStatusCd")).hide();
	if($(ele).attr("curStatusCd")=="1"){
		$(ele).attr("curStatusCd","0");
		$(ele).attr("title","点击展开");
	}else{
		$(ele).attr("title","点击折叠");
		$(ele).attr("curStatusCd","1");
		}
	$("#"+tagName).animate({height: 'toggle', opacity: 'toggle'}, "slow");
	$("#"+tagName+$(ele).attr("curStatusCd")).show();
}

function openDetail(resApproveInfoId) {
		var url = "${ctx}/res/res-approve-info.action?id="+resApproveInfoId;
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("res-approve-info","网批明细",url,true);
}
</script>
