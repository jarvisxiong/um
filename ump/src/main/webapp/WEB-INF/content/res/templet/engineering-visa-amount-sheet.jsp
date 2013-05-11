<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--工程签证费用核定单 --%>
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
			<col width="150"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目地点</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.projectPlace" value="${templateBean.projectPlace}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">工程名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
				<td class="td_title">施工单位</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.builder" value="${templateBean.builder}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
					
					<input class="inputBorder" type="hidden" id="contactNameId"/>
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contractNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contractNoId" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:else>
				</td>
				<td class="td_title">合同金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contractAmount" value="${templateBean.contractAmount}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">累计签证额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.totalVisaAmount" value="${templateBean.totalVisaAmount}"  />
				</td>
				<td class="td_title">占合同比例</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.rateInCont" value="${templateBean.rateInCont}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">签证主题</td>
				<td align="left">
					<input class="inputBorder" validate="required" type="text" name="templateBean.visaSubject" value="${templateBean.visaSubject}" />
				</td>
				<td class="td_title">签证单编号</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.visaNo" value="${templateBean.visaNo}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">变更原因</td>
				<td colspan="3" class="chkGroup" align="left">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.visaReason1" cssClass="group"></s:checkbox>设计错漏</td>
					<td><s:checkbox name="templateBean.visaReason2" cssClass="group" ></s:checkbox>招商原因</td>
					<td><s:checkbox name="templateBean.visaReason3"  cssClass="group"></s:checkbox>施工原因</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.visaReason4"  cssClass="group" ></s:checkbox>甲方原因</td>
					<td colspan="2"><s:checkbox name="templateBean.visaReason5"  cssClass="group"></s:checkbox>其他:
					<input class="inputBorder" type="text" style="width:100px;" name="templateBean.otherReason" value="${templateBean.otherReason}"  />
					</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">施工单位报送金额 (元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.builderAmount" value="${templateBean.builderAmount}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">地产公司审核金额(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.estateAmount" value="${templateBean.estateAmount}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">核价编制说明</td>
				<td align="left" colspan="3"><div style="padding-left:20px;">
					<ol>
						<li>
						附件《工程签证审批表》编号<input class="inputBorder" style="width:100px;" validate="required" type="text" name="templateBean.blank1" value="${templateBean.blank1}" />
						《现场签证单》编号 <input class="inputBorder" style="width:100px;" validate="required" type="text" name="templateBean.blank2" value="${templateBean.blank2}" />  。
						</li>
						<li>变更合同价款按合同<input class="inputBorder" style="width:100px;" validate="required" type="text" name="templateBean.blank3" value="${templateBean.blank3}" />条款 
						第<input class="inputBorder" validate="required" style="width:100px;" type="text" name="templateBean.blank4" value="${templateBean.blank4}" />种方法，其中工程量按
						<input class="inputBorder" validate="required" style="width:100px;" type="text" name="templateBean.blank5" value="${templateBean.blank5}" />计量，单价按
						<input class="inputBorder" validate="required" style="width:100px;" type="text" name="templateBean.blank6" value="${templateBean.blank6}" /> 确定。
						合同约定下浮率
						<input class="inputBorder" validate="required" style="width:50px;" type="text" name="templateBean.blank7" value="${templateBean.blank7}" />
						 %。（上传附件：合同的变更条款扫描件）
						</li>
						<li>特别事项说明（如扣减其他单位费用），同时附《另行委托/未施工扣减审批表》 </li>
					</ol>
				</div>
				</td>
			</tr>
			<tr>
				<td class="td_title">增加或减少的费用(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.changeAmount" value="${templateBean.changeAmount}" />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">应提供细项<br/>(请作为附件上传)</td>
				<td style="height:40px;"  validate="required" value="${templateBean.engineeringVisaFileId}">工程签证审批表</td>
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
	$("#contractNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"contactNoId",contName:"contactNameId"});
</script>

