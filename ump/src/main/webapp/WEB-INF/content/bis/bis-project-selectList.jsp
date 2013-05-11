<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<form action="${ctx}/bis/bis-project!selectList.action" method="post" id="searchForm">
	<input type ="hidden" name ="floorType" id="floorType" value="${floorType}">
	<table class="mainTable" width="100%" style="font-size:12px;" >
		<col width="30%" />
		<col />
		<tr>
			<th scope="col" align="center" width="4"></th>
			<s:if test="floorType==1||floorType==2">
				<th scope="col" align="center" width="4">编号</th>
			</s:if>
			<s:else>
				<th scope="col" align="center" width="4">多经名称</th>
			</s:else>
		</tr>
		<s:if test="floorType==1">
			<s:iterator value="bisStoreLis" status="s">
				<tr class="mainTr">
					<td align="center">
						<input type="checkbox" id="storeBox_${bisStoreId}" name="${bisStoreId}"  value="${square}`${innerSquare}`${rentSquare}"  onclick="saveTempId(this.value,'${bisStoreId}','${storeNo}');" /> 
					</td>
					<td align="center">${storeNo}</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:elseif test="floorType==2">
			<s:iterator value="bisFlatLis">
				<tr class="mainTr">
					<td align="center">
						<input type="checkbox" id="storeBox_${bisFlatId}" storeno="${flatNo}," storeid="${bisFlatId}," onclick="saveTempIdFlat('${bisFlatId}','${flatNo}');"/> 
					</td>
					<td align="center">${flatNo}</td>
				</tr>
			</s:iterator>
		</s:elseif>
		<s:else>
			<s:iterator value="bisMultiLis">
				<tr class="mainTr">
					<td align="center">
						<input type="checkbox" id="multiBox_${bisMultiId}"  onclick="saveMultiId('${bisMultiId}','${multiName}');"/> 
					</td>
					<td align="center">${multiName}</td>
				</tr>
			</s:iterator>
			</s:else>
	</table>

</form>