<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!-- 加班补休申请表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80px">
			<col width="100px">
			<col width="60px">
			<col width="60px">
			<col width="60px">
			<col width="60px">
			<col width="60px">
			<col> 
			<tr>
				<td class="td_title">中心/区域</td>
				<td colspan="5">
					<input validate="required" type="text" name="templateBean.centerOrgName" value="${templateBean.centerOrgName}" id="centerOrgName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerOrgCd"  name="templateBean.centerOrgCd" value="${templateBean.centerOrgCd}"  />
				</td>
				<td class="td_title">部门</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.deptOrgName" value="${templateBean.deptOrgName}"/>
					<input type="hidden" id="deptOrgCd"  name="templateBean.deptOrgCd" value="${templateBean.deptOrgCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">姓名</td>
				<td>
					<input id="applyUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="applyUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionName" value="${templateBean.positionName}"/>
					<input type="hidden" id="positionCd"  name="templateBean.positionCd" value="${templateBean.positionCd}"  />
				</td>
				<td class="td_title">职级</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionLevelName" value="${templateBean.positionLevelName}"/>
					<input type="hidden" id="positionLevelCd"  name="templateBean.positionLevelCd" value="${templateBean.positionLevelCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">补休日期</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.resetDate" value="${templateBean.resetDate}"/>
				</td>
				<td class="td_title">从(时)</td>
				<td>
					<input validate="required" class="inputBorder"  onfocus="WdatePicker({dateFmt:'HH:mm'})" type="text" name="templateBean.resetStartHour" value="${templateBean.resetStartHour}"/>
				</td>
				<td class="td_title">至(时)</td>
				<td>
					<input validate="required" class="inputBorder" onfocus="WdatePicker({dateFmt:'HH:mm'})" type="text" name="templateBean.resetEndHour" value="${templateBean.resetEndHour}"/>
				</td>
				<td class="td_title">时数</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.restHours" value="${templateBean.restHours}" onblur="formatVal($(this))"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title" rowspan="4">补偿替代</td>
				<td style="text-align:center;">加班日期</td>
				<td style="text-align:center;" colspan="3">加班时数</td>
				<td style="text-align:center;" colspan="3">上次补休后剩余时数</td>
			</tr>
			<tr>
				<td><input  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.overTimeDate1" value="${templateBean.overTimeDate1}"/></td>
				<td colspan="4"><input   class="inputBorder" type="text" id="overTimeHours1" name="templateBean.overTimeHours1" value="${templateBean.overTimeHours1}" onblur="formatVal($(this))" onchange="calculateTotal()"/></td>
				<td colspan="2"><input   class="inputBorder" type="text" id="lastLeaveHours1" name="templateBean.lastLeaveHours1" value="${templateBean.lastLeaveHours1}" onblur="formatVal($(this))" onchange="calculateTotal()"/></td>
			</tr>
			<tr>
				<td><input  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.overTimeDate2" value="${templateBean.overTimeDate2}"/></td>
				<td colspan="4"><input   class="inputBorder" type="text" id="overTimeHours2" name="templateBean.overTimeHours2" value="${templateBean.overTimeHours2}" onblur="formatVal($(this))" onchange="calculateTotal()"/></td>
				<td colspan="2"><input   class="inputBorder" type="text" id="lastLeaveHours2" name="templateBean.lastLeaveHours2" value="${templateBean.lastLeaveHours2}" onblur="formatVal($(this))" onchange="calculateTotal()"/></td>
			</tr>
			<tr>
				<td><input  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.overTimeDate3" value="${templateBean.overTimeDate3}"/></td>
				<td colspan="4"><input   class="inputBorder" type="text" id="overTimeHours3" name="templateBean.overTimeHours3" value="${templateBean.overTimeHours3}" onblur="formatVal($(this))" onchange="calculateTotal()"/></td>
				<td colspan="2"><input   class="inputBorder" type="text" id="lastLeaveHours3" name="templateBean.lastLeaveHours3" value="${templateBean.lastLeaveHours3}" onblur="formatVal($(this))" onchange="calculateTotal()"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">累计时数</td>
				<td colspan="4"><input  validate="required" class="inputBorder" type="text" id="totalOverTimeHours" name="templateBean.totalOverTimeHours" value="${templateBean.totalOverTimeHours}" readonly="readonly"/></td>
				<td colspan="2"><input  validate="required" class="inputBorder" type="text" id="totalLeaveHours" name="templateBean.totalLeaveHours" value="${templateBean.totalLeaveHours}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="td_title">补休原因 </td>
				<td colspan="7">
					<textarea class="inputBorder contentTextArea" name="templateBean.restReasonDesc" style="width:100%;height:180px;">${templateBean.restReasonDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<script type="text/javascript">

	function calculateTotal(){
		formatVal($('#overTimeHours1'));
		formatVal($('#lastLeaveHours1'));
		formatVal($('#overTimeHours2'));
		formatVal($('#lastLeaveHours2'));
		formatVal($('#overTimeHours3'));
		formatVal($('#lastLeaveHours3'));

		cleanNumInput('overTimeHours1');
		cleanNumInput('overTimeHours2');
		cleanNumInput('overTimeHours3');
		cleanNumInput('lastLeaveHours1');
		cleanNumInput('lastLeaveHours2');
		cleanNumInput('lastLeaveHours3');

		$('#totalOverTimeHours').val(formatMoney(getVal('overTimeHours1')+getVal('overTimeHours2')+getVal('overTimeHours3')));
		$('#totalLeaveHours').val(formatMoney(getVal('lastLeaveHours1')+getVal('lastLeaveHours2')+getVal('lastLeaveHours3')));
	}
	
	function cleanNumInput(elemId){
		if('0.00' == $('#'+elemId).val()){
			$('#'+elemId).val('');
		}
	}

</script>
