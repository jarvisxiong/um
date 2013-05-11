<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

	<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
		<thead>
		<tr class="header">
			<th width="35"   class="thNoLine">序号</th>
			<th   width="100" >项目编号</th>
			<th  >项目名称</th>
			<th  >项目特征描述</th>
			<th  >计量单位</th>
			<th   >工程量</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="bidDivisionSupRelRs"  var="bidDivisiton"  status="index">
			<tr class="commonTr">
				<td  align="center" style="text-align: center;" ><s:property value="#index.index+1"/></td>			
				<td  title='${itemNo}' ><div style="padding-left: 5px;">${itemNo}</div></td>
				<td  title='${itemName}' ><div  class="ellipsisDiv exceltableContent" >${itemName}</div></td>
				<td  title='${itemDesc}'><div  class="ellipsisDiv exceltableContent" >${itemDesc}</div></td>
				<td  title='${measurement}' ><div   class="ellipsisDiv exceltableContent" >${measurement}</div></td>
				<td  title='${quantitie}' ><div   class="ellipsisDiv exceltableContent" >${quantitie}</div></td>
			</tr>
		</s:iterator>
		</tbody>
</table>
<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-division-sup-rel!unimportedData.action">
			<p:page  pageInfo="voPage"/>
</div>

				