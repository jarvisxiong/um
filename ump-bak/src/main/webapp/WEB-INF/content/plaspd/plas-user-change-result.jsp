<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" /> 
	
	<style type="text/css">
	.res_tip {
	    background-color: #FEE3CD;
	    background-image: url("${ctx}/resources/images/res/res_tip.png");
	    background-position: 5px 5px;
	    background-repeat: no-repeat;
	    color: #5A5A5A;
	    line-height: 40px;
	    padding-left: 50px;
	    padding-top:8px;
	    text-align: left;
	    width: 90%;
	    color: red;
	}
	.re_login{
	    color: #0167A2;
	    cursor: pointer;
	    height: 42px;
	    line-height: 42px;
	    text-align: center;
	    width:80px;
	    background-color: #FFFFFF;
	    border: 1px solid #0167A2;
	    margin-top:20px;
	    color: #FFFFFF;
	    cursor: pointer;
	    height: 42px;
	    line-height: 42px;
	    text-align: center;
	    width:80px;
	    background-color: #0167A2;
	    margin-top:20px;
    }
	.re_login:hover{
    }
	</style>
	
</head>

<body style="padding: 10px;">
	<div class="res_tip">&nbsp;${infoMsg}</div>
</body>
</html>
