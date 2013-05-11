<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%--规费项目清单与计价表 --%>
	<table class="bidTable"  style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
		<thead>
		<tr class="header">
			<th width="35"  style="background-image:url('');">序号</th>
						
			<th>(措)项目名称 </th>
			<th>计算基础</th>
			<th>费率</th>
			<th>金额(元)</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="bidFeesSupRelRs.result"  var="bidFeesSupRel"  status="index">
		<tr class="commonTr">
			<td  align="center" style="text-align: center;"><s:property value="#index.index+1"/></td>	
			<td  title='${itemName}'><div  class="ellipsisDiv">${itemName}</div></td>
			<td  title='${calcBasicDesc}'><div   class="ellipsisDiv">${calcBasicDesc}</div></td>
			<td  title='${rate}'><div   class="ellipsisDiv">${rate}</div></td>
			<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
				<td  title='***'><div  class="ellipsisDiv">***</div></td>
			</s:if>
			<s:else>
				<td  title='${amt}'><div  class="ellipsisDiv">${amt}</div></td>
			</s:else>
			
		</tr>
		</s:iterator>
		</tbody>
</table>
<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-fees-sup-rel!list.action">
			<p:page />
</div>
				