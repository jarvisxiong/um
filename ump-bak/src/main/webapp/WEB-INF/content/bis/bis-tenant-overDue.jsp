<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<div id="tableDiv" style="overflow:auto;">
	<table class="content_table" id="editTable" >
			<tr class="header">
				<th nowrap="nowrap" align="right" style="background: #E4E7EC;cursor: pointer;width:15%;" >
					费用类别
				</th>				
				<th align="right" nowrap="nowrap"  class="pd-chill-tip" style="background: #E4E7EC;cursor: pointer;width:10%;" >年份</th>
				<th align="right" nowrap="nowrap" style="background: #E4E7EC;cursor: pointer;width:10%;" >月份</th>
				<th align="right" nowrap="nowrap" style="background: #E4E7EC;cursor: pointer;width:15%;" >应收(元)</th>
				<th align="right" nowrap="nowrap" style="background: #E4E7EC;cursor: pointer;width:15%;" >实收(元)</th>
				<th align="right" nowrap="nowrap" style="background: #E4E7EC;cursor: pointer;width:15%;" >欠费(元)</th>
				<th align="center" nowrap="nowrap" style="background: #E4E7EC;cursor: pointer;width:20%;" >应收日期</th>
			</tr>
			<s:iterator value="overDueList" status="st">
				<s:if test="chargeTypeCd==000">
				<tr class="mainTr" id="main_<s:property value="#st.index"/>">
					<td align="right" nowrap="nowrap" class="pd-chill-tip"  ><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td colspan="2"></td>
					<td align="right"  colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${ mustMoney}" >
						<div class="ellipsisDiv_full" ><font>${mustMoney }</font></div></td>
					
					<td align="right"  colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${ money}" onclick="datagridOnClick('${bisFactId}');">
						<div class="ellipsisDiv_full" ><font >${money }</font></div></td>
						<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${overDueMoney }" ><div class="ellipsisDiv_full" >
					<font style="color:red">${overDueMoney }</font></div></td>
					<td></td>
				</tr>
				</s:if>
				<s:if test="chargeTypeCd!=000">
				<tr class="mainTr" id="main_<s:property value="#st.index"/>">
					<td align="right" nowrap="nowrap" class="pd-chill-tip"  ><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factYear }</div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title=""><div class="ellipsisDiv_full" >${factMonth }</div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${mustMoney }" ><div class="ellipsisDiv_full" >
					<font >${mustMoney }</font>
					</div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${money }" ><div class="ellipsisDiv_full" >
					<font >${money }</font></div></td>
					<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${overDueMoney }" ><div class="ellipsisDiv_full" >
					<font style="color:red">${overDueMoney }</font></div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="" ><div class="ellipsisDiv_full" >${factDate }</div></td>
				</tr>
				</s:if>
			</s:iterator>
	</table>
</div>

