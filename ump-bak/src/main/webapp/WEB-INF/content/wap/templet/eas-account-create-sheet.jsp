<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--账套建立申请表 --%>

<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">申请人:</span>
			<span class="wap_value">${templateBean.applicant}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">联系电话:</span>
			<span class="wap_value">${templateBean.tel}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">帐套单位名称:</span>
			<span class="wap_value">${templateBean.accountName}</span>
		</div>
		<div class="div_row">
	 		<span class="wap_title">是否上市帐套:</span>
			<div><s:checkbox name="templateBean.listedAccountFlg1" cssClass="group"></s:checkbox><span>是</span></div>
			<div><s:checkbox name="templateBean.listedAccountFlg2" cssClass="group"></s:checkbox><span>否</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">初始建账日期:</span>
			<span class="wap_value">${templateBean.initCreatedDate}</span>
		</div>
		<div class="div_row">
	 		<span class="wap_title">是否有科目初始余额:</span>
			<div><s:checkbox name="templateBean.initBalanceFlag1" cssClass="group"></s:checkbox><span>是</span></div>
			<div><s:checkbox name="templateBean.initBalanceFlag2" cssClass="group"></s:checkbox><span>否</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">帐套操作用户姓名\职位\权限说明:</span>
			<span class="wap_value">${templateBean.accountDesc}</span>
		</div>
</div>
