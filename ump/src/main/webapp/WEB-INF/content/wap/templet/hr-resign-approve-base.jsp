<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 辞职审批表base --%>
<%@ include file="template-var.jsp"%>
<div class="div_row">
<span class="wap_title">姓名:</span>
<span class="wap_value">${templateBean.userName}</span>
</div>
<div class="div_row">
<span class="wap_title">中心/部门:</span>
<span class="wap_value">${templateBean.center}</span>
</div>
<div class="div_row">
<span class="wap_title">职位:</span>
<span class="wap_value">${templateBean.position}</span>
</div>
<div class="div_row">
<span class="wap_title">入职日期:</span>
<span class="wap_value">${templateBean.enterDate}</span>
</div>
<div class="div_row">
<span class="wap_title">合同期限:</span>
<span class="wap_value">${templateBean.contractExpires}</span>
</div>
<div class="div_row">
<span class="wap_title">最后工作日:</span>
<span class="wap_value">${templateBean.lastWorkDay}</span>
</div>
<div class="div_row">
<span class="wap_title">辞职原因:</span>
<span class="wap_value">${templateBean.resignReason}</span>
</div>
		<div class="div_row">
			<span class="wap_title">附件:</span>
			<div id="show_curPicId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.attachStr}','attachStr','false');
				</script>
		</div>