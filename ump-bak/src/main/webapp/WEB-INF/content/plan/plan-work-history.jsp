<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.web.struts2.Struts2Utils"%>
<%--当前用户所属的中心cd --%>

<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.CollectionHelper"%>
<%@page import="java.util.List"%>
<table class="content_table" id="scheTable">
	<thead>
		<tr>
			<th align="left" width="130px" nowrap="nowrap" style="background:none;">&nbsp;&nbsp;&nbsp;&nbsp;编号</th>
			<s:if test="centerCd=='11'">
			<th align="left" width="120px" nowrap="nowrap">目标节点</th>
			<th align="left" width="180px" nowrap="nowrap">地区(或项目名称)</th>
			</s:if>
			<th align="left" nowrap="nowrap">工作内容</th>
			<th align="left" width="180px" nowrap="nowrap">负责人</th>
			<th align="left" width="50px" nowrap="nowrap" style="cursor:pointer; word-break: break-all;">目标</th>
			<th align="left" width="50px" nowrap="nowrap" style="cursor:pointer; word-break: break-all;">更新</th>
			<th align="left" width="50px" nowrap="nowrap" style="cursor:pointer; word-break: break-all;">完成</th>
			<th align="left" width="50px" nowrap="nowrap" style="cursor:pointer; word-break: break-all;">状态</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="pageHistory.result.size == 0">
		<tr>
			<td <s:if test="now_orgCd=='11'">colspan="8"</s:if> colspan="6" align="center" style="height:200px;">没有搜索到的记录</td>
		</tr>
		</s:if>
		<s:iterator value="pageHistory.result" var="pageWorkplan">
		<tr id="main_${planWorkId}" class="mainTr" style="cursor:pointer;">
			<td>
				${serialNumber}${serialOrder}
			</td>
			<s:if test="centerCd=='11'">
			<td><p:code2name mapCodeName="mapTargetPointCd" value="targetPointCd"/></td>
			<td>${area}</td>
			</s:if>
			<td><div class="ellipsisDiv_full">${content}</div></td>
			<td><div class="ellipsisDiv_full"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("principal"),";") %></div></td>
			<td title="<s:date name='targetDate'/>"><s:date name="targetDate" format="MM-dd"></s:date></td>
			<td title="<s:date name='updatedDate'/>"><s:date name="updatedDate" format="MM-dd"></s:date></td>
			<td title="<s:date name='endDate'/>"><s:date name="endDate" format="MM-dd"></s:date></td>
			<td>
				<s:if test="statusCd==0"><img src="${ctx}/images/plan/pic_noConfirm.gif" title="未确认"></s:if>
				<s:elseif test="statusCd==1"><img src="${ctx}/images/plan/pic_confirm.gif" title="进行中"></s:elseif>
				<s:elseif test="statusCd==2"><img src="${ctx}/images/plan/pic_preFinish.gif" title="申请删除"></s:elseif>
				<s:elseif test="statusCd==3"><img src="${ctx}/images/plan/pic_finish.gif" title="已完成"></s:elseif>
				<s:elseif test="statusCd==4">已删除</s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/images/plan/pic_hide.gif" title="已隐藏"></s:elseif>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%" style="display:none;">
	<tr>
		<td align="center" id="td_page_source"><p:page/></td>
	</tr>
</table>
<script type="text/javascript">
//if(1==$("#if_search_all").val()){
	$("#td_page").html($("#td_page_source").html());
	$("#td_page_source").html("");
//}
</script>
<div id="pop_bg" class="pop_bg" style="display:none;">
	<div style='height:300px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/resources/images/common/loading.gif'/>
			</td>
		</tr>
	</table>
</div>
