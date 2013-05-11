<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div id="tableDiv" style="overflow: auto;">
<table class="content_table" id="editTable"
	style="width: expression(this.parentNode.offsetHeight &gt; this.parentNode.scrollHeight ?   '100%' : parseInt(this.parentNode.offsetWidth -   18) +   'px' );">
	<tr class="header">
		<th style="width: 20%;">日期</th>
		<th nowrap="nowrap"  align="left" style="cursor: pointer; width: 20%;">收入合计</th>
		<th nowrap="nowrap" align="left" style="width: 20%;">支出合计</th>
		<th nowrap="nowrap" align="left" style="width: 20%;">利润总额</th>
		<th nowrap="nowrap" align="left" style="width: 20%;">净利润</th>
	</tr>
	<input type="hidden" id="currentMonth" name="currentMonth" value="${currentMonth }"/>
	<s:iterator value="voPage.result" status="st">

		<tr class="mainTr" id="main_<s:property value="#st.index"/>" onclick="modBisBudget('${monthStr}','${bisBudgetId}');">
		   	<td style="text-align:center">${monthStr}</td>
			<td style="text-align:right">${incomeTotal}</td>
			<td nowrap="nowrap" class="pd-chill-tip" style="text-align:right">
				<div class="ellipsisDiv_full">${payTotal}</div>
			</td>
			<td style="text-align:right">${profitTotal }</td>
			<td style="text-align:right">${netProfit }</td>
		</tr>

	</s:iterator>
</table>
<div class="table_pager" style="margin-top: 5px;"><%@ include file="./bis-fact-page.jsp"%></div>
</div>
<%--
	<div id="footerDiv" class="bottom_bar">
	 
		<div id="operate_all_div">
		  
			<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
			
				<div class="btn_bottom_bar" onclick="doInsertBatch(1);" style="width:85px;"><div class="pd-chill-tip" title="默认应收数额为实收金额，当前系统日期为实收日期" >自动生成实收</div></div>
				
				<div class="btn_cutline_3_26"></div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_BIS_PROJ_VI_FINA,A_BIS_PROJ_FINA">
					<div class="btn_cutline_3_26"></div>
				</security:authorize> 
		</div>
		 --%>
<div id="td_page" style="float: right; text-align: center; height: 26px; margin-right: 10px;"></div>
