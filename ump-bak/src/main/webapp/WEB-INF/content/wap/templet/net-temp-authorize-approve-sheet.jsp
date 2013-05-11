<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 网批临时授权审批表 --%>
<div id="billContent">
  <%@ include file="template-var.jsp"%>
  <div class="div_row">
	  <span class="wap_title">授权人:</span>
	  <span class="wap_value">${templateBean.authorizeUserName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">职位:</span>
	  <span class="wap_value">${templateBean.position}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">被授权人:</span>
	  <span class="wap_value">${templateBean.authorizedUserName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">开始日期:</span>
	  <span class="wap_value">${templateBean.beginDate}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">结束日期:</span>
	  <span class="wap_value">${templateBean.endDate}</span>
    </div>
     <div class="div_row">
	  <span class="wap_title">授权原因:</span>
	  <span class="wap_value">${templateBean.authorizeReason}</span>
    </div>
</div>