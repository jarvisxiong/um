<%@page import="java.util.Date"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<style type="text/css">
<!--
	.stat_table{
		color:#464646;
		table-layout:fixed;
		width:100%;
	}
	.stat_table th{
		border:1px solid #aaabb0;
		background-color: #E4E7EC;
		line-height:20px;
	}
	.stat_table td{
		text-align:right;
		border:1px solid #aaabb0;
		padding-right:5px;
		line-height:26px;
	}
	.stat_table .total td{
		font-weight:bold;
	}
	.stat_title{
		text-align:center;
		font-size:16px;
		font-weight:bold;
		margin:0 100px;
	}
	.stat_bar{
		width:100px;
		font-size:12px;
		text-decoration: underline;
		cursor: pointer;
	}
	.change_month{
		font-size:12px;
		font-weight:normal;
		color:#0693e3;
		text-decoration: underline;
		cursor: pointer;
	}
-->
</style>

<!-- 招采统计页面 -->
<div style="margin:50px 20px;">
<table class="stat_table" id="statTable">
	<col width="10%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="5%"/>
	<col width="10%"/>
	<thead>
		<tr>
			<th style="line-height:30px;" colspan="18">
				<div style="float:left;" class="stat_bar" onclick="changeMonth('<s:date name="prevMonth" format="yyyy-MM-dd"/>')" title="<s:date name="prevMonth" format="yyyy年MM月"/>">上个月</div>
				<div style="float:right;" class="stat_bar" onclick="changeMonth('<s:date name="nextMonth" format="yyyy-MM-dd"/>')" title="<s:date name="nextMonth" format="yyyy年MM月"/>">下个月</div>
				<div class="stat_title" >
					<s:date name="currMonth" format="yyyy年MM月"/>
					招采报表统计
					<span class="change_month" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){changeStatDate(dp);}})">切换月份</span>
				</div>
			</th>
		</tr>
		<tr>
			<th rowspan="2">负责人</th>
			<th colspan="4">北区招采计划</th>
			<th colspan="4">南区区招采计划</th>
			<th colspan="4">上海区招采计划</th>
			<th colspan="5">合计</th>
		</tr>
		<tr>
			<th>未完成</th>
			<th>等待</th>
			<th>按期完成</th>
			<th>逾期完成</th>
			<th>未完成</th>
			<th>等待</th>
			<th>按期完成</th>
			<th>逾期完成</th>
			<th>未完成</th>
			<th>等待</th>
			<th>按期完成</th>
			<th>逾期完成</th>
			<th>未完成</th>
			<th>等待</th>
			<th>按期完成</th>
			<th>逾期完成</th>
			<th>未完成率</th>
		</tr>
	</thead>
	<tbody>
		
		<s:iterator value="costStatistics">   
			<tr class="data">	
				<td style="font-weight:bold;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				<td>${nwwc}</td>
				<td>${ndd}</td>
				<td>${naqwc}</td>
				<td>${nyqwc}</td>
				<td>${swwc}</td>
				<td>${sdd}</td>
				<td>${saqwc}</td>
				<td>${syqwc}</td>
				<td>${shwwc}</td>
				<td>${shdd}</td>
				<td>${shaqwc}</td>
				<td>${shyqwc}</td>
				<c:set value="${nwwc+swwc+shwwc}" var="totalWwc"></c:set>
				<c:set value="${nwwc+swwc+shwwc+ndd+sdd+shdd+naqwc+saqwc+shaqwc+nyqwc+syqwc+shyqwc}" var="total"></c:set>
				<td wwc="${totalWwc}" total="${total}">${totalWwc}</td>
				<td>${ndd+sdd+shdd}</td>
				<td>${naqwc+saqwc+shaqwc}</td>
				<td>${nyqwc+syqwc+shyqwc}</td>
				<td><span style="font-weight:bold;">0</span></td>
			</tr>	
		</s:iterator>
		<s:if test="costStatistics.size>0">
			<tr class="total">	
				<td>合计</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
			</tr>	
		</s:if>
		<s:if test="costStatistics.size==0">
			<tr style="height:80px;">
				<td colspan="18"><div align="center" style="font-weight:bold;">没有相关记录！</div></td>
			</tr>
		</s:if>
	</tbody>
</table>
</div>
<script type="text/javascript">
	var dataTr = $('#statTable tr.data');
	var totalTr = $('#statTable tr.total');
	
	//第2列到第17列需要进行合计
	for(var i=1;i<17;i++){
		sumTd(i);
	}
	//统计指定列的总和
	function sumTd(index){
		var total = 0;
		dataTr.find('td:eq('+index+')').each(function(i,n){
			total += parseInt($(n).text());
		});
		totalTr.find('td:eq('+index+')').text(total);
	}
	
	//计算每一行的未完成百分比
	dataTr.each(function(i,n){
		var wwcTd = $(n).find('td:eq(13)');
		var wwc = parseInt(wwcTd.attr('wwc'));
		var total = parseInt(wwcTd.attr('total'));
		var per = 0;
		if(total != 0){
			per = (wwc/total)*100;
			per = Math.round(per)+'%';
		}
		$(n).find('td:eq(17) span').text(per);
	});
	
	//计算合计行的未完成百分比
	var wwcTotal = totalTr.find('td:eq(13)').text();
	var allTotal = parseInt(totalTr.find('td:eq(13)').text())+parseInt(totalTr.find('td:eq(14)').text())
					+parseInt(totalTr.find('td:eq(15)').text())+parseInt(totalTr.find('td:eq(16)').text());
	var allPer = 0;
	if(allTotal !=0){
		allPer = (wwcTotal/allTotal)*100;
		allPer = Math.round(allPer)+'%';
	}
	totalTr.find('td:eq(17)').text(allPer);
</script>