<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--考察申请表-->
<div id="billContent">
	
			<div class="div_row">
				<span class="wap_title">项目:</span>
				<span class="wap_value">${templateBean.projectName}</span>
			</div>				
			<div class="div_row">
				<span class="wap_title">考察供方类别:</span>
				<span class="wap_value">${templateBean.reviewSupportType}</span>
			</div>		
			<div class="div_row">
				<span class="wap_title">考察要求:</span>
				<span class="wap_value">${templateBean.reviewRequirement}</span>
			</div>
			
				<div class="div_row">
				<span class="wap_title">考察时间:</span>
				<span class="wap_value">${templateBean.reviewDate}</span>
			</div>
			
		<div class=div_table_row>
			<div class="div_row">
				<span class="wap_label">【考察部门及参与人员】</span>
			</div>
			<span class="wap_title">姓名/部门/职务</span>	
				<s:iterator value="templateBean.reviewDeptAndPersons" var="item" status="s">
				<div class="orgDiv">	
					${item.firstName}/${item.department}/${item.post}
				</div>
				</s:iterator>
		</div>
		
			<div class=div_table_row>
			<div class="div_row">
				<span class="wap_label">【考察安排】</span>
			</div>
			<span class="wap_title">序号/单位/所属区域</span>	
				<s:iterator value="templateBean.reviewArranges" var="item" status="s">
				<div class="orgDiv">
					
					<s:property value="#s.index+1"/>./${item.unitName}/${item.blongArea}
				</div>
				</s:iterator>
		</div>
		
</div>