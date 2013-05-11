<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;" border="false">
		<form id="inputForm" method="post" action="${ctx}/res/res-option!save.action">
			<input id="resOptionId" type="hidden" name="id" value="${resOptionId}" />
			<input id="optionTypeVal" type="hidden" value="${optionType}">
			<table class="edit_table" style="font-size: 12px;">
				<col width="150"/>
				<col width="300"/>
				<tr>
					<td style="text-align: right;">选项的名称:</td>
					<td><input style="width:100%;" name="optionName" id="optionName" class="easyui-validatebox" required="true"  type="text" value="${optionName}"></input></td>
				</tr>
				<tr>
					<td style="text-align: right;">选项的属性名:</td>
					<td><input style="width:100%;" name="optionIdName" class="easyui-validatebox" required="true" type="text" value="${optionIdName}"></input></td>
				</tr>
				<tr>
					<td style="text-align: right;" valign="top">选项的类型:</td>
					<td>
						<table cellpadding="0" cellspacing="0" style="font-size: 12px;width:100%;">
							<col width="85"/>
							<col width="85"/>
							<col width="85"/>
							<tr>
								<td><input type="checkbox" name="optionType" value="1">单行文本框</td>
								<td><input type="checkbox" name="optionType" value="2">多行文本框</td>
								<td><input type="checkbox" name="optionType" value="3">HTML编辑器<br/></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="optionType" value="4">单选</td>
								<td colspan="2"><input type="checkbox" name="optionType" value="5">多选</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<div>
			<table id="tt2" 
				title="网批选项内容表" singleSelect="true" rownumbers="true"
				idField="resOptionValueId" url="${ctx}/res/res-option!listSub.action?id=${resOptionId}">
			</table>
		</div>
		
	</div>
	<div region="south" border="false" style="height:30px;line-height:30px;">
		<div class="toolbar">
			<s:if test="resOptionId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${resOptionId}');">刷新</a>
			</s:if>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveOption();">保存</a>
			<s:if test="resOptionId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteOptValList();">删除</a>
			</s:if>
			<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editrow('');">新增</a>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	initOptionDataGrid();
	
	// 选中对应的选项类别的checkbox
	var cbxs = $(".edit_table input[type='checkbox']");
	$.each(cbxs, function(k, v) {
		if($(this).val()==$("#optionTypeVal").val()) {
			$(this).attr("checked", true);
		}
	});
	// 设置checkbox为单选
	$.each(cbxs, function() {
		$(this).bind("click", function(){
			var val = $(this).val();
			$.each(cbxs, function(k, v) {
				if($(this).val() != val) {
					$(this).attr("checked", false);
				}
			});
		});
	});

});

</script>