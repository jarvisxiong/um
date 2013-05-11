<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--银行付款审批单-->
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	<s:if test="authTypeCd!='FKGL_220'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.type1" cssClass="group"></s:checkbox><span>全资公司</span></div>
		<div><s:checkbox name="templateBean.type2" cssClass="group"></s:checkbox><span>合资公司(宝龙拍卖、聚龙轩)</span></div>
	</div>
	</s:if>
	<s:if test="authTypeCd=='FKGL_220'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.area1" cssClass="group"></s:checkbox><span>集团内</span></div>
		<div><s:checkbox name="templateBean.area2" cssClass="group"></s:checkbox><span>集团外</span></div>
	</div>
	</s:if>
	<div class="div_row">
		<span class="wap_title">关联资金申请:</span>
		<span class="wap_value">${templateBean.resDisplayNo}</span>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.noApply" cssClass="group"></s:checkbox><span>无资金申请表</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">付款单位:</span>
		<span class="wap_value">${templateBean.payDeptName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">开户行账号:</span>
		<span class="wap_value">${templateBean.payAccount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">付款种类:</span>
		<span class="wap_value">${templateBean.payTypeName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">付款事由:</span>
		<span class="wap_value">${templateBean.payContentDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">付款金额(元):</span>
		<span class="wap_value">(大写):${templateBean.payAmtCapitalDesc}/(小写):${templateBean.payAmtSmallDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">凭证号码:</span>
		<span class="wap_value">${templateBean.voucherNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">报销单编号:</span>
		<span class="wap_value">${templateBean.expenseAccountNo}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【收款单位】</span>
		<div class="div_row">
			<span class="wap_title">全称:</span>
			<span class="wap_value">${templateBean.receiveUnitName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">帐号:</span>
			<span class="wap_value">${templateBean.receiveAccountNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">开户银行:</span>
			<span class="wap_value">${templateBean.receiveBankName}</span>
		</div>
	</div>
	
</div>
