<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	

<!-- 样板房物资清单采购及调配建议审批表 -->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px">
			<col>
			 <tr>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr> 
			 <tr>
				<td class="td_title">物业范围</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.propertyScopeDesc" value="${templateBean.propertyScopeDesc}"/>
				</td>
			</tr> 
			 <tr>
				<td class="td_title">内容简述<br/>(详细内容附后)</td>
				<td>
					<textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="width:100%;height:150px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
