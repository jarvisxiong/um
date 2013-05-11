<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${conPrintInfo.contractName}</title>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<style>
	<!--
body{
	font-size: 20px;
	letter-spacing: 2px;
}
/*填空*/
.span_input{
	border-bottom: 1px solid #000;
	display: inline-block;
	background-color: #fff !important;
}
.postil{
	border-bottom: 1px solid #000;
	display: inline-block;
	background-color: #fff !important;
}
/*封面标题*/
.fp_title{
	font-size:58px;
	font-weight:bolder;
	font-family:黑体;
}
/*封面小标题*/
.fp_title_sub{
	font-size:38px;
	font-weight:bolder;
	font-family:黑体;
}
/*封面正文*/
.fp_font{
	font-size:22px;
	font-weight:bolder;
	font-family:黑体;
}
/*正文标题*/
.c_title{
	font-size:38px;
	font-weight:bold;
	font-family:黑体;
}
/*正文甲乙方*/
.c_title_both{
	font-size:20px;
	font-weight:bold;
	font-family:黑体;
}
	-->
</style>
<script type="text/javascript">
	//window.resizeTo(860, window.screen.availHeight);
	//window.moveTo(10, 0);
	$(function(){
		$(".postil").removeAttr("style");
		$(".span_input").removeAttr("style");
		$("ins").removeAttr("style").css("text-decoration","none");
		$("table").css("background","none");
		$("ins").each(function(){
			if($(this).attr("isDel")){
				$(this).hide();
			}
		});
		window.print();
	});
</script>
</head>
<body style="background-color:#fff;">
编号：${conPrintInfo.contractNo}
<br/>
流水号：${conPrintInfo.randPrintSN}
<br/>
${scContractParams.scContractHtml}
</body>
</html>