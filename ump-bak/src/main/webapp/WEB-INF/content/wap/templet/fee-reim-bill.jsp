<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%--费用报销审批表--%>

<div class="billContent">
	
	<div class="div_row">
		<span class="wap_title">申请中心:</span>
		<span class="wap_value">${templateBean.applyCenterName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">入账公司:</span>
		<span class="wap_value">${templateBean.recordedCompany}</span>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		<div><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
	</div>
	<div class="div_label">
		<span class="wap_label">【开支内容】</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.feeType1" id="js_feeType1" cssClass="group"></s:checkbox><span>接待费</span></div>
			<div><s:checkbox name="templateBean.feeType2" id="js_feeType2" cssClass="group" ></s:checkbox><span>差旅费</span></div>
			<div><s:checkbox name="templateBean.feeType3" cssClass="group"></s:checkbox><span>符合制度要求的相关补贴(车补、房补、通讯费)</span></div>
		</div>
	</div>
	<div class="div_label">
		<span class="wap_label">【报销/付款信息】</span>
		<div class="div_row">
			<span class="wap_title">单据数/金额/说明</span>
		</div>
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="div_row">
			<div class="orgDiv">
				${item.docNumber}/${item.money}/${item.remark}
			</div>
		</div>
		</s:iterator>
	</div>
	<div class="div_row">
		<span class="wap_title">报销总计(元):</span>
		<span class="wap_value">${templateBean.total}(${templateBean.totalBig})</span>
	</div>
	<div class="div_row">
		<span class="wap_title">币种:</span>
		<span class="wap_value">${templateBean.currency}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">报销/付款信息:</span>
		<div><s:checkbox name="templateBean.payType1" cssClass="group"></s:checkbox><span>现金</span></div>
		<div><s:checkbox name="templateBean.payType2" cssClass="group"></s:checkbox><span>电汇</span></div>
		<div><s:checkbox name="templateBean.payType3" cssClass="group"></s:checkbox><span>信用证</span></div>
		<div><s:checkbox name="templateBean.payType4" cssClass="group"></s:checkbox><span>转账</span></div>
		<div><s:checkbox name="templateBean.payType5" cssClass="group"></s:checkbox><span>票汇</span></div>
		<div><s:checkbox name="templateBean.payType6" cssClass="group"></s:checkbox><span>其他</span></div>
	</div>
	<s:if test="(nodeCd==235||nodeCd==237) && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
		<div class="div_row">
			<span class="wap_title">公司代付合计(元):</span>
			<div class="div_input">
				<input edit='true' validate="required" type="text" id="js_companyPay" name="templateBean.companyPay" value="${templateBean.companyPay}"  onblur="formatVal($(this));" onkeyup="countReal();" />
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">冲预借款(元):</span>
			<div class="div_input">
				<input edit='true' validate="required" type="text" id="js_punchAdvance" name="templateBean.punchAdvance" value="${templateBean.punchAdvance}"  onblur="formatVal($(this));" onkeyup="countReal();" />
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">会计核减(元):</span>
			<div class="div_input">
				<input edit='true' validate="required" type="text" id="js_accountReduce" name="templateBean.accountReduce" value="${templateBean.accountReduce}"  onblur="formatVal($(this));" onkeyup="countReal();" />
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="div_row">
			<span class="wap_title">公司代付合计(元):</span>
			<span class="wap_value">${templateBean.companyPay}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">冲预借款(元):</span>
			<span class="wap_value">${templateBean.punchAdvance}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">会计核减(元):</span>
			<span class="wap_value">${templateBean.accountReduce}</span>
		</div>
	</s:else>
	<div class="div_label">
		<span class="wap_label">【转账】</span>
		<div class="div_row">
			<span class="wap_title">收款方全称:</span>
			<span class="wap_value">${templateBean.payee}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">收款账号:</span>
			<span class="wap_value">${templateBean.payeeAccount}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">开户行:</span>
			<span class="wap_value">${templateBean.payeeBank}</span>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">实付金额(元):</span>
		<span class="wap_value">${templateBean.payAmount}(${templateBean.payAmountBig})</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<s:if test="templateBean.feeType2=='true'">
		<div class="div_row">
			<span class="wap_title">出差申请单</span>
			<div id="show_tripFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.tripFileId}','tripFileId','false');
			</script>
		</div>
		</s:if>
		<s:if test="templateBean.feeType1=='true'">
		<div class="div_row">
			<span class="wap_title">接待申请单</span>
			<div id="show_jdFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.jdFileId}','jdFileId','false');
			</script>
		</div>
		</s:if>
	</div>
	
</div>
<script type="text/javascript">
	<%--实付金额：报销合计-公司代付合计-冲预借款-会计核减--%>
	function countReal(){
		var total=getVal("js_total");//报销合计
		var companyPay=getVal("js_companyPay");//公司代付合计
		var punchAdvance=getVal("js_punchAdvance");//冲预借款
		var accountReduce=getVal("js_accountReduce");//会计核减
		var payAmount=total-companyPay-punchAdvance-accountReduce;
		$("#js_payAmount").val(formatMoney(payAmount,2));
		var valBig=i2c(payAmount);
		$("#js_payAmountBig").val(valBig);
	}
</script>