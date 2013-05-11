<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<title>显示/隐藏左侧导航栏</title>
<script language="JavaScript">

function Submit_onclick(){
	if(parent.myFrame.cols == "199,7,*") {
		parent.myFrame.cols="0,7,*";
		document.getElementById("ImgArrow").src="${ctx}/images/switch_right.gif";
		document.getElementById("ImgArrow").alt="打开左侧导航栏";
		document.getElementById("ImgArrow").title="打开左侧导航栏";
	} else {
		parent.myFrame.cols="199,7,*";
		document.getElementById("ImgArrow").src="${ctx}/images/switch_left.gif";
		document.getElementById("ImgArrow").alt="隐藏左侧导航栏";
		document.getElementById("ImgArrow").title="隐藏左侧导航栏";
	}
}

</script>
</head>
<body>
<div id="switchpic" class="switchpic"><a href="javascript:Submit_onclick()"><img src="${ctx}/images/switch_left.gif" title="隐藏左侧导航栏" alt="隐藏左侧导航栏" id="ImgArrow" /></a></div>
</body>
</html>