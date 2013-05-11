<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
</head>

<body>
<form id="htlUplodForm" action="${ctx}/biz/biz-htl-hoe!importExlPoi.action"  method="post" enctype="multipart/form-data">
		<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
			  <table>
			    <col width="70">
			    <col >
			    <tr>
					<td align="right">酒店名称：</td>
					<td style="height: 25px;">
						<input type="text" name="imHotelName" id="imHotelName" />	
						<input type="hidden"  name="imHotelCd" id="imHotelCd"/>
					</td>
				</tr>
				
				<tr>
					<td align="right">路径：</td>
					<td style="height: 30px;"><input style="height: 20px;padding-top: 3px;"  type="file"  id="file"  name="upload"/></td>
				</tr>
				<tr>
				  <!--<td><input class="btn_green" type="button" onclick="submit()" value="提交" /></td>-->
				</tr>
			</table>
		</div>
	
</form>
<script type="text/javascript">
$("#imHotelName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearch.action",['dictName'],{dictCd:'imHotelCd',dictName:"imHotelName"});
</script>

</body>
</html>
