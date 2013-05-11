<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<s:form id="attachSingleForm" action="/sc/sc-contract-templet!uploadSingleFile.action" enctype="multipart/form-data" method="post">
<div id="tipUploadPorcess" style="display:none;width:100%;color:red;text-align: center;">正在上传中...</div>
	<div id="operationUploadDiv" style="padding:3px;">
	<fieldset style="padding:3px;"  class="fieldset">
		    <legend class="legend"><s:text name="common.upload" /></legend>
  <table style="width: 100%;">
  <tr style="display:none">
		<td align="right" style="width: 100px;">序号：</td>
		<td>
		<input type="text" name="sequenceNo" id="temSequenceNo" style="width:190px" maxlength="50" value="${entity.sequenceNo}"></input>
			<input type="hidden" name="templetTypeId" id="templetTypeId" style="width:190px" maxlength="50" value="${entity.scContractTempletType.contractTempletTypeId}"></input>
			<!-- id -->
		    <input type="hidden" name="templetId" id="templetId"  value="${entity.contractTempletId }"/>
		
		</td>
	</tr>
	<tr>
		<td align="right" style="width: 100px;">模板名称：</td>
		<td><input type="text" name="templetName" id="templetName"  class="input" style="width:290px" maxlength="50"   value="${entity.templetName}"></input></td>
	</tr>
	<tr id="templetFileTr">
		<td align="right" style="width: 100px;">文件路径：</td>
		<td>
		<div style="padding:0px;">	    
	    <div style="text-align:left;height: 25px;overflow: hidden;cursor: pointer;width:290px" title="请选择上传文件">
	    	<input type="file" id="attachSingleForm_uploadInput" name="upload" size="50" style="float: left; height: 25px; margin-left:0px; width: 190px;" maxlength="255"/>
   	 	    <input type="hidden" name="templetPath" id="templetPath"  value="${entity.templetPath}"/>
   	 	</div>
	</div>
	</td>
	</tr>
	<tr id="attachmentTr0" style="display:none">	
	<td align="right" style="width: 100px;">附件：</td>
		<td >	
		<div style="padding:0px;">	    
	    <div style="text-align:left;height: 25px;overflow: hidden;cursor: pointer;width:290px" title="请选择上传文件">
	    	<input type="file" id="attachSingleForm_uploadInput" name="attachment" size="50" style="float: left; height: 25px; margin-left:0px; width: 190px;" maxlength="255"/>

   	 	</div>
	</div>
	</td>
	</tr>
	
	<tr id="isEnabledTr">
		<td width="100" style="text-align:right">是否启用：</td>
		<td style="text-align:left;"><input type="checkbox" name="isvalid" id="isvalid" class="group"  <s:if test="entity.isvalid==1">checked="true"</s:if>/></td>
	</tr>
	<tr>
		<td width="100" style="text-align:right">是否当前版本：</td>
		<td style="text-align:left;"><input type="checkbox" name="iscurversion" id="iscurversion" class="group" <s:if test="entity.iscurversion==1">checked="true"</s:if>/></td>
	</tr>
	<tr>
		<td width="100" style="text-align:right">是否标准合同：</td>
		<td style="text-align:left;"><input type="checkbox" name="isstandard" id="isstandard" class="group"  <s:if test="entity.isstandard==1">checked="true"</s:if>/></td>
	</tr>
	<tr>
		<td width="100" style="padding: 5px 0 0 5px; text-align: right;">描述:</td>
		<td style="padding: 5px 0 0 0;">
		<textarea rows="5" cols="6" maxlength="200" style="margin-left: 0px; margin-right: 0px; width: 290px;" name="remark" id="temRemark"  maxlength="200">${entity.remark}</textarea>
		<input type="hidden" name="recordVersion" id="recordVersion" value="0"/>
		</td>
	</tr>
	
</table>
    </fieldset>
   </div>
   
</s:form>

 
    <div style="padding:3px;">
		<fieldset style="padding:3px;"  class="fieldset">
		    <legend class="legend"><s:text name="common.download" /></legend>
		    <div>
		    <table align="left" width="100%">
			    <s:iterator value="appAttachFileList">
			    <tr id="tr${appAttachFileId}">
			    <td width="60%">
					<s:url id="downUrl" action="download" namespace="/sc">
													<s:param name="fileName">${fileName}</s:param>
													<s:param name="realFileName">${realFileName}</s:param>
													<s:param name="bizModuleCd">${bizModuleCd}</s:param>
													<s:param name="operator">inline;</s:param>
													<s:param name="id">${appAttachFileId}</s:param>
												</s:url>
					<a href="${downUrl}">${realFileName}</a>
			    </td>
			    <td width="35%">
		    		<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>
		    		<s:property value="createdDate" />
			    </td>
			    <td width="20px">
			    	<!-- 如果是共享人,则不允许删除;否则,可删除 -->
					<s:set var="entityId" value="bizEntityId"></s:set>
					<s:set var="isSharedUser" value="%{isSharedUser(#entityId)}"></s:set>
					<s:set var="curUser" ><%=SpringSecurityUtils.getCurrentUiid() %></s:set>
					<s:if test="userCd==#curUserCd">
								    		<a href="javascript:delAttach(this,'${appAttachFileId}')">
			    			<font style='color:red;font-weight:bold;cursor:pointer;'>×</font></a>
					</s:if>
			    </td>
			    </tr>
			    </s:iterator>
		    </table>
		    </div>
		</fieldset>
<!--		<input type="button" value="完成"  class="btn_green_55_20" style="border:none;" onclick="doClose();" />-->
	</div>
	
	<script type="text/javascript">
	/**
	添加附件
	**/
	function addNewFile() {
		var trUpload = $("#attachmentTr0").clone();
		trUpload.show();
		$("#isEnabledTr").before(trUpload);
		trUpload.append("<td onclick='remove(this);' style='cursor: pointer;'><font style='color:red;font-weight:bold;cursor:pointer;'>×</font></td>");
	}
	//移除附件
	function remove(dom){
		$(dom).parent().remove();
	}
	//删除附件
	var _delTr=null;
	function delAttach(dom,attachId){

		_delTr=$("#tr"+attachId);
		$(_delTr).remove();
		var url = _ctx+ "/sc/sc-contract-templet!delAttach.action";
		var data = {					
			id : attachId,					
			bizEntityName:'scContractTemplet'
		};
		$.post(url, data, function(result) {
			result=""+result;
			if(result.indexOf("true")>-1){
				$("#resultMessage").html("附件删除成功！");
				_delTr.remove();
				window.setTimeout("$('#resultMessage').html('')",3000);
				}
			
		 });
		}
	</script>