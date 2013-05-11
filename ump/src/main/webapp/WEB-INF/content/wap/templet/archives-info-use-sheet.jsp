<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%--档案资料使用审批表--%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">申请人:</span>
		<span class="wap_value">${templateBean.applyUser}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">部门:</span>
		<span class="wap_value">${templateBean.applyUserDept}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">借阅性质:</span>
			<div><s:checkbox name="templateBean.borrowerProperties1"  cssClass="group"></s:checkbox><span>查阅</span></div>
			<div><s:checkbox name="templateBean.borrowerProperties2"  cssClass="group"></s:checkbox><span>复印</span></div>
			<div><s:checkbox name="templateBean.borrowerProperties3"  cssClass="group"></s:checkbox><span>借用</span></div>
			<div><s:checkbox name="templateBean.borrowerProperties4"  cssClass="group"></s:checkbox><span>领用</span></div>
			<div>
			<s:checkbox name="templateBean.borrowerProperties5"  cssClass="group"></s:checkbox><span>其它:</span>
			<span class="wap_value">${templateBean.borrowerOther}</span>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">档案密级:</span>
			<div><s:checkbox name="templateBean.securityLevel1"  cssClass="group"></s:checkbox><span>绝密</span></div>
			<div><s:checkbox name="templateBean.securityLevel2"  cssClass="group"></s:checkbox><span>机密</span></div>
			<div><s:checkbox name="templateBean.securityLevel3"  cssClass="group"></s:checkbox><span>秘密</span></div>
			<div><s:checkbox name="templateBean.securityLevel4"  cssClass="group"></s:checkbox><span>内部公开</span></div>
		</div>
		<div class="div_row">
		<span class="wap_title">借阅日期:</span>
		<span class="wap_value">${templateBean.borrowerTime}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">归还日期:</span>
		<span class="wap_value">${templateBean.returnTime}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">借阅用途说明:</span>
		<span class="wap_value">${templateBean.borrowerState}</span>
		</div>
		<div class=div_table_row>
		<span class="wap_label">【使用档案资料】</span>
		<span class="wap_title">序号/档案资料名称/档案检索号/页数</span>	
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="orgDiv">
			<s:property value="#s.index+1"/>./${item.archivesName}/${item.archivesIndexNum}/${item.pageNum}
		</div>
		</s:iterator>
		</div>
</div>
