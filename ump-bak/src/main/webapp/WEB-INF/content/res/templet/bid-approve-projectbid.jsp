<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 定标审批表（项目招标） --%>
<%@ include file="template-header.jsp"%>

<script type="text/javascript">
	function onchange_DesignTotalDay() {
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		if (isEmpty(fromDate) || isEmpty(toDate)) {
			return;
		}
		var fArray = fromDate.split("-");
		var tArray = toDate.split("-");
		var fDate = new Date(fArray[0], fArray[1] - 1, fArray[2]);
		var tDate = new Date(tArray[0], tArray[1] - 1, tArray[2]);
		if (tDate.getTime() < fDate.getTime()) {
			//alert("结束时间不能小于开始时间");
			$("#totalDay").val("");
			return;
		}
		var day = Math.abs(tDate.getTime() - fDate.getTime()) / 1000 / 60 / 60
				/ 24 + 1;
		$("#totalDay").val(day);
	}
</script>

<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
	<s:else>
false
</s:else>
</s:set>

<div align="center" class="billContent">

	<form action="res-approve-info!save.action" method="post"
		id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<c:if
			test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}">
			<input type="hidden" name="templateBean.isAuto" value="true" />
		</c:if>

		<table class="mainTable">
			<col width="100" />
			<col />
			<col width="125" />
			<col />
			<s:if test="authTypeCd=='ZCGL_HTQS_105'||authTypeCd=='ZCGL_HTQS_111'">
				<tr>
					<td class="td_title require"></td>
					<td colspan="3" class="chkGroup" align="center" validate="required">
						<table class="tb_checkbox">
							<col width="150">
							<col width="150">
							<tr>
								<td><s:checkbox name="templateBean.businessCompany"
										id="businessCompany" cssClass="group"></s:checkbox>总部发起</td>
								<td><s:checkbox name="templateBean.businessGroup"
										id="businessGroup" cssClass="group"></s:checkbox>地产公司发起</td>
							</tr>
						</table>
					</td>
				</tr>
			</s:if>
			<s:if test="authTypeCd=='ZCGL_HTQS_101'">
				<tr>
					<td class="td_title require"></td>
					<td colspan="3" class="chkGroup" align="center" validate="required">
						<table class="tb_checkbox">
							<col width="150">
							<col width="150">
							<tr>
								<td><s:checkbox name="templateBean.outFlag" id="outFlag"
										cssClass="group"></s:checkbox>预算外</td>
								<td><s:checkbox name="templateBean.inFlag" id="inFlag"
										cssClass="group"></s:checkbox>预算内</td>
							</tr>
						</table>
					</td>
				</tr>
			</s:if>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0"
						class="tb_noborder">
						<col />
						<col width="20" />
						<col width="40" />
						<col width="20" />
						<tr>
							<td><s:if
									test="authTypeCd == 'ZCGL_HTQS_60'||authTypeCd=='ZCGL_HTQS_80'">
									<input validate="required" type="text"
										name="templateBean.projectName"
										value="${templateBean.projectName}" id="projectName"
										<s:if test="#isMy==1"> class="inputBorderNoTip" style="cursor: pointer;" </s:if >
										<s:else> class="inputBorderNoTip" </s:else>
										resname="projectName" resContLedField="projectName" />
									<input type="hidden" id="projectCd"
										name="templateBean.projectCd"
										value="${templateBean.projectCd}" resname="projectCd"
										resContLedField="projectCd" />
								</s:if> <s:else>
									<input validate="required" type="text"
										name="templateBean.projectName"
										value="${templateBean.projectName}" id="projectName"
										readonly="readonly"
										<s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if >
										<s:else> class="inputBorderNoTip" </s:else>
										resname="projectName" resContLedField="projectName" />
									<input type="hidden" id="projectCd"
										name="templateBean.projectCd"
										value="${templateBean.projectCd}" resname="projectCd"
										resContLedField="projectCd" />
								</s:else></td>
							<td style="text-align: right;">(</td>
							<td><input class="inputBorder" type="text"
								name="templateBean.projectPeriod"
								value="${templateBean.projectPeriod}" /></td>
							<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title">工程名称</td>
				<td><input class="inputBorder" validate="required" type="text"
					name="templateBean.engineeringName"
					value="${templateBean.engineeringName}"
					resContLedField="engineeringName" /></td>
			</tr>
			<tr>
				<td class="td_title">审批权限</td>
				<td colspan="3" class="chkGroup" align="center" validate="required">
					<table class="tb_checkbox">
						<col width="150">
						<col width="150">
						<tr>
							<td><s:checkbox id="ckMoney" name="templateBean.below30"
									cssClass="group" onclick="choose()"></s:checkbox>金额≤30万</td>
							<td><s:checkbox name="templateBean.from30to100"
									cssClass="group" onclick="choose()"></s:checkbox>30万＜金额≤100万</td>
						</tr>
						<tr>
							<td><s:checkbox name="templateBean.from100to500"
									cssClass="group" onclick="choose()"></s:checkbox>100万＜金额≤500万</td>
							<td><s:checkbox name="templateBean.above500"
									cssClass="group" onclick="choose()"></s:checkbox>金额＞500万</td>
						</tr>
					</table>
				</td>
			</tr>
			<%-- 工程定标单 --%>
			<s:if test="authTypeCd == 'ZCGL_HTQS_80'">
				<tr>
					<td class="td_title">战略计划编号</td>
					<td style="border: none;" colspan="3" align="left"><input
						class="inputBorder" style="width: 150px;" validate="required"
						type="text" name="templateBean.ccbpNo"
						value="${templateBean.ccbpNo}" id="ccbpNo"
						<s:if test="#canEdit=='true'">
							onblur="loadBidAttachEval($('#bidLedgerId').val());"
							</s:if> />
						<input type="hidden" name="templateBean.ccbpId"
						value="${templateBean.ccbpId}" id="ccbpId" /> <input type="hidden"
						name="templateBean.ccbpPlanContentDesc" id="ccbpPlanContentDesc"
						value="${templateBean.ccbpPlanContentDesc}" /> <span
						id="ccbpPlanContentDescSpan">${templateBean.ccbpPlanContentDesc}</span>
						<input type="hidden" id="dataTypeCd" name="dataTypeCd" value="2" />
					<!-- 2-采购 --></td>
				</tr>
			</s:if>
			<tr>
				<td class="td_title" rowspan="5" valign="middle" align="center">招
					标<br />主 要<br />内 容
				</td>
				<td colspan="3" style="padding-top: 3px;">
					<table border="0" cellpadding="0" cellspacing="0"
						class="tb_noborder">
						<col width="70" />
						<tr>
							<td>招标范围:</td>
							<td><textarea class="inputBorder contentTextArea"
									validate="required" name="templateBean.bidRange">${templateBean.bidRange}</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top: 3px;">
					<table border="0" cellpadding="0" cellspacing="0"
						class="tb_noborder">
						<col width="70" />
						<tr>
							<td>施工面积:</td>
							<td><input class="inputBorder" validate="required"
								type="text" name="templateBean.constructionArea"
								value="${templateBean.constructionArea}" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top: 3px;">
					<table border="0" cellpadding="0" cellspacing="0"
						class="tb_noborder">
						<col width="70" />
						<col />
						<col width="30" />
						<col />
						<col width="30" align="center" />
						<col />
						<col width="30" />
						<tr>
							<td>施工工期:</td>
							<td><input onfocus="WdatePicker()"
								onchange="onchange_DesignTotalDay()" class="Wdate inputBorder"
								type="text" validate="required" id="fromDate"
								name="templateBean.fromDate" value="${templateBean.fromDate}"
								resContLedField="fromDate" /></td>
							<td align="center">至</td>
							<td><input onfocus="WdatePicker()"
								onchange="onchange_DesignTotalDay()" class="Wdate inputBorder"
								type="text" validate="required" id="toDate"
								name="templateBean.toDate" value="${templateBean.toDate}"
								resContLedField="toDate" /></td>
							<td>共</td>
							<td><input class="inputBorder" validate="required"
								type="text" id="totalDay" name="templateBean.totalDay"
								value="${templateBean.totalDay}" resContLedField="totalDay" />
							</td>
							<td>天</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top: 3px;">
					<table border="0" cellpadding="0" cellspacing="0"
						class="tb_noborder">
						<col width="120" />
						<tr>
							<td>合同性质:</td>
							<td colspan="3" class="chkGroup" align="center"
								validate="required">
								<table class="tb_checkbox">
									<col width="150">
									<col width="150">
									<tr>
										<td><s:checkbox name="templateBean.pricingModel1"
												cssClass="group" resContLedField="pricingModel1"></s:checkbox>总价包干</td>
										<td><s:checkbox name="templateBean.pricingModel2"
												cssClass="group" resContLedField="pricingModel2"></s:checkbox>可调总价包干</td>
									</tr>
									<tr>
										<td><s:checkbox name="templateBean.pricingModel3"
												cssClass="group"></s:checkbox>单价包干</td>
										<td><s:checkbox name="templateBean.pricingModel4"
												cssClass="group"></s:checkbox>定额计价</td>
									</tr>
								</table>
							</td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top: 3px;">
					<table border="0" cellpadding="0" cellspacing="0"
						class="tb_noborder">
						<col width="70" />
						<tr>
							<td>付款方式:</td>
							<td><textarea class="inputBorder contentTextArea"
									validate="required" name="templateBean.paymentType">${templateBean.paymentType}</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目权限内</td>
				<td class="chkGroup" align="left"><s:checkbox
						name="templateBean.isProjectAuth" cssClass="group"
						id="isProjectAuth" onclick="choose()"></s:checkbox></td>
				<td class="td_title">是垄断</td>
				<td class="chkGroup" align="left"><s:checkbox id="isMonopoly"
						name="templateBean.isMonopoly" cssClass="group" onclick="choose()"></s:checkbox>
				</td>
			</tr>
			<tr style="font-weight: bold">
				<td class="td_title">推荐中标单位</td>
				<td align="left">
					<%-- 战略定标单 --%> <s:if test="authTypeCd == 'ZCGL_HTQS_80'">
						<input class="inputBorder" read validate="required" type="text"
							name="templateBean.bidUnit" id="bidUnit"
							value="${templateBean.bidUnit}" resContLedField="bidUnit" />
						<input type="hidden" id="sid" name="templateBean.supBasicId"
							value="${templateBean.supBasicId}" resContLedField="supBasicId">
						<input type="hidden" id="bidSupId" name="templateBean.bidSupId"
							value="${templateBean.bidSupId}" />
						<input type="hidden" id="bidLedgerId"
							name="templateBean.bidLedgerId"
							value="${templateBean.bidLedgerId}" />
					</s:if> <s:else>
						<s:if
							test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
							<span class="link"
								onclick="getSup('${templateBean.supBasicId}');">${templateBean.bidUnit}</span>
							<input id="bidUnit" type="hidden" name="templateBean.bidUnit"
								value="${templateBean.bidUnit}" resContLedField="bidUnit" />
							<input type="hidden" id="sid" name="templateBean.supBasicId"
								value="${templateBean.supBasicId}" resContLedField="supBasicId">
						</s:if>
						<s:else>
							<input class="inputBorder" validate="required" type="text"
								name="templateBean.bidUnit" id="bidUnit"
								value="${templateBean.bidUnit}" resContLedField="bidUnit" />
							<input type="hidden" id="sid" name="templateBean.supBasicId"
								value="${templateBean.supBasicId}" resContLedField="supBasicId">
						</s:else>
					</s:else>
				</td>
				<td class="td_title">备注</td>
				<td><input class="inputBorder" type="text"
					name="templateBean.remark" id="remark"
					value="${templateBean.remark}" /></td>
			</tr>
			<tr>
				<td class="td_title">推荐中标理由</td>
				<td colspan="3"><textarea class="inputBorder contentTextArea"
						validate="required" name="templateBean.successfulBidReason">${templateBean.successfulBidReason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">目标成本(元)</td>
				<td><input class="inputBorder" validate="required" type="text"
					name="templateBean.targetCost" value="${templateBean.targetCost}"
					onblur="formatVal($(this));" /></td>
				<td class="td_title">标底(元)</td>
				<td><input class="inputBorder" validate="required" type="text"
					name="templateBean.baseBidPrice"
					value="${templateBean.baseBidPrice}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title">中标价(元)</td>
				<td><input class="inputBorder" validate="required" type="text"
					name="templateBean.successfulBidPrice"
					value="${templateBean.successfulBidPrice}"
					onblur="formatVal($(this));" /></td>
				<td class="td_title">单方造价（元/平米）</td>
				<td><input class="inputBorder" validate="required" type="text"
					name="templateBean.unilateralCost"
					value="${templateBean.unilateralCost}" onblur="formatVal($(this));" />
				</td>
			</tr>

			<tr>
				<td class="td_title" rowspan="5">上传附件</td>
				<td align="center" style="height: 40px; text-align: left;"
					validate="required"
					value="${templateBean.bidQualificationApproveId}"
					resattachname="邀标单位资质审批表"><font color="red">*</font>邀标单位资质审批表</td>
				<td align="center"><s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" bidflag='1'
							class="btn_green_65_20" style="border: none;"
							onclick="showUploadSingleAttachDialog('邀标单位资质审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidQualificationApproveId',event);" />
						<input type="hidden" name="templateBean.bidQualificationApproveId"
							id="bidQualificationApproveId"
							value="${templateBean.bidQualificationApproveId}" />
					</s:if></td>
				<td>
					<div id="show_bidQualificationApproveId"></div> <script
						type="text/javascript">
						showUploadedFile(
								'${templateBean.bidQualificationApproveId}',
								'bidQualificationApproveId', '${canEdit}');
					</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height: 40px; text-align: left;"
					validate="required" value="${templateBean.bidPriceListId}"
					resattachname="中标单位报价清单"><font color="red">*</font>中标单位报价清单</td>
				<td align="center"><s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" bidflag='1'
							class="btn_green_65_20" style="border: none;"
							onclick="showUploadSingleAttachDialog('中标单位报价清单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidPriceListId',event);" />
						<input type="hidden" name="templateBean.bidPriceListId"
							id="bidPriceListId" value="${templateBean.bidPriceListId}" />
					</s:if></td>
				<td>
					<div id="show_bidPriceListId"></div> <script type="text/javascript">
						showUploadedFile('${templateBean.bidPriceListId}',
								'bidPriceListId', '${canEdit}');
					</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height: 40px; text-align: left;"
					validate="required" value="${templateBean.bidSummaryId}"
					resattachname="投标报价汇总表"><font color="red">*</font>投标报价汇总表</td>
				<td align="center"><s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" bidflag='1'
							class="btn_green_65_20" style="border: none;"
							onclick="showUploadSingleAttachDialog('投标报价汇总表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidSummaryId',event);" />
						<input type="hidden" name="templateBean.bidSummaryId"
							id="bidSummaryId" value="${templateBean.bidSummaryId}" />
					</s:if></td>
				<td>
					<div id="show_bidSummaryId"></div> <script type="text/javascript">
						showUploadedFile('${templateBean.bidSummaryId}',
								'bidSummaryId', '${canEdit}');
					</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height: 40px; text-align: left;"
					value="${templateBean.budgetApproveFileId}" resattachname="预算和批准文件">&nbsp;&nbsp;预算和批准文件</td>
				<td align="center"><s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" bidflag='1'
							class="btn_green_65_20" style="border: none;"
							onclick="showUploadSingleAttachDialog('预算和批准文件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','budgetApproveFileId',event);" />
						<input type="hidden" name="templateBean.budgetApproveFileId"
							id="budgetApproveFileId"
							value="${templateBean.budgetApproveFileId}" />
					</s:if></td>
				<td>
					<div id="show_budgetApproveFileId"></div> <script
						type="text/javascript">
						showUploadedFile('${templateBean.budgetApproveFileId}',
								'budgetApproveFileId', '${canEdit}');
					</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height: 40px; text-align: left;"
					value="${templateBean.inviteBidFileId}" resattachname="招标文件、投标文件">&nbsp;&nbsp;招标文件、投标文件</td>
				<td align="center"><s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" bidflag='1'
							class="btn_green_65_20" style="border: none;"
							onclick="showUploadSingleAttachDialog('招标文件、投标文件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','inviteBidFileId',event);" />
						<input type="hidden" name="templateBean.inviteBidFileId"
							id="inviteBidFileId" value="${templateBean.inviteBidFileId}" />
					</s:if></td>
				<td>
					<div id="show_inviteBidFileId"></div> <script
						type="text/javascript">
						showUploadedFile('${templateBean.inviteBidFileId}',
								'inviteBidFileId', '${canEdit}');
					</script>
				</td>
			</tr>
			<%--
			<tr>
				<td align="center" style="height:40px;text-align: left;" value="${templateBean.otherFileId}" resattachname="其他附件">其他附件</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('其他附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','otherFileId',event);"/>
				<input type="hidden" name="templateBean.otherFileId" id="otherFileId" value="${templateBean.otherFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_otherFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.otherFileId}','otherFileId','${canEdit}');
				</script>
				</td>
			</tr>
			 --%>


			<s:if test="statusCd==2">
				<tr>
					<td class="td_title">审定价(元)</td>
					<td><input class="inputBorder"
						<s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required" </s:if>
						type="text" id="contractPrice" name="templateBean.contractPrice"
						value="${templateBean.contractPrice}" onblur="formatVal($(this));"
						 resname="contractPrice" resContLedField="contractPrice"/></td>
					<td class="td_title" align="right"><s:if test="!isImported">
							<div id="btn_importContLib" class="btn_green"
								title="把当前定标审批表导入到合同文本库"
								onclick="doImportContLib('${resApproveInfoId}');"
								style="width: 65px;">新建合同</div>
						</s:if></td>
					<td><s:if test="!isImported">
				 	请到合同文本库新建合同，系统会自动生成合同台账
				 	</s:if> <s:else>
							<span>合同台账编号：${templateBean.contractNo}</span>
						</s:else> <input class="inputBorder" type="hidden"
						<s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true'  onblur="contNoValidate(this);"</s:if>
						id="contractNo" name="templateBean.contractNo"
						value="${templateBean.contractNo}" /></td>
				</tr>
			</s:if>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<%--导入合同文本库脚本start --%>
<%@include file="bid-contract-import-base.jsp"%>
<%--导入合同文本库脚本end --%>


<%--若非垄断 --%>
<s:if test="#canEdit=='true'">
	<s:if test="templateBean.isMonopoly!='true'">

	</s:if>
</s:if>

<script type="text/javascript">
	$(function() {
		$("#projectName").quickSearch(
				"${ctx}/cont/cont-project-code!quickSearch.action",
				[ 'projectName' ], {
					projectCd : 'projectCd',
					projectName : 'projectName'
				});
		$('#ccbpNo').attr('title', '请选择【成本系统-招标平台（战略）】中的‘战略’计划编号');
		$('#ccbpNo').quickSearch(
				'${ctx}/bid/bid-ledger!quickSearchBid.action',
				[ 'ccbpProjectName', 'ccbpDataTypeName', 'ccbpNo',
						'ccbpPlanContentDesc', 'supName' ], {
					ccbpId : 'ccbpId',
					ccbpNo : 'ccbpNo',
					supName : 'bidUnit',
					supBasicId : 'sid',
					bidSupId : 'bidSupId',
					bidLedgerId : 'bidLedgerId'
				}, '', function(jdom) {

					var t = jdom.attr('ccbpPlanContentDesc');
					$('#ccbpPlanContentDesc').val("工程：" + t);
					$('#ccbpPlanContentDescSpan').attr("title", t);
					$('#ccbpPlanContentDescSpan').html("工程：" + t);
					$('#bidUnit').attr('readonly', 'readonly');

					//加载附件
					loadBidAttachEval(jdom.attr('bidLedgerId'));
				}, {
					projectCd : 'projectCd',
					dataTypeCd : 'dataTypeCd'
				});

		//加载信息
		loadBidAttachEval('${templateBean.bidLedgerId}');
	});

	function bindBidUnit() {
		//若有招标计划
		var isBidLedgerFlg = ($('#bidLedgerId').length > 0);
		if ( isBidLedgerFlg) {
			var isChkMoney = $("#ckMoney").attr("checked");
			var isProjectAuth = $("#isProjectAuth").attr("checked");
			var isMonopoly = $("#isMonopoly").attr("checked");
			if (!(isChkMoney || isProjectAuth || isMonopoly)) {
				$("#bidUnit").attr('readonly', 'readonly');
			}
		}
		else {
			$("#bidUnit").unbind();
			$("#bidUnit").quickSearch(
			"${ctx}/sup/sup-basic!quickSearch.action", [ 'sname' ], {
				sname : 'bidUnit',
				sid : 'sid'
			});
		}

	}
	function choose() {

		decorateNeedDom();

		var isBidLedgerFlg = ($('#bidLedgerId').length ==1);
		var isChkMoney = $("#ckMoney").attr("checked");
		var isProjectAuth = $("#isProjectAuth").attr("checked");
		var isMonopoly = $("#isMonopoly").attr("checked");
		//若可以编辑
		if (isChkMoney || isProjectAuth || isMonopoly) {

			if(isBidLedgerFlg){
				//非必选
				$('#ccbpNo').removeAttr('validate');
				$('#ccbpNo').removeClass('required');
	
				$('#ccbpNo').val('');
				$('#ccbpId').val('');
				$("#bidUnit").removeAttr("readonly");
				$("#bidLedgerId").val('');
			}

			$("#sid").val('');
			$("#bidSupId").val('');

			$("#bidUnit").val('');
			$("#bidUnit").unbind();
			$("#remark").attr("validate", "required");

		} else {

			if(isBidLedgerFlg){
				//必选
				$('#ccbpNo').attr('validate', 'required');
				$('#ccbpNo').addClass('required');

				$("#bidUnit").val('');
				$("#bidUnit").attr('readonly', 'readonly');
			}

			$("#remark").attr("class", "inputBorder");
			$("#remark").removeAttr("validate");
			$("#remark").removeAttr("title");
		}

		cleanBidFiles();

	}
	function getSup(sid) {
		var url = '';
		if (isNotEmpty(sid)) {
			url = "${ctx}/sup/sup-basic!input.action?id=" + sid;
		} else if (isNotEmpty(supName)) {
			url = "${ctx}/sup/sup-basic!input.action?sName=" + supName;
		}
		if (url != '') {
			if (parent.TabUtils == null)
				window.open(url);
			else
				parent.TabUtils.newTab("supQuery", "明细", url, true);
		}
	}
	//合同编号唯一性校验
	function contNoValidate(dom) {
		if ($(dom).val()) {
			$.post(_ctx + "/cont/cont-ledger!contNoValidate.action?contNo="
					+ $(dom).val(), function(result) {
				//合同新增，不是0条记录
				if (result != "0") {
					alert("该编号已存在，请重新输入");
					$(dom).val("");
				}
			});
		}
	}

	//设置是否选择项
	function decorateNeedDom() {
		var isChkMoney = $("#ckMoney").attr("checked");
		var isProjectAuth = $("#isProjectAuth").attr("checked");
		var isMonopoly = $("#isMonopoly").attr("checked");

		var isBidLedgerFlg = ($('#bidLedgerId').length > 0);
		if ( isChkMoney || isProjectAuth || isMonopoly) {
			$('input[bidflag=1]').show();
			if(isBidLedgerFlg){
				$('#ccbpNo').removeAttr('validate');
				$('#ccbpNo').removeClass('required');
	
				$('#ccbpPlanContentDescSpan').html('');
			}
		} else {
			$('input[bidflag=1]').hide();
			if(isBidLedgerFlg){
				$('#ccbpNo').attr('validate', 'required');
				$('#ccbpNo').addClass('required');
			}
		}
	}

	//刷新附件列表
	function cleanBidFiles(){
		$('span[fromModule=bidledger]').each(function() {
			$(this).remove();
		});
		
		processAttaRow('show_bidQualificationApproveId');
		processAttaRow('show_bidPriceListId');
		processAttaRow('show_bidSummaryId');
	}

	//修饰附件行
	function processAttaRow(divId) {
		//若已上传附件,则不处理
		if ($("#" + divId).find('li').length > 0) {
			//$("#"+divId).parent().prev().prev().attr('value','2');
		} else {
			var next = $("#" + divId).next();
			if (next.length == 0) {
				//若上传附件，且无'评标附件'
				$("#" + divId).parent().prev().prev().removeAttr('value');
				$("#" + divId).parent().prev().prev().attr('validate','required');
				$("#" + divId).parent().prev().prev().addClass('required');
			}
		}
	}
	//加载评标文件
	function loadBidAttachEval(bidLedgerId) {

		var isBidLedgerFlg = ($('#bidLedgerId').length == 1);
		if( isBidLedgerFlg){
			var ccbpNo = $('#ccbpNo').val();
			if (ccbpNo == '') {
				bidLedgerId = '';
				$('#ccbpPlanContentDescSpan').html('');
				
				$('#ccbpNo').removeAttr('validate');
				$('#ccbpNo').removeClass('required');
			}else{
				$('#ccbpNo').attr('validate','required');
				$('#ccbpNo').addClass('required');
			}
	
			cleanBidFiles();
			decorateNeedDom();
	
			if (bidLedgerId == '') {
				return;
			}
	
			var url = '${ctx}/bid/strategy-bid-ledger!loadEvalLast.action';
			var data = {
				id : bidLedgerId
			};
			$.post(url, data, function(result) {
				//邀标单位资质审批表
				//中标单位报价清单
				//投标报价汇总表
				//预算和批准文件
				//招标文件、投标文件
				//其他文件
				processAppend(result, 0, 2, 'show_bidQualificationApproveId');
				processAppend(result, 1, 2, 'show_bidPriceListId');
				processAppend(result, 2, 2, 'show_bidSummaryId');
				processAppend(result, 3, 2, 'show_budgetApproveFileId');
				processAppend(result, 4, 2, 'show_inviteBidFileId');
				//processAppend(result,5,2,'show_otherFileId');
	
				$('input[bidflag=1]').hide();
			});
		}else{
			$('input[bidflag=1]').show();
			bindBidUnit();
		}
	}
	
	function processAppend(result, trIndex, tdIndex, divId) { 
		var html = $(result).find('tr').eq(trIndex).find('td').eq(tdIndex).html();
		
		var t = $('<span fromModule="bidledger">' + html + "</span>");//表示那些附件从投标平台
		$("#" + divId).parent().append(t);
		
		var links = $(html).find('a').length;
		if (links > 0) {
			//去掉验证
			$("#" + divId).parent().prev().prev().attr('value', '1');
		}
	}

	//打开网批
	function openResTask(id, typeAndCd, resAuthTypeCd){
		var url = _ctx + '/res/res-approve-info.action?id=' + id + '&resAuthTypeCd=' + resAuthTypeCd;
		showPageLink(url,'网上审批');
	} 
	function showPageLink(url, type){
		//if(parent && parent.showAll){
		//	parent.showAll(url, type);
		//}else{
			window.open(url, type);
		//}
	}
</script>
