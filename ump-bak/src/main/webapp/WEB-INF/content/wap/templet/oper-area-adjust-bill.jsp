<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	开业后业态面积调整审批表(eg:开业后业态面积调整审批表)	 
--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">条件选择:</span>
		<div><s:checkbox name="templateBean.areaRate1" cssClass="group"></s:checkbox><span>面积占比&le;10%</span></div>
		<div><s:checkbox name="templateBean.areaRate2" cssClass="group"></s:checkbox><span>10%&lt;面积占比&le;20%</span></div>
		<div><s:checkbox name="templateBean.areaRate3" cssClass="group"></s:checkbox><span>面积占比&gt;20%</span></div>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.rentMoney1" cssClass="group"></s:checkbox><span>租金收益高于标准水平</span></div>
		<div><s:checkbox name="templateBean.rentMoney2" cssClass="group"></s:checkbox><span>租金收益低于标准水平</span></div>
	</div>
	<div class="div_row">
	  <span class="wap_title">文件标题:</span>
	  <span class="wap_value">关于${templateBean.projectName}业态面积调整的审批</span>
    </div>
    <div class="div_row">
		<span class="wap_title">密级:</span>
		<div><s:checkbox name="templateBean.securityCd1" cssClass="group"></s:checkbox><span>绝密</span></div>
		<div><s:checkbox name="templateBean.securityCd2" cssClass="group"></s:checkbox><span>机密</span></div>
		<div><s:checkbox name="templateBean.securityCd3" cssClass="group"></s:checkbox><span>保密</span></div>
		<div><s:checkbox name="templateBean.securityCd4" cssClass="group"></s:checkbox><span>内部公开</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">店面类别:</span>
		<div><s:checkbox name="templateBean.storeTypeCd1" cssClass="group"></s:checkbox><span>主力店(百货、超市)</span></div>
		<div><s:checkbox name="templateBean.storeTypeCd2" cssClass="group"></s:checkbox><span>次主力店</span></div>
		<div><s:checkbox name="templateBean.storeTypeCd3" cssClass="group"></s:checkbox><span>品牌店</span></div>
		<div><s:checkbox name="templateBean.storeTypeCd4" cssClass="group"></s:checkbox><span>其他&nbsp;${templateBean.storeTypeCd4Desc}</span></div>
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
	  <span class="wap_title">业态调整说明:</span>
	  <span class="wap_value">${templateBean.adjustDesc}</span>
    </div>
</div>
