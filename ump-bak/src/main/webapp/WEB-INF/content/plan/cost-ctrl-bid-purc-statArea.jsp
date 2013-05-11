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
		/*margin:0 100px;*/
		cursor: pointer;
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
<!-- 第一个 -->
<table class="stat_table" id="statTable0">
	<col width="100px"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<thead>
		<tr id="statTitle0">
			<th style="line-height:30px;" colspan="10">
				<div style="float:left;" class="stat_bar" onclick="changeMonth('<s:date name="prevMonth" format="yyyy-MM-dd"/>')" title="<s:date name="prevMonth" format="yyyy年MM月"/>">上个月</div>
				<div style="float:right;" class="stat_bar" onclick="changeMonth('<s:date name="nextMonth" format="yyyy-MM-dd"/>')" title="<s:date name="nextMonth" format="yyyy年MM月"/>">下个月</div>
				<div class="stat_title">
					<s:date name="month1" format="yyyy年MM月"/>
					招采报表统计
					<span class="change_month" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){changeStatDate(dp);}})">切换月份</span>
				</div>
			</th>
		</tr>
		<tr>
			<th rowspan="2">部门</th>
			<th rowspan="2">负责人</th>
			<th rowspan="2">未完成</th>
			<th rowspan="2">按期完成</th>
			<th rowspan="2">逾期完成</th>
			<th rowspan="2">总数</th>
			<th rowspan="2">未完成率</th>
			<th colspan="3">完成率</th>
		</tr>
		<tr>
			<th >按期完成率</th>
			<th >逾期完成率</th>
			<th >总完成率</th>
		</tr> 
	</thead>
	<tbody>
		<s:iterator value="costStatisticsArea0">   
			<tr class="data" relArea="${qy}">	
				<s:if test="qy == 'bq' || qy == 'nq' || qy == 'sh' || qy == 'zhb' ">
					<td style="text-align: center;">
						<s:if test="qy == 'bq'">北区招采</s:if>
						<s:elseif test="qy == 'nq'">南区招采</s:elseif>
						<s:elseif test="qy == 'sh'">上海区招采</s:elseif>
						<s:elseif test="qy == 'zhb'">综合部<br/>中心内部任务</s:elseif>
						<s:else>${qy}</s:else>
					</td>
					<td style="font-weight:bold;text-align: center;" title="${uiid}"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				</s:if>
				<s:else>
					<td style="font-weight:bold;text-align: center;" title="${uiid}" colspan="2"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				</s:else>
				
				<td>${wwc}</td>
				<td>${aqwc}</td>
				<td>${yqwc}</td> 
				
				<td>${total}</td> 
				<td>${wwcRate}%</td> 
				<td>${aqwcRate}%</td> 
				<td>${yqwcRate}%</td> 
				<td>${zwcRate}%</td> 
			</tr>	
		</s:iterator> 
		<s:if test="costStatisticsArea0.size==0">
			<tr style="height:80px;">
				<td colspan="18"><div align="center" style="font-weight:bold;">没有相关记录！</div></td>
			</tr>
		</s:if>
	</tbody>
</table>

<br/>
<br/>
<!-- 第二个 -->
<table class="stat_table" id="statTable1">
	<col width="100px"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<thead>
		<tr id="statTitle1">
			<th style="line-height:30px;" colspan="10">
				<%--
				<div style="float:left;" class="stat_bar" onclick="changeMonth('<s:date name="prevMonth" format="yyyy-MM-dd"/>')" title="<s:date name="prevMonth" format="yyyy年MM月"/>">上个月</div>
				<div style="float:right;" class="stat_bar" onclick="changeMonth('<s:date name="nextMonth" format="yyyy-MM-dd"/>')" title="<s:date name="nextMonth" format="yyyy年MM月"/>">下个月</div>
				 --%>
				<div class="stat_title">
					<s:date name="month2" format="yyyy年MM月"/>
					招采报表统计
					<%--
					<span class="change_month" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){changeStatDate(dp);}})">切换月份</span>
					 --%>
				</div>
			</th>
		</tr>
		<tr>
			<th rowspan="2">部门</th>
			<th rowspan="2">负责人</th>
			<th rowspan="2">未完成</th>
			<th rowspan="2">按期完成</th>
			<th rowspan="2">逾期完成</th>
			<th rowspan="2">总数</th>
			<th rowspan="2">未完成率</th>
			<th colspan="3">完成率</th>
		</tr>
		<tr>
			<th >按期完成率</th>
			<th >逾期完成率</th>
			<th >总完成率</th>
		</tr> 
	</thead>
	<tbody>
		<s:iterator value="costStatisticsArea">   
			<tr class="data" relArea="${qy}">	
				<s:if test="qy == 'bq' || qy == 'nq' || qy == 'sh' || qy == 'zhb' ">
					<td style="text-align: center;">
						<s:if test="qy == 'bq'">北区招采</s:if>
						<s:elseif test="qy == 'nq'">南区招采</s:elseif>
						<s:elseif test="qy == 'sh'">上海区招采</s:elseif>
						<s:elseif test="qy == 'zhb'">综合部<br/>中心内部任务</s:elseif>
						<s:else>${qy}</s:else>
					</td>
					<td style="font-weight:bold;text-align: center;" title="${uiid}"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				</s:if>
				<s:else>
					<td style="font-weight:bold;text-align: center;" title="${uiid}" colspan="2"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				</s:else>
				
				<td>${wwc}</td>
				<td>${aqwc}</td>
				<td>${yqwc}</td> 
				
				<td>${total}</td> 
				<td>${wwcRate}%</td> 
				<td>${aqwcRate}%</td> 
				<td>${yqwcRate}%</td> 
				<td>${zwcRate}%</td> 
			</tr>	
		</s:iterator> 
		<s:if test="costStatisticsArea.size==0">
			<tr style="height:80px;">
				<td colspan="18"><div align="center" style="font-weight:bold;">没有相关记录！</div></td>
			</tr>
		</s:if>
	</tbody>
