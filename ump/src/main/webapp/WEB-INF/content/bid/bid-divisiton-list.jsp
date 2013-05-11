<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0">
	<colgroup>
		<col style="width:45px"/>
		<col style="width:100px"/>
		<col style="width:100px"/>
		<col />
		<col style="width:80px"/>
		<col style="width:100px"/>
	</colgroup>
	<thead>
	<tr class="header">
		<th class="thNoLine"><div class="decorate">序号</div></th>
		<th><div class="decorate">项目编号</div></th>
		<th><div class="decorate">项目名称</div></th>
		<th><div class="decorate">项目特征描述</div></th>
		<th><div class="decorate" style="text-align: center;">计量单位</div></th>
		<th><div class="decorate">工程量</div></th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidDivisitonRs.result"  var="bidDivisiton"  status="index">
		<tr>
			<td align="center" style="text-align: center;"><s:property value="#index.index+1"/></td>
			<td align="left"  style="text-align: left;" title='${itemNo}'><div class="decorate" style="padding-left: 5px;">${itemNo}</div></td>
			<td align="left"  title='${itemName}'><div class="decorate">${itemName}</div></td>
			<td align="left"  title='${itemDesc}'><div class="decorate">${itemDesc}</div></td>
			<td align="left"  title='${unitDesc}' style="text-align: center;"><div class="decorate">${unitDesc}</div></td>
			<td align="left"  title='${quantitie}'><div class="decorate">${quantitie}</div></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-divisiton!list.action">
	<p:page />
</div>

				