<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<form action="${ctx}/bis/bis-floor!save.action" method="post" id="processForm">
<input type="hidden" name="bisFloorId" value="${bisFloorId}" />
<input type="hidden" name="subFloorTypeName" value="${subFloorTypeName}" />
<input type="hidden" name="recordVersion" value="${recordVersion}" />
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
			<s:if test="bisFloorId==null">新增</s:if><s:else>修改</s:else>&nbsp;&nbsp;&nbsp;&nbsp;
		</font>
	</legend>
	<div style="font-size:12px;padding-bottom:10px; line-height:30px;background-color:#F7F7F7;padding-top:10px;">
	<table class="table_input main_content">
		<col width="120"/>
		<col width="180"/>
		<col width="120"/>
		<col width="220"/>
		<col />
		<tr>
			<td style="text-align: right;">项目：</td>
			<td>
				<input validate="required" type="text" id="bisProjectNameNew" name="bisProjectName" value="${bisProjectName}" style="cursor: pointer; font-size: 12px; color: #ff0000;" />
				<input type="hidden" id="bisProjectIdNew" name="bisProjectId" value="${bisProjectId}"/>
			</td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td style="text-align: right;">楼宇类型：</td>
			<td>
				<s:select validate="required"  cssStyle="width:99%;" list="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" listKey="key" listValue="value" name="floorType"></s:select>
			</td>
			<td style="text-align: right;">子类型：</td>
			<td>
				<select name="subFloorType"  style="width:98%;" >
				    <option value="">--选择--</option>
				    <option value="1" <c:if test="${subFloorType==1}"> selected="selected"</c:if>>mall</option>
				    <option value="2" <c:if test="${subFloorType==2}"> selected="selected"</c:if>>步行街</option>
				    <option value="3" <c:if test="${subFloorType==3}"> selected="selected"</c:if>>公寓</option>
				    <option value="4" <c:if test="${subFloorType==4}"> selected="selected"</c:if>>虚拟商铺分区</option>
				<%--<input type="text" name="subFloorType" value="${subFloorType}" /> 
				
				 --%>
			
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">楼栋名称：</td>
			<td>
				<input validate="required" type="text" name="buildingNum" value="${buildingNum}" />
			</td>
			<td style="text-align: right;">楼层：</td>
			<td>
				<input type="text" name="floorNum" value="${floorNum}" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">小图路径：</td>
			<td>
				<input type="text" name="floorUrl" value="${floorUrl}" />
			</td>
			<td style="text-align: right;">大图路径：</td>
			<td>
				<input type="text" name="bigPicUrl" value="${bigPicUrl}" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">序号：</td>
			<td>
				<input type="text" name="sequenceNo" value="${sequenceNo}" />
			</td>
			<td style="text-align: right;">父楼层：</td>
			<td>
				<input type="text" name="parentId" value="${parentId}" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3" style="padding-top: 10px;">
				<input class="btn_green"  type="button" onclick="doSaveEntity();" value="保存"/>
				<input class="btn_red"  type="button" onclick="doCancel();" value="取消"/>
			</td>
			<td></td>
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