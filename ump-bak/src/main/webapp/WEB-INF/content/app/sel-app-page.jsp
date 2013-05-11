<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<title>管理区域</title>

<style type="text/css">

 .selColor{background-color:#FFFFFF;} 

</style>
</head>

<body>
<div >
	    
	    
<s:form id="mainForm" action="sel-app-page" method="get">

	<s:hidden name="page.pageNo" id="pageNo" />
	<s:hidden name="page.orderBy" id="orderBy" />
	<s:hidden name="page.order" id="order" />
	<table>
	<tr>
	<td>
	<div class="search">
		<fieldset>
		    <legend><s:text name="common.search" /></legend>
		    <div>
			      <s:text name="appAppPage.pageCd"/>:<s:textfield name="filter_EQS_pageCd" id="filter_EQS_pageCd" size="18" maxlength="30" />
			      <s:text name="appAppPage.pageName"/>:<s:textfield name="filter_LIKES_pageName" id="filter_LIKES_pageName" size="18" maxlength="30" />
			  	  <input type="submit" class="submit" value="<s:text name="common.search" />" />
			      <input type="button" class="button" value="<s:text name="common.reset" /> " onclick="resetToEmpty();"/>
			      <input type="button" class="button" value="<s:text name="common.refresh" /> " onclick="refreshDocument();"/>
	 	 	</div>
		</fieldset> 
	</div>
	</td>
	</tr>
	<tr>
 	<td>
	<table class="commonTable" id="editTable" align="left" width="99%">
		<tr>
			<th style="width:50px;"><s:text name="common.select" /></th>
			<th><a href="javascript:sort('pageCd','pageCd')"><s:text name="appAppPage.pageCd"></s:text></a></th>
			<th><s:text name="appAppPage.pageName" /></th>
			<th><a href="javascript:sort('pagePath','pagePath')"><s:text name="appAppPage.pagePath" /></a></th>
			<%-- 
			<th><s:text name="appAppPage.pageStatusCd" /></th>
			<th><s:text name="appAppPage.remark" /></th> 
			 --%>
		</tr>
		<s:iterator value="page.result">
		<tr>
			<td><input type="checkbox" name="chkStatus"/></td>
			<td><div style="display:none;">${appPageId}</div><div>${pageCd}</div></td>
			<td>${pageName}</td>
			<td>${pagePath}</td>
			<%-- 
			<td><p:code2name mapCodeName="mapEnabledFlg" value="pageStatusCd"/></td>
			<td>${remark}</td>
			 --%>
		</tr>
		</s:iterator>
		<tr>
			<td  align="center" colspan="4">
				<p:page />
			</td>
		</tr> 
	</table>
	</td>
	</tr>
	<tr>
	<td align="center">
			<input id="appPageId" type="hidden"/>
			<input id="pageCd"   type="hidden"/>
			<input id="pageName" type="hidden"/>
			<input class="button" type="button" id="btn_ok"     value="<s:text name="common.ok"/>" onclick="btn_ok_onclick()"/>
			<input class="button" type="button" id="btn_cancel" value="<s:text name="common.cancel"/>" onclick="btn_cancel_onclick()"/> 
	</td>
	</tr>
	</table>
</s:form>

	
</div>

<script language="javascript">
	
	$(document).ready(function() { 
	    $("#editTable tr").click(function() { 
	        //$("#editTable tr").removeClass("selColor"); 
	        //$(this).addClass("selColor"); 
	        
	        $("#editTable tr").find("input[type='checkbox']").removeAttr("checked"); 
	        
	        //分别获取当前行1-5列的值 
	  		$(this).find("input[type='checkbox']").attr("checked","true");
	        var appPageId = $(this).children("td").eq(1).children("div").eq(0).text();
	        var pageCd = $(this).children("td").eq(1).children("div").eq(1).text();
	        var pageName = $(this).children("td").eq(2).text();

			//alert('click:' + appPageId+','+pageCd+','+pageName);

	        $("#appPageId").val(appPageId);
	        $("#pageCd").val(pageCd);
	        $("#pageName").val(pageName);
	    }); 
	}); 

	function refreshDocument(){
		window.location.reload(true);
	}


	function btn_ok_onclick(){
		//alert('sel:' + appPageId+','+pageCd+','+pageName);
		window.returnValue = $("#appPageId").val() + ',' + $("#pageCd").val() + ',' +  $("#pageName").val(); 
		window.close();
	}

	function btn_cancel_onclick(){
		window.close();
	}

</script>
</body>
</html>
