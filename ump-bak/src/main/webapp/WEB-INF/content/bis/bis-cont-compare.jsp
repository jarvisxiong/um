<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<!--
-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"  />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
<title>商业合同管理</title>

</head>
<body>

<input type="hidden" id="bisContId" name="bisContId" value="${bisContId}"/>

<div class="title_bar">
	<div style="font-weight:bold;padding-left:8px;font-size:14px;float:left;">合同对比</div>
	<div style="float:right;font-size:12px;padding-right:10px;line-height:30px;">
		<input type="button" value="返回" class="btn_green" onclick="goBackCompare();" style="margin-top: 4px;"/>
	</div>
</div>

<div id="resultDiv" style="overflow: scroll; overflow-x:hidden; width:100%;">
<table style="width:100%;" cellspacing="0" cellpadding="0" >
	<tr>
		<td style="padding-top: 20px; padding-left: 10px; padding-right: 8px;">
			<table class="tb_noborder" cellspacing="0" cellpadding="0" style="width:100%;">
				<col width="80"/>
				<col width="200"/>
				<col width="200"/>
				<col />
				<tr style="height:30px; color: #464646">
					<td >项目名称：</td>
					<td>${bisProjectName}</td>
					<td style="text-align: right;">合同类型：</td>
					<td>
						<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="compareCont1.contBigTypeCd"/>-
						<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="compareCont1.contSmallTypeCd"/>
					</td>
				</tr>
				<s:if test="compareCont1.creator!=null">
				<tr style="height:30px; color: #464646">
					<td >创建人：</td>
					<td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("compareCont1.creator")) %></td>
					<td style="text-align: right;">创建时间：</td>
					<td><s:date name="compareCont1.createdDate" format="yyyy-MM-dd"/></td>
				</tr>
				</s:if>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="padding-top: 10px; padding-left: 8px; padding-right: 8px;">
			<table class="sup_table" cellspacing="0" cellpadding="0" style="background-color:#e4e7ec;overflow:hidden;width:100%;">
				<col />
				<tr style="height:38px;background-color: #909090;ont-size:14px;">
					<td style="padding-left: 10px; font-weight: bold; font-size: 12px;"><span style="color: #fff">合同基本信息</span></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="padding-left: 8px; padding-right: 8px;">
			<table class="mainTable" style="width: 100%">
				<col width="20%"/>
				<col width="40%"/>
				<col width="40%"/>
				<s:if test="compareCont1.checkDate!=null || compareCont2.checkDate!=null ">
				<tr>
					<td align="right">审核时间：</td>
					<td <s:if test="compareCont1.checkDate != compareCont2.checkDate">style="color: red;"</s:if>><s:date name="compareCont1.checkDate" format="yyyy-MM-dd"/></td>
					<td <s:if test="compareCont1.checkDate != compareCont2.checkDate">style="color: red;"</s:if>><s:date name="compareCont2.checkDate" format="yyyy-MM-dd"/></td>
				</tr>
				</s:if>
				<tr>
					<td align="right">合同编号：</td>
					<td <s:if test="compareCont1.contNo != compareCont2.contNo">style="color: red;"</s:if>>${compareCont1.contNo}</td>
					<td <s:if test="compareCont1.contNo != compareCont2.contNo">style="color: red;"</s:if>>${compareCont2.contNo}</td>
				</tr>
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd!=6">
				<s:set var="shName1">
					<s:property value="%{getShopName(compareCont1.bisTenantId)}"/>
				</s:set>
				<s:set var="shName2">
					<s:property value="%{getShopName(compareCont2.bisTenantId)}"/>
				</s:set>
				<s:set var="shConnName1">
					<s:property value="%{getShopConnName(compareCont1.bisTenantId)}"/>
				</s:set>
				<s:set var="shConnName2">
					<s:property value="%{getShopConnName(compareCont2.bisTenantId)}"/>
				</s:set>
				<s:if test="#shName1!=null || #shName2!=null ">
				<tr>
					<td align="right">品牌：</td>
					<td <s:if test="#shName1 != #shName2">style="color: red;"</s:if>>${shName1}</td>
					<td <s:if test="#shName1 != #shName2">style="color: red;"</s:if>>${shName2}</td>
				</tr>
				</s:if>
				<s:if test="#shConnName1!=null || #shConnName2!=null ">
				<tr>
					<td align="right">经销商：</td>
					<td <s:if test="#shConnName1 != #shConnName2">style="color: red;"</s:if>>${shConnName1}</td>
					<td <s:if test="#shConnName1 != #shConnName2">style="color: red;"</s:if>>${shConnName2}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==2">
				<s:if test="compareCont1.bisContShopBacks[0].owner!=null || compareCont2.bisContShopBacks[0].owner!=null ">
				<tr>
					<td align="right">业主：</td>
					<td <s:if test="compareCont1.bisContShopBacks[0].owner != compareCont2.bisContShopBacks[0].owner">style="color: red;"</s:if>>${compareCont1.bisContShopBacks[0].owner}</td>
					<td <s:if test="compareCont1.bisContShopBacks[0].owner != compareCont2.bisContShopBacks[0].owner">style="color: red;"</s:if>>${compareCont2.bisContShopBacks[0].owner}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.bisContShopBacks[0].sellPrice!=null || compareCont2.bisContShopBacks[0].sellPrice!=null ">
				<tr>
					<td align="right">售价：</td>
					<td <s:if test="compareCont1.bisContShopBacks[0].sellPrice != compareCont2.bisContShopBacks[0].sellPrice">style="color: red;"</s:if>>${compareCont1.bisContShopBacks[0].sellPrice}</td>
					<td <s:if test="compareCont1.bisContShopBacks[0].sellPrice != compareCont2.bisContShopBacks[0].sellPrice">style="color: red;"</s:if>>${compareCont2.bisContShopBacks[0].sellPrice}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==6">
				<s:if test="compareCont1.owner!=null || compareCont2.owner!=null ">
				<tr>
					<td align="right">业主：</td>
					<td <s:if test="compareCont1.owner != compareCont2.owner">style="color: red;"</s:if>>${compareCont1.owner}</td>
					<td <s:if test="compareCont1.owner != compareCont2.owner">style="color: red;"</s:if>>${compareCont2.owner}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd==2">
				<s:if test="compareCont1.bisContFlatProps[0].owner!=null || compareCont2.bisContFlatProps[0].owner!=null ">
				<tr>
					<td align="right">业主：</td>
					<td <s:if test="compareCont1.bisContFlatProps[0].owner != compareCont2.bisContFlatProps[0].owner">style="color: red;"</s:if>>${compareCont1.bisContFlatProps[0].owner}</td>
					<td <s:if test="compareCont1.bisContFlatProps[0].owner != compareCont2.bisContFlatProps[0].owner">style="color: red;"</s:if>>${compareCont2.bisContFlatProps[0].owner}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.bisContFlatProps[0].idCardNo!=null || compareCont2.bisContFlatProps[0].idCardNo!=null ">
				<tr>
					<td align="right">身份证号码：</td>
					<td <s:if test="compareCont1.bisContFlatProps[0].idCardNo != compareCont2.bisContFlatProps[0].idCardNo">style="color: red;"</s:if>>${compareCont1.bisContFlatProps[0].idCardNo}</td>
					<td <s:if test="compareCont1.bisContFlatProps[0].idCardNo != compareCont2.bisContFlatProps[0].idCardNo">style="color: red;"</s:if>>${compareCont2.bisContFlatProps[0].idCardNo}</td>
				</tr>
				</s:if>
				</s:if>
				<s:set var="stName1">
					<s:if test="compareCont1.tempHisId != null">
					<s:property value="%{getHisStoreNos(compareCont1.tempHisId)}"/>
					</s:if>
					<s:else>
					<s:property value="%{getStoreNos(compareCont1.bisContId)}"/>
					</s:else>
				</s:set>
				<s:set var="stName2">
					<s:if test="compareCont2.tempHisId != null">
					<s:property value="%{getHisStoreNos(compareCont2.tempHisId)}"/>
					</s:if>
					<s:else>
					<s:property value="%{getStoreNos(compareCont2.bisContId)}"/>
					</s:else>
				</s:set>
				<s:if test="compareCont1.contBigTypeCd==3">
				<s:if test="#stName1!=null || #stName2!=null ">
				<tr>
					<td align="right">多经编号：</td>
					<td <s:if test="#stName1 != #stName2">style="color: red;"</s:if>>${stName1}</td>
					<td <s:if test="#stName1 != #stName2">style="color: red;"</s:if>>${stName2}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.bisContMultis[0].renterName!=null || compareCont2.bisContMultis[0].renterName!=null ">
				<tr>
					<td align="right">承租方：</td>
					<td <s:if test="compareCont1.bisContMultis[0].renterName != compareCont2.bisContMultis[0].renterName">style="color: red;"</s:if>>${compareCont1.bisContMultis[0].renterName}</td>
					<td <s:if test="compareCont1.bisContMultis[0].renterName != compareCont2.bisContMultis[0].renterName">style="color: red;"</s:if>>${compareCont2.bisContMultis[0].renterName}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.buildingName!=null || compareCont2.buildingName!=null">
				<tr>
					<td align="right">楼栋名称：</td>
					<td <s:if test="compareCont1.buildingName != compareCont2.buildingName">style="color: red;"</s:if>>${compareCont1.buildingName}</td>
					<td <s:if test="compareCont1.buildingName != compareCont2.buildingName">style="color: red;"</s:if>>${compareCont2.buildingName}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd == 1">
				<s:if test="#stName1!=null || #stName2!=null ">
				<tr>
					<td align="right">商铺：</td>
					<td <s:if test="#stName1 != #stName2">style="color: red;"</s:if>>${stName1}</td>
					<td <s:if test="#stName1 != #stName2">style="color: red;"</s:if>>${stName2}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd == 2">
				<s:if test="#stName1!=null || #stName2!=null ">
				<tr>
					<td align="right">公寓：</td>
					<td <s:if test="#stName1 != #stName2">style="color: red;"</s:if>>${stName1}</td>
					<td <s:if test="#stName1 != #stName2">style="color: red;"</s:if>>${stName2}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.square!=null || compareCont2.square!=null ">
				<tr>
					<td align="right">建筑面积：</td>
					<td <s:if test="compareCont1.square != compareCont2.square">style="color: red;"</s:if>>${compareCont1.square}</td>
					<td <s:if test="compareCont1.square != compareCont2.square">style="color: red;"</s:if>>${compareCont2.square}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.innerSquare!=null || compareCont2.innerSquare!=null ">
				<tr>
					<td align="right">套内面积：</td>
					<td <s:if test="compareCont1.innerSquare != compareCont2.innerSquare">style="color: red;"</s:if>>${compareCont1.innerSquare}</td>
					<td <s:if test="compareCont1.innerSquare != compareCont2.innerSquare">style="color: red;"</s:if>>${compareCont2.innerSquare}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.rentSquare!=null || compareCont2.rentSquare!=null ">
				<tr>
					<td align="right">计租面积：</td>
					<td <s:if test="compareCont1.rentSquare != compareCont2.rentSquare">style="color: red;"</s:if>>${compareCont1.rentSquare}</td>
					<td <s:if test="compareCont1.rentSquare != compareCont2.rentSquare">style="color: red;"</s:if>>${compareCont2.rentSquare}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.contStartDate!=null || compareCont2.contStartDate!=null ">
				<tr>
					<td align="right">合同开始日期：</td>
					<td <s:if test="compareCont1.contStartDate != compareCont2.contStartDate">style="color: red;"</s:if>><s:date name="compareCont1.contStartDate" format="yyyy-MM-dd"/></td>
					<td <s:if test="compareCont1.contStartDate != compareCont2.contStartDate">style="color: red;"</s:if>><s:date name="compareCont2.contStartDate" format="yyyy-MM-dd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.contEndDate!=null || compareCont2.contEndDate!=null ">
				<tr>
					<td align="right">合同结束日期：</td>
					<td <s:if test="compareCont1.contEndDate != compareCont2.contEndDate">style="color: red;"</s:if>><s:date name="compareCont1.contEndDate" format="yyyy-MM-dd"/></td>
					<td <s:if test="compareCont1.contEndDate != compareCont2.contEndDate">style="color: red;"</s:if>><s:date name="compareCont2.contEndDate" format="yyyy-MM-dd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.rentDate!=null || compareCont2.rentDate!=null ">
				<tr>
					<td align="right">计租起始日：</td>
					<td <s:if test="compareCont1.rentDate != compareCont2.rentDate">style="color: red;"</s:if>><s:date name="compareCont1.rentDate" format="yyyy-MM-dd"/></td>
					<td <s:if test="compareCont1.rentDate != compareCont2.rentDate">style="color: red;"</s:if>><s:date name="compareCont2.rentDate" format="yyyy-MM-dd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.signDate!=null || compareCont2.signDate!=null ">
				<tr>
					<td align="right">签约时间：</td>
					<td <s:if test="compareCont1.signDate != compareCont2.signDate">style="color: red;"</s:if>><s:date name="compareCont1.signDate" format="yyyy-MM-dd"/></td>
					<td <s:if test="compareCont1.signDate != compareCont2.signDate">style="color: red;"</s:if>><s:date name="compareCont2.signDate" format="yyyy-MM-dd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.rentYears!=null || compareCont2.rentYears!=null ">
				<tr>
					<td align="right">合同期限：</td>
					<td <s:if test="compareCont1.rentYears != compareCont2.rentYears">style="color: red;"</s:if>>${compareCont1.rentYears}</td>
					<td <s:if test="compareCont1.rentYears != compareCont2.rentYears">style="color: red;"</s:if>>${compareCont2.rentYears}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd==3">
				<s:if test="compareCont1.contMoney!=null || compareCont2.contMoney!=null ">
				<tr>
					<td align="right">合同金额：</td>
					<td <s:if test="compareCont1.contMoney != compareCont2.contMoney">style="color: red;"</s:if>>${compareCont1.contMoney}</td>
					<td <s:if test="compareCont1.contMoney != compareCont2.contMoney">style="color: red;"</s:if>>${compareCont2.contMoney}</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.contBigTypeCd==2">
				<s:if test="compareCont1.bisContFlatProps[0].housingTypeCd!=null || compareCont2.bisContFlatProps[0].housingTypeCd!=null ">
				<tr>
					<td align="right">住宅类型：</td>
					<td <s:if test="compareCont1.bisContFlatProps[0].housingTypeCd != compareCont2.bisContFlatProps[0].housingTypeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" value="compareCont1.bisContFlatProps[0].housingTypeCd"/></td>
					<td <s:if test="compareCont1.bisContFlatProps[0].housingTypeCd != compareCont2.bisContFlatProps[0].housingTypeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" value="compareCont2.bisContFlatProps[0].housingTypeCd"/></td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="compareCont1.equityNature!=null || compareCont2.equityNature!=null ">
				<tr>
					<td align="right">产权性质：</td>
					<td <s:if test="compareCont1.equityNature != compareCont2.equityNature">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" value="compareCont1.equityNature"/></td>
					<td <s:if test="compareCont1.equityNature != compareCont2.equityNature">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" value="compareCont2.equityNature"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.manageCd!=null || compareCont2.manageCd!=null ">
				<tr>
					<td align="right">经营性质：</td>
					<td <s:if test="compareCont1.manageCd != compareCont2.manageCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="compareCont1.manageCd"/></td>
					<td <s:if test="compareCont1.manageCd != compareCont2.manageCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="compareCont2.manageCd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.layoutCd!=null || compareCont2.layoutCd!=null ">
				<tr>
					<td align="right">业态：</td>
					<td <s:if test="compareCont1.layoutCd != compareCont2.layoutCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="compareCont1.layoutCd"/></td>
					<td <s:if test="compareCont1.layoutCd != compareCont2.layoutCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="compareCont2.layoutCd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.coopWayCd!=null || compareCont2.coopWayCd!=null ">
				<tr>
					<td align="right">合作方式：</td>
					<td <s:if test="compareCont1.coopWayCd != compareCont2.coopWayCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisCoopWay()" value="compareCont1.coopWayCd"/></td>
					<td <s:if test="compareCont1.coopWayCd != compareCont2.coopWayCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisCoopWay()" value="compareCont2.coopWayCd"/></td>
				</tr>
				</s:if>
				<s:if test="compareCont1.connPeople!=null || compareCont2.connPeople!=null ">
				<tr>
					<td align="right">联系人：</td>
					<td <s:if test="compareCont1.connPeople != compareCont2.connPeople">style="color: red;"</s:if>>${compareCont1.connPeople}</td>
					<td <s:if test="compareCont1.connPeople != compareCont2.connPeople">style="color: red;"</s:if>>${compareCont2.connPeople}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.connTel!=null || compareCont2.connTel!=null ">
				<tr>
					<td align="right">联系电话：</td>
					<td <s:if test="compareCont1.connTel != compareCont2.connTel">style="color: red;"</s:if>>${compareCont1.connTel}</td>
					<td <s:if test="compareCont1.connTel != compareCont2.connTel">style="color: red;"</s:if>>${compareCont2.connTel}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.connFax!=null || compareCont2.connFax!=null ">
				<tr>
					<td align="right">联系传真：</td>
					<td <s:if test="compareCont1.connFax != compareCont2.connFax">style="color: red;"</s:if>>${compareCont1.connFax}</td>
					<td <s:if test="compareCont1.connFax != compareCont2.connFax">style="color: red;"</s:if>>${compareCont2.connFax}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.connAccountNo!=null || compareCont2.connAccountNo!=null ">
				<tr>
					<td align="right">联系人银行账号：</td>
					<td <s:if test="compareCont1.connAccountNo != compareCont2.connAccountNo">style="color: red;"</s:if>>${compareCont1.connAccountNo}</td>
					<td <s:if test="compareCont1.connAccountNo != compareCont2.connAccountNo">style="color: red;"</s:if>>${compareCont2.connAccountNo}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.connAddr!=null || compareCont2.connAddr!=null ">
				<tr>
					<td align="right">联系地址：</td>
					<td <s:if test="compareCont1.connAddr != compareCont2.connAddr">style="color: red;"</s:if>>${compareCont1.connAddr}</td>
					<td <s:if test="compareCont1.connAddr != compareCont2.connAddr">style="color: red;"</s:if>>${compareCont2.connAddr}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.bisPeople!=null || compareCont2.bisPeople!=null ">
				<tr>
					<td align="right">招商人员：</td>
					<td <s:if test="compareCont1.bisPeople != compareCont2.bisPeople">style="color: red;"</s:if>>${compareCont1.bisPeople}</td>
					<td <s:if test="compareCont1.bisPeople != compareCont2.bisPeople">style="color: red;"</s:if>>${compareCont2.bisPeople}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.partyA!=null || compareCont2.partyA!=null ">
				<tr>
					<td align="right">甲方：</td>
					<td <s:if test="compareCont1.partyA != compareCont2.partyA">style="color: red;"</s:if>>${compareCont1.partyA}</td>
					<td <s:if test="compareCont1.partyA != compareCont2.partyA">style="color: red;"</s:if>>${compareCont2.partyA}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.partyB!=null || compareCont2.partyB!=null ">
				<tr>
					<td align="right">乙方：</td>
					<td <s:if test="compareCont1.partyB != compareCont2.partyB">style="color: red;"</s:if>>${compareCont1.partyB}</td>
					<td <s:if test="compareCont1.partyB != compareCont2.partyB">style="color: red;"</s:if>>${compareCont2.partyB}</td>
				</tr>
				</s:if>
				<s:if test="compareCont1.partyC!=null || compareCont2.partyC!=null ">
				<tr>
					<td align="right">丙方：</td>
					<td <s:if test="compareCont1.partyC != compareCont2.partyC">style="color: red;"</s:if>>${compareCont1.partyC}</td>
					<td <s:if test="compareCont1.partyC != compareCont2.partyC">style="color: red;"</s:if>>${compareCont2.partyC}</td>
				</tr>
				</s:if>
				
				<tr>
					<td align="right">合同内部批文：</td>
					<td><textarea class="inputBorder contentTextArea" readonly="readonly" <s:if test="compareCont1.contApproval != compareCont2.contApproval">style="width:100%;height:60px;resize:none;color:red;"</s:if><s:else>style="width:100%;height:60px;resize:none;"</s:else>>${compareCont1.contApproval}</textarea></td>
					<td><textarea class="inputBorder contentTextArea" readonly="readonly" <s:if test="compareCont1.contApproval != compareCont2.contApproval">style="width:100%;height:60px;resize:none;color:red;"</s:if><s:else>style="width:100%;height:60px;resize:none;"</s:else>>${compareCont2.contApproval}</textarea></td>
				</tr>
				<tr>
					<td align="right">合同条款：</td>
					<td><textarea class="inputBorder contentTextArea" readonly="readonly" <s:if test="compareCont1.contContent != compareCont2.contContent">style="width:100%;height:60px;resize:none;color:red;"</s:if><s:else>style="width:100%;height:60px;resize:none;"</s:else>>${compareCont1.contContent}</textarea></td>
					<td><textarea class="inputBorder contentTextArea" readonly="readonly" <s:if test="compareCont1.contContent != compareCont2.contContent">style="width:100%;height:60px;resize:none;color:red;"</s:if><s:else>style="width:100%;height:60px;resize:none;"</s:else>>${compareCont2.contContent}</textarea></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="padding-top: 10px; padding-left: 8px; padding-right: 8px;">
			<table class="sup_table" cellspacing="0" cellpadding="0" style="background-color:#e4e7ec;overflow:hidden;width:100%;">
				<col />
				<tr style="height:38px;background-color: #909090;ont-size:14px;">
					<td style="padding-left: 10px; font-weight: bold; font-size: 12px;"><span style="color: #fff">合同类别信息</span></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="padding-left: 8px; padding-right: 8px;">
			<table class="mainTable" style="width: 100%">
				<col width="20%"/>
				<col width="40%"/>
				<col width="40%"/>
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==1">
					<s:if test="compareCont1.bisContShopRents[0].paymentModeCd!=null || compareCont2.bisContShopRents[0].paymentModeCd!=null ">
					<tr>
						<td align="right">支付方式：</td>
						<td <s:if test="compareCont1.bisContShopRents[0].paymentModeCd != compareCont2.bisContShopRents[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont1.bisContShopRents[0].paymentModeCd"/></td>
						<td <s:if test="compareCont1.bisContShopRents[0].paymentModeCd != compareCont2.bisContShopRents[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont2.bisContShopRents[0].paymentModeCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopRents[0].payCycleCd!=null || compareCont2.bisContShopRents[0].payCycleCd!=null ">
					<tr>
						<td align="right">租金支付周期：</td>
						<td <s:if test="compareCont1.bisContShopRents[0].payCycleCd != compareCont2.bisContShopRents[0].payCycleCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPayCycle()" value="compareCont1.bisContShopRents[0].payCycleCd"/></td>
						<td <s:if test="compareCont1.bisContShopRents[0].payCycleCd != compareCont2.bisContShopRents[0].payCycleCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPayCycle()" value="compareCont2.bisContShopRents[0].payCycleCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopRents[0].earnestMoney!=null || compareCont2.bisContShopRents[0].earnestMoney!=null ">
					<tr>
						<td align="right">租金履约保证金：</td>
						<td <s:if test="compareCont1.bisContShopRents[0].earnestMoney != compareCont2.bisContShopRents[0].earnestMoney">style="color: red;"</s:if>>${compareCont1.bisContShopRents[0].earnestMoney}</td>
						<td <s:if test="compareCont1.bisContShopRents[0].earnestMoney != compareCont2.bisContShopRents[0].earnestMoney">style="color: red;"</s:if>>${compareCont2.bisContShopRents[0].earnestMoney}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopRents[0].freeRentPeriod!=null || compareCont2.bisContShopRents[0].freeRentPeriod!=null ">
					<tr>
						<td align="right">免租期：</td>
						<td <s:if test="compareCont1.bisContShopRents[0].freeRentPeriod != compareCont2.bisContShopRents[0].freeRentPeriod">style="color: red;"</s:if>>${compareCont1.bisContShopRents[0].freeRentPeriod}</td>
						<td <s:if test="compareCont1.bisContShopRents[0].freeRentPeriod != compareCont2.bisContShopRents[0].freeRentPeriod">style="color: red;"</s:if>>${compareCont2.bisContShopRents[0].freeRentPeriod}</td>
					</tr>
					</s:if>
				</s:if>
				
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==2">
					<s:if test="compareCont1.bisContShopBacks[0].paymentModeCd!=null || compareCont2.bisContShopBacks[0].paymentModeCd!=null ">
					<tr>
						<td align="right">支付方式：</td>
						<td <s:if test="compareCont1.bisContShopBacks[0].paymentModeCd != compareCont2.bisContShopBacks[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont1.bisContShopBacks[0].paymentModeCd"/></td>
						<td <s:if test="compareCont1.bisContShopBacks[0].paymentModeCd != compareCont2.bisContShopBacks[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont2.bisContShopBacks[0].paymentModeCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopBacks[0].paymentTime!=null || compareCont2.bisContShopBacks[0].paymentTime!=null ">
					<tr>
						<td align="right">付款日期：</td>
						<td <s:if test="compareCont1.bisContShopBacks[0].paymentTime != compareCont2.bisContShopBacks[0].paymentTime">style="color: red;"</s:if>>${compareCont1.bisContShopBacks[0].paymentTime}</td>
						<td <s:if test="compareCont1.bisContShopBacks[0].paymentTime != compareCont2.bisContShopBacks[0].paymentTime">style="color: red;"</s:if>>${compareCont2.bisContShopBacks[0].paymentTime}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopBacks[0].freeRentPeriod!=null || compareCont2.bisContShopBacks[0].freeRentPeriod!=null ">
					<tr>
						<td align="right">免租期：</td>
						<td <s:if test="compareCont1.bisContShopBacks[0].freeRentPeriod != compareCont2.bisContShopBacks[0].freeRentPeriod">style="color: red;"</s:if>>${compareCont1.bisContShopBacks[0].freeRentPeriod}</td>
						<td <s:if test="compareCont1.bisContShopBacks[0].freeRentPeriod != compareCont2.bisContShopBacks[0].freeRentPeriod">style="color: red;"</s:if>>${compareCont2.bisContShopBacks[0].freeRentPeriod}</td>
					</tr>
					</s:if>
				</s:if>
				
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==3">
					<s:if test="compareCont1.bisContShopProps[0].paymentModeCd!=null || compareCont2.bisContShopProps[0].paymentModeCd!=null ">
					<tr>
						<td align="right">支付方式：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].paymentModeCd != compareCont2.bisContShopProps[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont1.bisContShopProps[0].paymentModeCd"/></td>
						<td <s:if test="compareCont1.bisContShopProps[0].paymentModeCd != compareCont2.bisContShopProps[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont2.bisContShopProps[0].paymentModeCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopProps[0].performanceBond!=null || compareCont2.bisContShopProps[0].performanceBond!=null ">
					<tr>
						<td align="right">物业履约保证金：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].performanceBond != compareCont2.bisContShopProps[0].performanceBond">style="color: red;"</s:if>>${compareCont1.bisContShopProps[0].performanceBond}</td>
						<td <s:if test="compareCont1.bisContShopProps[0].performanceBond != compareCont2.bisContShopProps[0].performanceBond">style="color: red;"</s:if>>${compareCont2.bisContShopProps[0].performanceBond}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopProps[0].operationBond!=null || compareCont2.bisContShopProps[0].operationBond!=null ">
					<tr>
						<td align="right">经营保证金：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].operationBond != compareCont2.bisContShopProps[0].operationBond">style="color: red;"</s:if>>${compareCont1.bisContShopProps[0].operationBond}</td>
						<td <s:if test="compareCont1.bisContShopProps[0].operationBond != compareCont2.bisContShopProps[0].operationBond">style="color: red;"</s:if>>${compareCont2.bisContShopProps[0].operationBond}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopProps[0].paymentTime!=null || compareCont2.bisContShopProps[0].paymentTime!=null ">
					<tr>
						<td align="right">付款日期：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].paymentTime != compareCont2.bisContShopProps[0].paymentTime">style="color: red;"</s:if>>${compareCont1.bisContShopProps[0].paymentTime}</td>
						<td <s:if test="compareCont1.bisContShopProps[0].paymentTime != compareCont2.bisContShopProps[0].paymentTime">style="color: red;"</s:if>>${compareCont2.bisContShopProps[0].paymentTime}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopProps[0].integMoneyFreeRentPeriod!=null || compareCont2.bisContShopProps[0].integMoneyFreeRentPeriod!=null ">
					<tr>
						<td align="right">免租期：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].integMoneyFreeRentPeriod != compareCont2.bisContShopProps[0].integMoneyFreeRentPeriod">style="color: red;"</s:if>>${compareCont1.bisContShopProps[0].integMoneyFreeRentPeriod}</td>
						<td <s:if test="compareCont1.bisContShopProps[0].integMoneyFreeRentPeriod != compareCont2.bisContShopProps[0].integMoneyFreeRentPeriod">style="color: red;"</s:if>>${compareCont2.bisContShopProps[0].integMoneyFreeRentPeriod}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopProps[0].integMoneyFirstRentDay!=null || compareCont2.bisContShopProps[0].integMoneyFirstRentDay!=null ">
					<tr>
						<td align="right">综合管理费起收日：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].integMoneyFirstRentDay != compareCont2.bisContShopProps[0].integMoneyFirstRentDay">style="color: red;"</s:if>><s:date name="compareCont1.bisContShopProps[0].integMoneyFirstRentDay" format="yyyy-MM-dd"/></td>
						<td <s:if test="compareCont1.bisContShopProps[0].integMoneyFirstRentDay != compareCont2.bisContShopProps[0].integMoneyFirstRentDay">style="color: red;"</s:if>><s:date name="compareCont2.bisContShopProps[0].integMoneyFirstRentDay" format="yyyy-MM-dd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopProps[0].inPoolBl!=null || compareCont2.bisContShopProps[0].inPoolBl!=null ">
					<tr>
						<td align="right">是否参与公摊：</td>
						<td <s:if test="compareCont1.bisContShopProps[0].inPoolBl != compareCont2.bisContShopProps[0].inPoolBl">style="color: red;"</s:if>><s:if test="compareCont1.bisContShopProps[0].inPoolBl==0">否</s:if><s:else>是</s:else></td>
						<td <s:if test="compareCont1.bisContShopProps[0].inPoolBl != compareCont2.bisContShopProps[0].inPoolBl">style="color: red;"</s:if>><s:if test="compareCont2.bisContShopProps[0].inPoolBl==0">否</s:if><s:else>是</s:else></td>
					</tr>
					</s:if>
				</s:if>
				
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==4">
					<s:if test="compareCont1.bisContShopEntrs[0].paymentModeCd!=null || compareCont2.bisContShopEntrs[0].paymentModeCd!=null ">
					<tr>
						<td align="right">支付方式：</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].paymentModeCd != compareCont2.bisContShopEntrs[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont1.bisContShopEntrs[0].paymentModeCd"/></td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].paymentModeCd != compareCont2.bisContShopEntrs[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont2.bisContShopEntrs[0].paymentModeCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopEntrs[0].earnestMoney!=null || compareCont2.bisContShopEntrs[0].earnestMoney!=null ">
					<tr>
						<td align="right">保证金：</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].earnestMoney != compareCont2.bisContShopEntrs[0].earnestMoney">style="color: red;"</s:if>>${compareCont1.bisContShopEntrs[0].earnestMoney}</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].earnestMoney != compareCont2.bisContShopEntrs[0].earnestMoney">style="color: red;"</s:if>>${compareCont2.bisContShopEntrs[0].earnestMoney}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopEntrs[0].paymentTime!=null || compareCont2.bisContShopEntrs[0].paymentTime!=null ">
					<tr>
						<td align="right">付款日期：</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].paymentTime != compareCont2.bisContShopEntrs[0].paymentTime">style="color: red;"</s:if>>${compareCont1.bisContShopEntrs[0].paymentTime}</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].paymentTime != compareCont2.bisContShopEntrs[0].paymentTime">style="color: red;"</s:if>>${compareCont2.bisContShopEntrs[0].paymentTime}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContShopEntrs[0].freeRentPeriod!=null || compareCont2.bisContShopEntrs[0].freeRentPeriod!=null ">
					<tr>
						<td align="right">免租期：</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].freeRentPeriod != compareCont2.bisContShopEntrs[0].freeRentPeriod">style="color: red;"</s:if>>${compareCont1.bisContShopEntrs[0].freeRentPeriod}</td>
						<td <s:if test="compareCont1.bisContShopEntrs[0].freeRentPeriod != compareCont2.bisContShopEntrs[0].freeRentPeriod">style="color: red;"</s:if>>${compareCont2.bisContShopEntrs[0].freeRentPeriod}</td>
					</tr>
					</s:if>
				</s:if>
				
				<s:if test="compareCont1.contBigTypeCd==2">
					<s:if test="compareCont1.bisContFlatProps[0].paymentModeCd!=null || compareCont2.bisContFlatProps[0].paymentModeCd!=null ">
					<tr>
						<td align="right">支付方式：</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].paymentModeCd != compareCont2.bisContFlatProps[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont1.bisContFlatProps[0].paymentModeCd"/></td>
						<td <s:if test="compareCont1.bisContFlatProps[0].paymentModeCd != compareCont2.bisContFlatProps[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont2.bisContFlatProps[0].paymentModeCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContFlatProps[0].firstCycleMustRent!=null || compareCont2.bisContFlatProps[0].firstCycleMustRent!=null ">
					<tr>
						<td align="right">首个收费周期应收费用：</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].firstCycleMustRent != compareCont2.bisContFlatProps[0].firstCycleMustRent">style="color: red;"</s:if>>${compareCont1.bisContFlatProps[0].firstCycleMustRent}</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].firstCycleMustRent != compareCont2.bisContFlatProps[0].firstCycleMustRent">style="color: red;"</s:if>>${compareCont2.bisContFlatProps[0].firstCycleMustRent}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContFlatProps[0].propertyDerateMoney!=null || compareCont2.bisContFlatProps[0].propertyDerateMoney!=null ">
					<tr>
						<td align="right">物业费减免金额：</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].propertyDerateMoney != compareCont2.bisContFlatProps[0].propertyDerateMoney">style="color: red;"</s:if>>${compareCont1.bisContFlatProps[0].propertyDerateMoney}</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].propertyDerateMoney != compareCont2.bisContFlatProps[0].propertyDerateMoney">style="color: red;"</s:if>>${compareCont2.bisContFlatProps[0].propertyDerateMoney}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContFlatProps[0].integMoneyFreeRentPeriod!=null || compareCont2.bisContFlatProps[0].integMoneyFreeRentPeriod!=null ">
					<tr>
						<td align="right">物业管理费减免期限：</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].integMoneyFreeRentPeriod != compareCont2.bisContFlatProps[0].integMoneyFreeRentPeriod">style="color: red;"</s:if>>${compareCont1.bisContFlatProps[0].integMoneyFreeRentPeriod}</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].integMoneyFreeRentPeriod != compareCont2.bisContFlatProps[0].integMoneyFreeRentPeriod">style="color: red;"</s:if>>${compareCont2.bisContFlatProps[0].integMoneyFreeRentPeriod}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContFlatProps[0].integMoneyFirstRentDay!=null || compareCont2.bisContFlatProps[0].integMoneyFirstRentDay!=null ">
					<tr>
						<td align="right">综合管理费起收日：</td>
						<td <s:if test="compareCont1.bisContFlatProps[0].integMoneyFirstRentDay != compareCont2.bisContFlatProps[0].integMoneyFirstRentDay">style="color: red;"</s:if>><s:date name="compareCont1.bisContFlatProps[0].integMoneyFirstRentDay" format="yyyy-MM-dd"/></td>
						<td <s:if test="compareCont1.bisContFlatProps[0].integMoneyFirstRentDay != compareCont2.bisContFlatProps[0].integMoneyFirstRentDay">style="color: red;"</s:if>><s:date name="compareCont2.bisContFlatProps[0].integMoneyFirstRentDay" format="yyyy-MM-dd"/></td>
					</tr>
					</s:if>
				</s:if>
				
				<s:if test="compareCont1.contBigTypeCd==1 && compareCont1.contSmallTypeCd==3">
					<s:if test="compareCont1.bisContMultis[0].operationProjectCd!=null || compareCont2.bisContMultis[0].operationProjectCd!=null ">
					<tr>
						<td align="right">经营项目：</td>
						<td <s:if test="compareCont1.bisContMultis[0].operationProjectCd != compareCont2.bisContMultis[0].operationProjectCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisOperationProjec()" value="compareCont1.bisContMultis[0].operationProjectCd"/></td>
						<td <s:if test="compareCont1.bisContMultis[0].operationProjectCd != compareCont2.bisContMultis[0].operationProjectCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisOperationProjec()" value="compareCont2.bisContMultis[0].operationProjectCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContMultis[0].paymentModeCd!=null || compareCont2.bisContMultis[0].paymentModeCd!=null ">
					<tr>
						<td align="right">支付方式：</td>
						<td <s:if test="compareCont1.bisContMultis[0].paymentModeCd != compareCont2.bisContMultis[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont1.bisContMultis[0].paymentModeCd"/></td>
						<td <s:if test="compareCont1.bisContMultis[0].paymentModeCd != compareCont2.bisContMultis[0].paymentModeCd">style="color: red;"</s:if>><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="compareCont2.bisContMultis[0].paymentModeCd"/></td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContMultis[0].contPromiseBond!=null || compareCont2.bisContMultis[0].contPromiseBond!=null ">
					<tr>
						<td align="right">合同约定保证金：</td>
						<td <s:if test="compareCont1.bisContMultis[0].contPromiseBond != compareCont2.bisContMultis[0].contPromiseBond">style="color: red;"</s:if>>${compareCont1.bisContMultis[0].contPromiseBond}</td>
						<td <s:if test="compareCont1.bisContMultis[0].contPromiseBond != compareCont2.bisContMultis[0].contPromiseBond">style="color: red;"</s:if>>${compareCont2.bisContMultis[0].contPromiseBond}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContMultis[0].paymentTime!=null || compareCont2.bisContMultis[0].paymentTime!=null ">
					<tr>
						<td align="right">付款日期：</td>
						<td <s:if test="compareCont1.bisContMultis[0].paymentTime != compareCont2.bisContMultis[0].paymentTime">style="color: red;"</s:if>>${compareCont1.bisContMultis[0].paymentTime}</td>
						<td <s:if test="compareCont1.bisContMultis[0].paymentTime != compareCont2.bisContMultis[0].paymentTime">style="color: red;"</s:if>>${compareCont2.bisContMultis[0].paymentTime}</td>
					</tr>
					</s:if>
					<s:if test="compareCont1.bisContMultis[0].operationArea!=null || compareCont2.bisContMultis[0].operationArea!=null ">
					<tr>
						<td align="right">位置区域：</td>
						<td <s:if test="compareCont1.bisContMultis[0].operationArea != compareCont2.bisContMultis[0].operationArea">style="color: red;"</s:if>>${compareCont1.bisContMultis[0].operationArea}</td>
						<td <s:if test="compareCont1.bisContMultis[0].operationArea != compareCont2.bisContMultis[0].operationArea">style="color: red;"</s:if>>${compareCont2.bisContMultis[0].operationArea}</td>
					</tr>
					</s:if>
				</s:if>
			</table>
		</td>
	</tr>
	
</table>

<div style="height: 50px;"></div>

</div>

<script type="text/javascript">
$(function(){
	$("#resultDiv").css("height",$(window).height()-27-3+"px");
	//$("#processForm :input").each(function(i,n){});
	//$("#processForm :input").filter(".inputBorder").filter("[edit!=true]").attr("readonly","readonly");
	//$("#processForm :input").filter(".inputBorder").filter("[edit!=true]").addClass("inputBorder_readOnly");
	//$("select").filter(".inputBorder").attr("disabled","disabled");
	//$("#processForm .Wdate").filter("[edit!=true]").not(".edit-date").attr("onfocus","");
	//$("#processForm .Wdate").filter("[edit!=true]").not(".edit-date").removeClass("Wdate");
	//$("#bisStoreNos").attr("onclick","");
	//$("#bisShopName").unbind();
	//$(".search").unbind();
	//$(".money").unbind();
});
</script>

</body>
</html>
