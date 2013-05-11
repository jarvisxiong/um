<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
</head>

<body>
<form id="costMateModuleUplodForm" action="${ctx}/cost/cost-mate-module!importExlPoi.action"  method="post" enctype="multipart/form-data">
		<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
			  <table>
			   	<tr>
					<td style="height: 30px;" align="right">&nbsp;路径：</td>
					<td style="height: 30px;"><input style="height: 20px;padding-top: 3px;" type="file" id="file" name="upload"/></td>
				</tr>
			</table>
		</div>
</form>
</body>
</html>
