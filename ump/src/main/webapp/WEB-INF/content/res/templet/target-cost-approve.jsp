<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 目标成本审批表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="3">
				<table class="tb_checkbox" style="width:100%;">
				    <col width="100">
				    <col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td>	&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.approvePrivFlg" cssClass="group"/>与酒店有关<!--<s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关11111--></td>
					</tr>
				</table>
			</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3" class="chkGroup" align="left"  validate="required">
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.checkType1"  cssClass="group"></s:checkbox>目标成本(可研版)
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.checkType2"  cssClass="group"></s:checkbox>目标成本
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">开工时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.startDate" value="${templateBean.startDate}"/>
				</td>
				<td class="td_title">交房时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.submitDate" value="${templateBean.submitDate}"/>
				</td>
			</tr>
			
			<tr>
				<td class="td_title">总建筑面积(㎡)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.totalBuildingArea" value="${templateBean.totalBuildingArea}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">计入容积率建筑面积(㎡)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.capacityRatioBuildingArea" value="${templateBean.capacityRatioBuildingArea}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">目标成本总额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.totalTargetCost" value="${templateBean.totalTargetCost}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">容积率面积单方造价(元/㎡)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.capacityRatioUnitCost" value="${templateBean.capacityRatioUnitCost}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">主要内容及说明</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.indicateOrProductStandardId}">规划指标或产品标准</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('规划指标或产品标准','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','indicateOrProductStandardId',event);"/>
				<input type="hidden" name="templateBean.indicateOrProductStandardId" id="indicateOrProductStandardId" value="${templateBean.indicateOrProductStandardId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_indicateOrProductStandardId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.indicateOrProductStandardId}','indicateOrProductStandardId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.targetCostDrawingDescId}">目标成本编制说明</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('目标成本编制说明','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','targetCostDrawingDescId',event);"/>
				<input type="hidden" name="templateBean.targetCostDrawingDescId" id="targetCostDrawingDescId" value="${templateBean.targetCostDrawingDescId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_targetCostDrawingDescId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.targetCostDrawingDescId}','targetCostDrawingDescId','${canEdit}');
				</script>
				</td>
			</tr> 
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.targetCostSheetId}">目标成本组成表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('目标成本组成表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','targetCostSheetId',event);"/>
				<input type="hidden" name="templateBean.targetCostSheetId" id="targetCostSheetId" value="${templateBean.targetCostSheetId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_targetCostSheetId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.targetCostSheetId}','targetCostSheetId','${canEdit}');
				</script>
				</td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
