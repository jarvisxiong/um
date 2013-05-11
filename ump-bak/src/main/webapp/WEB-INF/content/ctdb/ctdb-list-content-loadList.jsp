<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table class="ctdbTable"  cellpadding="0" cellspacing="0" style="margin-top: 5px;">
			<%--<colgroup>
				<col width="5%"></col>
				<col width="8%"></col>
				<col width="8%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="10%"></col>		
				<col width="10%"></col>		
				<col width="10%"></col>		
				<col width="10%"></col>				
			</colgroup> --%>
			<thead>
				<tr>
					<th width="40"rowspan="2" title="清单编号" nowrap="nowrap" style="background-image: url('');">清单编号</th>
					<th width="40" rowspan="2"  nowrap="nowrap" title="清单名称">清单名称</th>
					<%--<th width="40" rowspan="2"  nowrap="nowrap" title="说明">说明</th> --%>
					<th width="20" rowspan="2" title="数量" style="text-align: right;padding-right:5px;">数量</th>
					<th width="20"rowspan="2" title="单位" style="text-align: right;padding-right:5px;">单位</th>
					<th width="20" rowspan="2" title="单价" style="text-align: right;padding-right:5px;">单价</th>
					<th colspan="6" align="center" style="text-align: right;padding-right:5px;" title="单价组价明细">单价组价明细</th>
					<th width="40" rowspan="2" title="主材损耗率" nowrap="nowrap" width="40" style="text-align: right;padding-right:5px;">损耗率</th>
					<%--<th width="40" rowspan="2" title="价格来源" nowrap="nowrap" width="40">来源</th> --%>
					<th rowspan="2" title="状态" nowrap="nowrap" width="40">状态</th>
				</tr>
				<tr>
					<th width="40" title="人工费" style="text-align: right;padding-right:5px;">人工费</th>
					<th width="40" title="主材费" style="text-align: right;padding-right:5px;">主材费</th>
					<th width="40" title="辅材费" style="text-align: right;padding-right:5px;">辅材费</th>
					<th width="40" title="机械费" style="text-align: right;padding-right:5px;">机械费</th>
					<th width="40" nowrap="nowrap" style="text-align: right;padding-right:5px;" title="管理费及利润"  class="pd-chill-tip"><div class="partHide">管理费及利润</div></th>
					<th width="40" title="税金" style="text-align: right;padding-right:5px;">税金</th>					
				</tr>
			</thead>
			<tbody>
			<s:iterator value="page.result" var="bided">
				<tr onclick="editListForm('${listContentId}','${page.pageNo}');">
					<td  title="${listCd}" class="pd-chill-tip"><div class="partHide" >${listCd}</div></td>
					<td  title="${listName}" class="pd-chill-tip"><div class="partHide" >${listName}</div></td>
					<%--
					<td  title="${listDesc}" ><div class="ellipsisDiv" >${listDesc}</div></td> 
					--%>
					<td  title="${qty}"  class="pd-chill-tip" style="float:right;padding-right:5px;"><div class="partHide" >${qty}</div></td>
					<td  title="${measurement}"  class="pd-chill-tip" style="text-align: center;"><div class="partHide"  style="float:right;padding-right:5px;">${measurement}</div></td>
					<td title="${unitPrice}"  class="pd-chill-tip" style="text-align: right;"><div class="partHide"  style="float:right;padding-right:5px;">${unitPrice}</div></td>
					<td title="${personalPrice}" class="pd-chill-tip" style="text-align: right;"><div class="partHide"  style="float:right;padding-right:5px;">${personalPrice}</div></td>
					<td title="${mainMatePrice}" class="pd-chill-tip" style="text-align: right;"><div class="partHide"  style="float:right;padding-right:5px;">${mainMatePrice}</div></td>
					<td title="${aidMatePrice}" class="pd-chill-tip" style="text-align: right;"><div class="partHide"  style="float:right;padding-right:5px;">${aidMatePrice}</div></td>
					<td title="${machinePrice}" class="pd-chill-tip" style="text-align: right;"><div class="partHide" style="float:right;padding-right:5px;">${machinePrice}</div></td>
					<td title="${manageFees}" class="pd-chill-tip" style="text-align: right;"><div class="partHide"  style="float:right;padding-right:5px;">${manageFees}</div></td>
					<td title="${tax}" class="pd-chill-tip" style="text-align: right;"><div class="partHide"  style="float:right;padding-right:5px;">${tax}</div></td>
					<td title="${mainMateRate}" class="pd-chill-tip" style="text-align: right;"><div class="partHide" style="float:right;padding-right:5px;">${mainMateRate}</div></td>
					<%--
					<td title="${priceFrom}" ><div class="ellipsisDiv" >${priceFrom}</div></td> 
					--%>
					<td title="" >
						<div >
							<s:if test="statusCd==0">暂存</s:if>
							<s:elseif test="statusCd==1">待确认</s:elseif>
							<s:elseif test="statusCd==2">已确认</s:elseif>			
							<s:elseif test="statusCd==3">驳回</s:elseif>			
						</div>
					</td>
				</tr>
			</s:iterator>
			</tbody>
	</table>
<div style="float: right;margin: 10px 15px 0  0;">
	<input type="hidden" name="listCd" value="<%=request.getParameter("listCd")==null?"":request.getParameter("listCd")%>">
	<input type="hidden" name="listName" value="<%=request.getParameter("listName")==null?"":request.getParameter("listName")%>">
	<input type="hidden" name="createdDate" value="<%=request.getParameter("createdDate")==null?"":request.getParameter("createdDate")%>">
	<p:page pageInfo="page"/>
</div>
