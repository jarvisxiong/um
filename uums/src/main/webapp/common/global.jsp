<script language="javascript">
	var _ctx = '${ctx}';
</script>
<%
	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>