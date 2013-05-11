<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--能耗收费标准及调整审批表--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.titleName}" title="标题"></r:wapRow>
	<r:wapRow value="${templateBean.companyName}" title="公司名称"></r:wapRow>
	<div class="div_label">
	<div class="div_scroll">
	<table>
		<tr>
		<td></td>
		<td>公用事业部门定价</td>
		<td>向商户收费方案</td>
		</tr>
		<tr>
		<td><span class="wap_title">水</span></td>
		<td>${templateBean.waterPrice1}</td>
		<td>${templateBean.waterPrice2}</td>
		</tr>
		<tr>
		<td><span class="wap_title">电</span></td>
		<td>${templateBean.electricPrice1}</td>
		<td>${templateBean.electricPrice2}</td>
		</tr>
		<tr>
		<td><span class="wap_title">供暖</span></td>
		<td>${templateBean.heatingPrice1}</td>
		<td>${templateBean.heatingPrice2}</td>
		</tr>
		<tr>
		<td><span class="wap_title">其他</span></td>
		<td>${templateBean.otherPrice1}</td>
		<td>${templateBean.otherPrice2}</td>
		</tr>
	</table>
	</div>
	</div>
	<r:wapRow value="${templateBean.remark}" title="相关说明"></r:wapRow>
</div>
