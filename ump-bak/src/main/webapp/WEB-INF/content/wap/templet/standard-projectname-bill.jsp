<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--	标准审批表-项目名称	-->
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<s:if test="authTypeCd == 'CPDW_CBCP_10'||authTypeCd =='SJGL_CLSBXX_10' ">
		<div class="div_row">
			<span class="wap_title">审批权限:</span>
			<div><s:checkbox name="templateBean.estate" cssClass="group"></s:checkbox><span>与商业有关</span></div>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		</s:if>
		<s:if test="authTypeCd == 'SJGL_CLSBXX_10'||authTypeCd =='SJGL_CLSBXX_20'||authTypeCd =='SJGL_CLSBXX_30'">
			<div class="div_row">
			<s:checkbox name="templateBean.isOutFace" cssClass="group"></s:checkbox><span>涉及外立面</span>
			</div>
		</s:if>
		<div class="div_row">
			<span class="wap_title">标题:</span>
			<span class="wap_value">${templateBean.titleName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">内容简述(详细内容附后):</span>
			<span class="wap_value">${templateBean.contentBriefly}</span>
		</div>
</div>
