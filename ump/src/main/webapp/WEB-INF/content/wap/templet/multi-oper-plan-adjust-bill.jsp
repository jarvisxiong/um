<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	多经调整审批表(eg:多经点位规划及调整审批表)	
 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
	  <span class="wap_title">文件标题:</span>
	  <span class="wap_value">关于${templateBean.projectName}多经点位规划及调整的审批</span>
    </div>
    <div class="div_row">
		<span class="wap_title">密级:</span>
		<div><s:checkbox name="templateBean.securityCd1" cssClass="group"></s:checkbox><span>绝密</span></div>
		<div><s:checkbox name="templateBean.securityCd2" cssClass="group"></s:checkbox><span>机密</span></div>
		<div><s:checkbox name="templateBean.securityCd3" cssClass="group"></s:checkbox><span>保密</span></div>
		<div><s:checkbox name="templateBean.securityCd4" cssClass="group"></s:checkbox><span>内部公开</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">多经类别:</span>
		<div><s:checkbox name="templateBean.multiTypeCd1" cssClass="group"></s:checkbox><span>路演</span></div>
		<div><s:checkbox name="templateBean.multiTypeCd2" cssClass="group"></s:checkbox><span>促销</span></div>
		<div><s:checkbox name="templateBean.multiTypeCd3" cssClass="group"></s:checkbox><span>其他</span>
		(${templateBean.multiTypeCd3Desc})
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">紧急:</span>
		<div><s:checkbox name="templateBean.urgencyCd1" cssClass="group"></s:checkbox><span>是</span></div>
		<div><s:checkbox name="templateBean.urgencyCd2" cssClass="group"></s:checkbox><span>否</span></div>
	</div>
	<div class="div_row">
	  <span class="wap_title">中心:</span>
	  <span class="wap_value">${templateBean.centerName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">多经点位规划及调整说明<br/>(多经点位规划图附后):</span>
	  <span class="wap_value">${templateBean.adjustDesc}</span>
    </div>
</div>
