<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table class="prodTable">
	<colgroup>
		<col width="35px;"></col>
		<col width="40%"></col>
		<col width="20%"></col>
		<col></col>
	</colgroup>
	<thead>
		<tr>
			<th style="background-image: url('');">序号</th>
			<th>业态</th>
			<th>权重</th>
			<th>描述</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="w" value="page.result" status="status">
			<tr bussinessCd="${bussinessCd}">
			<td><s:property value="#status.index+1"></s:property></td>
			<td bussinessCd="${bussinessCd}"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/></td>
			<td weight="${weight}">${weight}</td>
			<td weightDesc="${weightDesc}">${weightDesc}</td>
			</tr>
		</s:iterator>
		
	</tbody>	
</table>
	<form action="${ctx}/prod/prod-bussiness-weight!wieghtContent.action" id="pageForm">
	<input type="hidden" name="bussinessCd" id="input_bussinessCd"></input>
	<div class="pageFooter">
		<input type="hidden" id="pageNums" value="${page.totalPages}"></input>
		<p:page/>
	</div>
	</form>