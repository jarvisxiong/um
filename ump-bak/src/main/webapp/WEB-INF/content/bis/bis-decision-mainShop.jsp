<%@page import="com.hhz.ump.util.HelperUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp" %>

<div style="width: 98%;height: 100%;overflow-x: hidden;margin-left: 15px;">
	<table class="decTable" style="width: 100%;height: 100%;" cellpadding="0" cellspacing="0">
		<col width="10%"/>
		<col width="14%"/>
		<col width="14%"/>
		<col width="14%"/>
		<col width="13%"/>
		<col width="13%"/>
		<col width="13%"/>
		<thead>
			<tr id="linebottom">
				<th style="text-align: left;">项目/业态</th>
				<th><div style="margin: 0px 30px 0px 30px;">超市</div></th>
				<th><div style="margin: 0px 30px 0px 30px;">百货</div></th>
				<th><div style="margin: 0px 30px 0px 30px;">影院</div></th>
				<th><div style="margin: 0px 30px 0px 30px;">电玩</div></th>
				<th><div style="margin: 0px 30px 0px 30px;">KTV</div></th>
				<th><div style="margin: 0px 30px 0px 30px;">电器</div></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="bisMainShopReportList" status="sta">
				<tr style="height: 35px;">
					<td style="text-align: right;" class="pd-chill-tip" title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId")) %>">
						<div class="short_div">
							<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId")) %>
							<span style="font-weight: bolder;">. . . . . . . . . . . . . . .</span>
		    			</div>
	    		 	</td>
	    		 	<%--HelperUtil.clearHtml()清理文本中的 HTML标记 --%>
					<td class="pd-chill-tip" title="<%=HelperUtil.clearHtml(JspUtil.findString("superMarket")) %>">
						<div class="short_div2" style="text-align: left;margin: 0px 0px 0px 31px;">${superMarket }</div>
					</td>
					<td class="pd-chill-tip" title="<%=HelperUtil.clearHtml(JspUtil.findString("departmentStore")) %>">
						<div class="short_div2" style="text-align: left;margin: 0px 0px 0px 31px;">${departmentStore }</div>
					</td>
					<td class="pd-chill-tip" title="<%=HelperUtil.clearHtml(JspUtil.findString("cinema")) %>">
						<div class="short_div2" style="text-align: left;margin: 0px 0px 0px 31px;">${cinema }</div>
					</td>
					<td class="pd-chill-tip" title="<%=HelperUtil.clearHtml(JspUtil.findString("videoGames")) %>">
						<div class="short_div2" style="text-align: left;margin: 0px 0px 0px 31px;">${videoGames }</div>
					</td>
					<td class="pd-chill-tip" title="<%=HelperUtil.clearHtml(JspUtil.findString("ktv")) %>">
						<div class="short_div2" style="text-align: left;margin: 0px 0px 0px 31px;">${ktv }</div>
					</td>
					<td class="pd-chill-tip" title="<%=HelperUtil.clearHtml(JspUtil.findString("electricEquipment")) %>">
						<div class="short_div2" style="text-align: left;margin: 0px 0px 0px 31px;">${electricEquipment }</div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

