<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<!--人事变动表(宝龙商业)-->
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
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1"  cssClass="group"></s:checkbox>中心总经理及以上人员</td>
				<td><s:checkbox name="templateBean.positionLevel2"  cssClass="group"></s:checkbox>中心副总经理</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox>双线管理人员(财务)</td>
				<td><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox>其他</td>
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
