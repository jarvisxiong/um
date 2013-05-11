<%--
手机审批附件显示
 --%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" description="附件标题" required="true"%>
<%@ attribute name="fileId" description="字段ID" required="true"%>
<%@ attribute name="fileValue" description="文件ID" required="true"%>
<%@ attribute name="index" description="列表序号" required="false"%>
<div class="div_row">
	<span class="wap_title">${title}:</span>
	<div id="show_${index}${fileId}"></div>
	<script type="text/javascript">
	showUploadedFile('${fileValue}','${index}${fileId}','false');
	</script>
</div>