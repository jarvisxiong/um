<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		<%-- 搜素结果 --%>
		<div id="tableDiv" style="overflow:auto;border-bottom:1px solid #dddbdc; ">
			<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
				<col>
					<s:if test="selectedOrderBy=='approveUserCd'||selectedOrderBy=='userCd'">
					<s:iterator value="page.result">
						<jsp:include page="wap-approve-info-data.jsp">
							<jsp:param value="0" name="listMode"/>
						</jsp:include>
					</s:iterator>
					</s:if>
					<s:else>
					<s:iterator value="mapResult" var="lst">
					<s:if test="#lst.value.size > 0">
					<s:iterator value="#lst.value" var="row"  status="status">
						<tr>
							<td style="font-size: 12px; cursor: default; font-weight: bold; padding-left:5px;">
								<div style="padding-top:5px;float:left;">
									<div style="float:left;">${lst.key}</div>
								</div>
								<s:set var="fColor">#0167a2</s:set>
								<s:if test="statusCd==0">
									<s:set var="fColor">#f46614</s:set>
								</s:if>
								<div style="padding-top:5px;float:left;color:${fColor};">&nbsp;
									(<s:property value='#row.resApproveInfos.size'/>)
								</div>
							</td>
						</tr>
						<s:iterator value="#row.resApproveInfos">
							<jsp:include page="wap-approve-info-data.jsp">
								<jsp:param value="0" name="listMode"/>
							</jsp:include>
						</s:iterator>
					</s:iterator>
					</s:if> 
				</s:iterator>
				</s:else>
					<tr>
						<td class="wap_table_pager" style="margin-top:5px;">
							<p:page />
						</td>
					</tr>
			</table>
		</div>