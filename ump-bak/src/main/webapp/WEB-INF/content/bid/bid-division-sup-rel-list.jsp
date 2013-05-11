<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<table class="bidTable" style="border-collapse: separate;border: 0px;" cellspacing="0" cellpadding="0" >
	<colgroup>
		<col style="width:45px"/>
		<col style="width:100px"/>
		<col style="width:100px"/>
		<col/>
		<col style="width:100px"/>
		<col style="width:100px"/>
		<col style="width:100px"/>
		<col style="width:100px"/>
	</colgroup>
	<thead>
	<tr class="header">
		<th class="thNoLine">序号</th>
		<th>项目编号</th>
		<th>项目名称</th>
		<th>项目特征描述</th>
		<th style="text-align: center;">计量单位</th>
		<th>工程量</th>
		<th>综合单价(元)</th>
		<th>合计(元)</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidDivisionSupRelRs"  var="bidDivisiton"  status="index">
		<tr>
			<td  align="center" style="text-align: center;" ><s:property value="#index.index+1"/></td>			
			<td  title='${itemNo}' ><div style="padding-left: 5px;">${itemNo}</div></td>
			<td  title='${itemName}' ><div class="decorate" >${itemName}</div></td>
			<td  title='${itemDesc}'><div class="decorate" ><div>${itemDesc}</div></div></td>
			<td  title='${measurement}' ><div class="decorate" style="text-align: center;">${measurement}</div></td>
			<td  title='${quantitie}' ><div class="decorate" >${quantitie}</div></td>
			<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
				<td  title='***' ><div class="decorate" >***</div></td>
				<td  title='***' ><div class="decorate" >***</div></td>
			</s:if>
			<s:else>
				<td  title='${compUnitAmt}' ><div class="decorate" >${compUnitAmt}</div></td>
				<td  title='${totalAmt}' ><div class="decorate" >${totalAmt}</div></td>
			</s:else>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-division-sup-rel!list.action">
	<p:page  pageInfo="voPage"/>
</div>

				