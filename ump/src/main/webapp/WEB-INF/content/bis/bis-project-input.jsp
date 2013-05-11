<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div class="list_header2" style="height:10px;line-height:10px;margin-left: 8px;"><span style="font-size:14px;"></span></div>
<form action="${ctx}/bis/bis-project!saveProject.action" method="post" id="saveProjectForm">
	<input type="hidden" name="id" id="bisProjectId" value="${bisProjectId}" />
	<input type="hidden" name="recordVersion" value="${recordVersion}" />
	<input type="hidden" name="areaCd" value="${areaCd}" />
	<input type="hidden" name="city" value="${city}" />
	<input type="hidden" name="orgCd" value="${orgCd}" />
	<input type="hidden" name="sequenceNo" value="${sequenceNo}" />
	<input type="hidden" name="projectShortName" value="${projectShortName}" />
	<table style="line-height:30px; padding:0 5px;">
		<col width="90"/>
		<col />
		<col width="100"/>
		<col />
		<col width="100"/>
		<col />
		<tr>
			<td align="right">项目名称：</td>
			<td>${projectName}</td>
			<td align="right">负责人：</td>
			<td>
				<input type="text" name="principalName" value="${principalName}"/>
			</td>
			<td align="right">总面积：</td>
			<td>
				<input type="text" name="totalSquare" value="${totalSquare}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商铺面积：</td>
			<td>
				<input type="text" name="storeSquare" value="${storeSquare}"/>
			</td>
			<td align="right">公寓面积：</td>
			<td>
				<input type="text" name="flatSquare" value="${flatSquare}"/>
			</td>
			<td align="right">停车场面积：</td>
			<td>
				<input type="text" name="parkSquare" value="${parkSquare}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商铺数量：</td>
			<td>
				<input type="text" name="storeNum" value="${storeNum}"/>
			</td>
			<td align="right">公寓数量：</td>
			<td>
				<input type="text" name="flatNum" value="${flatNum}"/>
			</td>
			<td align="right">停车位数量：</td>
			<td>
				<input type="text" name="parkNum" value="${parkNum}"/>
			</td>
		</tr>
		<tr>
			<td align="right">开业日期：</td>
			<td>
				<input class="Wdate" type="text" name="openDate" value='<s:date name="openDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()"/>
			</td>
			<td align="right">电费单价：</td>
			<td>
				<input type="text" name="electricPrice" value="${electricPrice}"/>
			</td>
			<td align="right">水费单价：</td>
			<td>
				<input type="text" name=waterPrice value="${waterPrice}"/>
			</td>
		</tr>
	</table>
</form>