<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table class="showTable">
	<col width="50"/>
	<col />
	<col />
	<col />
	<col width="110"/>
	<col width="110"/>
	<col width="35"/>
	<col width="60"/>
	<col width="35"/>
	<col width="80"/>
	<thead>
		<tr class="header">
			<th style="padding-left:8px;cursor: pointer;" onclick="sortBy(this,'USER_CD','${selectedOrder}')">
			<div style="float:left;">审批人</div>
				<s:if test="selectedOrderBy=='USER_CD'">
					<s:if test="selectedOrder=='desc'">
						<div class="sort_desc"></div>
					</s:if>
					<s:if test="selectedOrder=='asc'">
						<div class="sort_asc"></div>
					</s:if>
				</s:if>
			</th>
			<th style="cursor: pointer;" onclick="sortBy(this,'LAND_PROJECT','${selectedOrder}')">
				<div style="float:left;">项目/中心</div>
				<s:if test="selectedOrderBy=='LAND_PROJECT'">
					<s:if test="selectedOrder=='desc'">
						<div class="sort_desc"></div>
					</s:if>
					<s:if test="selectedOrder=='asc'">
						<div class="sort_asc"></div>
					</s:if>
				</s:if>
			</th>
			<th >类型</th>
			<th >标题</th>
			<th style="cursor: pointer;" onclick="sortBy(this,'PRE_APPROVE_DATE','${selectedOrder}')">
				<div style="float:left;">审批开始时间</div>
				<s:if test="selectedOrderBy=='PRE_APPROVE_DATE'">
					<s:if test="selectedOrder=='desc'">
						<div class="sort_desc"></div>
					</s:if>
					<s:if test="selectedOrder=='asc'">
						<div class="sort_asc"></div>
					</s:if>
				</s:if>
			</th>
			<th >审批结束时间</th>
			<th >时限</th>
			<th ><s:if test="isApproach=='true'">剩余时间</s:if><s:else>耗时</s:else></th>
			<th >过期</th>
			<th >操作</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="page.result.size() > 0">
		<s:iterator id="id" value="page.result">
			<tr id="main_${id[8]}" <s:if test="#id[12] == 1">style="color:red;font-weight:bold;background-color:#E6E6E6;"</s:if> class="mainTr" >
				<td style="padding-left:8px;" class="pd-chill-tip" title='${id[4]}'>
					<div class="ellipsisDiv_full" >
						${id[4]}
					</div>
				</td>
				<td class="pd-chill-tip" id="landProject" title='${id[10]}'>
					<div class="ellipsisDiv_full" >
						${id[10]}
					</div>
				</td>
				<s:set var="authTypeName" ><%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("#id[11]")) %></s:set>
				<td class="pd-chill-tip" title='${authTypeName}'>
					<div class="ellipsisDiv_full">
						${authTypeName}
					</div>
				</td>
				<td class="pd-chill-tip" id="titleName" title='${id[9]}'>
					<div class="ellipsisDiv_full" >
						${id[9]}
					</div>
				</td>
				<td>${id[1]}</td>
				<td>${id[2]}</td>
				<td>${id[6]}</td>
				<td>${id[0]}</td>
				<td>
					<s:if test="#id[12] == 1">是</s:if>
					<s:if test="#id[12] == 0">否</s:if>
				</td>
				<td>
				<input type="button" class="btn_blue_auto" value="查看" onclick="openDetail('${id[8]}','${id[11]}');" />
				<s:if test="isApproach=='true'"><input type="button" class="btn_blue_auto" value="提醒" onclick="notifyThisUser('${id[3]}','${id[8]}','${id[11]}','${authTypeName}','${id[9]}','${id[0]}','${id[13]}');" /></s:if>
				</td>
			</tr>
		</s:iterator>
		</s:if>
	</tbody>
</table>
<div class="table_pager" style="margin-top:5px;">
	<p:page />
</div>
<s:if test="page.result.size() == 0">
	<div align="center" style="font-weight: bold;color:#5B6B83;font-size: 14px; margin-top:10px;">
		没有相关的数据！
	</div>
</s:if>


