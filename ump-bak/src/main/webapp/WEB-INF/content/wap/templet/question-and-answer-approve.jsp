<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 答疑审批表  -->

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">招标文件编号:</span>
			<span class="wap_value">${templateBean.bidFileCd}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">答疑回复时间:</span>
			<span class="wap_value">${templateBean.answerDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">答疑附件页数:</span>
			<span class="wap_value">${templateBean.answerAttachmentTotalPage}</span>
		</div>
		
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="div_label">
			<span class="wap_label">【投标单位内容】</span>
			<div class="div_row">
				<span class="wap_title">投标单位疑问:</span>
				<span class="wap_value">${item.bidUnitQuestion}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">责任部门:</span>
				<span class="wap_value"><%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#item.resDeptCd")) %></span>
			</div>
			<div class="div_row">
				<c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
				<span class="wap_title">责任部门回复:</span>
			<c:if test="${!(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
				<span class="wap_value">${item.resDeptOption}</span>
			</c:if>
			<c:if test="${(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
				<s:hidden id="isEdit" value="true"/>
				<div class="div_input">
					<textarea edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption">${item.resDeptOption}</textarea>
				</div>
			</c:if>
			</div>
			<div class="div_row">
				<span class="wap_title">区域公司成本管理部回复:</span>
				<s:if test="(#curNodeCd==156 && isMyApprove==1)">
				<s:hidden id="isEdit" value="true"/>
				<div class="div_input">
					<textarea edit='true' type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].costDeptOption">${item.costDeptOption}</textarea>
				</div>	
				</s:if>
				<s:else>
				<span class="wap_value">${item.costDeptOption}</span>
				</s:else>
			</div>
			<div class="div_row">
				<span class="wap_title">区域总经理回复:</span>
				<s:if test="(#curNodeCd==157 && isMyApprove==1)">
				<s:hidden id="isEdit" value="true"/>
				<div class="div_input">
					<textarea edit='true' type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].regionManagerOption">${item.regionManagerOption}</textarea>
				</div>
				</s:if>
				<s:else>
				<span class="wap_value">${item.regionManagerOption}</span>
				</s:else>
			</div>
			<div class="div_row">
				<span class="wap_title">最终回复执行副总裁:</span>
				<s:if test="(#curNodeCd==148 && isMyApprove==1)">
				<s:hidden id="isEdit" value="true"/>
				<div class="div_input">
					<textarea edit='true' type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption">${item.vicePresidentsOption}</textarea>
				</div>
				</s:if>
				<s:else>
				<span class="wap_value">${item.vicePresidentsOption}</span>
				</s:else>
			</div>
		</div>
		</s:iterator>
		
				
</div>
