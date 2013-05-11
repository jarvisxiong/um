<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" href="${ctx}/resources/css/biz/biz-rela.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
</head>

<body>
<form id="purchaseUplodForm" action="${ctx}/biz/biz-htl-purchase!importExlPoi.action"  method="post" enctype="multipart/form-data">
		<div style="margin:10px 20px;">
			  <table class="shop-table">
			    <col width="100">
			    <col width="250" >
			    <col width="100">
			    <col >
			    <tr>
					<td align="right">酒店名称：</td>
					<td>
						<s:select style="width: 100%;" name="hotelCd" id="hotelCd" list="@com.hhz.ump.util.DictMapUtil@getMapHotelName()" listKey="key" listValue="value" ></s:select>
					</td>
					<%--<td><font style="color:red;">*</font>细项类</td>
			      	<td>
			      	 <s:select  style="width:100%;height: 20px;"  name="itemTypeCd" id="itemTypeCd"  list="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" listKey="key" listValue="value" ></s:select>
			      	</td>--%>
					<td align="right">路径：</td>
					<td><input style="height: 20px;padding-top: 3px;width: 100%;"  type="file"  id="file"  name="upload"/></td>
			    </tr>
				<tr>
				  <td colspan="4"><input class="btn_green" type="button" onclick="submitImportExl()" value="提交" /></td>
				</tr>
			</table>
		</div>
</form>
<script type="text/javascript">

$("#hotelName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearch.action",['dictName'],{dictCd:'hotelCd',dictName:"hotelName"});
function submitImportExl(){
	    if($('#hotelCd').val()==""){
	    	alert("请选择酒店");
	    	return false;
	    }
/**	    if($('#itemTypeCd').val()==""){
	    	alert("请选择细项");
	    	return false;
	    }**/
	    if(confirm("确定要导入该记录吗？")){
		TB_showMaskLayer("正在导入请稍后...");
		$('#purchaseUplodForm').ajaxSubmit(function(result) {
			TB_removeMaskLayer();
			if(result=="success"){
				alert("导入成功");
			}
		});
	   }
}
</script>
</body>
</html>
