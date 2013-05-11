<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 设计变更审批表 --%>
<%@ include file="template-header.jsp"%>
<div class="billContent" align="center">
<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
</s:set>
	
	<div>
	 <form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
	  <%@ include file="template-var.jsp"%>
	  <input type="hidden" name="templateBean.resApproveCd" value="${approveCd}${serialNo}" >
	  <input type="hidden" name="templateBean.resApproveId" value="${resApproveInfoId}" >
	  <table class="mainTable">
	    <col width="100"/>
		<col/>
		<col width="120"/>
		<col/>
		<tr>
			<td colspan="4" class="chkGroup" align="left">
			   <table class="tb_checkbox" style="width:100%;">
			        <col />
					<tr align="center">
					<td>
						<s:checkbox name="templateBean.isThreeLine" cssClass="group"></s:checkbox>涉及园林景观、公共区域装修、精装修（销售楼盘）和“三点一线”（售楼处、样板房、景观示范区和看房通道）
					</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="chkGroup" align="left"  validate="required">
			   <table class="tb_checkbox" style="width:100%;">
			        <col width="25%"/>
			        <col width="25%"/>
			        <col width="25%"/>
			        <col />
					<tr align="center">
					<td>
						<s:checkbox name="templateBean.importDesignVisa" cssClass="group"></s:checkbox>重大设计变更
					</td>
					<td> 
						<s:checkbox name="templateBean.guraDesignVisa" cssClass="group"></s:checkbox>其他设计变更
					</td>
					<td> 
						<s:checkbox name="templateBean.tenantsHadVisaBefore" cssClass="group"></s:checkbox>物业移交前商户提出
					</td>
					<td> 
						<s:checkbox name="templateBean.tenantsHadVisa" cssClass="group"></s:checkbox>物业移交后商户提出
					</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">合同编号</td>
			<td align="left" colspan="3">
				<input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
				
					
					<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input id="contNo" class="inputBorder" validate="required" type="text" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:else>
			</td>
		</tr>
		<tr>
			<td class="td_title">合同名称</td>
			<td align="left">
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
			</td>
			<td class="td_title">乙方</td>
			<td align="left">
				<input class="inputBorder" type="text" readonly="readonly" name="templateBean.partB" id="partB" value="${templateBean.partB}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">项目名称</td>
			<td align="left" colspan="3">
				<input class="inputBorder" type="text" readonly="readonly" name="templateBean.projectName" id="projectName" value="${templateBean.projectName}" style="width:300px;"/>
				(<input class="inputBorder" type="text" name="templateBean.period" value="${templateBean.period}" style="width:40px;"/>)期
			</td>
		</tr>
		<tr>
			<td class="td_title">变更内容</td>
			<td align="left">
				<input validate="required" class="inputBorder" type="text" name="templateBean.visaContent" value="${templateBean.visaContent}" />
			</td>
			<td class="td_title">拟签发变更单编号</td>
			<td align="left">
				<input class="inputBorder" type="text" name="templateBean.issueVisaNo" value="${templateBean.issueVisaNo}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">变更原因</td>
			<td align="left" colspan="3" class="chkGroup"  validate="required">
			     <table class="tb_checkbox">
					<col width="100">
					<col width="120">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.designWrong"  cssClass="group"></s:checkbox>变更错漏</td>
					<td><s:checkbox name="templateBean.saleCause"  cssClass="group"></s:checkbox>招商/销售原因</td>
					<td><s:checkbox name="templateBean.constructCause"  cssClass="group"></s:checkbox>施工原因</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.partACause"  cssClass="group"></s:checkbox>甲方原因</td>
					<td><s:checkbox name="templateBean.otherCause"  cssClass="group"></s:checkbox>其它</td>
					<td></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td class="td_title">原图纸编号</td>
		 <td align="left" colspan="3">
			<input validate="required" class="inputBorder" type="text" name="templateBean.drawingNo" value="${templateBean.drawingNo}"/>
		 </td>
		</tr>
		<tr>
			<td class="td_title">合同总价</td>
			<td align="left">
				<input class="inputBorder" type="text" readonly="readonly" name="templateBean.totalPrice" id="totalPrice" value="${templateBean.totalPrice}" />
			</td>
			<td class="td_title">已确认合同总价</td>
			<td align="left">
				<input class="inputBorder" type="text" readonly="readonly" name="templateBean.updateTotal" id="updateTotal" value="${templateBean.updateTotal}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">已累计变更预估费用(元)</td>
			<td align="left">
				<input id="preFeeNum_before" class="inputBorder" type="text" readonly="readonly" name="templateBean.prepareFeeTotal" value="${templateBean.prepareFeeTotal}" />
			</td>
			<td class="td_title">已预估变更比例%</td>
			<td align="left">
				<input id="preFeeRate_before" class="inputBorder" type="text" readonly="readonly" name="templateBean.preFeeTotRate" value="${templateBean.preFeeTotRate}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">本次预估费用(元)</td>
			<td align="left">
				<input validate="required" class="inputBorder" type="text" alt="amount" onblur="formatVal($(this));" name="templateBean.prepareFee" value="${templateBean.prepareFee}" onkeyup="doFeeRate(this);"/>
			</td>
			<td class="td_title">本次预估变更比例%</td>
			<td align="left">
				<input type="text" id="preFeeRate" class="inputBorder" readonly="readonly" name="templateBean.preFeeRate" value="${templateBean.preFeeRate}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">备注说明</td>
			<td align="left" colspan="3">
			   <textarea name="templateBean.remark" class="inputBorder contentTextArea">${templateBean.remark}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_title" rowspan="3">附件上传</td>
			<td align="center" style="height:40px;" validate="required" value="${templateBean.issueVisaDrawId}">拟签发设计变更图</td>
			<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','issueVisaDrawId',event);"/>
				<input id="issueVisaDrawId" type="hidden" name="templateBean.issueVisaDrawId" value="${templateBean.issueVisaDrawId}"/>
				</s:if>
			</td>
			<td >
			<div id="show_issueVisaDrawId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.issueVisaDrawId}','issueVisaDrawId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" validate="required" value="${templateBean.updateBudgetId}">变更预算</td>
			<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','updateBudgetId',event);"/>
				<input id="updateBudgetId" type="hidden" name="templateBean.updateBudgetId" value="${templateBean.updateBudgetId}"/>
				</s:if>
			</td>
			<td >
			<div id="show_updateBudgetId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.updateBudgetId}','updateBudgetId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.adjureFileId}">提出部门的文件(如果有)</td>
			<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','adjureFileId',event);"/>
				<input id="adjureFileId" type="hidden" name="templateBean.adjureFileId" value="${templateBean.adjureFileId}"/>
				</s:if>
			</td>
			<td >
			<div id="show_adjureFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.adjureFileId}','adjureFileId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="3" class="chkGroup" validate="required">
				<table class="tb_checkbox" style="width:100%;">
				    <col width="100">
				    <col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.land"  cssClass="group"></s:checkbox>仅与地产有关</td>
					<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
					<td><s:checkbox name="templateBean.estate"  cssClass="group"></s:checkbox>与商业有关</td>
					</tr>
				</table>
			</td>
		</tr>
	  </table>
	  <%@ include file="template-approver.jsp"%>
	 </form>
	</div>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectCd:'projectCd',
		projectName:'projectName',
		partB:'partB',
		totalPrice:'totalPrice',
		updateTotal:'updateTotal',
		preFeeNum_before:'preFeeNum_before',
		preFeeRate_before:'preFeeRate_before'
	};
var param={codeType:"0"};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,param);
function doFeeRate(dom){
	var prepFee =$(dom).val();
	if($("#updateTotal").val()!=0){
		var updateTotal = formatFloat($("#updateTotal").val());
		$("#preFeeRate").val(((getValByJ($(dom))/updateTotal)*100).toFixed(2));
	}
}
</script>