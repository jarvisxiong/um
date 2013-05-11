<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看机构明细</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
</head>
<body>
<div style="overflow:auto;background-color: white;line-height: 24px;" fit="true" style="+position: relative">
	<table style="width:100%;" cellpadding="0" cellspacing="0">
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;;">
			<td style="padding-left:5px;width:120px"><a style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();" ><font color="#15428B">基本信息</font></a></td>
			<td style="width:35%;"></td>
			<td style="width:120px"></td>
			<td></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">机构业务编号:</td>
			<td id="orgBizCd">${orgBizCd}</td>
			<td>机构名称:</td>
			<td>${orgName}</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">机构简称:</td>
			<td>${shortOrgName}</td>
			<td>机构类型:</td>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapOrgType()" value="orgTypeCd"/> </td>
		</tr>
		<tr>
			<td style="padding-left:5px;">机构电话:</td>
			<td>${phoneDesc}</td>
			<td>传真:</td>
			<td>${faxDesc}</td>
		</tr>
		
		<tr>
			<td style="padding-left:5px;">机构负责人:</td>
			<td>
				<span id="orgMgrName" style="font-weight: bolder; ">${orgMgrName}</span>
			</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">上级机构名称(逻辑):</td>
			<td>
				<span id="parentOrgName">${parentOrgNameLogical}</span>
			</td>
			<td>上级机构名称(物理):</td>
			<td>
				<span id="parentPhysicalOrgName">${parentOrgNamePhysical}</span>
			</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">是否上市:</td>
			<td><p:code2name mapCodeName="mapPubFlg" value="pubFlg"/> </td>
			<td>显示序号:</td>
			<td>${sequenceNo}</td>
		</tr> 
		<tr>
			<td style="padding-left:5px;" valign="top">备注:</td>
			<td colspan="3">${remark}</td>
		</tr> 
	</table>
	
	<table style="width:100%;">
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();" >逻辑机构</div></td>
			<td style="padding-left:5px;">机构名称</td>
			<td style="padding-left:5px;">对应管理员</td>
			<td style="padding-left:5px;"></td>
		</tr>
		<s:iterator value="bubbleLogicalOrgs" id="bubbleOrg" status="st">
		<tr>
			<td style="padding-left:5px;"></td>
			<td style="padding-left:5px;"><s:property value='#st.index+1'/>.${bubbleOrg.orgName}</td>
			<td style="padding-left:5px;"  colspan="2"><p:code2name mapCodeName="orgMangerMap" value="#bubbleOrg.plasOrgId"/> </td>
		</tr>
		</s:iterator>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();" >物理机构</div></td>
			<td style="padding-left:5px;">机构名称</td>
			<td style="padding-left:5px;">对应管理员</td>
			<td style="padding-left:5px;"></td>
		</tr>
		<s:iterator value="bubblePhysicalOrgs" id="bubbleOrg" status="st">
		<tr>
			<td style="padding-left:5px;"></td>
			<td style="padding-left:5px;"><s:property value='#st.index+1'/>.${bubbleOrg.orgName}</td>
			<td style="padding-left:5px;" colspan="2"><p:code2name mapCodeName="orgMangerMap" value="#bubbleOrg.plasOrgId"/> </td>
		</tr>
		</s:iterator>
	</table>
		
	<table style="width:100%;">
	<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;"><td style="padding-left:5px;" colspan="4">审计信息</td></tr>
	</table>
	<table style="width:100%">
	<tr>
		<td style="padding-left:5px;width:120px;">创建时间:</td>
		<td style="width:20%;">${createdDate}</td>
		<td style="width:120px">创建人员:</td>
		<td style="width:120px">${creator}</td>
		<td style="width:15%;">创建部门:</td>
		<td>${createdDeptCd}</td>
	</tr>
	<tr>
		<td style="padding-left:5px;">最近更新时间:</td>
		<td>${updatedDate}</td>
		<td>最近更新人员:</td>
		<td>${updator}</td>
		<td>最近更新部门:</td>
		<td>${updatedDeptCd}</td>
	</tr>
	</table>
	<table style="width:100%;">			
		<tr class="panel-header" style="line-height:20px">
			<td colspan="4" ></td>
		</tr>
		<tr>
			<td colspan="4" style="width:100%;text-align: center; height: 200px">
			&nbsp;
		<%--
				<a class="buttom" name="btnBack" id="btnBack" href="plas-search-org.action">返回</a>
				<a class="buttom" href="${ctx}/plas/plas-search-org!detail.action?id=${plasOrgId}">刷新</a>
		 --%>
			</td>
		</tr>	
	</table> 
</div>
</body>
</html>