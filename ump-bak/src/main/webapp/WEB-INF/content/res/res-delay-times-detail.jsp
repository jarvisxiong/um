<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<fieldset>
    <table class="showTable" >
		<col />
		<col />
		<col />
		<col width="120"/>
		<col width="120"/>
		<col width="60"/>
		<col width="70"/>
		<col width="110"/>
		<thead>
			<tr class="header">
				<th >项目/中心</th>
				<th >类型</th>
				<th >标题</th>
				<th >审批开始时间</th>
				<th >审批结束时间</th>
				<th >时限</th>
				<th >耗时</th>
				<th >操作</th>
			</tr>
		</thead>
    	<s:iterator id="detail" value="details">
			<tr name="trName">
				<td style="padding-left:8px;" class="pd-chill-tip" id="landProject" title='${detail[10]}'>
					<div class="ellipsisDiv_full" >
						${detail[10]}
					</div>
				</td>
				<s:set var="authTypeName" ><%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("#detail[11]")) %></s:set>
				<td class="pd-chill-tip" title='${authTypeName}'>
					<div class="ellipsisDiv_full">
						${authTypeName}
					</div>
				</td>
				<td class="pd-chill-tip" id="titleName" title='${detail[9]}'>
					<div class="ellipsisDiv_full" >
						${detail[9]}
					</div>
				</td>
				<td>${detail[1]}</td>
				<td>${detail[2]}</td>
				<td>${detail[6]}</td>
				<td>${detail[0]}</td>
				<td>
				<input type="button" class="btn_blue_auto" value="查看" onclick="openDetail('${detail[8]}','${detail[11]}');" />
				<input type="button" class="btn_blue_auto" value="取消时限" onclick="cancelDelay('${detail[8]}','${detail[3]}',this);" />
				</td>
			</tr>
		</s:iterator>
	</table>
</fieldset>
