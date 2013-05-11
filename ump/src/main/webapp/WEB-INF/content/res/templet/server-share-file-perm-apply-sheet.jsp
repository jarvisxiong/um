<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--服务器共享文件权限申请单-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col style="width:80px;">
			<col>
			<col style="width:40px;">
			<col style="width:150px;">
			<tr>
				<td class="td_title">所属中心</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.applyCenterOrgName" value="${templateBean.applyCenterOrgName}" id="applyCenterOrgName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="applyCenterOrgCd"  name="templateBean.applyCenterOrgCd" value="${templateBean.applyCenterOrgCd}"  />
				</td>
			</tr>
			<!--<tr>
				<td class="td_title">部门</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" id="applyDeptOrgName" name="templateBean.applyDeptOrgName" value="${templateBean.applyDeptOrgName}"/>
					<input type="hidden" id="applyCenterOrgCd"  name="templateBean.applyDeptOrgCd" value="${templateBean.applyDeptOrgCd}"  />
				</td>
			</tr>
			-->
			<tr>
				<td class="td_title">申请人</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.applyUserName" value="${templateBean.applyUserName}"/>
					<input type="hidden" id="applyUserCd"  name="templateBean.applyUserCd" value="${templateBean.applyUserCd}"  />
				</td>
				<td class="td_title">日期</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.applyDate" value="${templateBean.applyDate}"/>
				</td>
			</tr>  
			<tr>
				<td rowspan="5" class="td_title">文件夹名称</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.fileName1" value="${templateBean.fileName1}"/>
				</td>
				<td rowspan="5" class="td_title">权限</td>
				<td class="chkGroup" align="left" validate="required" >
					<s:checkbox name="templateBean.selectFileName1Read"  cssClass="group"/>只读 
					<s:checkbox name="templateBean.selectFileName1Write" cssClass="group" cssStyle="margin-left:10px;"/>读写 
				</td>
			</tr>  
			<tr>
				<td>
					<input class="inputBorder" type="text" name="templateBean.fileName2" value="${templateBean.fileName2}"/>
				</td>
				<td class="chkGroup" align="left">
					<s:checkbox name="templateBean.selectFileName2Read"  cssClass="group"/>只读 
					<s:checkbox name="templateBean.selectFileName2Write" cssClass="group" cssStyle="margin-left:10px;"/>读写 
				</td>
			</tr>
			<tr>
				<td>
					<input class="inputBorder" type="text" name="templateBean.fileName3" value="${templateBean.fileName3}"/>
				</td>
				<td class="chkGroup" align="left">
					<s:checkbox name="templateBean.selectFileName3Read"  cssClass="group"/>只读 
					<s:checkbox name="templateBean.selectFileName3Write" cssClass="group" cssStyle="margin-left:10px;"/>读写 
				</td>
			</tr>
			<tr>
				<td>
					<input class="inputBorder" type="text" name="templateBean.fileName4" value="${templateBean.fileName4}"/>
				</td>
				<td class="chkGroup" align="left">
					<s:checkbox name="templateBean.selectFileName4Read"  cssClass="group"/>只读 
					<s:checkbox name="templateBean.selectFileName4Write" cssClass="group" cssStyle="margin-left:10px;"/>读写 
				</td>
			</tr>
			<tr>
				<td>
					<input class="inputBorder" type="text" name="templateBean.fileName5" value="${templateBean.fileName5}"/>
				</td>
				<td class="chkGroup" align="left">
					<s:checkbox name="templateBean.selectFileName5Read"  cssClass="group"/>只读 
					<s:checkbox name="templateBean.selectFileName5Write" cssClass="group" cssStyle="margin-left:10px;"/>读写 
				</td>
			</tr>
			<tr>
				<td class="td_title">申请理由</td>
				<td colspan="3">
					<textarea validate="required" class="inputBorder contentTextArea" name="templateBean.contentDesc" style="width:100%;height:200px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
