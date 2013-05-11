<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--能耗收费标准及调整审批表--%>
<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="80px"/>
			<col/>
			<col/>
			<tr>
				<td class="td_title">标题</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.titleName" value="${templateBean.titleName}"/></td>
			</tr>
			<tr>
				<td class="td_title">公司名称</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="td_title" style="text-align: center;">公用事业部门定价</td>
				<td class="td_title" style="text-align: center;">向商户收费方案</td>
				</tr>
				<tr>
				<td class="td_title">水</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.waterPrice1" value="${templateBean.waterPrice1}"/></td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.waterPrice2" value="${templateBean.waterPrice2}"/></td>
				</tr>
				<tr>
				<td class="td_title">电</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.electricPrice1" value="${templateBean.electricPrice1}"/></td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.electricPrice2" value="${templateBean.electricPrice2}"/></td>
				</tr>
				<tr>
				<td class="td_title">供暖</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.heatingPrice1" value="${templateBean.heatingPrice1}"/></td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.heatingPrice2" value="${templateBean.heatingPrice2}"/></td>
				</tr>
				<tr>
				<td class="td_title">其他</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.otherPrice1" value="${templateBean.otherPrice1}"/></td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.otherPrice2" value="${templateBean.otherPrice2}"/></td>
			</tr>
			<tr>
				<td class="td_title">相关说明</td>
       			<td colspan="2"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark" style="height:200px;">${templateBean.remark}</textarea></td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>