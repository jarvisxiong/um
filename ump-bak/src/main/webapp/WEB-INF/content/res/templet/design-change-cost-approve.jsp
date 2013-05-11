<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--设计变更费用核定单 --%>
<%@ include file="template-header.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="110px">
			<col>
			<col width="110px">
			<col>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目地点</td>
				<td  colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.projectPlace" value="${templateBean.projectPlace}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">工程名称</td>
				<td colspan="3" >
					<input validate="required" class="inputBorder" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">施工单位</td>
				<td colspan="3" >
					<input validate="required" class="inputBorder" type="text" name="templateBean.constructionCompany" value="${templateBean.constructionCompany}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
					
					<input type="hidden" id="contLedgerId"  />
					<input class="inputBorder" type="hidden" id="contactNameId"/>
					
					<s:if test="!templateBean.contractSN.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractSN}');">${templateBean.contractSN}</span>
						<input id="contractNoId" type="hidden" name="templateBean.contractSN" value="${templateBean.contractSN}" />
					</s:if>
					<s:else>
					<input validate="required" class="inputBorder" id="contractNoId" type="text" name="templateBean.contractSN" value="${templateBean.contractSN}"/>
					</s:else>
				</td>
				<td class="td_title">合同金额(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.contractMoney" value="${templateBean.contractMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">累计变更额(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.addUpMoney" value="${templateBean.addUpMoney}"/>
				</td>
				<td class="td_title">占合同比例</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.addUpRate" value="${templateBean.addUpRate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">设计变更主题</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.changeTitle" value="${templateBean.changeTitle}"/>
				</td>
				<td class="td_title">设计变更单编号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.changeSN" value="${templateBean.changeSN}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">变更原因</td>
				<td colspan="3" class="chkGroup">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.reason1" cssClass="group"></s:checkbox>设计遗漏</td>
					<td><s:checkbox name="templateBean.reason2" cssClass="group"></s:checkbox>招商原因</td>
					<td><s:checkbox name="templateBean.reason3" cssClass="group"></s:checkbox>施工原因</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.reason4" cssClass="group"></s:checkbox>甲方原因</td>
					<td colspan="2"><s:checkbox name="templateBean.reasonOther" cssClass="group"></s:checkbox>其他:<input class="inputBorder" style="width:120px;" type="text" name="templateBean.otherContent" value="${templateBean.otherContent}"/></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">施工单位上报金额(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.constructionCompanyMoney" value="${templateBean.constructionCompanyMoney}"/>
				</td>
				<td class="td_title">地产公司审核金额(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.dcMoney" value="${templateBean.dcMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">核价编制说明</td>
				<td colspan="3">
					<textarea validate="required" class="inputBorder contentTextArea" name="templateBean.content">${templateBean.content}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">增加或减少的费用(元)</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.changeMoney" value="${templateBean.changeMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">应提供细项<br/>(请作为附件上传)</td>
				<td style="height:40px;"  validate="required" value="${templateBean.engineeringVisaFileId}" id="td_engineeringVisaFileId">工程签证审批表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('工程签证审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','engineeringVisaFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.engineeringVisaFileId" id="engineeringVisaFileId" value="${templateBean.engineeringVisaFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_engineeringVisaFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.engineeringVisaFileId}','engineeringVisaFileId','${canEdit}');
				</script>
				</td>
				
			</tr>
			<tr>
				<td style="height:40px;" validate="required" value="${templateBean.contChangeFileId}">合同变更条款扫描件</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('合同变更条款扫描件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','contChangeFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.contChangeFileId" id="contChangeFileId" value="${templateBean.contChangeFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_contChangeFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.contChangeFileId}','contChangeFileId','${canEdit}');
				</script>
				</td>
			</tr> 
			<tr>
				<td style="height:40px;" validate="required" value="${templateBean.budgetFileId}">预算书</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('预算书','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','budgetFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.budgetFileId" id="budgetFileId" value="${templateBean.budgetFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_budgetFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.budgetFileId}','budgetFileId','${canEdit}');
				</script>
				</td>
			</tr> 
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
	$("#contractNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"contractNoId",contName:"contactNameId"});
</script>