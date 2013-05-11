<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<div style="width: 98%;height: 100%;overflow-x: hidden;margin-left: 15px;">
	<table class="decTable" style="width: 100%;height: 100%;" cellpadding="0" cellspacing="0">
		<col width="10%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<col width="9%"/>
		<thead>
			<tr id="linebottom">
				<th rowspan="3" style="text-align: left;">项目</th>
				<th colspan="4"><div style="margin:0px 100px 0px 100px;">主次力店(万元)</div></th>
				<th colspan="4"><div style="margin:0px 70px 0px 90px;">其他店(万元)</div></th>
				<th rowspan="3"><div>小商户<br/>(万元)</div></th>
				<th rowspan="3"><div>广场销售额<br/>(万元)</div></th>
			</tr>
			<tr id="linebottom">
				<th rowspan="2"><div>超市</div></th>
				<th rowspan="2"><div>百货</div></th>
				<th rowspan="2"><div>影院</div></th>
				<th rowspan="2"><div style="width: 110px;">主次力店合计</div></th>
				<th colspan="2"><div style="margin:0px 30px 0px 30px;">自营</div></th>
				<th rowspan="2"><div>草上飞</div></th>
				<th rowspan="2"><div>合计</div></th>
			</tr>
			<tr id="linebottom">
				<th><div>宝莱</div></th>
				<th><div>龙麦</div></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="bisManageDayVoList" status="sta">
				<tr style="height: 35px;">
					<td style="text-align: right;" class="pd-chill-tip" title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId")) %>">
						<input type="hidden" id="prijextId<s:property value="#sta.index"/>" value="${bisProjectId}"/>
						<div class="short_div">
							<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId")) %>
							<span style="font-weight: bolder;">. . . . . . . . . . . . . . .</span>
						</div>
					</td>
					<td class="pd-chill-tip" title="${superMarket}">
						${superMarket}
					</td>
					<td class="pd-chill-tip" title="${departmentStore}">
						${departmentStore}
					</td>
					<td class="pd-chill-tip" title="${cinema}">
						${cinema}
					</td>
					<td class="pd-chill-tip" title="${mainTotal}">
						${mainTotal}
					</td>
					<td class="pd-chill-tip" title="${baolai}">
						${baolai}
					</td>
					<td class="pd-chill-tip" title="${longmai}">
						${longmai}
					</td>
					<td class="pd-chill-tip" title="${caoshangfei}">
						${caoshangfei}
					</td>
					<td class="pd-chill-tip" title="${otherTotal}">
						${otherTotal}
					</td>
					<td class="pd-chill-tip" title="${storeTotal}">
						${storeTotal}
					</td>
					<td class="pd-chill-tip" title="${plazaTotal}">
						${plazaTotal}
					</td>
				</tr>
			</s:iterator>
			
			<%-- 合计列  --%>
			<s:if test="bisManageDayVoList.size() > 0">
				<tr style="height: 45px;">
					<td style="text-align:left;font-weight: bolder;">合计</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.superMarket}">
						<div class="countline">${bisManageDayCount.superMarket}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.departmentStore}">
						<div class="countline">${bisManageDayCount.departmentStore}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.cinema}">
						<div class="countline">${bisManageDayCount.cinema}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.mainTotal}">
						<div class="countline">${bisManageDayCount.mainTotal}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.baolai}">
						<div class="countline">${bisManageDayCount.baolai}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.longmai}">
						<div class="countline">${bisManageDayCount.longmai}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.caoshangfei}">
						<div class="countline">${bisManageDayCount.caoshangfei}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.otherTotal}">
						<div class="countline">${bisManageDayCount.otherTotal}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.storeTotal}">
						<div class="countline">${bisManageDayCount.storeTotal}</div>
						<div class="countline2"></div>
					</td>
					<td class="pd-chill-tip" title="${bisManageDayCount.plazaTotal}">
						<div class="countline">${bisManageDayCount.plazaTotal}</div>
						<div class="countline2"></div>
					</td>
				</tr>
			</s:if>
		</tbody>
	</table>
</div>