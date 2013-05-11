<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<%-- 证照办理确认单 --%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">标题:</span>
			<span class="wap_value">${templateBean.titleName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目:</span>
			<div><s:checkbox name="templateBean.matters1" cssClass="group"></s:checkbox><span>设立</span></div>
			<div><s:checkbox name="templateBean.matters2" cssClass="group"></s:checkbox><span>基本信息变更（名称、地址、法人或负责人、类型、注册资金、经营范围、其他）</span></div>
			<div><s:checkbox name="templateBean.matters3" cssClass="group"></s:checkbox><span>注销</span></div>
			<div><s:checkbox name="templateBean.matters4" cssClass="group"></s:checkbox><span>任职变更（董事长、总经理、股东、董事、监事）</span></div>
			<div><s:checkbox name="templateBean.matters5" cssClass="group"></s:checkbox><span>资质（新办、年检、升级）</span></div>
			<div><s:checkbox name="templateBean.matters6" cssClass="group"></s:checkbox><span>项目手册（新办、年检）</span></div>
		</div>	
		<div class="div_row">
			<span class="wap_title">申请内容(详细内容附后):</span>
			<span class="wap_value">${templateBean.applContent}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【需明确的事项】</span>
			<div class="div_row">
				<span class="wap_title">公司名称:</span>
				<span class="wap_value">${templateBean.companyName}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">公司性质:</span>
				<div><s:checkbox name="templateBean.companyNatureN" cssClass="group"></s:checkbox><span>内资</span></div>
				<div><s:checkbox name="templateBean.companyNatureW" cssClass="group"></s:checkbox><span>外资</span></div>
			</div>
			<div class="div_row">
				<span class="wap_title">备用名称1:</span>
				<span class="wap_value">${templateBean.backupName1}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">注册资本:</span>
				<span class="wap_value">${templateBean.registCapital}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">备用名称2:</span>
				<span class="wap_value">${templateBean.backupName2}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">法定代表人:</span>
				<span class="wap_value">${templateBean.legalPerson}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">经营范围:</span>
				<span class="wap_value">${templateBean.businessScope}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">股东及持股比例:</span>
				<span class="wap_value">${templateBean.shareProportion}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">董事会成员或董事成员:</span>
				<span class="wap_value">${templateBean.bds}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">监事:</span>
				<span class="wap_value">${templateBean.supervisors}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">注册地址:</span>
				<span class="wap_value">${templateBean.registAddress}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">其他要求:</span>
				<span class="wap_value">${templateBean.otherRequirements}</span>
			</div>
		</div>
</div>
