<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程预算审批表 --%>
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
			<col width="100"/>
			<col/>
			<col width="100"/>
			<col/>
			<s:if test="aboutHotel">
			<tr>
				<td class="td_title"></td>
				<td align="left" colspan="3">
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
			</s:if>
			<s:if test="authTypeCd=='BLSY_ZCGL_GCYS_20'||authTypeCd=='SYGS_ZCGL_GCYS_20'">
				<tr>
					<td class="td_title require"></td>
					<td colspan="3" class="chkGroup" align="left">
						<table class="tb_checkbox">
						<col width="150">
						<col width="150">
						<tr>
						<td><s:checkbox name="templateBean.isLandCompany"  cssClass="group"></s:checkbox>有地产公司</td>
						</tr>
						</table>
					</td>
				</tr>
			</s:if>
			<s:if test="authTypeCd=='JD_ZCGL_GCYS_10'">
			<tr>
				<td class="td_title require"></td>
				<td colspan="3" class="chkGroup" align="center"  validate="required">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外</td>
					<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内</td>
					</tr>
					</table>
				</td>
			</tr>
			</s:if>
			
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="60"/>
						<col width="20"/>
						<tr>
						<td>
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
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
				
			</tr>
			<tr>
				<td class="td_title">编号</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.serialNo" value="${templateBean.serialNo}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">拟招标工程名称</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.planBidProjectName" value="${templateBean.planBidProjectName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">工程范围/内容</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.projectContent" value="${templateBean.projectContent}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">计划开工时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planBeginDate" value="${templateBean.planBeginDate}"/>
				</td>
				<td class="td_title">计划竣工时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planEndDate" value="${templateBean.planEndDate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">工期</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.planDays" value="${templateBean.planDays}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">拟招标方式</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col />
						<tr>
						<td colspan="5" class="chkGroup" validate="required">
						<table class="tb_checkbox" style="width:100%;">
						<col width="100">
						<col width="100">
						<col>
						<tr>
							<td><s:checkbox name="templateBean.planBidMode1"  cssClass="group" onclick="checkPlanBidMode(this)"></s:checkbox>邀请招标</td>
							<td><s:checkbox name="templateBean.planBidMode2"  cssClass="group" onclick="checkPlanBidMode(this)"></s:checkbox>竞争性议标</td>
							<td><s:checkbox name="templateBean.planBidMode3"  cssClass="group" onclick="checkPlanBidMode(this)"></s:checkbox>直接委托议价/垄断议价</td>
						</tr>
						<tr>
							<td rowspan="2"><s:checkbox name="templateBean.planBidMode4"  cssClass="group" onclick="checkPlanBidMode(this)"></s:checkbox>定向续标：</td>
							<td>主合同编号</td>
							<td align="left">
							
							
							<input type="hidden" id="contLedgerId"  />
							<s:if test="!templateBean.contractCd.isEmpty() && #canEdit == 'false'">
								<span  class="link" onclick="getContByno('${templateBean.contractCd}');">${templateBean.contractCd}</span>
								<input id="contractCd" type="hidden" name="templateBean.contractCd" value="${templateBean.contractCd}" />
							</s:if>
							<s:else>
							<input class="inputBorder" type="text" id="contractCd" name="templateBean.contractCd" value="${templateBean.contractCd}" />
							</s:else>
							</td>
						</tr>
						<tr>
							<td>和合同名称</td>
							<td><input class="inputBorder" type="text" readonly="readonly" id="contractName" name="templateBean.contractName" value="${templateBean.contractName}" /></td>
						</tr>
						</table>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">审批用途</td>
				<td colspan="3" class="chkGroup" validate="required">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
						<td><s:checkbox name="templateBean.approveUsage1"  cssClass="group"></s:checkbox>标底限价</td>
						<td><s:checkbox name="templateBean.approveUsage2"  cssClass="group"></s:checkbox>谈判指导价</td>
						<td><s:checkbox name="templateBean.approveUsage3"  cssClass="group"></s:checkbox>立项预算</td>
						<td><s:checkbox name="templateBean.approveUsage4"  cssClass="group"></s:checkbox>其他</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">预算编制依据</td>
				<td colspan="3" class="chkGroup" validate="required">
					<table class="tb_checkbox">
					<col width="200">
					<col width="200">
					<tr>
						<td><s:checkbox name="templateBean.budgetAccordingTo1"  cssClass="group"></s:checkbox>施工图(经批准的)</td>
						<td><s:checkbox name="templateBean.budgetAccordingTo2"  cssClass="group"></s:checkbox>方案(经批准的)</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
			
				<td class="td_title">
				<s:if test="authTypeCd==BLSY_ZCGL_GCYS_10">商业公司申报预算金额(元)</s:if>
				<s:elseif test="authTypeCd=='JD_ZCGL_GCYS_10'" >酒店公司申报预算金额(元)</s:elseif>
				<s:else>地产公司申报预算金额(元)</s:else>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.declareBudgetAmount" value="${templateBean.declareBudgetAmount}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">指标</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.indicate" value="${templateBean.indicate}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">预算编制说明</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.budgetDesc">${templateBean.budgetDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">附件上传</td>
				<td align="center" style="height:40px;" value="${templateBean.approveFileId}">立项审批或图纸审批文件</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('立项审批或图纸审批文件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','approveFileId',event);"/>
				<input type="hidden" name="templateBean.approveFileId" id="approveFileId" value="${templateBean.approveFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_approveFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.approveFileId}','approveFileId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.pictureId}">图纸</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('图纸','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','pictureId',event);"/>
				<input type="hidden" name="templateBean.pictureId" id="pictureId" value="${templateBean.pictureId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_pictureId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.pictureId}','pictureId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<s:if test="#canEdit=='true'">
<script type="text/javascript">
	$("#contractCd").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:'contractCd',contName:'contractName'});
</script>
</s:if>
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
	function checkPlanBidMode(obj) {
		if(obj.name == 'templateBean.planBidMode4' && obj.checked) {
			$("#contractCd").attr("validate","required");
			$("#contractName").attr("validate","required");
		} else {
			$("#contractCd").attr("class","inputBorder");
			$("#contractCd").removeAttr("validate");
			$("#contractCd").removeAttr("title");
			$("#contractName").attr("class","inputBorder");
			$("#contractName").removeAttr("validate");
			$("#contractName").removeAttr("title");
		}
	}
</script>