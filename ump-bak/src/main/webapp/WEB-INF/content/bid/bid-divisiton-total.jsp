<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

	<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
		<thead>
		<tr class="header">
			<th style="width:35px;"  class="thNoLine">序号</th>
			<th  style="width:20%;text-align: left;" >项目名称</th>
			<th   style="width:20%text-align: left;">金额汇总</th>			
		</tr>
		</thead>
		<tbody>
			<s:iterator value="perSupTotalVoList"  var="perSupTotalVo"  status="index">
				<tr class="commonTr">
					<td  align="center" style="text-align: center;"><s:property value="#index.index+1"/></td>			
					<td  title='${tableName}' ><div class="ellipsisDiv exceltableContent" >${tableName}</div></td>
					<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
					<td title='***' totalValue="totalValue"><div class="ellipsisDiv exceltableContent" >***</div></td>
					</s:if>
					<s:else>
					<td title='${totalValue}' totalValue="totalValue"><div class="ellipsisDiv exceltableContent" >${totalValue}</div></td>
					</s:else>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="2" align="right"><font color="red" style="float: right;">总金额:</font></td>
				<td colspan="2" align="right"><div id="totalAll" style="float:left; margin-left: 10px;"></div>
			</tr>
		</tbody>
</table>
<script>
$(function() {
	var t=0;
	var status = ${bidLedger.bidStatusCd};
	if(status<3){
		$("#totalAll").html("<font color='red'>***</font>");
	}
	else{
		$(".bidTable tbody tr").each(function(){
			var v=$(this).find("td[totalValue='totalValue']").children().eq(0).html();
			if(v=='null'||v==null){
			}
			else{
				t+=parseFloat(v);
			}
		});
		$("#totalAll").html("<font color='red'>"+t.toFixed(2)+"</font>");
	}
});
</script>


				