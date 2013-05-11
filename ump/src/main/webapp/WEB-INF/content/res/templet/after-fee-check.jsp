<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 标后核对费用核定单 --%>
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
	    <col width="120"/>
		<col/>
		<col width="100"/>
		<col/>
		<col width="100"/>
		<col/>
		<tr>
			<td class="td_title">合同编号
			</td>
			<td align="left" colspan="5">
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
			<td align="left" colspan="2">
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
			</td>
			<td class="td_title">乙方</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" readonly="readonly" name="templateBean.partB" id="partB" value="${templateBean.partB}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">项目名称</td>
			<td align="left" colspan="5">
				<input id="projectName" class="inputBorder" type="text" readonly="readonly" name="templateBean.projectName" value="${templateBean.projectName}" style="width:300px;"/>
				(<input class="inputBorder" type="text" name="templateBean.period" value="${templateBean.period}" style="width:40px;"/>)期
			</td>
		</tr>
		<tr>
			<td class="td_title">面积</td>
			<td align="left" colspan="5">
				<input class="inputBorder" type="text" id="area" name="templateBean.area"  value="${templateBean.area}" name="templateBean.area" validate="required" onblur="formatVal($(this));doContTarget();checkRate();"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">核对内容</td>
			<td align="left" colspan="5">
				<input class="inputBorder" type="text" name="templateBean.checkContent" id="checkContent" value="${templateBean.checkContent}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">暂定合同总价(元)(暂定合同总价)</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" id="temporaryPrice" name="templateBean.temporaryPrice" value="${templateBean.temporaryPrice}" onblur="formatVal($(this));"/>
			</td>
			<td class="td_title">原合同单位指标(元/m2)</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" id="contTarget" readonly="readonly" name="templateBean.contTarget" value="${templateBean.contTarget}"/>
			</td>
		</tr>
		<tr>
		    <td class="td_title">施工单位上报金额(元)</td>
		    <td>
		     <input type="text" id="projAppMoney" onblur="formatVal($(this));constRate();" value="${templateBean.projAppMoney}" name="templateBean.projAppMoney" validate="required" class="inputBorder"/>
		    </td>
		    <td class="td_title">相应单位指标(元/m2)</td>
		    <td>
		     <input type="text" onblur="formatVal($(this));" value="${templateBean.relProjTarget}" readonly="readonly" name="templateBean.relProjTarget" validate="required" class="inputBorder"/>
		    </td>
		    <td class="td_title">施工单位上报时间</td>
		    <td>
		     <input type="text" value="${templateBean.projAppTime}" name="templateBean.projAppTime" validate="required" class="inputBorder" onfocus="WdatePicker();" />
		    </td>
		</tr>
		 <tr>
		    <td class="td_title">咨询机构上报金额(元)</td>
		    <td>
		     <input type="text" id="instAppMoney" onblur="formatVal($(this));doRate('instAppMoney','cutInstRate');" value="${templateBean.instAppMoney}" name="templateBean.instAppMoney" validate="required" class="inputBorder"/>
		    </td>
		    <td class="td_title">相应单位指标(元/m2)</td>
		    <td><input type="text" id="relInstTarget" onblur="formatVal($(this));" readonly="readonly" value="${templateBean.relInstTarget}" name="templateBean.relInstTarget" validate="required" class="inputBorder"/></td>
		    <td class="td_title">核减率%</td>
		    <td><input type="text" id="cutInstRate"  readonly="readonly" name="templateBean.cutInstRate" validate="required" class="inputBorder" value="${templateBean.cutInstRate}"/></td>
	   </tr>
	   <tr>
		    <td class="td_title">区域公司核对金额(元)</td>
		    <td>
		     <input type="text" id="compCheckMoney" onblur="formatVal($(this));doRate('compCheckMoney','cutComRate');" value="${templateBean.compCheckMoney}"  name="templateBean.compCheckMoney" validate="required" class="inputBorder"/>
		    </td>
		    <td class="td_title">相应单位指标(元/m2)</td>
		    <td><input type="text" onblur="formatVal($(this));" readonly="readonly" value="${templateBean.relComTarget}" name="templateBean.relComTarget" validate="required" class="inputBorder"/></td>
		    <td class="td_title">核减率%</td>
		    <td><input type="text" id="cutComRate" readonly="readonly" value="${templateBean.cutComRate}" name="templateBean.cutComRate" validate="required" class="inputBorder"></td>
	   </tr>
	   <tr>
		    <td class="td_title">目标成本金额(元)</td>
		    <td>
		     <input type="text" id="targetCostMoney" onblur="formatVal($(this));doRate('targetCostMoney','differRate');" value="${templateBean.targetCostMoney}" name="templateBean.targetCostMoney" validate="required" class="inputBorder"/>
		    </td>
		    <td class="td_title">相应单位指标(元/m2)</td>
		    <td>
		     <input type="text" readonly="readonly" onblur="formatVal($(this));" readonly="readonly" value="${templateBean.relCostTarget}" name="templateBean.relCostTarget" class="inputBorder"/>
		    </td>
		    <td class="td_title">差异率%</td>
		    <td>
		    <input type="text" id="differRate" readonly="readonly" value="${templateBean.differRate}" name="templateBean.differRate" class="inputBorder"/>
		    </td>
		   </tr>
		 <tr>
		    <td class="td_title">编制说明</td>
			<td align="left" colspan="5">
				<input type="text" id="orgExplan" class="inputBorder" validate="required" name="templateBean.orgExplan" value="${templateBean.orgExplan}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">原合同、标后核对差异说明</td>
			<td align="left" colspan="5">
				<input class="inputBorder" type="text" name="templateBean.differExplan" value="${templateBean.differExplan}" />
			</td>
		</tr>
		<tr>
				<td class="td_title" rowspan="7">附件上传</td>
				<td align="center" colspan="2" style="height:40px;" validate="required" value="${templateBean.checkGraphListId}">核对的图纸清单目录</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','checkGraphListId',event);"/>
					<input id="checkGraphListId" type="hidden" name="templateBean.checkGraphListId" value="${templateBean.checkGraphListId}"/>
					</s:if>
				</td>
				<td colspan="2">
				<div id="show_checkGraphListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.checkGraphListId}','checkGraphListId','${canEdit}');
				</script>
				</td>
			</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.projBudgetId}" colspan="2">单位工程预算汇总和指示表</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','projBudgetId',event);"/>
			<input id="projBudgetId" type="hidden" name="templateBean.projBudgetId" value="${templateBean.projBudgetId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_projBudgetId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.projBudgetId}','projBudgetId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.origCheckDiffId}" colspan="2">原合同、标后核对差异附表</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','origCheckDiffId',event);"/>
			<input id="origCheckDiffId" type="hidden" name="templateBean.origCheckDiffId" value="${templateBean.origCheckDiffId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_origCheckDiffId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.origCheckDiffId}','origCheckDiffId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.easteAuditId}" colspan="2">地产公司审核意见汇总</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','easteAuditId',event);"/>
			<input id="easteAuditId" type="hidden" name="templateBean.easteAuditId" value="${templateBean.easteAuditId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_easteAuditId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.easteAuditId}','easteAuditId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="height:40px;" value="${templateBean.materialNumId}">甲供材料设备数量表</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('施工单位预算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','materialNumId',event);"/>
			<input id="materialNumId" type="hidden" name="templateBean.materialNumId" value="${templateBean.materialNumId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_materialNumId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.materialNumId}','materialNumId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.materialNumId}" colspan="2">咨询机构成果(含编制说明、主要工程量指标统计表、预算书)</td>
			<td align="center" colspan="3">
			 需邮寄
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.materialNumId}" colspan="2">施工单位上报预算书</td>
			<td align="center" colspan="3">
			 需邮寄
			</td>
		</tr>
		<s:if test="statusCd==2">
			<tr>
				<td align="center">核定费用</td>
				<td colspan="5">
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));"/>
			</tr>
		</s:if>
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
		projectName:'projectName',
		partB:'partB'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama);
