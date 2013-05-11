<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<script type="text/javascript">
	</script>
<div id="tableDiv" style="width:100%">
	<table class="content_table" id="editTable" >
		<col width="10%"/>
		<col width="15%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="5%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
			<tr class="header">
				<%-- 
				<th style="width:100px;">编号</th>
				--%>
				<th nowrap="nowrap" style="cursor: pointer;" onclick="sortBy(this,'landProject','${selectedOrder}')">
					<div style="float:left;">多经名称</div>
				</th>
				<th nowrap="nowrap" style="cursor: pointer;" onclick="sortBy(this,'userCd','${selectedOrder}')">
					<div style="float:left;">合同编号</div>
				</th>
				<th nowrap="nowrap" style="cursor: pointer;" onclick="sortBy(this,'userCd','${selectedOrder}')">
					<div style="float:left;">经营项目</div>
				</th>
				<th nowrap="nowrap" onclick="sortBy(this,'authTypeCd','${selectedOrder}')">
					<div style="float:left;cursor: pointer;">承租方</div>
				</th>
				<th nowrap="nowrap" onclick="sortBy(this,'titleName','${selectedOrder}')">
					<div style="float:left;cursor: pointer;">费用类别</div>
				</th>
				<th nowrap="nowrap" style="cursor: pointer;" onclick="sortBy(this,'approveUserCd','${selectedOrder}')">
					<div style="float:left;">年份</div>
				</th>
				<th nowrap="nowrap">月份</th>
				<th nowrap="nowrap" align="right">应收(元)</th>
				<th nowrap="nowrap" align="right">实收(元)</th>
				<th nowrap="nowrap">收款时间</th>
			</tr>
			<s:iterator value="voPage.result">
				<tr class="mainTr" id="main_${bisFactId}" onclick="appendBisFact1('${bisProjectId}','3','${bisMultiId }','${multiName }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ multiName}','${operationProjectCd }','${bisContId}')">
					<td nowrap="nowrap" class="pd-chill-tip" title="${multiName }"><div class="ellipsisDiv_full" >${multiName }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${contNo}"><div class="ellipsisDiv_full" >${contNo }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${operationProjectCd }"><div class="ellipsisDiv_full" >${operationProjectCd }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${renterName }"><div class="ellipsisDiv_full" >${renterName }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factYear }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factMonth }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" align="right"><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if>>${mustMoney }</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" align="right"><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if>>${money }</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factDate }</div></td>
				</tr>
			</s:iterator>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<%@ include file="./bis-fact-page.jsp"%>
	</div>
</div>