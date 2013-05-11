<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<form action="${ctx}/bis/bis-project!selectList.action" method="post" id="searchForm">
<input type ="hidden" name ="floorType" id="floorType" value="${floorType}">
<input type ="hidden" name ="bisStoreIdTemps" id="bisStoreIdTemps">
<input type ="hidden" name ="bisStoreNoTemps" id="bisStoreNoTemps">
<table  width="100%" style="font-size:12px; line-height: 30px;" >
	<col width="80"/>
	<col width="150"/>
	<col />
	<tr>
	   <s:if test="floorType==1">
	    	<td align="right">楼层：</td>
	    	<td>
	    		<s:select cssStyle="width:90%;" list="mapFloorSel" listKey="key" listValue="value" id="bisFloorId" name="bisFloorId" onchange="selectFloorNum(this.value);"></s:select>
	        </td>
	    	<td align="left"><input type="button" class="btn_green" value="清空" onclick="clearAll();"></td>
       </s:if>
       <s:else>
       	    <td align="right">楼号：</td>
	    	<td>
	    		<s:select cssStyle="width:90%;" list="mapBuildSel" listKey="key" listValue="value" id="bisFlatId" name="bisFlatId" onchange="selectFloorNum(this.value);"></s:select>
	        </td>
	        <td align="left"><input type="button" class="btn_green" value="清空" onclick="clearAll();"></td>
       </s:else>
     </tr>
</table>
</form>
<div id ="selectList" style="padding: 5px;"></div>