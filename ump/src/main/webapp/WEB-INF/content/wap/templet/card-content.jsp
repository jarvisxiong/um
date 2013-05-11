<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--名片印刷内容确认单-->
		<div id="billContent">
			
				<%@ include file="template-var.jsp"%>
				<div class="div_row">
					<span class="wap_title">姓名:</span>
					<span class="wap_value">${templateBean.userName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">英文名:</span>
					<span class="wap_value">${templateBean.englishName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">手机:</span>
					<span class="wap_value">${templateBean.mobilePhone}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">电话:</span>
					<span class="wap_value">${templateBean.telephone}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">传真:</span>
					<span class="wap_value">${templateBean.fax}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">E-mail:</span>
					<span class="wap_value">${templateBean.email}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">所属公司:</span>
					<span class="wap_value">${templateBean.company}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">部门:</span>
					<span class="wap_value">${templateBean.department}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">职位:</span>
					<span class="wap_value">${templateBean.position}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">数量(盒):</span>
					<span class="wap_value">${templateBean.amount}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">地址:</span>
					<span class="wap_value">${templateBean.address}</span>
				</div>
		</div>
