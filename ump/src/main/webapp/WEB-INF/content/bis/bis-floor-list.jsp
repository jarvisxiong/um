<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<style type="text/css">
.btn_red_50_20{
	width: 50px;
	height: 20px;
	line-height: 22px;
	background-color:rgb(172, 39, 39);
	color: #FFF;
	cursor:pointer;
	text-align: center;
}
</style>
<table class="content_table" id="result_table">
	<col width="150"/>
	<col width="100"/>
	<col width="120"/>
	<col />
	<col width="50"/>
	<col width="70"/>
	<thead>
		<tr class="header">
			<th align="left" style="font-weight:bolder;">项目</th>
			<th align="left" style="font-weight:bolder;">楼宇类型</th>
			<th align="left" style="font-weight:bolder;">楼栋名称</th>
			<th align="left" style="font-weight:bolder;">楼层</th>
			<th align="left" style="font-weight:bolder;">序号</th>
			<th align="left" style="font-weight:bolder;">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result">
			<tr class="mainTr" id="main_${bisFloorId}" >	
				<td onclick="doEditEntity('${bisFloorId}');">
					${bisProject.projectName}
				</td>
				<td onclick="doEditEntity('${bisFloorId}');">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="floorType" />
				</td>
				<td onclick="doEditEntity('${bisFloorId}');">${buildingNum}</td>
				<td onclick="doEditEntity('${bisFloorId}');">${floorNum}</td>
				<td onclick="doEditEntity('${bisFloorId}');">${sequenceNo}</td>
				<td align="center">
					<button class="btn_red_50_20" type="button" onclick="doDeleteEntity('${bisFloorId}');">删除</button>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:24px;}
</style>
<div class="table_pager">
	<p:page />
</div>