<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 宝龙行业经营定价审批表 --%>
<%-- KTV经营定价审批表 --%>
<%-- 电玩经营定价审批表 --%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">名称:</span>
			<span class="wap_value">${templateBean.name}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">类别:</span>
			<div><s:checkbox name="templateBean.priceStrategy" cssClass="group"></s:checkbox><span>价格策略</span></div>
			<div><s:checkbox name="templateBean.priceList" cssClass="group"></s:checkbox>
			<span>价目表<s:if test="authTypeCd=='HY_JYGL_60'">(超市和包房)</s:if><s:else>(电玩项目)</s:else></span>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">内容简述(详细内容附后):</span>
			<span class="wap_value">${templateBean.desc}</span>
		</div>
</div>
