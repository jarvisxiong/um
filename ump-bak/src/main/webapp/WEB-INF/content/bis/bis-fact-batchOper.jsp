<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css"  />

<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis-batch-oper.js"></script>
<script type="text/javascript">
$(function(){
	initOperMoudel();
	$("#must").click();
});
$('#bisProjectName').onSelect({
	muti:false,
	callback:function(project){
		$("#bisProjectName").val(project.projectName);
		$("#bisProjectId").val(project.bisProjectId);
		changeFloor();
	}
});
var currProjectId ='${bisProjectId}';

</script>
<style type="text/css">
.bis_fact_clicked{border:1px solid #999999;height: 22px;background-color: #2d8bef;}
</style>
</head>
<body>
<div>
 <ul id="bis_rpt"  style="margin: 10px;list-style-type:none;">
	<li value="2" id="must" class="bis_fact_click ">应收款项</li>
	<li value="3" id="factAuth"  class="bis_fact_click " >权责月实收</li>
	<li value="6" id="factCash"  class="bis_fact_click ">现金流月实收</li>
 </ul>
</div>
<div id="showDiv" style="padding:10px;padding-top:50px;">
</div>
<%-- 
<div style="width:100%; border-top:0 solid #909090;margin-top:20px;" id="footerDiv" class="followed_div">
	<div>
		<ul class="bottom-nav" id="bottom-oper">
			<li onclick="toAuthority(this,'${bisProjectId}')" class="bottom-nav-click">权责</li>
			<li onclick="toCash(this,'${bisProjectId}');">现金</li>
		</ul>
	</div> 
</div>
--%>
</body>