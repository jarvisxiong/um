<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 变更费用核定单 --%>
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
	  <table class="mainTable">
	    <col width="120"/>
		<col/>
		<col width="120"/>
		<col/>
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
				<td class="td_title">设计变更审批表</td>
				<td>
				
				<input type="hidden" name="templateBean.resApproveId" id="resApproveId" value="${templateBean.resApproveId}"/>
					<s:if test="!templateBean.resApproveId.isEmpty() && #canEdit == 'false'">
						<span  class="link"   onclick="openSheet('${templateBean.resApproveCd}','${templateBean.resApproveId}');">${templateBean.resApproveCd}</span>
						<input class="inputBorder" name="templateBean.resApproveCd"  type="hidden" id="resApproveCd" value="${templateBean.resApproveCd}"/>
					</s:if>
					<s:else>
						<input class="inputBorder" name="templateBean.resApproveCd"  type="text" id="resApproveCd" value="${templateBean.resApproveCd}"/>
					</s:else>
				</td>
				<td class="td_title">设计变更审批表</td>
				<td align="left">
				<c:choose>
					<c:when test="${empty templateBean.resApproveTitleName || templateBean.resApproveTitleName eq 'null'}">
						<input  class="inputBorder" readonly="readonly" name="templateBean.resApproveTitleName"  type="text" id="resApproveTitleName"  value=""/>
					</c:when>
					<c:otherwise>
						<input  class="inputBorder" readonly="readonly" name="templateBean.resApproveTitleName"  type="text" id="resApproveTitleName"  value="${templateBean.resApproveTitleName}"/>
					</c:otherwise>
				</c:choose>
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
		    <td class="td_title">设计变更/现场变更审批表编号</td>
			<td align="left">
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
				    <input type="hidden" id="visaNoId"  name="templateBean.visaNoId" value="${templateBean.visaNoId}" />
				    <input type="hidden" id="visaNo"  name="templateBean.visaNo" value="${templateBean.visaNo}" />
					<span  class="link" onclick="getCont('${templateBean.contLedgerId}','${templateBean.visaNoId}');">${templateBean.visaNo}</span>
				</s:if>
				<s:else>
				    <input type="hidden" id="visaNoId"  name="templateBean.visaNoId" value="${templateBean.visaNoId}" />
					<input type="text" id="visaNo" class="inputBorder" validate="required" name="templateBean.visaNo" value="${templateBean.visaNo}"/>
				</s:else>
			</td>
			<td class="td_title">变更内容</td>
			<td align="left">
				<input type="text" id="visaContent" class="inputBorder" readonly="readonly" name="templateBean.visaContent" value="${templateBean.visaContent}" />
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
			<td class="td_title">已累计变更额(元)</td>
			<td align="left">
				<input id="amountUpdateTotal" class="inputBorder" type="text" readonly="readonly" name="templateBean.amountUpdateTotal" value="${templateBean.amountUpdateTotal}" />
			</td>
			<td class="td_title">已变更比例%</td>
			<td align="left">
				<input id="amountUpdateRate" class="inputBorder" type="text" readonly="readonly" name="templateBean.amountUpdateRate" value="${templateBean.amountUpdateRate}"/>
			</td>
		</tr>
			<tr>
			<td class="td_title">已审批预算(元)</td>
			<td align="left">
				<input id="prepareFee" validate="required" class="inputBorder" readonly="readonly" type="text" name="templateBean.prepareFee" value="${templateBean.prepareFee}"/>
			</td>
			<td class="td_title">施工单位上报费用(元)</td>
			<td align="left">
				<input id="preFeeRate" class="inputBorder" type="text" name="templateBean.workFee" value="${templateBean.workFee}" alt="amount"  onblur="formatVal($(this));"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">地产公司审核费用(元)</td>
			<td align="left">
				<input id="projectAuditFee" validate="required" class="inputBorder" type="text" alt="amount"  onblur="formatVal($(this));projUpdateRate(this);" name="templateBean.projectAuditFee" value="${templateBean.projectAuditFee}"/>
			</td>
			<td class="td_title">本次变更比例%</td>
			<td align="left">
				<input id="updateRate" validate="required" readonly="readonly" class="inputBorder" type="text" name="templateBean.updateRate" value="${templateBean.updateRate}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">核价编制说明</td>
			<td align="left" colspan="3" style="width: 100%;">
			     <table class="tb_checkbox">
			        <col width="250">
			        <col/>
					<tr>
					<td colspan="2">
					 1、变更费用按合同<input class="inputBorder" type="text" name="templateBean.contItem" value="${templateBean.contItem}" style="width:60px;"/>
					 条款 &nbsp;第<input class="inputBorder" type="text" name="templateBean.contWay" value="${templateBean.contWay}" style="width:60px;"/>
					 种方法约定，其中工程量按计量，单价按
					 <s:checkbox name="templateBean.contPrice"  cssClass="group"></s:checkbox>合同综合单价
					 <s:checkbox name="templateBean.refeContPrice"  cssClass="group"></s:checkbox>参考合同综合单价
					 <s:checkbox name="templateBean.fixedPrice"  cssClass="group"></s:checkbox>定额计价
					 &nbsp;确定，合同约定下浮率
					 <input class="inputBorder" type="text" name="templateBean.lowerRate" value="${templateBean.lowerRate}" style="width:60px;"/>
					  % <br></br>
					 </td>
					 </tr>
					 <tr>
					 <td>
					 2、扣减其他单位费用：<s:checkbox name="templateBean.haveOtherFee"  cssClass="group"></s:checkbox>无
					 <s:checkbox name="templateBean.noOtherFee"  cssClass="group"></s:checkbox>有，
					 </td>
					 <td>
					 已同时上报《扣减费用核定单》
					 <s:checkbox name="templateBean.preHaveOtherFee"  cssClass="group"></s:checkbox>无
					 <s:checkbox name="templateBean.preNoOtherFee"  cssClass="group"></s:checkbox>有。
					 </td>
					 </tr>
					 <tr>
					 <td colspan="2">
					 3、其他说明<input class="inputBorder" type="text" name="templateBean.otherDeclare" value="${templateBean.otherDeclare}" style="width:80%;"/>
					</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">审批权限</td>
			<td colspan="3" class="chkGroup" align="left""  validate="required">
			
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.abovePercent3"  cssClass="group"></s:checkbox>累计签证超过目标成本的3%</td>
					</tr>
					<tr><td><s:checkbox name="templateBean.abovePercent5"  cssClass="group"></s:checkbox>累计签证超过单项合同额5%且累计签证金额超过100万元</td></tr>
					<tr><td><s:checkbox name="templateBean.other"  cssClass="group"></s:checkbox>其他</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title" rowspan="4">附件上传</td>
			<td align="center" style="height:40px;" validate="required" value="${templateBean.projectAuditId}">地产公司成本部审核预算</td>
			<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('审核预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','projectAuditId',event);"/>
				<input id="projectAuditId" type="hidden" name="templateBean.projectAuditId" value="${templateBean.projectAuditId}"/>
				</s:if>
			</td>
			<td>
			<div id="show_projectAuditId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.projectAuditId}','projectAuditId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.constructFeeId}">施工单位预算</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','constructFeeId',event);"/>
			<input id="constructFeeId" type="hidden" name="templateBean.constructFeeId" value="${templateBean.constructFeeId}"/>
			</s:if>
			</td>
			<td>
			<div id="show_constructFeeId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.constructFeeId}','constructFeeId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.designVisaAppId}">设计变更审批表</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','designVisaAppId',event);"/>
			<input id="designVisaAppId" type="hidden" name="templateBean.designVisaAppId" value="${templateBean.designVisaAppId}"/>
			</s:if>
			</td>
			<td>
			<div id="show_designVisaAppId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.designVisaAppId}','designVisaAppId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.visaCheckId}">设计变更图纸或现场签证工程量确认单</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','visaCheckId',event);"/>
			<input id="visaCheckId" type="hidden" name="templateBean.visaCheckId" value="${templateBean.visaCheckId}"/>
			</s:if>
			</td>
			<td>
			<div id="show_visaCheckId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.visaCheckId}','visaCheckId','${canEdit}');
			</script>
			</td>
		</tr>
		<s:if test="statusCd==2">
			<tr>
				<td align="center">核定费用</td>
				<td colspan="3">
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));"/>
			</tr>
		</s:if>
	  </table>
	  <%@ include file="template-approver.jsp"%>
	 </form>
	</div>
