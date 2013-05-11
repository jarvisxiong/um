<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<!--人事变动表(商业公司)-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="60px"/>
			<col />
			<tr>
			<td class="td_title">
			选择
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="230">
				<col width="250">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1"  cssClass="group"></s:checkbox>总经理</td>
				<td><s:checkbox name="templateBean.positionLevel2"  cssClass="group"></s:checkbox>副总经理</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox>高级经理级(除垂直管理人员外)及以上</td>
				<td><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox>双线管理部门负责人(财务、成本)</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel7"  cssClass="group"></s:checkbox>双线管理部门负责人(人资)</td>
				<td><s:checkbox name="templateBean.positionLevel5"  cssClass="group"></s:checkbox>其他人员</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td class="td_title">
			类别
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="230">
				<col width="250">
				<tr>
				<td><s:checkbox name="templateBean.changeType1"  cssClass="group"></s:checkbox>薪资、岗位确定及调整</td>
				<td><s:checkbox name="templateBean.changeType2"  cssClass="group"></s:checkbox>仅政策内补贴申请</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="biz-personnel-change-base.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
