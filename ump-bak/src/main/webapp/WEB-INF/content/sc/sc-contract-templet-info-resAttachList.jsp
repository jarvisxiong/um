<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<style>
.attach_subtitle {
	clear: both;
	color: #0167A2;
	font-weight: bold;
	padding-left: 10px;
	padding-top: 10px;
	font-size: 18px;
}

.attach_type {
	clear: both;
	color: #6BAD42;
	font-weight: bold;
	padding-left: 5px;
	padding-top: 10px;
}
</style>
<s:set var="contAttachType" value="__" />
<s:if test="(conAttachOutputList==null or conAttachOutputList.size==0) && scContractTempletInfo.isstandard==1">
	<div class="attach_subtitle" style="display: none">合同文本库附件</div>
	<div class="attach_group" id="idOtherGroup">
	<table width="100%">
				<tr attachHdName="合同文本">
					<td attachHdName="合同文本"><span class="link scContractLink"
						onclick="getContById('${scContractTempletInfo.contractTempletInfoId}','${scContractTempletInfo.scContractTempletHis.contractTempletHisId}');">${scContractTempletInfo.contractNo}</span>
					</td>
					<td>&nbsp;</td>
					<td width="75px" style="padding-left: 5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil
								.findString("creator"))%></td>
					<td width="120px" align="right" style="padding-left: 5px;"><%-- 上传日期--%>
					<span
						title="<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss" />">
					<s:date name="createdDate" format="MM-dd" /> </span></td>
				</tr>
	</table>
	</div>
</s:if>
<s:if test="conAttachOutputList.size > 0">
	<div class="attach_subtitle" style="display: none">合同文本库附件</div>
	<div class="attach_group" id="idOtherGroup">
	<table width="100%">
		<!-- 列表按"上传时间"降序 -->
		<s:set var="isSet">false</s:set>
		<s:iterator value="conAttachOutputList" status="st">
			<s:if test="#contAttachType != attachTypeCd">
				<tr>
					<td colspan="4"><s:if
						test="attachTypeCd == null || attachTypeCd==''">
						<div class="attach_type" attachHdName="其他">其他</div>
					</s:if> <s:else>
						<div class="attach_type" attachHdName="${attachTypeCd}">
						${attachTypeCd}</div>
					</s:else></td>
				</tr>
				<s:set var="contAttachType" value="attachTypeCd" />
			</s:if>
			<s:if test="scContractTempletInfo.isstandard==1 && #isSet=='false'">
				<s:set var="isSet">true</s:set>
				<tr attachHdName="合同文本">
					<td attachHdName="合同文本"><span class="link scContractLink"
						onclick="getContById('${scContractTempletInfo.contractTempletInfoId}','${scContractTempletInfo.scContractTempletHis.contractTempletHisId}');">${scContractTempletInfo.contractNo}</span>
					</td>
					<td>&nbsp;</td>
					<td width="75px" style="padding-left: 5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil
								.findString("creator"))%></td>
					<td width="120px" align="right" style="padding-left: 5px;"><%-- 上传日期--%>
					<span
						title="<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss" />">
					<s:date name="createdDate" format="MM-dd" /> </span></td>
				</tr>
			</s:if>
			<tr attachHdName="${attachTypeCd}">
				<td attachHdName="${attachTypeCd}">
				<s:if test="attachName=='987'">
					<span class="link" style="cursor: pointer;"
						onclick="parent.TabUtils.newTab('sc_res','前置网批','${ctx}/res/res-approve-info.action?id=${scContractInfoAttachId}');">${realFileName}</span>
				</s:if> <s:elseif test="attachName=='988'">
					<span class="link" style="cursor: pointer;"
						onclick="parent.TabUtils.newTab('sc_res','合同条款','${ctx}/res/res-approve-info.action?id=${scContractInfoAttachId}');">${realFileName}</span>
				</s:elseif> <s:else>
					<s:url id="downUrl" action="download" namespace="/sc">
						<s:param name="fileName">${attachName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">sctemplet</s:param>
						<s:param name="operator">inline;</s:param>
						<s:param name="attchid">${scContractInfoAttachId}</s:param>
					</s:url>
					<div style="padding-left: 5px; text-align: left;"
						class="ellipsisDiv_full link"
						title="${realFileName};上传人：<%=CodeNameUtil.getUserNameByCd(JspUtil
								.findString("creator"))%>;上传时间：<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss" />">
					<a href="${downUrl}" target="_blank">${realFileName}
					<span style="display: none !important" class="operuserinfo"
						style="color:#6BAD42;"> &nbsp; (<s:date
						name="createdDate" format="MM-dd" />) </span> </a></div>
				</s:else></td>
				<td>&nbsp;</td>
				<td width="75px" style="padding-left: 5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil
							.findString("creator"))%></td>
				<td width="120px" align="right" style="padding-left: 5px;"><%-- 上传日期--%>
				<span
					title="<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss" />">
				<s:date name="createdDate" format="MM-dd" /> </span></td>
			</tr>
		</s:iterator>
	</table>
	</div>
</s:if>
<%--合同附件个数 --%>
<input type="hidden" id="attachSize" name="attachSize"
	value="<s:property value='conAttachOutputList.size'/>" />
<%--合同文本类别（1.标准合同0非标） --%>
<input type="hidden" id="scIsStandard"
	value="${scContractTempletInfo.isstandard}" />