</div>
<%@ include file="template-footer.jsp"%>

<%--表类型判断 --%>
<c:choose>
	<%--HOE供应商 --%>
	<c:when test="${authTypeCd == 'JSGL_QZGL_10'}">
	<c:set var="selectAuthTypeCd"  value="JSGL_SJBG_01"/>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
var mapPrama={
		approveCd:'resApproveCd',
		titleName:'resApproveTitleName',
		id:'resApproveId'
	};
$("#resApproveCd").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama,{authTypeCd:'${selectAuthTypeCd}',statusCd:'2'});

function getCont(id, contVisaId){
	
	if(typeof(resVisaId) == undefined){
		contVisaId ='';
	}
	
	var url="${ctx}/cont/cont-ledger!input.action?id="+id+'&contVisaId=' + contVisaId;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectName:'projectName',
		partB:'partB',
		totalPrice:'totalPrice',
		updateTotal:'updateTotal',
		visaTotal:'amountUpdateTotal',
		amountUpdateRate:'amountUpdateRate',
		visaNo:'visaNo',
		preFeeNum_before:'preFeeNum_before',
		preFeeRate_before:'preFeeRate_before'
	};
	var param={codeType:"0"};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,param);
var visaParma={
		visaNo:'visaNo',
		visaContent:'visaContent',
		prepareFee:'prepareFee',
		visaNoId:'visaNoId'
};
	$("#visaNo").quickSearch("${ctx}/cont/cont-ledger!searchVisaById.action",['visaNo','visaContent'],visaParma,{},false,{ledgerId:'contLedgerId'});
function projUpdateRate(dom){
	var prepFee =formatFloat($(dom).val());
	var updateTotal =formatFloat($("#updateTotal").val());
	if($("#updateTotal").val()!=0){
		$("#updateRate").val(((prepFee/updateTotal)*100).toFixed(2));
	}
}
</script>