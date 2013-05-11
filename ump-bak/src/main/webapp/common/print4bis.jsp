<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>打印</title>

	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/res.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/resApprove.css" />
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<style type="text/css">
	<!--
	
	body{
		font-size: 12px;
	}
	.STYLE4 {
		color: #CC0000;
		font-weight: bold;
	}
	.inputBorder_readOnly,.approveTable .approveOption {
		font-size: 14px;
	}
	-->
	</style>
	<script type="text/javascript">
		window.resizeTo(window.screen.availWidth, window.screen.availHeight);
		window.moveTo(0, 0);
	</script>
</head>
<body style="background-color: White;">

<div id="title" class="STYLE4"></div>

<div id="print" ></div>

<script type="text/javascript">
	var father = window.opener;
	if (father != null) {
		//$("#print").html(father.document.getElementById("detailPanelDiv").innerHTML);
		$("#print").html($(father.document).find("#detailPanelDiv").html());
		$(".noprint").hide();
		$("#print").append($("#divRandomNo").clone());
		$('.xheditor-simple').filter("[edit!=true]").each(function(i){
			var str = $(this)[0].value;
			$(this).replaceWith('<div class="orgDiv"><pre style="white-space:pre-wrap;">'+str+'</pre></div>');
		});
		$('.xhe_default').remove();
		$(father.document).find("#detailPanelDiv .signArea").remove();
		$(".approveOption").each(function(i){
			var oriValue=$(this).attr('oriValue');
			if(oriValue){
				this.innerHTML = oriValue;
			}
		 });
		window.print();
	}

</script>

</body>
</html>