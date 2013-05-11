<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--EAS用户权限变动申请表--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="140"/>
			<col/>
			<tr>
				<td class="td_title">申请人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.applyUserName" value="${templateBean.applyUserName}" />
				</td>
				<td class="td_title">申请人帐号</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.applyUserAccounts" value="${templateBean.applyUserAccounts}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请日期</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onfocus="WdatePicker()" name="templateBean.applyUserDate" value="${templateBean.applyUserDate}"/>
				</td>
				<td class="td_title">所在单位/部门/职位</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.deptName" value="${templateBean.deptName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">变更类型</td>
				<td colspan="3" class="chkGroup" align="left">
					<s:checkbox  name="templateBean.ctFinancePeople" cssClass="group"></s:checkbox>财务人员
				</td>
			</tr>
			<tr>
				<td colspan="3" class="chkGroup" validate="required">
					<table class="tb_checkbox">
						<col width="100">
						<col width="100">
						<tr>
						<td>
						<s:checkbox  name="templateBean.ctAddRight" cssClass="group"></s:checkbox>增加权限
						</td>
						<td>
						<s:checkbox  name="templateBean.ctDelRight" cssClass="group"></s:checkbox>删除权限
						</td>
						</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">变动原因</td>
				<td colspan="3" class="chkGroup" validate="required" align="left">
				<p>
					<s:checkbox id="personnelChange"  name="templateBean.personnelChange" cssClass="group"></s:checkbox>人事异动
				</p>	
				<p>
					异动前公司/部门/职位：
					<span class="pd-chill-tip" title="">							
					<input class="inputBorder" id="frontPersonnelChange"  validate="" type="text" style="width: 300px;height:10px;" name="templateBean.frontPersonnelChange" value="${templateBean.frontPersonnelChange}"/>
					</span>
				</p>
				<p>
					异动后公司/部门/职位：
					<span class="pd-chill-tip" title="">
					<input class="inputBorder" id="afterPersonnelChange" validate=""  type="text" style="width: 300px;height:10px;" name="templateBean.afterPersonnelChange" value="${templateBean.afterPersonnelChange}"/>
					</span>
				</p>
				<p>
				<s:checkbox id="addUnit"  name="templateBean.addUnit" cssClass="group"></s:checkbox>增加单位/部门：&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="pd-chill-tip" title="">
						<input class="inputBorder" type="text" id="addUnitValue" validate="" style="width: 300px;height:10px;" name="templateBean.addUnitValue" value="${templateBean.addUnitValue}"/>
					</span>
				</p>	
				<p>
				<s:checkbox id="other"  name="templateBean.other" cssClass="group"></s:checkbox>其它（请作其它原因说明）：
					<span class="pd-chill-tip" title="">
					<input class="inputBorder" type="text" id="otherValue" validate="" style="width: 250px;height:10px;" name="templateBean.otherValue" value="${templateBean.otherValue}"/>
					</span>
				</p>		
				</td>
			</tr>
			<tr>
				<td class="td_title">申请人岗位职责描述</td>
				<td colspan="3" >
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.postResponsibilityDes">${templateBean.postResponsibilityDes}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">组织范围</td>
				<td colspan="3" >
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.groupScope">${templateBean.groupScope}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">权限模块</td>
				<td colspan="3"  validate="required">
					<table class="tb_checkbox">
						<col width="170">
						<col width="150">
						<col width="150">
						<col width="150">
						<tr>
						<td><s:checkbox  name="templateBean.abilityQualityModel" cssClass="group"></s:checkbox>能力素质模型</td>
						<td><s:checkbox  name="templateBean.performanceManage" cssClass="group"></s:checkbox>绩效管理</td>
						<td><s:checkbox  name="templateBean.groupPlanning" cssClass="group"></s:checkbox>组织规划</td>
						<td><s:checkbox  name="templateBean.personnelManage" cssClass="group"></s:checkbox>职员管理</td>
						</tr>
						<tr>
						<td><s:checkbox  name="templateBean.workManage" cssClass="group"></s:checkbox>考勤管理</td>
						<td><s:checkbox  name="templateBean.jobSelect" cssClass="group"></s:checkbox>招聘选拔</td>
						<td><s:checkbox  name="templateBean.trainDevelop" cssClass="group"></s:checkbox>培训发展</td>
						<td><s:checkbox  name="templateBean.salaryDesign" cssClass="group"></s:checkbox>薪酬设计</td>
						
						</tr>
						<tr>
						<td><s:checkbox  name="templateBean.salaryCheck" cssClass="group"></s:checkbox>薪酬核算</td>
						<td><s:checkbox  name="templateBean.socialSecurity" cssClass="group"></s:checkbox>社保副利</td>
						<td><s:checkbox  name="templateBean.seachReport" cssClass="group"></s:checkbox>搜索报表</td>
						<td><s:checkbox  name="templateBean.mobileHRGuide" cssClass="group"></s:checkbox>移动HR向导</td>
						</tr>
						<tr>
						<td><s:checkbox  name="templateBean.myWorkbench" cssClass="group"></s:checkbox>我的工作台</td>
						<td></td>
						<td></td>
						<td></td>
						</tr>
						
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">工资项目权限</td>
				<td colspan="3" >
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.salaryProjectRight">${templateBean.salaryProjectRight}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">申请权限内容描述</td>
				<td colspan="3" >
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applyRightContentDes">${templateBean.applyRightContentDes}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script>
$('#personnelChange').click(function(){
	if(this.checked){
		$('#frontPersonnelChange').attr("validate","required");
		$('#afterPersonnelChange').attr("validate","required");

		$('#addUnitValue').attr("validate","");
		$('#addUnitValue').val("");
		$('#otherValue').attr("validate","");
		$('#otherValue').val("");
	}else{
		$('#frontPersonnelChange').attr("validate","");
		$('#afterPersonnelChange').attr("validate","");
		$('#frontPersonnelChange').val("");
		$('#afterPersonnelChange').val("");
	}
	
});
$('#addUnit').click(function(){
	if(this.checked){
		$('#addUnitValue').attr("validate","required");

		$('#frontPersonnelChange').attr("validate","");
		$('#afterPersonnelChange').attr("validate","");
		$('#frontPersonnelChange').val("");
		$('#afterPersonnelChange').val("");
		$('#otherValue').attr("validate","");
		$('#otherValue').val("");
	}else{
		$('#addUnitValue').attr("validate","");
		$('#addUnitValue').val("");
	}
	
});
$('#other').click(function(){
	if(this.checked){
		$('#otherValue').attr("validate","required");

		$('#frontPersonnelChange').attr("validate","");
		$('#afterPersonnelChange').attr("validate","");
		$('#frontPersonnelChange').val("");
		$('#afterPersonnelChange').val("");
		$('#addUnitValue').attr("validate","");
		$('#addUnitValue').val("");
	}else{
		$('#otherValue').attr("validate","");
		$('#otherValue').val("");
	}
	
});


</script>
