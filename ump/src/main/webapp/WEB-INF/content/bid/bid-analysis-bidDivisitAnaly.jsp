<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="divisionList"  style="width:100%;clear: both;overflow-x:auto;" >
<table  class="bidTable" style="border: 0px none; border-collapse: separate;" cellspacing="0" cellpadding="0">
	<thead>
	
		<tr>
			<th style="background: none;width: 30px;">序号</th>
			<th width="90">项目编号</th>
			<th width="60">项目名称</th>
			<th>项目特征描述</th>
			<th width="60">计量单位</th>
			<th width="60">工程量</th>
			<th width="35">第${batchOne}轮</th>
			<th width="35">第${batchTwo}轮</th>
		</tr>
	</thead>
	<tbody>
	 	<s:iterator  value="divisionList.result"   status="index" >
	 		<tr>
	 		<td align="center"  style="text-align: center;"><s:property value="#index.index+1"/></td>
	 			<td  title="${itemNo}"><div class='ellipsisDiv analysisTd' >${itemNo}</div></td>
	 			<td  title="${itemName}"><div  class='ellipsisDiv analysisTd'>${itemName}</div></td>
	 			<td  title="${itemDesc}"><div  class='ellipsisDiv analysisTd' >${itemDesc}</div></td>
	 			<td  title="${unitDesc}"><div  class='ellipsisDiv analysisTd' >${unitDesc}</div></td>
	 			<td  title="${quantitie}"><div  class='ellipsisDiv analysisTd' >${quantitie}</div></td>
	 			<s:if test="batchNo==batchOne">
	 			<td  title="有"><div  class='ellipsisDiv analysisTd' ><font color="green">有</font></div></td>
	 			</s:if>
	 			<s:else>
	 			<td  title="无"><div  class='ellipsisDiv analysisTd' ><font color="red">无</font></div></td>
	 			</s:else>
	 			<s:if test="batchNo==batchTwo">
	 			<td  title="有"><div  class='ellipsisDiv analysisTd' ><font color="green">有</font></div></td>
	 			</s:if>
	 			<s:else>
	 			<td  title="无"><div  class='ellipsisDiv analysisTd' ><font color="red">无</font></div></td>
	 			</s:else>
	 		</tr>
	 	</s:iterator> 	
	</tbody>
</table>
</div>