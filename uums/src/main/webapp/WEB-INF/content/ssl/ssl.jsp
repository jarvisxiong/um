<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title>SSL校验</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
</head>
<body>
   <s:form id="inputForm" action="ssl!result.action" method="post">
	    <table>
	    <tr>
	    	<td colspan="2">
				 校验类型: <br/>
				1.elem-返回页面元素(默认)<br/>
				2.url-返回sucess.jsp/failure.jsp/exception.jsp<br/>
				3.返回response参数(eg:head/session/request)<br/><br/>
	    	</td>
	    </tr>
	    <tr>
		    <td style="text-align: right;">用户名</td>
		    <td style="text-align: left; "><input id="name" name="name" type="text" /></td>
	    </tr>
	    <tr>
		    <td style="text-align: right;">密码</td>
		    <td style="text-align: left; "><input id="password" name="password" type="text" /></td>
	    </tr>
	    <tr>
		    <td style="text-align: right;"></td>
		    <td style="text-align: left; ">
		    	<input type="submit" name="mysubmit" id="mysubmit" value="提交" />
		    	&nbsp;
		    	<input type="reset"  name="reset"    id="reset" value="重置" />
		    </td>
	    </tr>
	    </table>
    </s:form>
</body>
</html>
