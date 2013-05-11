<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 扣减费用核定单 --%>
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
		<input type="hidden" name="templateBean.resApproveCd" value="${approveCd}${serialNo}" >
	  	<input type="hidden" name="templateBean.resApproveId" value="${resApproveInfoId}" >
		<table class="mainTable">
			<col width="135"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				<input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
				
				
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input id="contNo" class="inputBorder" validate="required" type="text" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="1">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="10"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="projectName" name="templateBean.projectName" value="${templateBean.projectName}" />
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title">乙方</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="partB" name="templateBean.partB" value="${templateBean.partB}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">扣减内容</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reduceContent">${templateBean.reduceContent}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">扣减原因</td>
				<td colspan="3" class="chkGroup" align="left" validate="required">
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.reduceReason1"  cssClass="group"></s:checkbox>另行委托
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.reduceReason2"  cssClass="group"></s:checkbox>设计变更减少
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.reduceReason3"  cssClass="group"></s:checkbox>未施工甩项，且经同意
				</td>
			</tr>
			<tr>
				<td class="td_title">地产公司扣减费用(元)</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.reduceMoney" value="${templateBean.reduceMoney}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title">核价编制说明</td>
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<tr>
							<td>1、变更费用按合同<input class="inputBorder" validate="required" type="text" name="templateBean.itemNo" style="width:20%;" value="${templateBean.itemNo}" />
							条款  第<input class="inputBorder" validate="required" type="text" name="templateBean.functionNo" value="${templateBean.functionNo}" style="width:40%;"/>
							种方法约定，</td>
						</tr>
						<tr>
						 <td>其中工程量按<input class="inputBorder" validate="required" type="text" name="templateBean.projectQuantity" value="${templateBean.projectQuantity}" style="width:50%;"/>计量，</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="150"/>
						<col/>
						<tr>
							<td class="chkGroup" colspan="2" align="left" validate="required">单价按
								&nbsp;&nbsp;<s:checkbox name="templateBean.unitPriceType1"  cssClass="group"></s:checkbox>合同综合单价
								&nbsp;&nbsp;<s:checkbox name="templateBean.unitPriceType2"  cssClass="group"></s:checkbox>参考合同综合单价
								&nbsp;&nbsp;<s:checkbox name="templateBean.unitPriceType3"  cssClass="group"></s:checkbox>定额计价
							</td>
						</tr>
						<tr>
						  <td>确定，合同约定下浮率</td>
						  <td><input class="inputBorder" validate="required" type="text" name="templateBean.reduceRate" value="${templateBean.reduceRate}" style="width:60%;"/>%</td>
					    </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.reduceBudgetId}">核减的预算</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核减的预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','reduceBudgetId',event);"/>
				<input type="hidden" name="templateBean.reduceBudgetId" id="reduceBudgetId" value="${templateBean.reduceBudgetId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_reduceBudgetId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.reduceBudgetId}','reduceBudgetId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" value="${templateBean.liveVisaId}">图纸或现场签证</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('图纸或现场签证','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','liveVisaId',event);"/>
				<input type="hidden" name="templateBean.liveVisaId" id="liveVisaId" value="${templateBean.liveVisaId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_liveVisaId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.liveVisaId}','liveVisaId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" value="${templateBean.quantityConformId}">工程量确认单</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('工程量确认单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','quantityConformId',event);"/>
				<input type="hidden" name="templateBean.quantityConformId" id="quantityConformId" value="${templateBean.quantityConformId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_quantityConformId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.quantityConformId}','quantityConformId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">集团核定费用(元)</td>
				<td colspan="3">
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			</s:if>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script>
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
</script>
<s:if test="#canEdit=='true'">
<script type="text/javascript">
	var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectCd:'projectCd',
		projectName:'projectName',
		partB:'partB'
	};
	var param={codeType:"0"};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,param);
</script>
</s:if>
