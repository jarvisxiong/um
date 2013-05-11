<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程造价咨询单项委托单审批表 --%>
<%@ include file="template-var.jsp"%>
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">委托单编号:</span>
			<span class="wap_value">${templateBean.visaNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partB}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">项目名称	:</span>
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
	</div>
	<div class="div_row">
			<span class="wap_title">面积(m2):</span>
			<span class="wap_value">${templateBean.area}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">甲方委托部门:</span>
			<span class="wap_value">${templateBean.partATrustPart}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">甲方经办人:</span>
			<span class="wap_value">${templateBean.partAOperator}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">咨询费用计算规则:</span>
			<span class="wap_value">${templateBean.consultFeeRule}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">咨询费比例:</span>
			<span class="wap_value">${templateBean.consultRate}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【委托事项】</span>
		<div class="div_row">
			<span class="wap_title">工程招标造价咨询 :</span>
			<span class="wap_value">${templateBean.trustBidItem}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">工程结算造价咨询:</span>
				<span class="wap_value">${templateBean.settleItem}</span>
		</div>
	</div>
	<div class="div_row">
			<span class="wap_title">核定咨询费(元):</span>
			<span class="wap_value">${templateBean.consultFee}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">咨询内容:</span>
			<div><s:checkbox name="templateBean.braidList" cssClass="group"></s:checkbox><span>编制订单</span></div>
			<div><s:checkbox name="templateBean.braidBaseBidPrice" cssClass="group"></s:checkbox><span>编制标底</span></div>
			<div><s:checkbox name="templateBean.rePriceCheck" cssClass="group"></s:checkbox><span>重新度量标后核对</span></div>
			<div><s:checkbox name="templateBean.projectSettle" cssClass="group"></s:checkbox><span>工程结算</span></div>
			<div><s:checkbox name="templateBean.braidOther" cssClass="group"></s:checkbox><span>其他</span></div>
	</div>
	
	<div class="div_row">
			<span class="wap_title">标底预算价(元):</span>
			<span class="wap_value">${templateBean.baseBidPriceBudget}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">重新度量施工图预算价(元):</span>
			<span class="wap_value">${templateBean.reDrawingBudget}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【一、工程造价基数(=1或2+3-4+5)】</span>
		<div class="div_row">
			<span class="wap_title">1、定标价(适用总价包干定标):</span>
			<span class="wap_value">${templateBean.bidApprovalPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">2、标后核对批准价(适用模拟清单、费率定标):</span>
			<span class="wap_value">${templateBean.afterCheckApprove}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">3、应计入工程造价的电缆、面砖、石材的价格:</span>
			<span class="wap_value">${templateBean.projectPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">4、应扣除套图部分造价:</span>
			<span class="wap_value">${templateBean.cutDrawingPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">5、其他工程造价加减额:</span>
			<span class="wap_value">${templateBean.otherProjectPrice}</span>
		</div>
	</div>	
	
	<div class="div_label">
		<span class="wap_label">【二、咨询费(=6+7)】</span>
		<div class="div_row">
			<span class="wap_title">6、标准咨询费(=工程造价基数*咨询费比例):</span>
			<span class="wap_value">${templateBean.standardConsultFee}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">7、加减咨询费:</span>
				<span class="wap_value">${templateBean.addSubFee}</span>
		</div>
	</div>
	<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【附件】</span>
		<div class="div_row">
			<span class="wap_title">单项委托单:</span>
			  <div id="show_singleTrustId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.singleTrustId}','singleTrustId','${canEdit}');
				</script>
	   </div>
		<div class="div_row">
			<span class="wap_title">定标单或标后核对核定单:</span>
			<div id="show_bidApprovalId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidApprovalId}','bidApprovalId','${canEdit}');
				</script>
	   </div>
		<div class="div_row">
			<span class="wap_title">与咨询机构初审意见(内容同上):</span>
			<div id="show_firstTrialId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.firstTrialId}','firstTrialId','${canEdit}');
				</script>
	   </div>
	</div>	
</div>
