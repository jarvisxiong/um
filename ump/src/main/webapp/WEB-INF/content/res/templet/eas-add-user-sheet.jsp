<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--EAS用户增加申请表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120"/>
			<col />
			<col width="80"/>
			<col/>
			<tr>
				<td class="td_title">申请人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}" />
				</td>
				<td class="td_title">申请人帐号</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.applAccount" value="${templateBean.applAccount}"  /></td>
			</tr>
			<tr>
				<td class="td_title">申请日期</td>
				<td colspan="3" align="left">
					<input class="Wdate inputBorder" validate="required" style="width:100px;" type="text" name="templateBean.applDate" value="${templateBean.applDate}" onfocus="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">所在单位/部门/职位</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}" /></td>
			</tr>
			<tr>
				<td class="td_title">工作职责描述</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.jobDuties">${templateBean.jobDuties}</textarea></td>
			</tr>
			<tr>
				<td class="td_title">帐套范围</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.accountScope">${templateBean.accountScope}</textarea></td>
			</tr>
			<tr>
				<td class="td_title">职位级别</td>
				<td colspan="3" align="left" class="chkGroup" validate="required">
					是否为公司财务负责人、中心经理级以上人员
					<s:checkbox name="templateBean.postionLevel1" cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.postionLevel2" cssClass="group"></s:checkbox>否
				</td>
			</tr>
			<tr>
				<td class="td_title">权限模块</td>
				<td colspan="3" align="left" class="chkGroup" validate="required">
					<s:checkbox name="templateBean.module1"  ></s:checkbox>总账 
					<s:checkbox name="templateBean.module2"  ></s:checkbox>报表
					<s:checkbox name="templateBean.module3"  ></s:checkbox>现金管理
					<s:checkbox name="templateBean.module4"  ></s:checkbox>固定资产管理
					<s:checkbox name="templateBean.module5"  ></s:checkbox>现金流量表
					<s:checkbox name="templateBean.module6"  ></s:checkbox>合并报表
					<br/>
					<s:checkbox name="templateBean.module7"  ></s:checkbox>基础资料
					<s:checkbox name="templateBean.module8"  ></s:checkbox>初始化
					<s:checkbox name="templateBean.module9"  ></s:checkbox>系统设置
					<s:checkbox name="templateBean.module10"  ></s:checkbox>预算管理
					<s:checkbox name="templateBean.module11"  ></s:checkbox>其他
					<input class="inputBorder" type="text" style="width:100px;" name="templateBean.moduleOther" value="${templateBean.moduleOther}"  />
				</td>
			</tr>					
			<tr>
				<td class="td_title">权限内容</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.permissionContent">${templateBean.permissionContent}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
