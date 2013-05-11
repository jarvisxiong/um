<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/global.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<%--<script type="text/javascript" src="${ctx}/resources/js/common/ConverUtil.js"></script>--%>
<script type="text/javascript">
initFactMust();
function initFactMust(){
	AllFactMust = new Array();
	<s:iterator value="voPage.result" status="st">
		 var obj = "<s:property value='#st.count'/>";
		 id = obj;
		AllFactMust.push(new FactMust(obj,"${bisTenantId}","${storeNo}","${bisContId}",
				"${bisProjectId}","${chargeTypeCd}","${chargeTypeCdName}","${mustMoney}","${factYear}","${factMonth}","${connName}","${factDate}","${bisShopId}"));
	</s:iterator>
}

</script>
<div id="tableDiv">
	<table class="content_table" id="editTable"  style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
			<tr class="header">
				<th nowrap="nowrap" align="left" style="cursor: pointer;width:10%;" >
					楼层	   商铺编号
				</th>
				<th nowrap="nowrap" align="left" style="width:13%;">
					商家名称
				</th>
				<th nowrap="nowrap" align="left" style="width:15%;">
					该商家所占商铺号
				</th>				
				<th nowrap="nowrap" align="left" class="pd-chill-tip" style="width:10%;">商铺性质</th>
				<th nowrap="nowrap" align="left" style="width:10%;">计租面积(㎡)</th>
				<th align="right" nowrap="nowrap" align="left" style="width:10%;">累计应收(元)</th>
				<th align="right" nowrap="nowrap" align="left" style="width:10%;">累计实收(元)</th>
				<th nowrap="nowrap" align="left" style="width:10%;">累计预收(元)</th>
				<th nowrap="nowrap" align="left" style="width:10%;">累计欠费(元)</th>
			</tr>
			<s:set var="storeNameCn" value="__"/>
			<s:iterator value="voPage.result" status="st">
				<tr class="mainTr" id="main_<s:property value="#st.index"/>">
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${storeNo }"><div class="ellipsisDiv_full">${storeNo }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${nameCn }"><div class="ellipsisDiv_full">${nameCn }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${statusCd }"><div class="ellipsisDiv_full">${statusCd }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title='<p:code2name mapCodeName="mapStoreType" value="manageCd"/>'><div class="ellipsisDiv_full"><p:code2name mapCodeName="mapStoreType" value="manageCd"/></div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${money }"><div class="ellipsisDiv_full">${money }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${sumMustMoney }"><div class="ellipsisDiv_full">${sumMustMoney }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${sumFactMoney }"><div class="ellipsisDiv_full">${sumFactMoney }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${sumYuMoney }"><div class="ellipsisDiv_full">${sumYuMoney }</div></td>
					<td align="center" nowrap="nowrap" class="pd-chill-tip" title="${sumQianMoney }"><div class="ellipsisDiv_full">${sumQianMoney }</div></td>
				</tr>
			</s:iterator>
			<tr id="totalTrId">
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ><input type="button" style="width: 120px; margin-top:2px;" class="btn_blue" value="显示合计值" onclick="getTotalCountBtn(this)"/></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ><div class="ellipsisDiv_full"><span id="countMustSpan"></span></div></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ><div class="ellipsisDiv_full"><span id="countFactSpan"></span></div></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ><div class="ellipsisDiv_full"><span id="countYuSpanSpan"></span></div></td>
				<td align="center" nowrap="nowrap" class="pd-chill-tip" ><div class="ellipsisDiv_full"><span id="countQianSpan"></span></div></td>
			</tr>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<%@ include file="./bis-fact-page.jsp"%>
	</div>
</div>
<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
