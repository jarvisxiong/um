<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--招标文件与招标合同评审审批表 --%>


<div id="billContent">

		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<div>
				<s:checkbox name="templateBean.containLawFlg"  id="containLawFlg"></s:checkbox>正式合同
			</div>
			<div>
				<s:checkbox name="templateBean.containFinancialFlg"  id="containFinancialFlg"></s:checkbox>正式合同
			</div>
			<div>
				<s:checkbox name="templateBean.containHotelFlg"  id="containHotelFlg"></s:checkbox>正式合同
			</div>
		</div>
		<div class="div_row">
			<div>
				<s:checkbox name="templateBean.companySendFlg"  id="companySendFlg"></s:checkbox>总部发起
			</div>
			<div>
				<s:checkbox name="templateBean.areaSendFlg"  id="areaSendFlg"></s:checkbox>公司发起
			</div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.oriBidConA}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约人:</span>
			<span class="wap_value">${templateBean.oriBidConPersonA}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.oriBidFileB}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约人:</span>
			<span class="wap_value">${templateBean.oriBidConPersonB}</span>
		</div>
		<!-- Start Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.oriBidConC}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约人:</span>
			<span class="wap_value">${templateBean.oriBidConPersonC}</span>
		</div>
		<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">评审内容:</span>
			<div><s:checkbox name="templateBean.oriBidFileCheckA" cssClass="group"></s:checkbox>
			<span>招标文件(招标文件编号：</span>
			<span class="wap_value">${templateBean.oriBidFileCode}</span>
			</div>
			<div><s:checkbox name="templateBean.oriBidFileCheckB" cssClass="group"  id="examType2"></s:checkbox>正式合同
			</div>
		</div>
		<div class="div_label">
			<span class="wap_label">【招标主要内容】</span>
			<div class="div_row">
				<span class="wap_title">合同标段:</span>
				<span class="wap_value">${templateBean.oriBidConSection}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">合同工期:</span>
				<span class="wap_value">${templateBean.oriBidConPeriod}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">招标范围:</span>
				<span class="wap_value">${templateBean.oriBidConRangeAmount}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">预估价款:</span>
				<span class="wap_value">${templateBean.oriBidConEvaluated}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.oriBidConPayType}</span>
			</div>
		</div>
		
</div>
