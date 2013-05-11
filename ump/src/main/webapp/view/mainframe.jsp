<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/tab4view.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/tab4view.js"></script>
<title>内容</title>

<script type="text/javascript">
function createOrShowTab(tabId,tabName,src,isClose){
	TabUtils.newTab(tabId,tabName,src,isClose);
}
$(function(){
	$("#divFrame").height($(window).height()*0.95);
	createOrShowTab('viewWelcome','首页','mainEdit.jsp',true);
});
</script>
</head>
<body>
<div id="ModuleRight">
		  <div class="tab" id="divTab" ></div>
		  <div class="tab-line" id="divTabLine" ></div>
		  <div class="tab-content" id="divFrame" >
		  </div>
</div>
</body>
</html>

