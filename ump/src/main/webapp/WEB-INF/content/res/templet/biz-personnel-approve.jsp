<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--商业公司人事审批表--%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="80px">
		<col/>
		<col width="80px">
		<col/>
		<tr>
			<td class="td_title">选择</td>
			<td colspan="3" class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col width="120">
					<col width="120">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.positionLevel1"  cssClass="group"></s:checkbox>总经理</td>
					<td><s:checkbox name="templateBean.positionLevel2"  cssClass="group"></s:checkbox>副总经理</td>
					<td><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox>高级经理级(除垂直管理人员外)</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox>垂直管理人员</td>
					<td><s:checkbox name="templateBean.positionLevel5"  cssClass="group"></s:checkbox>其他人员</td>
					<td></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">姓名</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.personName" value="${templateBean.personName}"/>
			</td>
			<td class="td_title">岗位名称</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.stationName" value="${templateBean.stationName}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">所在单位</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.personDept" value="${templateBean.personDept}"/>
			</td>
			<td class="td_title">职级</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.rank" value="${templateBean.rank}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">入职日期</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" onfocus="WdatePicker()" name="templateBean.enterDate" value="${templateBean.enterDate}"/>
			</td>
			<td class="td_title">员工编号</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.personNo" value="${templateBean.personNo}"/>
			</td>
		</tr>
		<tr>
		 <td class="td_title">审核种类</td>
			<td colspan="3" class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
				<col width="100">
				<col width="100">
				<col width="100">
				<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.tryOut"  cssClass="group"></s:checkbox>试用</td>
					<td><s:checkbox name="templateBean.regularWorker"  cssClass="group"></s:checkbox>转正</td>
					<td><s:checkbox name="templateBean.remove"  cssClass="group"></s:checkbox>任免</td>
					<td><s:checkbox name="templateBean.upSalary"  cssClass="group"></s:checkbox>调薪</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.reward"  cssClass="group"></s:checkbox>奖励</td>
					<td><s:checkbox name="templateBean.punish"  cssClass="group"></s:checkbox>处罚</td>
					<td><s:checkbox name="templateBean.dismiss"  cssClass="group"></s:checkbox>辞退</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td class="td_title">简要说明</td>
		<td colspan="3">
		  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc" rows="6" cols="30">${templateBean.contentDesc}</textarea>
		</td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>