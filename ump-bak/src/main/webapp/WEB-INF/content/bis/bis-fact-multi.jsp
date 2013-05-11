<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<script type="text/javascript">
	</script>
<div id="tableDiv">
	<table class="content_table" id="editTable" >
	    <col width="5%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="7%"/>
		<col width="8%"/>
		<col width="5%"/>
		<col width="5%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="8%"/>
		<col width="8%"/>
			<tr class="header">
				<th style=" "></th>
				<th nowrap="nowrap" style="cursor: pointer;" onclick="sortBy(this,'landProject','${selectedOrder}')">
					<div style="float:left;">合同编号</div>
				</th>
				<th nowrap="nowrap" align="left">多经名称 </th>
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
				<th nowrap="nowrap" align="right">应收</th>
				<th nowrap="nowrap" align="right">实收(元)</th>
				<th nowrap="nowrap">状态(元)</th>
				<th nowrap="nowrap">实收日期</th>
			</tr>
			<s:iterator value="voPage.result">
				<s:if test="chargeTypeCd==000">
					<td colspan="5"></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""	 onclick="datagridOnClick('${bisFactId}');"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td colspan="2"></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${ mustMoney}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${mustMoney }</font></div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${ money}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money }</font></div></td>
				    <td colspan="2"></td>
				</s:if>
				<s:if test="chargeTypeCd!=000">
				<tr class="mainTr" id="main_${bisFactId}" onclick="datagridOnClick('${bisFactId}');">
					<td id="chk_${bisFactId}" nowrap="nowrap" onclick='scheClick("${bisFactId}");' align="center">
						<div style="display:none;" id="attention_status_${bisFactId}">${statusCd}</div>
						<div style="display:inline;">
							<input type="checkbox" id="chk_all" statusCd="${statusCd }" ids="${bisFactId}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input>
						</div>
					</td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${contNo}"><div class="ellipsisDiv_full" >${contNo}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${multiName}"><div class="ellipsisDiv_full" >${multiName}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${operationProjectCd}"><div class="ellipsisDiv_full" >${operationProjectCd}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${renterName}"><div class="ellipsisDiv_full" >${renterName}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${chargeTypeCdName}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factYear}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factMonth}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" align="right"><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${mustMoney}</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" align="right"><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money}</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${statusCdName}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factDate}</div></td>
				</tr>
				</s:if>
			</s:iterator>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<%@ include file="./bis-fact-page.jsp"%>
	</div>
</div>

	<div style="font-size:14px;height:30px;line-height:30px;width:100%;">
		<div id="operate_all_div">
			<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
<security:authorize ifAnyGranted="A_FACT_CHECK">
				<div class="btn_bottom_bar" onclick="doUpdateAll(1);">批量审核</div>
				<div class="btn_bottom_bar" onclick="doExport4Search();">导出</div>
</security:authorize>
		</div>
		<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
	</div>
