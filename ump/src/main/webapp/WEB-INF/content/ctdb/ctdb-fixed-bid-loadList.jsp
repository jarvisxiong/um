<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table class="ctdbTable" cellpadding="0" cellspacing="0" style="margin-top: 5px;">
	<colgroup>
		<col width="100px;"/>
		<col/>
		<col/>
		<col width="80px;"/>
		<col width="80px;"/>
		<col width="80px;"/>
		<col width="80px;"/>		
		<col width="80px;"/>		
		<col width="80px;"/>		
		<col width="80px;"/>				
	</colgroup>
	<thead>
		<tr>
			<th title="项目名称" nowrap="nowrap"  style="background-image: url('');">项目</th>
			<th title="工程名称(设计阶段)" nowrap="nowrap">工程</th>
			<th title="中标单位" nowrap="nowrap">中标单位</th>
			<th title="招标范围" nowrap="nowrap">招标范围</th>
			<th title="计价模式" nowrap="nowrap">计价模式</th>
			<th title="施工面积(m2)" nowrap="nowrap">施工面积</th>
			<th title="中标价" nowrap="nowrap">中标价</th>
			<th title="单位造价(单价)" nowrap="nowrap">单位造价</th>
			<th title="定标日期" nowrap="nowrap">定标日期</th>
			<th title="网批编号" nowrap="nowrap">网批编号</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.result" var="bided">
	<tr>
		<td class="pd-chill-tip" title="${orgName}"><div class="partHide">${orgName}</div></td>
		<td class="pd-chill-tip" title="${projectName}"><div class="partHide"  >${projectName}</div></td>
		<td class="pd-chill-tip" title="${bidSupCd}"><div class="partHide" >${bidSupCd}</div></td>
		<td class="pd-chill-tip" title="${bidArea}"><div class="partHide" >${bidArea}</div></td>
		<td class="pd-chill-tip" title="${calcuMode}"><div class="partHide" >${calcuMode}</div></td>
		<td class="pd-chill-tip" title="${constructArea}"><div class="partHide" >${ constructArea}</div></td>
		<td class="pd-chill-tip" title="${bidedPrice}"><div class="partHide" >${ bidedPrice}</div></td>
		<td class="pd-chill-tip" title="${unitPrice}"><div class="partHide" >${unitPrice}</div></td>
		<td class="pd-chill-tip" title="<s:date name="#bided.bidDate" format="yy-MM-dd"/>"><div><s:date name="#bided.bidDate" format="yy-MM-dd"/></div></td>
		<td title="${approveCd}" >
			<div class="partHide">
			<a  style="color:red;" href="javascript:void(0)" 
				title="点击查看网批号：${approveCd}"  class="pd-chill-tip" 
				onclick="openResTask('${resCd}','${approveCd}','${importType}');">
				${approveCd}
			</a>
			</div>
		</td><%--${resCd} --%>
	</tr>
	</s:iterator>
	</tbody>
</table>
<div class="pageFooter">		
	<input type="hidden" name="pageNums" value="${page.totalPages}"></input>		
	<input type="hidden" name="orgName" value="<%=request.getParameter("orgName")==null?"":request.getParameter("orgName")%>"></input>
	<input type="hidden" name="projectName"  value="<%=request.getParameter("projectName")==null?"":request.getParameter("projectName")%>"></input>
	<input type="hidden" name="bidSupName"  value="<%=request.getParameter("bidSupName")==null?"":request.getParameter("bidSupName")%>"></input>
	<input type="hidden" name="bidDate"  value="<%=request.getParameter("bidDate")==null?"":request.getParameter("bidDate")%>"></input>
	<p:page/>
</div>

