<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<script type="text/javascript">
	</script>
<div id="tableDiv" style="overflow:auto;">
	<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
			<col width="15%"/>
			<col width="10%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<tr class="header">
				<%-- 
				<th style="width:100px;background: none;">编号</th>
				--%>
				<th nowrap="nowrap" align="left">楼号</th>
				<th nowrap="nowrap" align="left">公寓编号</th>
				<th nowrap="nowrap" align="left">合同编号</th>
				<th nowrap="nowrap" align="left">费用类别</th>
				<th nowrap="nowrap" align="left" >年份</th>
				<th nowrap="nowrap" align="left" >月份</th>
				<th nowrap="nowrap" align="right" >应收(元)</th>
				<th nowrap="nowrap" align="right" >实收(元)</th>
				<th nowrap="nowrap" align="left" >收款时间</th>
			</tr>
			<s:iterator value="voPage.result" status="st">
			<s:if test="chargeTypeCd==000">
					<td colspan="3"></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""	 onclick="datagridOnClick('${bisFactId}');"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td align="right" colspan="3" nowrap="nowrap" class="pd-chill-tip" title="${mustMoney}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${mustMoney }</font></div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${ money}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money }</font></div></td>
					<td></td>
				</s:if>
				<s:if test="chargeTypeCd!=000">
				<tr class="mainTr" id="main_${bisMustId}" onclick="appendBisFact1('${bisProjectId}','2','${bisFlatId}','${buildingNum }${floorNum }-${flatNo }','${chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${buildingNum }','${ flatNo}','${bisContId}')">
					<td nowrap="nowrap" class="pd-chill-tip" title="${buildingNum }"><div class="ellipsisDiv_full" >${buildingNum }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${flatNo }"><div class="ellipsisDiv_full" >${flatNo }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${contNo }"><div class="ellipsisDiv_full" >${contNo }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factYear }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factMonth }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" align="right"><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if>>${mustMoney }</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" align="right"><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if>>${money }</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factDate }</div></td>
				</tr>
				</s:if>
			</s:iterator>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<%@ include file="./bis-fact-page.jsp"%>
	</div>
</div>