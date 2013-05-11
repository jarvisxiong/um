<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%--
<%@ taglib prefix="com" tagdir="/WEB-INF/tags/common" %>
 --%>

<table class="content_table" style="width:100%;">
	<colgroup>
		<col />
		<col />
		<col width="140px;"/>
		<col width="140px;"/>
	</colgroup>
	<thead>
	<tr>
		<th width="80px;">序号</th>
		<th>模板名称</th>
		<th>版本</th>
		<th>创建时间</th>
		<th>更新时间</th>
	</tr>
	</thead>
	<tbody>
	<s:if test="voPage == null || voPage.result().size() == 0">
	<tr>
		<td colspan="4">搜索无记录!</td>
	</tr>
	</s:if>
	<s:else>
	<s:iterator value="voPage.result" var="tmp" status="s">
		<tr class="mainTr">
			<td>${s.index+1}</td>
			<td><a title="点击查看明细" href="javascript:viewPlanTplDetail('${tmp.costContPlanTplId}');"><input type="hidden" name="tplName" id="tplName" value="${tmp.tplName}"/>${tmp.tplName}</a></td>
			<td><input type="hidden" name="tplVersion" id="tplVersion" value="${tplVersion}"/>${tplVersion}</td>
			<td><s:date name="createdDate" format="yyyy-MM-dd 24h:mm"/> </td>
			<td><s:date name="updatedDate" format="yyyy-MM-dd 24h:mm"/> </td>
		</tr>
	</s:iterator>
	</s:else>
	</tbody>
</table>
<div class="pull-right">
	<p:page pageInfo="voPage"/> 
	<%--
	<com:page pageCount="10" curPage="1" maxNo="5" action=""></com:page>
	--%>
</div>
<%--
<div class="pagination pull-right">
	<ul>
	    <li><a href="#">Prev</a></li>
	    <li class="active">
	      <a href="#">1</a>
	    </li>
	    <li><a href="#">2</a></li>
	    <li><a href="#">3</a></li>
	    <li><a href="#">4</a></li>
	    <li><a href="#">Next</a></li>
	</ul>
</div>
 --%>