</table>

<br/>
<br/>

<!-- 第三个 -->
<table class="stat_table" id="statTable2">
	<col width="100px"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="10%"/>
	<thead>
		<tr id="statTitle2">
			<th style="line-height:30px;" colspan="10">
			<%--
				<div style="float:left;" class="stat_bar" onclick="changeMonth('<s:date name="currMonth" format="yyyy-MM-dd"/>')" title="<s:date name="currMonth" format="yyyy年MM月"/>">上个月</div>
				<div style="float:right;" class="stat_bar" onclick="changeMonth('<s:date name="nextMonth2" format="yyyy-MM-dd"/>')" title="<s:date name="nextMonth2" format="yyyy年MM月"/>">下个月</div>
			 --%>
				<div class="stat_title">
					<s:date name="month3" format="yyyy年MM月"/>
					招采报表统计
					<%--
					<span class="change_month" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){changeStatDate(dp);}})">切换月份</span>
					 --%>
				</div>
			</th>
		</tr>
		<tr>
			<th rowspan="2">部门</th>
			<th rowspan="2">负责人</th>
			<th rowspan="2">未完成</th>
			<th rowspan="2">按期完成</th>
			<th rowspan="2">逾期完成</th>
			<th rowspan="2">总数</th>
			<th rowspan="2">未完成率</th>
			<th colspan="3">完成率</th>
		</tr>
		<tr>
			<th >按期完成率</th>
			<th >逾期完成率</th>
			<th >总完成率</th>
		</tr> 
	</thead>
	<tbody>
		<s:iterator value="costStatisticsArea2">   
			<tr class="data" relArea="${qy}">	
				<s:if test="qy == 'bq' || qy == 'nq' || qy == 'sh' || qy == 'zhb' ">
					<td style="text-align: center;">
						<s:if test="qy == 'bq'">北区招采</s:if>
						<s:elseif test="qy == 'nq'">南区招采</s:elseif>
						<s:elseif test="qy == 'sh'">上海区招采</s:elseif>
						<s:elseif test="qy == 'zhb'">综合部<br/>中心内部任务</s:elseif>
						<s:else>${qy}</s:else>
					</td>
					<td style="font-weight:bold;text-align: center;" title="${uiid}"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				</s:if>
				<s:else>
					<td style="font-weight:bold;text-align: center;" title="${uiid}" colspan="2"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) %></td>
				</s:else>
				
				<td>${wwc}</td>
				<td>${aqwc}</td>
				<td>${yqwc}</td> 
				
				<td>${total}</td> 
				<td>${wwcRate}%</td> 
				<td>${aqwcRate}%</td> 
				<td>${yqwcRate}%</td> 
				<td>${zwcRate}%</td> 
			</tr>	
		</s:iterator> 
		<s:if test="costStatisticsArea2.size==0">
			<tr style="height:80px;">
				<td colspan="18"><div align="center" style="font-weight:bold;">没有相关记录！</div></td>
			</tr>
		</s:if>
	</tbody>
</table>
</div>
<script type="text/javascript">

	$(function(){
		//第一月
		$.each( ['bq','nq','sh','zhb'], function(i, n){
			var size = $('#statTable0 tr[relArea="'+n+'"]').length;
			$('#statTable0 tr[relArea="'+n+'"]').each(function(i,n){
				if(i == 0){
					$(this).find("td:eq(0)").attr("rowspan",size);
				}else{
					$(this).find("td:eq(0)").remove();
				}
			});
		});
		//第二月
		$.each( ['bq','nq','sh','zhb'], function(i, n){
			var size = $('#statTable1 tr[relArea="'+n+'"]').length;
			$('#statTable1 tr[relArea="'+n+'"]').each(function(i,n){
				if(i == 0){
					$(this).find("td:eq(0)").attr("rowspan",size);
				}else{
					$(this).find("td:eq(0)").remove();
				}
			});
		});
		//第三月
		$.each( ['bq','nq','sh','zhb'], function(i, n){
			var size = $('#statTable2 tr[relArea="'+n+'"]').length;
			$('#statTable2 tr[relArea="'+n+'"]').each(function(i,n){
				if(i == 0){
					$(this).find("td:eq(0)").attr("rowspan",size);
				}else{
					$(this).find("td:eq(0)").remove();
				}
			});
		}); 
		
		$.each( ['0','1','2'], function(i, n){
			$('#statTitle'+n).toggle(
				function(){
					$('#statTitle'+n).attr('title','点击展开明细');
					$('#statTitle'+n).nextAll().hide();
					$('#statTable'+n+' tbody').hide();
				},
				function(){
					$('#statTitle'+n).attr('title','点击收起明细');
					$('#statTitle'+n).nextAll().show();
					$('#statTable'+n+' tbody').show();
				}
			);
		});
		$('#statTitle1').click();
		$('#statTitle2').click();
		
		/*
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
		*/
		
		//计算合计行的未完成百分比
		/*
		var wwcTotal = totalTr.find('td:eq(13)').text();
		var allTotal = parseInt(totalTr.find('td:eq(13)').text())+parseInt(totalTr.find('td:eq(14)').text())
						+parseInt(totalTr.find('td:eq(15)').text())+parseInt(totalTr.find('td:eq(16)').text());
		var allPer = 0;
		if(allTotal !=0){
			allPer = (wwcTotal/allTotal)*100;
			allPer = Math.round(allPer)+'%';
		}
		totalTr.find('td:eq(17)').text(allPer);
		*/
		
	});
</script>