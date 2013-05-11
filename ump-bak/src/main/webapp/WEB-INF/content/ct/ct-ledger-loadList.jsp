<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<table width="100%" id="result_table" class="content_table">
	<thead>
		<tr class="header">
			<th style="background: none;" width="30px;">序号</th>
			<th width="110px;" class="pd-chill-tip" title="项目名称">项目名称</th>
			<th width="40px;">期数</th>
			<th  width="60px; class="pd-chill-tip" title="总建筑面积(m2)">
			<div class="ellipsisDiv_full">总建面积(m2)</div>
			</th>
			<th width="80px;" class="pd-chill-tip" title="目标成本总额(元)">
			<div class="ellipsisDiv_full">成本总额(元)</div>
			</th>
			<th width="80px;" class="pd-chill-tip" title="分解总额(元)">
			<div  class="ellipsisDiv_full">分解总额(元)</div>
			</th>
			<th width="60px;">开工日期</th>
			<th width="60px;">交房日期</th>
			<th width="60px;">状态</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="voLedgerList" status="stat">
			<tr class="mainTr" onclick="loadCtLedgerDetail('${ctLedgerId}')" style="cursor: pointer;">
				<td>${stat.index+1}</td>
				<%-- 项目名称 --%>
				<td>${projectName}</td>
				<%-- 期数 --%>
				<td>${periods}期</td>
				<%-- 总建筑面积(m2)--%>
				<td>${totalConsArea}</td>

				<%-- 目标成本总额 --%>
				<td>${costTargetTotalAmt}</td>
				<%-- 分解总额 --%>
				<td>${totalDiviPlanContAmt }</td>
				<%-- 开工日期 --%>
				<td><s:date name="startDate" format="yy-MM-dd" /></td>
				<%-- 交房日期 --%>
				<td><s:date name="handDate" format="yy-MM-dd" /></td>
				<%-- 状态  --%>
				<td>					
					<s:if test="ledgerStatus==1 || ledgerStatus==9">
						<font class="waitReviewLedger">待审核</font>
					</s:if>
					<s:if test="ledgerStatus==2">
						<font class="approveLedger">已审核</font>
					</s:if>
					<s:if test="ledgerStatus==3 || ledgerStatus==7">
						<font class="overruleLedger">已驳回</font>
					</s:if>
				
					<s:if test="ledgerStatus==4">
						<font class="approveLedger">调整中</font>	
					</s:if>
					<s:if test="ledgerStatus==null || ledgerStatus == 0">
						<font class="defaultLedger">未提交</font>	
					</s:if>
				</td>
			

				<%--附件
			<td>
				${attachFlg}
			</td>--%>
			</tr>
		</s:iterator>
		<!--<tr>
			<td colspan="9" class="pageFooter" >
		  		<p:page  pageInfo="voPage"/>
			</td>
		</tr>
		-->
	</tbody>
</table>
<div class="pagerRight" id="sorucePager"><p:page pageInfo="voPage" /></div>
