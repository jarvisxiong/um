<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 合同付款审批表 -->
<%@ include file="template-header.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="30px"/>
			<col width="100px"/>
			<col />
			<col width="100px"/>
			<col />
			<tr>
				<td class="td_title" colspan="2">公司名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"  /></td>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr>
			<s:if test="authTypeCd=='JD_CWGL_100'||authTypeCd=='FSS_ZYJD_CWGL_110'">
			<tr>
			<td class="td_title" colspan="2"></td>
			<td colspan="3" class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.contractArea1" cssClass="group"></s:checkbox>合同规定范围内</td>
				<td><s:checkbox name="templateBean.contractArea2" cssClass="group"></s:checkbox>超出合同规定</td>
				</tr>
				</table>
			</td>
			</tr>
			</s:if>
			<tr>
				<s:if test="authTypeCd=='FKGL_YXFY'||authTypeCd=='FKGL_XZFY'||authTypeCd=='FKGL_GDZC'||authTypeCd=='FKGL_ZXFY'||authTypeCd=='JD_CWGL_100'">
				<td colspan="2" class="td_title">合同编号</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.contractNo" value="${templateBean.contractNo}"/></td>
				<td class="td_title">合同名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.contractName" value="${templateBean.contractName}" /></td>
				</s:if>
				<s:else>
				<td colspan="2" class="td_title">合同编号</td>
				<td align="left">
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contactNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contactNoId" name="templateBean.contractNo" value="${templateBean.contractNo}"/>
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td><input class="inputBorder" readonly="readonly" validate="required" type="text" id="contactNameId" name="templateBean.contractName" value="${templateBean.contractName}" /></td>
				</s:else>
			</tr>
			<tr>
				<td colspan="2" class="td_title">合同总金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contractTotalAmt" value="${templateBean.contractTotalAmt}"/></td>
				<td class="td_title">已付合同款(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contractPaidAmt" value="${templateBean.contractPaidAmt}"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">本次支付金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"/></td>
				<td colspan="2">
					<!-- 工程预算 以及 设计费 不需要此选项 -->
					<s:if test="authTypeCd!='FKGL_GCCL' && authTypeCd!='FKGL_SJF' && authTypeCd!='CWGL_JDBD_10' && authTypeCd!='CWGL_JDBD_20'">
						<s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外
						<s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内
					</s:if>
					<s:if test="authTypeCd=='FKGL_XZFY'">
						<s:checkbox name="templateBean.salaryFlag" id ="salaryFlag" cssClass="group"></s:checkbox>员工工资
					</s:if>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3" valign="center">收款人信息</td>
				<td class="td_title">收款人名称</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payer" value="${templateBean.payer}"/></td>
			</tr>
			<tr>
				<td class="td_title">收款人开户银行</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerAccount" value="${templateBean.payerAccount}"/></td>
			</tr>
			<tr>
				<td class="td_title">收款人账户号</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerBank" value="${templateBean.payerBank}"/></td>
			</tr>
			<tr>
				<td class="td_title" colspan="2">需说明事项</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
			</tr>
			<s:if test="authTypeCd=='FKGL_GCCL'">
			<!--	工程、材料设备结算款		-->
			<tr>
				<td class="td_title" colspan="2" rowspan="1">应提供细项<br/>(请作为附件上传)</td>
				<td style="height:40px;"  validate="required" value="${templateBean.settAppFileId}">结算审批表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('结算审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','settAppFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.settAppFileId" id="settAppFileId" value="${templateBean.settAppFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_settAppFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settAppFileId}','settAppFileId','${canEdit}');
				</script>
				</td>
			</tr>
			</s:if>
			<s:elseif test="authTypeCd=='JD_CWGL_70' || authTypeCd=='JD_CWGL_100'">
			<!--	工程、材料设备结算款		-->
			<tr>
				<td class="td_title" colspan="2" rowspan="1">附件上传</td>
				<td style="height:40px;"  validate="required" value="${templateBean.settAppFileId}">付款依据</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('付款依据','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','settAppFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.settAppFileId" id="settAppFileId" value="${templateBean.settAppFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_settAppFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settAppFileId}','settAppFileId','${canEdit}');
				</script>
				</td>
			</tr>
			</s:elseif>
			<s:elseif test="authTypeCd=='JD_CWGL_110'">
			<tr>
				<td class="td_title" colspan="2" rowspan="3">附件上传</td>
				<td style="height:40px;"  validate="required" value="${templateBean.settAppFileId}">进度款</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('进度款','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','settAppFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.settAppFileId" id="settAppFileId" value="${templateBean.settAppFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_settAppFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settAppFileId}','settAppFileId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td style="height:40px;"  validate="required" value="${templateBean.appKeepFile}">质保金</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('质保金','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','appKeepFile',event);"/>
				<input  validate="required" type="hidden" name="templateBean.appKeepFile" id="appKeepFile" value="${templateBean.appKeepFile}"/>
				</s:if>
				</td>
				<td>
				<div id="show_appKeepFile"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.appKeepFile}','appKeepFile','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td style="height:40px;"  validate="required" value="${templateBean.appCloseFile}">结算付款</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('结算付款','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','appCloseFile',event);"/>
				<input  validate="required" type="hidden" name="templateBean.appCloseFile" id="appCloseFile" value="${templateBean.appCloseFile}"/>
				</s:if>
				</td>
				<td>
				<div id="show_appCloseFile"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.appCloseFile}','appCloseFile','${canEdit}');
				</script>
				</td>
			</tr>
			</s:elseif>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getContByno(contNo){
	var data = {contNo:contNo};
    var getContUrl="${ctx}/cont/cont-ledger!detail.action";
	var url;
	$.post(getContUrl,data, function(result){
		$("#contLedgerId").val(result);
		url="${ctx}/cont/cont-ledger!input.action?id="+$("#contLedgerId").val();
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	});
	
}
	$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"contactNoId",contName:"contactNameId"},{codeType:'0'});
</script>