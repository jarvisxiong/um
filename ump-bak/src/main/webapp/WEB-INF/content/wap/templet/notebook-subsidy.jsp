<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--A类笔记本补贴申请审批表-->
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">所属中心/公司:</span>
			<span class="wap_value">${templateBean.centerOrCompanyName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请人:</span>
			<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职位:</span>
			<span class="wap_value">${templateBean.position}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">文件内容简述:</span>
			<span class="wap_value">${templateBean.documentContent}</span>
		</div>
		<s:if test="nodeCd==115&&isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<div class="div_row">
				<span class="wap_title">使用人:</span>
				<div class="div_input">
					<input type="text" edit='true' validate="required"   name="templateBean.applyUser" value="${templateBean.applyUser}"  />
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">A类笔记本型号:</span>
				<div class="div_input">
					<input type="text" edit='true' validate="required"   name="templateBean.notebookType" value="${templateBean.notebookType}"  />
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">每月补贴金额(元):</span>
				<div class="div_input">
					<input type="text" edit='true' validate="required"   name="templateBean.subsidyMoney" value="${templateBean.subsidyMoney}" />
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">补贴生效日期:</span>
				<div class="div_input">
					<input edit='true'  type="text" validate="required" name="templateBean.subsidyStartDate" value="${templateBean.subsidyStartDate}"  />
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">补贴终止日期:</span>
				<div class="div_input">
					<input edit='true'  type="text"   name="templateBean.subsidyEndDate" value="${templateBean.subsidyEndDate}"  />
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">注:</span>
				<div class="div_input">
					<textarea edit='true'  name="templateBean.remark">${templateBean.remark}</textarea>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="div_row">
				<span class="wap_title">使用人:</span>
				<span class="wap_value">${templateBean.applyUser}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">A类笔记本型号:</span>
				<span class="wap_value">${templateBean.notebookType}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">每月补贴金额(元):</span>
				<span class="wap_value">${templateBean.subsidyMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">补贴生效日期:</span>
				<span class="wap_value">${templateBean.subsidyStartDate}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">补贴终止日期:</span>
				<span class="wap_value">${templateBean.subsidyEndDate}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">注:</span>
				<span class="wap_value">${templateBean.remark}</span>
			</div>
		</s:else>
</div>
