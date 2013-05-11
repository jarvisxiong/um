<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<form action="${ctx}/bis/bis-project-layout!save.action" method="post" id="processForm">
<input type="hidden" name="bisProjectLayoutId" value="${bisProjectLayoutId}" />
<input type="hidden" name="entityTmpId" value="${entityTmpId}" />
<input type="hidden" name="recordVersion" value="${recordVersion}" />
<input type="hidden" name="attachFlg" id="attachFlg" value="${attachFlg}" />
<input type="hidden" name="creator" value="${creator}" />
<input type="hidden" name="createdCenterCd" value="${createdCenterCd}" />
<input type="hidden" name="createdDeptCd" value="${createdDeptCd}" />
<input type="hidden" name="createdPositionCd" value="${createdPositionCd}" />
<input type="hidden" name="createdDate" value="${createdDate}" />
<input type="hidden" name="updator" value="${updator}" />
<input type="hidden" name="updatedCenterCd" value="${updatedCenterCd}" />
<input type="hidden" name="updatedDeptCd" value="${updatedDeptCd}" />
<input type="hidden" name="updatedPositionCd" value="${updatedPositionCd}" />
<input type="hidden" name="updatedDate" value="${updatedDate}" />

<fieldset style="border:0;">
	<legend style="padding-left:10px;">
		<font style="font-weight:bolder; font-size:14px; color:#fb9032;">
			<s:if test="bisProjectLayoutId==null">新增</s:if><s:else>修改</s:else>&nbsp;&nbsp;&nbsp;&nbsp;
		</font>
		<font style="font-weight:bolder; font-size:14px; color:#fb9032;">
			(注：每条记录为一个版本，项目总况中只显示签批日期最新的版本)
		</font>
	</legend>
	<div style="font-size:12px;padding-bottom:10px; line-height:30px;background-color:#F7F7F7;padding-top:10px;">
	<table class="main_content" border="0" >
		<col width="80"/>
		<col width="180"/>
		<col />
		<col />
		<tr>
			<td style="text-align: right;">项目：</td>
			<td>
				<input validate="required" type="text" id="bisProjectNameNew" name="bisProjectName" value="${bisProjectName}" style="width:96%; cursor: pointer; font-size: 12px; color: #ff0000;" />
				<input type="hidden" id="bisProjectIdNew" name="bisProjectId" value="${bisProjectId}"/>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td style="text-align: right;">签批日期：</td>
			<td>
				<input validate="required" class="Wdate" type="text" id="imgDate" name="imgDate"  style="width:96%;" value='<s:date name="imgDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" />
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td style="text-align: right;">附件类型：</td>
			<td>
				<s:select validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapBisImgType()" listKey="key" listValue="value" name="imgType" ></s:select>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td style="text-align: right;" >附件上传：</td>
			<td>
				<a href="javascript:openAttachment('附件管理','${bisProjectLayoutId==null?entityTmpId:bisProjectLayoutId}','projectLayoutAttachId','attachFlg');" >
				<img id="projectLayoutAttachId" style="padding-top: 12px;" <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
				</a>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td style="text-align: right;">备注：</td>
			<td colspan="3">
				<textarea style="width:80%;height:50px;font-size:12px;" name="remark">${remark}</textarea>
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3" style="padding-top: 10px;">
				<input class="btn_green"  type="button" onclick="doSaveLayout();" value="保存"/>
				<input class="btn_red"  type="button" onclick="doCancel();" value="取消"/>
			</td>
		</tr>
	</table>
	</div>
</fieldset>

</form>

<script type="text/javascript">
$(function(){
	$('#bisProjectNameNew').onSelect({muti:false});
});

</script>