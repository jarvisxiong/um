<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--工程缺陷改造-->
<div id="billContent">
	<div class="div_row">
		<div><s:checkbox name="templateBean.feeType1"  cssClass="group"></s:checkbox>维保期，地产公司承担费用</div>
		<div><s:checkbox name="templateBean.feeType2"  cssClass="group"></s:checkbox>营运期，资本性支出</div>
		<s:if test="authTypeCd == 'JD_JSGL_10'">
		<div><s:checkbox name="templateBean.modifyType3"  cssClass="group"></s:checkbox>施工单位未能按照维保协议履行维保义务,由酒店公司自行安排</div>
		</s:if>
		<div><s:checkbox name="templateBean.modifyType2"  cssClass="group"></s:checkbox>其他</div>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.modifyType1"  cssClass="group"></s:checkbox>设计外观、效果改造</div>
	</div>
	
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.itemName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">预估工程费用(元):</span>
			<span class="wap_value">${templateBean.preProjectFee}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">工程改造内容及原因:</span>
			<span class="wap_value">${templateBean.transformCause}</span>
	</div>
	<s:if test="#isJcw">
	<div class="div_row">
			<span class="wap_title">决策委员会名单:</span>
			<span class="wap_value">
			    <input id="receiveUserNames" type="text"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip mutiSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="receiveUserCds"/>
			</span>
	</div>
	</s:if>
	
</div>
