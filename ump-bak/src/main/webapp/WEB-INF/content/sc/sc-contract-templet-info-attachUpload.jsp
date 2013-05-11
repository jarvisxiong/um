<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<s:form id="conAttachForm" action="/sc/sc-contract-templet-info!doAttachUpload.action" enctype="multipart/form-data" method="post">

	<table style="width: 100%;">
		<tr>
			<td align="right" style="width: 100px;">附件路径：</td>
			<td>
			<div style="padding: 0px;">
			<div style="text-align: left; height: 25px; overflow: hidden; cursor: pointer; width: 190px" title="请选择上传文件">
			<input type="file" id="attachSingleForm_uploadInput" name="upload" size="50" style="float: left; height: 25px; margin-left: 0px; width: 190px;" maxlength="255" /></div>
			</div>
			<input type="hidden" id="scAttachContId" name="scAttachContId" />
			 <input type="hidden" id="scAttachHistContId" name="scAttachHistContId" />
			 </td>
		</tr>
		<tr>
			<td width="100" style="text-align:right">附件类型：</td>
			<td><s:select list="mapmoduleTypeCd" listKey="key" listValue="value" id="attachTypeCd" name="attachTypeCd" style="width:190px"></s:select>
			<input type="hidden" id="sysTempletId" name="sysTempletId"/>
			<%--合同类型（1.标准0.非标） --%>
			<input type="hidden" name="isscstandard" id="isscstandard" value="1"/>
			</td>
		</tr>
		<tr>
			<td width="100" style="text-align:right">附件描述：</td>
		<td>
			<s:textarea cssStyle="width:190px;height:51px; border: 1px solid #AAC0D5;" name="remark" id="remark"  maxlength="180"/>	
    	</td>
		</tr>
	</table>

</s:form>