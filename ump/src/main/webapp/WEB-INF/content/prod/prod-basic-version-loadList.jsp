<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="basicVersionList" >
	<table  class="prodTable" style="width: 100%;">	
	<col style="max-width: 10%;"></col>
	<col style="max-width: 10%;"></col>
	<col style="max-width: 10%;"></col>
	<col style="max-width: 10%;"></col>
	<col style="max-width: 10%;"></col>
	<col ></col>
	<col ></col>
	<thead>
		<tr>
			<th nowrap="nowrap" style="background-image: url('');">序号</th>
			<th nowrap="nowrap">版本号</th>
			<th nowrap="nowrap">年月</th>			
			<th nowrap="nowrap">状态</th>			
			<th nowrap="nowrap">创建人</th>
			<th nowrap="nowrap">创建时间</th>
			<th >备注</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result" var="bv" status="status">
			<tr basicVersionId="${prodBasicVersionId}">
				<td ><s:property value="#status.index+1"></s:property></td>
				<td nowrap="nowrap" title="${versionNo}" >${versionNo}</td>
				<td  nowrap="nowrap" title="${yearCd}-${monthCd}" >${yearCd}-${monthCd}</td>
				<td  nowrap="nowrap" >
					<s:if test="active==1">
					<div style="color: green;" title="当前版本，正在使用。">激活</div>
					</s:if>
					<s:else>
					<div  style="color:red;" title="非当前版本，未使用。">未使用</div>
					</s:else>		
				</td>				
				<td  nowrap="nowrap" title="${creator}">${creator}</td>
				<td nowrap="nowrap"  title="<s:date name="#bv.createdDate" format="yy-MM-dd"/>"><div><s:date name="#bv.createdDate" format="yy-MM-dd"/></div></td>
				<td title="${versionDes}" style="max-width: 100px;">
					<div style="max-width: 100px;margin-left: 2px;overflow: hidden;max-height: 26px;text-overflow: ellipsis;"  style="overflow:hidden;"  title="${versionDes}" >
						${versionDes}
					</div>
				</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<div class="pageFooter">
		<input type="hidden" id="pageNums" value="${page.totalPages}"></input>
		<p:page/>
	</div>
</div>