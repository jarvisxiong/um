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
	<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
		<col width="18">
		<col width="50">
		<col width="60">
		<col width="50">
		<col width="60">
		<col width="30">
		<col width="20">
		<col width="50">
		<col width="50">
		<col width="40">
		<col width="40">
			<tr class="header">
				<th style="width:100px;"></th>
				<th nowrap="nowrap" align="left">合同编号</th>
				<th nowrap="nowrap" align="left">楼号</th>
				<th nowrap="nowrap" align="left" onclick="">公寓编号</th>
				<th nowrap="nowrap" align="left" onclick="">费用类别</th>
				<th nowrap="nowrap" align="left">年份</th>
				<th nowrap="nowrap" align="left">月份</th>
				<th align="right" nowrap="nowrap" align="left">应收(元)</th>
				<th align="right" nowrap="nowrap" align="left">实收(元)</th>
				<th nowrap="nowrap" align="left">状态</th>
				<th nowrap="nowrap" align="left">收款时间</th>
			</tr>
			<s:iterator value="voPage.result">
				<tr class="mainTr" id="main_${bisFactId}" onclick="datagridOnClick('${bisFactId}');">
				<s:if test="chargeTypeCd==000">
					<td colspan="7" style="text-align:center;" nowrap="nowrap" class="pd-chill-tip" title=""	 onclick="datagridOnClick('${bisFactId}');"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
                    <td align="right" colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${ mustMoney}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${mustMoney }</font></div></td>
					<td align="right" colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${ money}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money }</font></div></td>
                    <td></td>
                    <td></td>
				</s:if>
				<s:if test="chargeTypeCd!=000">
				<td id="chk_${bisFactId}" nowrap="nowrap" onclick='scheClick("${bisFactId}");' align="center">
						<div style="display:none;" id="attention_status_${bisFactId}">${statusCd}</div>
						<div style="display:inline;">
							<input type="checkbox" id="chk_all" statusCd="${statusCd }" ids="${bisFactId}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input>
						</div>
					</td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${contNo}"><div class="ellipsisDiv_full" >${contNo}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${buildingNum}"><div class="ellipsisDiv_full" >${buildingNum}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${flatNo}"><div class="ellipsisDiv_full" >${flatNo}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${chargeTypeCdName}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factYear}</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factMonth}</div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${mustMoney}</font></div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money}</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="">	<div class="ellipsisDiv_full" >	<s:if test="statusCd==1"><span class="color_blue">${statusCdName }</span>	</s:if>
						<s:if test="statusCd==2"><font style="color:red">${statusCdName }</font></s:if>
						<s:if test="statusCd==0">	 ${statusCdName } 	</s:if>
						</div></td>
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
