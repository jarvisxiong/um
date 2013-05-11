<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>附件管理-只上传</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/content.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/table.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>

</head>
<body>
	<s:form id="mainForm" action="app-attachment!upload"  method="post" enctype="multipart/form-data">
	<div id="tipUploadPorcess" style="display:none;width:100%;color:red;text-align: center;">正在上传中...</div>
	<div id="operationUploadDiv" style="padding:3px;">
		<fieldset style="padding:3px;">
		    <legend><s:text name="common.upload" /></legend>
		    <div>
			    <div>
				    <input type="hidden" name="bizModuleCd" value="${bizModuleCd}" />
				    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
				    <input type="hidden" name="filterType" value="${filterType}" />
				    <input type="hidden" name="bizEntityName" value="${bizEntityName}" />
				    <input type="hidden" name="attachModelType" value="${attachModelType}" />
				    <input type="hidden" name="uiid" value="${uiid}" />
				    <input type="hidden" name="onlyUpload" value="1" />
				    <table id="downTable" class="editTable"  >
				    	<tr id="trUpload">
				    	<td><input type="file" id="file" style="line-height:22px;height:22px;margin-bottom:3px;" name="upload"/></td></tr>
				    </table>
			    </div>
			    <div>
			     	<!-- 默认允许多个上传 -->
				    <s:if test="attachModelType == null || attachModelType == ''">
				   	 	<input onclick="addNewFile();" type="button" style="line-height:22px;height:22px;" value="<s:text name="appAttachment.add"/>"/>
				    </s:if>
				    <input type="button" style="line-height:22px;height:22px;" value="<s:text name="common.upload"/>" onclick="submitAttachment()"/>
			    </div>
		    </div>
		</fieldset>
	</div>
	</s:form>
	
	<div style="padding:3px;">
		<fieldset style="padding:3px;">
		    <legend><s:text name="common.download" /></legend>
		    <div>
		    <table align="left" width="100%">
		     	<col/>
			    <col width="100px"/>
			    <col width="70px"/>
			    <s:iterator value="page.result">
			    <tr>
			    <td>
				    <s:url id="down" action="download" namespace="/app">
				  	  <s:param name="fileName">${fileName}</s:param>
				  	  <s:param name="realFileName">${realFileName}</s:param>
				  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
				  	  <s:param name="filterType">${filterType}</s:param>
				  	  <s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>
			    </td>
			    <td title="<s:property value="createdDate" />">
		    		<s:date name="createdDate" format="yy-MM-dd HH:mm"/>
			    </td>
			    <td>
			    	<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
			    </td>
			    </tr>
			    </s:iterator>
		    </table>
		    </div>
		</fieldset>
	</div>
<script type="text/javascript">
	function doClose(){
		ymPrompt.close();
	}
	function addNewFile() {
		var trUpload = $("#trUpload").clone();
		trUpload.appendTo("#downTable");
		trUpload.append("<td onclick='remove(this);' style='cursor: pointer;'><font style='color:red;font-weight:bold;cursor:pointer;'>×</font></td>");
	}

	function submitAttachment(){
		$("#tipUploadPorcess").show();
		$("#operationUploadDiv").hide();
		
		$("#mainForm").ajaxSubmit(function(result){
			
			$("#tipUploadPorcess").hide();
			$("#operationUploadDiv").show();
			
			if(result.indexOf("maxSizeError") == 0){
				var max = result.split("-")[1];
				alert("上传文件最大不能超过 " + max + " M");
				//ymPrompt.alert({message:'上传文件最大不能超过'+max+'M',title:"提示",width:260,height:180});
				return;
			}
		    
			document.location = _ctx + "/app/app-attachment.action?bizModuleCd=${bizModuleCd}&bizEntityId=${bizEntityId}&filterType=${filterType}&bizEntityName=${bizEntityName}&attachModelType=${attachModelType}&onlyUpload=1";
		});
	}
</script>
</body>
</html>