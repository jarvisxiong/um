<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 长途用车申请单 --%>
<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		
		<table  class="mainTable">
			<col width="80"/>
			<col/>
			<col width="80"/>
			<col/>
			<col width="80"/>
			<col width="120"/>
			<tr>
				<td class="td_title">驾驶员</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.driverName" value="${templateBean.driverName}"  />
					<input type="hidden" name="templateBean.driverCd" value="${templateBean.driverCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}"  />
					<input type="hidden" name="templateBean.positionCd" value="${templateBean.positionCd}"  />	
				</td>
				<td  class="td_title">所属单位</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}"  />
					<input type="hidden" name="templateBean.deptCd" value="${templateBean.deptCd}"  />
				</td>
			</tr>
			<tr>
				<td rowspan="3" class="td_title">行程安排</td>
				<td rowspan="3" colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.scheduling">${templateBean.scheduling}</textarea>
				</td>
				<td class="td_title">发车时间</td>
				<td>
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.departTime" value="${templateBean.departTime}" onfocus="WdatePicker()"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">返回时间</td>
				<td>
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.returnTime" value="${templateBean.returnTime}" onfocus="WdatePicker()" />
				</td>
			</tr>
			<tr>
				<td class="td_title">随行人员</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.entourage" value="${templateBean.entourage}"  />
					<input type="hidden" name="templateBean.entourageCd" value="${templateBean.entourageCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">事由</td>
				<td  colspan="5">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason">${templateBean.reason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">公里数</td>
				<td colspan="5">
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.mileage" value="${templateBean.mileage}"  />
				</td>
			</tr>
		</table>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
