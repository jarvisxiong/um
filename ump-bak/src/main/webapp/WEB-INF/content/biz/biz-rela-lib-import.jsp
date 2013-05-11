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
</head>

<body>
<form id="relaUplodForm" action="${ctx}/biz/biz-rela-lib!importExl.action"  method="post" enctype="multipart/form-data">
		<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
			  <table>
			    <col width="70">
			    <col >
			    <tr>
					<td align="right">类别：</td>
					<td style="height: 25px;"> <s:select style="width:150px;padding-top: 3px;"  list="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" listKey="key" listValue="value" id="relaTypeCd" name="relaTypeCd"></s:select>
						<input type="hidden"/>
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
			<div id="result" style="display:none;padding-left:10px;height:30px;line-height:30px;">
				
			</div>	
	
</form>		
<script type="text/javascript">
//function submit(){
//	var relaTypeCd = $('#relaTypeCd').val();
	//var fileName = document.getElementById("fileName").value;
//	alert(fileName);
//$('#relaUplodForm').submit();
//	var data = {relaTypeCd:relaTypeCd,fileName:fileName};
//	TB_showMaskLayer("正在导入...");
	//$.post('${ctx}/biz/biz-rela-lib!importExl.action',data,function(result){
	//	$('#result').html(result).show();
	//	TB_removeMaskLayer();
//	});
//}
</script>

</body>
</html>
