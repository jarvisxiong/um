<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 目标成本调整审批表 --%>
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
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable">
			<col width="160"/>
			<col/>
			<col width="160"/>
			<col/>
			<col width="40"/>
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="4">
				<table class="tb_checkbox" style="width:100%;">
				    <col width="100">
				    <col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
					</tr>
				</table>
			</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="4" class="chkGroup" align="left"  validate="required">
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.checkType1"  cssClass="group"></s:checkbox>目标成本(可研版)
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.checkType2"  cssClass="group"></s:checkbox>目标成本
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="4">
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
				<td colspan="2">
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.submitDate" value="${templateBean.submitDate}"/>
				</td>
			</tr>
			
			<tr>
				<td class="td_title">总建筑面积(㎡)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.totalBuildingArea" value="${templateBean.totalBuildingArea}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">计入容积率建筑面积(㎡)</td>
				<td colspan="2">
				<input class="inputBorder" validate="required" type="text" name="templateBean.capacityRatioBuildingArea" value="${templateBean.capacityRatioBuildingArea}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">原目标成本总额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.oriTotalTargetCost" value="${templateBean.oriTotalTargetCost}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">原容积率面积单方造价(元/㎡)</td>
				<td colspan="2">
				<input class="inputBorder" validate="required" type="text" name="templateBean.oriCapacityRatioUnitCost" value="${templateBean.oriCapacityRatioUnitCost}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">调整后目标成本总额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.totalTargetCost" value="${templateBean.totalTargetCost}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">调整后容积率面积单方造价(元/㎡)</td>
				<td colspan="2">
				<input class="inputBorder" validate="required" type="text" name="templateBean.capacityRatioUnitCost" value="${templateBean.capacityRatioUnitCost}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="1">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.subItemTargetCostDetailId}">调整子项成本明细表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('调整子项成本明细表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','subItemTargetCostDetailId',event);"/>
				<input type="hidden" name="templateBean.subItemTargetCostDetailId" id="subItemTargetCostDetailId" value="${templateBean.subItemTargetCostDetailId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_subItemTargetCostDetailId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.subItemTargetCostDetailId}','subItemTargetCostDetailId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		<table id="tbConItem" class="mainTable" style="margin-top: 5px;">
			<col width="160"/>
			<col/>
			<col width="160"/>
			<col/>
			<col width="40"/>
			<tr>
				<th style="text-align: center;">调整子项内容</th>
				<th style="text-align: center;">调整原因</th>
				<th style="text-align: center;">原子项目标成本</th>
				<th style="text-align: center;">调整后子项目标成本</th>
				<th style="text-align: center;">操作</th>
			</tr>
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display: none;margin-bottom:5px;">
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].subItemContent" /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].adjustReason" /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].oriSubItemTargetCost" /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].nowSubItemTargetCost" /></td>
				<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
			</tr>
			</s:if>
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
			<tr style="margin-bottom:5px;">
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].subItemContent" value="${item.subItemContent}" /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].adjustReason" value="${item.adjustReason}" /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].oriSubItemTargetCost" value="${item.oriSubItemTargetCost}" /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].nowSubItemTargetCost" value="${item.nowSubItemTargetCost}" /></td>
				<td width="15px" align="center">
				<s:if test="#canEdit=='true'">
				<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</td>
			</tr>
			</s:iterator>
		</table>
		<s:if test="statusCd==0 || statusCd==3">
		<input type="button"  class="btn_blue_75_55" value="增加子项" onclick="addItem();" />
		</s:if>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>