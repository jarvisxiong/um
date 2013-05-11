<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<input id="parentId" type="hidden" />
<input id="ggBisProjectId" type="text" />
			<table>
			<tr>
				<td>平面图说明</td>
				<td>是否一级分类</td>
				<td>更新楼层类别【subFloorType】为1</td>
				<td>父节点</td>
				<td>查看平面图</td>
			</tr>
			<s:iterator value="floors">
			<tr>
				<td>${buildingNum }</td>
				<td><s:if test="subFloorType==0">是</s:if>${subFloorType }</td>
				<td><input type="button" id="updateFloorBt" style="height:30px" onclick="updateFloor('${bisFloorId}')" value="更新"/></td>
				<td>
				<s:select style="height:30px;" list="mapFloor" 
			listKey="key" listValue="value" name="parentId" 
			onchange="$('#parentId').val($(this).val())" ></s:select></td>
				<td><a href="#" onclick="toGGPic('${bisFloorId}')">查看平面图</a></td>
			</tr>
			</s:iterator>
			</table>