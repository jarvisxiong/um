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
			<span class="wap_title">合同名称 	:</span>
			<span class="wap_value">${templateBean.contName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partB}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">项目名称 	:</span>
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
	</div>
	<div class="div_row">
			<span class="wap_title">面积(m2) :</span>
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
			<span class="wap_title">预估咨询费(元):</span>
			<span class="wap_value">${templateBean.preConsultFee}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">招标报价模式:</span>
			<div><s:checkbox name="templateBean.totalDoPrice" cssClass="group"></s:checkbox><span>总价包干报价</span></div>
			<div><s:checkbox name="templateBean.totalMainPrice" cssClass="group"></s:checkbox><span>总价包干为主，局部模拟清单报价</span></div>
			<div><s:checkbox name="templateBean.analogPrice" cssClass="group"></s:checkbox><span>模拟清单报价</span></div>
			<div><s:checkbox name="templateBean.quotaPrice" cssClass="group"></s:checkbox><span>定额费率报价</span></div>
			<div><s:checkbox name="templateBean.otherPrice" cssClass="group"></s:checkbox><span>其他</span></div>
	</div>
	<div class="div_label">
		<span class="wap_label">【咨询业务内容】</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.totalDoPrice" cssClass="group"></s:checkbox><span>编制工程量清单</span></div>
			<span class="wap_title">提交时间:</span>
			<span class="wap_value">${templateBean.reformListDate}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.controlProjectList" cssClass="group"></s:checkbox><span>编制工程造价标底及最高控制价</span></div>
			<span class="wap_title">提交时间:</span>
			<span class="wap_value">${templateBean.controlListDate}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.afterProjectList" cssClass="group"></s:checkbox><span>工程量偏差标后核对(适用于总价包干报价范围)</span></div>
			<span class="wap_title">完成时间(定标前):</span>
			<span class="wap_value">${templateBean.afterListDate}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.drawingAfterList" cssClass="group"></s:checkbox><span>重新度量，施工图预算标后核对(适用于模拟清单、定额费率报价)</span></div>
			<span class="wap_title">预计工作时间段:</span>
			<span class="wap_value">${templateBean.planWorkPeriod}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.writtenComments" cssClass="group"></s:checkbox><span>施工图的错漏彭雀的书面意见反馈</span></div>
			<span class="wap_title">提交时间:</span>
			<span class="wap_value">${templateBean.submitDate}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.projectSettlement" cssClass="group"></s:checkbox><span>施工结算</span></div>
			<span class="wap_title">预计工作时间段:</span>
			<span class="wap_value">${templateBean.projectWorkPeriod}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.otherConsultContent" cssClass="group"></s:checkbox><span>其他咨询内容:</span></div>
			<span class="wap_value">${templateBean.ohterConsContent}</span>
		</div>
	</div>
	<div class="div_label">
		<span class="wap_label">【委托方(甲方)提供资料】</span>
		<div class="div_row">
				<span class="wap_title">招标施工图(加盖招标专用章) </span>
				<div>
				<span class="wap_title">提交时间:</span>
				<span class="wap_value">${templateBean.bidDrawingDate}</span>
				</div>
		</div>
		<div class="div_row">
				<span class="wap_title">招标文件</span>
				<div>
				<span class="wap_title">提交时间:</span>
				<span class="wap_value">${templateBean.bidFileDate}</span>
				</div>
		</div>
		<div class="div_row">
				<span class="wap_title">正式施工图(适用于标后核对，加盖标后核对专用章)</span>
				<div>
				<span class="wap_title">预计提交时间:</span>
				<span class="wap_value">${templateBean.officialSubmitDate}</span>
				</div>
		</div>
		<div class="div_row">
				<span class="wap_title">其他:</span>
				<span class="wap_value">${templateBean.other}</span>
		</div>
		
	</div>
	
	<div class="div_row">
				<span class="wap_title">其他咨询机构回执确认签字:</span>
				<span class="wap_value"></span>
		</div>
	
</div>