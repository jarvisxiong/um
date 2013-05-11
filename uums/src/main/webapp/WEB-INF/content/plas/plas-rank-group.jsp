<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="dataList.size() > 0">
	<div  class="datagrid-body">
	<table style="width:100%;border:1px solid #99BBE8" cellpadding="0" cellspacing="0">
		<col width="120px;"/>
		<col width="60px;"/>
		<tr class="panel-header">
			<td style="padding-left:5px;line-height: 22px;">职级名称</td>
			<td style="padding-left:5px;line-height: 22px;text-align: left">序号</td>
		</tr>
		<s:iterator value="dataList">
		<tr class="rank_group_row" style="cursor:pointer;" title="点击分配组员">
			<td>
				<div class="datagrid-cell"  id="group_${dictCd}" onclick="viewGroup('${dictCd}','${dictName}','${remark }','${updatedDeptCd }','${updatedDate }');">${dictName}</div>
			</td>
			<td style="padding-left:5px;text-align: left">
				${dictCd}
			<%--
				<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="viewGroup('${dictCd}','${dictName}','${remark }','${updatedDeptCd }','${updatedDate }')">选择分配</a>
			 --%>
			</td>
		</tr>
		</s:iterator>
	</table>
	</div>
</s:if>
<s:else>
 	暂未配置标签
</s:else>

<script type="text/javascript">

	$(function(){
		$(".rank_group_row").bind("click", function(){
			$(".rank_group_row").css({'background-color':''});
			$(this).css({'background-color':'#FBEC88'});
		});
	});
</script>