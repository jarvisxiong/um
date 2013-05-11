<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 样板房方案审批表(eg:定价) -->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100px">
			<col >
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr> 
			 <tr>
				<td class="td_title">样板房位置</td>
				<td style="padding-left:5px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<tr>
							<td style="width:60px;"><input style="width:60px;" validate="required" class="inputBorder" type="text" name="templateBean.buildName" value="${templateBean.buildName}"/></td>
							<td style="width:15px;">栋</td>
							<td style="width:60px;"><input style="width:60px;" validate="required" class="inputBorder" type="text" name="templateBean.floorName" value="${templateBean.floorName}"/></td>
							<td style="width:15px;">层</td>
							<td style="width:60px;"><input style="width:60px;" validate="required" class="inputBorder" type="text" name="templateBean.unitName" value="${templateBean.unitName}"/></td>
							<td style="width:28px;">单元</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.storeName" value="${templateBean.storeName}"/></td>
							<td style="width:80px;">住宅/店面</td>
						</tr>
					</table>
				</td>
			</tr> 
			 <tr>
				<td class="td_title">内容简述<br/>(详细内容附后)</td>
				<td>
					<textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="width:100%;height:180px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
