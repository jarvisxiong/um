<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<title>月计划管理</title>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script language="javascript">
var trainPeriod = "${trainPeriod}";
if(null==trainPeriod || ""==trainPeriod){
	trainPeriod = 0;
}
function myFlash_DoFSCommand(command, args){
	var data={
			trainPeriod:trainPeriod,
			questionNo:command,
			answer:args
		};
	$.post(_ctx+"/plan/plan-train-golf!save.action",data,function(result) {
	});
}
if (navigator.appName && navigator.appName.indexOf("Microsoft") != -1 && 
  	navigator.userAgent.indexOf("Windows") != -1 && navigator.userAgent.indexOf("Windows 3.1") == -1) {
  	document.write('<SCRIPT LANGUAGE=VBScript\> \n');
  	document.write('on error resume next \n');
  	document.write('Sub myFlash_FSCommand(ByVal command, ByVal args)\n');
  	document.write(' call myFlash_DoFSCommand(command, args)\n');
  	document.write('end sub\n');
  	document.write('</SCRIPT\> \n');
}
</script>
</head>

<body>
<table width="100%" border="0">
	<tr><td align="center">
		<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" WIDTH="550" HEIGHT="400" id="myFlash">
	        <PARAM NAME=movie VALUE="${ctx}/resources/golf/${trainPeriod}.swf">
	        <PARAM NAME=quality VALUE=high><EMBED src="${ctx}/resources/golf/${trainPeriod}.swf" quality=high WIDTH="550" HEIGHT="400" NAME="myFlash" swLiveConnect="true"
	            TYPE="application/x-shockwave-flash" PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"></EMBED>
	    </OBJECT>
	</td></tr>
</table>
</body>
</html>
