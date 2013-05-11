<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--商业工程改造审批表-->
<div id="billContent">
<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value"><input class="inputBorder" validate="required" type="text" name="templateBean.itemName" value="${templateBean.itemName}"  /></span>
		</div>
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value"><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">预估工程费用(元):</span>
			<span class="wap_value"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.preProjectFee" value="${templateBean.preProjectFee}"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">工程改造内容及原因:</span>
			<span class="wap_value"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.transformCause">${templateBean.transformCause}</textarea></span>
		</div>
		<s:if test="#isJcw">
			<div class="div_row">
				<span class="wap_title">决策委员会名单:</span>
				<span class="wap_value"><input id="receiveUserNames" type="text"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip mutiSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="receiveUserCds"/></span>
			</div>
		</s:if>
</div>
		
