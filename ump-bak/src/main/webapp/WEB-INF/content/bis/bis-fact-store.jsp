<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<div id="tableDiv" style="overflow-x:hidden;">
	<table class="content_table" id="editTable">
		<col width="18">
		<col width="50">
		<col width="30">
		<col width="60">
		<col width="60">
		<col width="30">
		<col width="30">
		<col width="50">
		<col width="60">
		<col width="40">
		<col width="40">
		<col width="40">
		<col width="20">
			<tr class="header">
				<th style="width:100px;"><div class="btn_bottom_chk_all"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></th>
				<th nowrap="nowrap" colspan="2" align="left" style="cursor: pointer;" onclick="">
					楼层-商铺编号
				</th>
				<th nowrap="nowrap" align="left" onclick="">
					商家名称
				</th>
				<th nowrap="nowrap" align="left" onclick="sortBy(this,'titleName','${selectedOrder}')">
					费用类别
				</th>				
				<th nowrap="nowrap" align="left" class="pd-chill-tip" >年份</th>
				<th nowrap="nowrap" align="left" >月份</th>
				<th align="right" nowrap="nowrap" align="left" >应收(元)</th>
				<th align="right" nowrap="nowrap" align="left" >实收(元)</th>
				<th nowrap="nowrap" align="left" >状态</th>
				<th nowrap="nowrap" align="left" >实收日期</th>
				<th nowrap="nowrap" align="left" >更新日期</th>
				<th nowrap="nowrap" align="left" >附件</th>
			</tr>
			<s:iterator value="voPage.result" status="#st">
				<s:if test="chargeTypeCd==000">
					<td colspan="3"></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""	 onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td colspan="3"></td>
					<td align="right" colspan="1" nowrap="nowrap" class="pd-chill-tip" title="<s:if test="null==mustMoney"></s:if><s:else>${mustMoney}</s:else>" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>><s:if test="null==mustMoney"></s:if><s:else>${mustMoney}</s:else></font></div></td>
					<td align="right" colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${ money}" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money }</font></div></td>
					<td colspan="3"></td>
				</s:if>
				<s:if test="chargeTypeCd!=000">
				<tr class="mainTr" id="main_${bisFactId}">
					<td id="chk_${bisFactId}" nowrap="nowrap" onclick='scheClick("${bisFactId}");' align="center">
						<div style="display:none;" id="attention_status_${bisFactId}">${statusCd}</div>
						<div style="display:inline;">
							<input type="checkbox" id="chk_all" statusCd="${statusCd }" ids="${bisFactId}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input>
						</div>
					</td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${storeNo }"   onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');" colspan="2"><div class="ellipsisDiv_full" >${storeNo }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="${connName }"	 onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${connName }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""	 onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${factYear }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${factMonth }</div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="<s:if test="null==mustMoney"></s:if><s:else>${mustMoney}</s:else>" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>><s:if test="null==mustMoney"></s:if><s:else>${mustMoney}</s:else></font></div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${ money}" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');">
						<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money }</font></div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');">
						<div class="ellipsisDiv_full" >	<s:if test="statusCd==1"><span class="color_blue">${statusCdName }</span>	</s:if>
						<s:if test="statusCd==2"><font style="color:red">${statusCdName }</font></s:if>
						<s:if test="statusCd==0">	 ${statusCdName } 	</s:if>
						</div>
					</td>
					<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${factDate }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="datagridOnClick('${bisFactId}','${bisFactYuSId}');"><div class="ellipsisDiv_full" >${updateDate }</div></td>
					<td nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >
						<a href="javascript:openAttachment('附件管理','${bisFactId}');" >
							<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
							<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
						</a>
						</div>
					</td>
				</tr>
				</s:if>
			</s:iterator>
	</table> 
	<div class="table_pager" style="margin-top:5px;">
		<%@ include file="./bis-fact-page.jsp"%>
	</div>
</div>