//原合同单位指标
function doContTarget(){
	var temporaryPrice=($("#temporaryPrice").val()==""?0:formatFloat($("#temporaryPrice").val()));
	var area =($("#area").val()==""?0:formatFloat($("#area").val()));
	if(area!=0){
		$("#contTarget").val(temporaryPrice/area);
	}
}
function doRate(beforeMoney,cutRate){
	var befMoney =$("#"+beforeMoney).val()==""?0:formatFloat($("#"+beforeMoney).val());
	var projAppMoney =$("#projAppMoney").val()==""?0:formatFloat($("#projAppMoney").val());
	var areaByRate =$("#area").val()==""?0:formatFloat($("#area").val());
	//相应单位指标
	if(areaByRate!="0"){
		var targetRate =befMoney/areaByRate;
		$('#'+beforeMoney).closest('td').next().next().find('input').val(targetRate.toFixed(2));
	}
	//核减率
	if(cutRate!=""&&projAppMoney!=0){
		$("#"+cutRate).val((befMoney/projAppMoney-1).toFixed(2));
	}
	//差异率
	befMoney=$("#targetCostMoney").val()==""?0:formatFloat($("#targetCostMoney").val());
	var compCheckMoney =$("#compCheckMoney").val()==""?0:formatFloat($("#compCheckMoney").val());
	if(befMoney!=0&&(beforeMoney=='targetCostMoney'||beforeMoney=='compCheckMoney')&&compCheckMoney!=0){
		$("#differRate").val((befMoney/compCheckMoney-1).toFixed(2));
	}
}
function checkRate(){
	doRate('instAppMoney','cutInstRate');
	doRate('compCheckMoney','cutComRate');
	doRate('targetCostMoney','differRate');
}
function constRate(){
	doRate('projAppMoney','');
	doRate('instAppMoney','cutInstRate');
	doRate('compCheckMoney','cutComRate');
}
</script>