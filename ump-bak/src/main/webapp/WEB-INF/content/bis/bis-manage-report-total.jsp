<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!-- ${resultMap['1']} -->
<table style="width: 100%;" align="center" >
	<tr>
		<td valign="top">
			<table id="tableLeft" class="mTable" cellpadding="0" cellspacing="0">
				<col width="180px">
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<col width="50px"/>
				<tr>
					<td nowrap="nowrap" class="toptr" style="text-align: center;">${reportDate}</td>
					<td nowrap="nowrap" class="toptr" style="text-align: center; background-color:#FDE9D9;" colspan="6" >已开业项目合计</td>
					<td nowrap="nowrap" class="toptr" style="text-align: center; background-color:#DBEEF3;" colspan="6" >未开业项目合计</td>
				</tr>
				<tr class="headTr">
					<td>集团资金流量表</td>
					<td nowrap="nowrap" colspan="3" >月</td>
					<td nowrap="nowrap" colspan="3" style="border-left:0px;">本年度</td>
					<td nowrap="nowrap" colspan="3" >月</td>
					<td nowrap="nowrap" colspan="3" style="border-left:0px;">本年度</td>
				</tr>
				<tr>
					<th nowrap="nowrap" style="text-align: left;padding-left:5px;">(单位：万元)</th>
					<th nowrap="nowrap" style="text-align: center;">预算</th>
					<th nowrap="nowrap" style="text-align: center;">实收</th>
					<th nowrap="nowrap" style="text-align: center;">完成率</th>
					<th nowrap="nowrap" style="text-align: center;">预算</th>
					<th nowrap="nowrap" style="text-align: center;">实收</th>
					<th nowrap="nowrap" style="text-align: center;">完成率</th>
					<th nowrap="nowrap" style="text-align: center;">预算</th>
					<th nowrap="nowrap" style="text-align: center;">实收</th>
					<th nowrap="nowrap" style="text-align: center;">完成率</th>
					<th nowrap="nowrap" style="text-align: center;">预算</th>
					<th nowrap="nowrap" style="text-align: center;">实收</th>
					<th nowrap="nowrap" style="text-align: center;">完成率</th>
				</tr>
				<tr myid="1" ifHasSub="1">
					<td class="indent_1" style="text-align:left;">1&nbsp;资金流入合计<span class="triangle"/></td>
					<td><!--<s:if test="resultMap['A_1-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_1-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.1" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.1&nbsp;物业管理费收入</td>
					<td><!--<s:if test="resultMap['A_1_1-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_1-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_1-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_1-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_1-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_1-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_1-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_1-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_1-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_1-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_1-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_1-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_1-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_1-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_1-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_1-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_1-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_1-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_1-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_1-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_1-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_1-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_1-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_1-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.2" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.2&nbsp;停车场收入<span class="triangle"/></td>
					<td><!--<s:if test="resultMap['A_1_2-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_2-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_2-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_2-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_2-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_2-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_2-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_2-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_2-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_2-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_2-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_2-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_2-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_2-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_2-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_2-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_2-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_2-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_2-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_2-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_2-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_2-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_2-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_2-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.2.1" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.2.1&nbsp;车位管理费收入</td>
					<td><!--<s:if test="resultMap['19-1'].moneyMust==null">0</s:if><s:else>${resultMap['19-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['19-1'].moneyFact==null">0</s:if><s:else>${resultMap['19-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['19-1'].moneyRate==null">0</s:if><s:else>${resultMap['19-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['19-2'].moneyMust==null">0</s:if><s:else>${resultMap['19-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['19-2'].moneyFact==null">0</s:if><s:else>${resultMap['19-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['19-2'].moneyRate==null">0</s:if><s:else>${resultMap['19-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['19-3'].moneyMust==null">0</s:if><s:else>${resultMap['19-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['19-3'].moneyFact==null">0</s:if><s:else>${resultMap['19-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['19-3'].moneyRate==null">0</s:if><s:else>${resultMap['19-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['19-4'].moneyMust==null">0</s:if><s:else>${resultMap['19-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['19-4'].moneyFact==null">0</s:if><s:else>${resultMap['19-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['19-4'].moneyRate==null">0</s:if><s:else>${resultMap['19-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.2.2" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.2.2&nbsp;临时停车收入</td>
					<td><!--<s:if test="resultMap['17-1'].moneyMust==null">0</s:if><s:else>${resultMap['17-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['17-1'].moneyFact==null">0</s:if><s:else>${resultMap['17-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['17-1'].moneyRate==null">0</s:if><s:else>${resultMap['17-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['17-2'].moneyMust==null">0</s:if><s:else>${resultMap['17-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['17-2'].moneyFact==null">0</s:if><s:else>${resultMap['17-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['17-2'].moneyRate==null">0</s:if><s:else>${resultMap['17-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['17-3'].moneyMust==null">0</s:if><s:else>${resultMap['17-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['17-3'].moneyFact==null">0</s:if><s:else>${resultMap['17-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['17-3'].moneyRate==null">0</s:if><s:else>${resultMap['17-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['17-4'].moneyMust==null">0</s:if><s:else>${resultMap['17-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['17-4'].moneyFact==null">0</s:if><s:else>${resultMap['17-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['17-4'].moneyRate==null">0</s:if><s:else>${resultMap['17-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.3" ifHasSub="0" style="display:none;"><!-- <span class="triangledown"/> -->
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.3&nbsp;多种经营收入<span class="triangle"/></td>
					<td><!--<s:if test="resultMap['A_1_3-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_3-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_3-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_3-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.3.1" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.1&nbsp;广告场地使用收入</td>
					<td><!--<s:if test="resultMap['16-1'].moneyMust==null">0</s:if><s:else>${resultMap['16-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['16-1'].moneyFact==null">0</s:if><s:else>${resultMap['16-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['16-1'].moneyRate==null">0</s:if><s:else>${resultMap['16-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['16-2'].moneyMust==null">0</s:if><s:else>${resultMap['16-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['16-2'].moneyFact==null">0</s:if><s:else>${resultMap['16-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['16-2'].moneyRate==null">0</s:if><s:else>${resultMap['16-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['16-3'].moneyMust==null">0</s:if><s:else>${resultMap['16-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['16-3'].moneyFact==null">0</s:if><s:else>${resultMap['16-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['16-3'].moneyRate==null">0</s:if><s:else>${resultMap['16-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['16-4'].moneyMust==null">0</s:if><s:else>${resultMap['16-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['16-4'].moneyFact==null">0</s:if><s:else>${resultMap['16-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['16-4'].moneyRate==null">0</s:if><s:else>${resultMap['16-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.3.2" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.2&nbsp;服务费收入</td>
					<td><!--<s:if test="resultMap['20-1'].moneyMust==null">0</s:if><s:else>${resultMap['20-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['20-1'].moneyFact==null">0</s:if><s:else>${resultMap['20-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['20-1'].moneyRate==null">0</s:if><s:else>${resultMap['20-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['20-2'].moneyMust==null">0</s:if><s:else>${resultMap['20-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['20-2'].moneyFact==null">0</s:if><s:else>${resultMap['20-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['20-2'].moneyRate==null">0</s:if><s:else>${resultMap['20-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['20-3'].moneyMust==null">0</s:if><s:else>${resultMap['20-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['20-3'].moneyFact==null">0</s:if><s:else>${resultMap['20-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['20-3'].moneyRate==null">0</s:if><s:else>${resultMap['20-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['20-4'].moneyMust==null">0</s:if><s:else>${resultMap['20-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['20-4'].moneyFact==null">0</s:if><s:else>${resultMap['20-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['20-4'].moneyRate==null">0</s:if><s:else>${resultMap['20-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.3.3" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.3&nbsp;维修服务收入</td>
					<td><!--<s:if test="resultMap['21-1'].moneyMust==null">0</s:if><s:else>${resultMap['21-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['21-1'].moneyFact==null">0</s:if><s:else>${resultMap['21-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['21-1'].moneyRate==null">0</s:if><s:else>${resultMap['21-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['21-2'].moneyMust==null">0</s:if><s:else>${resultMap['21-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['21-2'].moneyFact==null">0</s:if><s:else>${resultMap['21-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['21-2'].moneyRate==null">0</s:if><s:else>${resultMap['21-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['21-3'].moneyMust==null">0</s:if><s:else>${resultMap['21-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['21-3'].moneyFact==null">0</s:if><s:else>${resultMap['21-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['21-3'].moneyRate==null">0</s:if><s:else>${resultMap['21-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['21-4'].moneyMust==null">0</s:if><s:else>${resultMap['21-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['21-4'].moneyFact==null">0</s:if><s:else>${resultMap['21-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['21-4'].moneyRate==null">0</s:if><s:else>${resultMap['21-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.3.4" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.4&nbsp;清洁服务收入</td>
					<td><!--<s:if test="resultMap['22-1'].moneyMust==null">0</s:if><s:else>${resultMap['22-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['22-1'].moneyFact==null">0</s:if><s:else>${resultMap['22-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['22-1'].moneyRate==null">0</s:if><s:else>${resultMap['22-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['22-2'].moneyMust==null">0</s:if><s:else>${resultMap['22-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['22-2'].moneyFact==null">0</s:if><s:else>${resultMap['22-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['22-2'].moneyRate==null">0</s:if><s:else>${resultMap['22-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['22-3'].moneyMust==null">0</s:if><s:else>${resultMap['22-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['22-3'].moneyFact==null">0</s:if><s:else>${resultMap['22-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['22-3'].moneyRate==null">0</s:if><s:else>${resultMap['22-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['22-4'].moneyMust==null">0</s:if><s:else>${resultMap['22-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['22-4'].moneyFact==null">0</s:if><s:else>${resultMap['22-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['22-4'].moneyRate==null">0</s:if><s:else>${resultMap['22-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.3.5" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.5&nbsp;其他收入</td>
					<td><!--<s:if test="resultMap['A_1_3_5-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3_5-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3_5-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3_5-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3_5-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3_5-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_3_5-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3_5-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3_5-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3_5-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3_5-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3_5-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_3_5-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3_5-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3_5-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3_5-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3_5-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3_5-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_3_5-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_3_5-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_3_5-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3_5-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_3_5-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_3_5-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.4" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.4&nbsp;招商佣金收入<span class="triangle"/></td>
					<td><!--<s:if test="resultMap['A_1_4-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_4-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_4-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_4-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_4-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_4-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_4-2'A_1_4-neyMust==null">0</s:if><s:else>${resultMap['A_1_4-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_4-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_4-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_4-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_4-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_4-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_4-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_4-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_4-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_4-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_4-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_4-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_4-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_4-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_4-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_4-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_4-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.4.1" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.4.1&nbsp;招商代理费</td>
					<td><!--<s:if test="resultMap['2-1'].moneyMust==null">0</s:if><s:else>${resultMap['2-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['2-1'].moneyFact==null">0</s:if><s:else>${resultMap['2-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['2-1'].moneyRate==null">0</s:if><s:else>${resultMap['2-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['2-2'].moneyMust==null">0</s:if><s:else>${resultMap['2-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['2-2'].moneyFact==null">0</s:if><s:else>${resultMap['2-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['2-2'].moneyRate==null">0</s:if><s:else>${resultMap['2-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['2-3'].moneyMust==null">0</s:if><s:else>${resultMap['2-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['2-3'].moneyFact==null">0</s:if><s:else>${resultMap['2-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['2-3'].moneyRate==null">0</s:if><s:else>${resultMap['2-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['2-4'].moneyMust==null">0</s:if><s:else>${resultMap['2-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['2-4'].moneyFact==null">0</s:if><s:else>${resultMap['2-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['2-4'].moneyRate==null">0</s:if><s:else>${resultMap['2-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.4.2" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.4.2&nbsp;租金管理费</td>
					<td><!--<s:if test="resultMap['18-1'].moneyMust==null">0</s:if><s:else>${resultMap['18-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['18-1'].moneyFact==null">0</s:if><s:else>${resultMap['18-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['18-1'].moneyRate==null">0</s:if><s:else>${resultMap['18-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['18-2'].moneyMust==null">0</s:if><s:else>${resultMap['18-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['18-2'].moneyFact==null">0</s:if><s:else>${resultMap['18-2'].moneyFact}</s:else></18->
					<td><!--<s:if test="resultMap['18-2'].moneyRate==null">0</s:if><s:else>${resultMap['18-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['18-3'].moneyMust==null">0</s:if><s:else>${resultMap['18-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['18-3'].moneyFact==null">0</s:if><s:else>${resultMap['18-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['18-3'].moneyRate==null">0</s:if><s:else>${resultMap['18-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['18-4'].moneyMust==null">0</s:if><s:else>${resultMap['18-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['18-4'].moneyFact==null">0</s:if><s:else>${resultMap['18-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['18-4'].moneyRate==null">0</s:if><s:else>${resultMap['18-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.5" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.5&nbsp;委托租赁收入（返租收入）</td>
					<td><!--<s:if test="resultMap['B29-1'].moneyMust==null">0</s:if><s:else>${resultMap['B29-'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B29-1'].moneyFact==null">0</s:if><s:else>${resultMap['B29-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B29-1'].moneyRate==null">0</s:if><s:else>${resultMap['B29-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B29-2'].moneyMust==null">0</s:if><s:else>${resultMap['B29-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B29-2'].moneyFact==null">0</s:if><s:else>${resultMap['B29-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B29-2'].moneyRate==null">0</s:if><s:else>${resultMap['B29-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B29-3'].moneyMust==null">0</s:if><s:else>${resultMap['B29-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B29-3'].moneyFact==null">0</s:if><s:else>${resultMap['B29-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B29-3'].moneyRate==null">0</s:if><s:else>${resultMap['B29-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B29-4'].moneyMust==null">0</s:if><s:else>${resultMap['B29-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B29-4'].moneyFact==null">0</s:if><s:else>${resultMap['B29-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B29-4'].moneyRate==null">0</s:if><s:else>${resultMap['B29-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="1.6" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.6&nbsp;代收款项资金流入</td>
					<td><!--<s:if test="resultMap['A_1_6-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_6-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_6-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_6-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_6-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_6-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_6-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_6-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_6-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_6-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_6-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_6-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_6-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_6-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_6-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_6-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_6-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_6-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_1_6-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_1_6-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_1_6-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_6-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_1_6-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_1_6-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2" ifHasSub="1">
					<td class="indent_1" style="text-align:left;">2&nbsp;成本支出合计<span class="triangle"/></td>
					<td><!--<s:if test="resultMap['A_2-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_2-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_2-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_2-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_2-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_2-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_2-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_2-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_2-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_2-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_2-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.1" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.1&nbsp;人工福利费</td>
					<td><!--<s:if test="resultMap['A_2_1-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_1-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_1-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_1-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_1-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_1-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_1-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_1-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_1-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_1-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_1-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_1-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_1-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_1-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_1-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_1-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_1-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_1-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_1-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_1-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_1-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_1-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_1-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_1-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.2" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.2&nbsp;其他人事费用</td>
					<td><!--<s:if test="resultMap['B42-1'].moneyMust==null">0</s:if><s:else>${resultMap['B42-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B42-1'].moneyFact==null">0</s:if><s:else>${resultMap['B42-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B42-1'].moneyRate==null">0</s:if><s:else>${resultMap['B42-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B42-2'].moneyMust==null">0</s:if><s:else>${resultMap['B42-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B42-2'].moneyFact==null">0</s:if><s:else>${resultMap['B42-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B42-2'].moneyRate==null">0</s:if><s:else>${resultMap['B42-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B42-3'].moneyMust==null">0</s:if><s:else>${resultMap['B42-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B42-3'].moneyFact==null">0</s:if><s:else>${resultMap['B42-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B42-3'].moneyRate==null">0</s:if><s:else>${resultMap['B42-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B42-4'].moneyMust==null">0</s:if><s:else>${resultMap['B42-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B42-4'].moneyFact==null">0</s:if><s:else>${resultMap['B42-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B42-4'].moneyRate==null">0</s:if><s:else>${resultMap['B42-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.3" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.3&nbsp;行政类费用</td>
					<td><!--<s:if test="resultMap['B36-1'].moneyMust==null">0</s:if><s:else>${resultMap['B36-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B36-1'].moneyFact==null">0</s:if><s:else>${resultMap['B36-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B36-1'].moneyRate==null">0</s:if><s:else>${resultMap['B36-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B36-2'].moneyMust==null">0</s:if><s:else>${resultMap['B36-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B36-2'].moneyFact==null">0</s:if><s:else>${resultMap['B36-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B36-2'].moneyRate==null">0</s:if><s:else>${resultMap['B36-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B36-3'].moneyMust==null">0</s:if><s:else>${resultMap['B36-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B36-3'].moneyFact==null">0</s:if><s:else>${resultMap['B36-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B36-3'].moneyRate==null">0</s:if><s:else>${resultMap['B36-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B36-4'].moneyMust==null">0</s:if><s:else>${resultMap['B36-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B36-4'].moneyFact==null">0</s:if><s:else>${resultMap['B36-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B36-4'].moneyRate==null">0</s:if><s:else>${resultMap['B36-4'].moneyRate}</s:else>%--></td>
				</tr>
				
				<tr myid="2.4" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.4&nbsp;差旅费</td>
					<td><!--<s:if test="resultMap['B35-1'].moneyMust==null">0</s:if><s:else>${resultMap['B35-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B35-1'].moneyFact==null">0</s:if><s:else>${resultMap['B35-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B35-1'].moneyRate==null">0</s:if><s:else>${resultMap['B35-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B35-2'].moneyMust==null">0</s:if><s:else>${resultMap['B35-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B35-2'].moneyFact==null">0</s:if><s:else>${resultMap['B35-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B35-2'].moneyRate==null">0</s:if><s:else>${resultMap['B35-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B35-3'].moneyMust==null">0</s:if><s:else>${resultMap['B35-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B35-3'].moneyFact==null">0</s:if><s:else>${resultMap['B35-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B35-3'].moneyRate==null">0</s:if><s:else>${resultMap['B35-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B35-4'].moneyMust==null">0</s:if><s:else>${resultMap['B35-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B35-4'].moneyFact==null">0</s:if><s:else>${resultMap['B35-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B35-4'].moneyRate==null">0</s:if><s:else>${resultMap['B35-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.5" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.5&nbsp;业务招待费</td>
					<td><!--<s:if test="resultMap['B37-1'].moneyMust==null">0</s:if><s:else>${resultMap['B37-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B37-1'].moneyFact==null">0</s:if><s:else>${resultMap['B37-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B37-1'].moneyRate==null">0</s:if><s:else>${resultMap['B37-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B37-2'].moneyMust==null">0</s:if><s:else>${resultMap['B37-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B37-2'].moneyFact==null">0</s:if><s:else>${resultMap['B37-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B37-2'].moneyRate==null">0</s:if><s:else>${resultMap['B37-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B37-3'].moneyMust==null">0</s:if><s:else>${resultMap['B37-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B37-3'].moneyFact==null">0</s:if><s:else>${resultMap['B37-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B37-3'].moneyRate==null">0</s:if><s:else>${resultMap['B37-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B37-4'].moneyMust==null">0</s:if><s:else>${resultMap['B37-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B37-4'].moneyFact==null">0</s:if><s:else>${resultMap['B37-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B37-4'].moneyRate==null">0</s:if><s:else>${resultMap['B37-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.6" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.6&nbsp;广告宣传费</td>
					<td><!--<s:if test="resultMap['B21-1'].moneyMust==null">0</s:if><s:else>${resultMap['B21-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B21-1'].moneyFact==null">0</s:if><s:else>${resultMap['B21-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B21-1'].moneyRate==null">0</s:if><s:else>${resultMap['B21-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B21-2'].moneyMust==null">0</s:if><s:else>${resultMap['B21-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B21-2'].moneyFact==null">0</s:if><s:else>${resultMap['B21-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B21-2'].moneyRate==null">0</s:if><s:else>${resultMap['B21-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B21-3'].moneyMust==null">0</s:if><s:else>${resultMap['B21-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B21-3'].moneyFact==null">0</s:if><s:else>${resultMap['B21-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B21-3'].moneyRate==null">0</s:if><s:else>${resultMap['B21-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B21-4'].moneyMust==null">0</s:if><s:else>${resultMap['B21-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B21-4'].moneyFact==null">0</s:if><s:else>${resultMap['B21-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B21-4'].moneyRate==null">0</s:if><s:else>${resultMap['B21-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.7" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.7&nbsp;招商奖金</td>
					<td><!--<s:if test="resultMap['B22-1'].moneyMust==null">0</s:if><s:else>${resultMap['B22-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B22-1'].moneyFact==null">0</s:if><s:else>${resultMap['B22-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B22-1'].moneyRate==null">0</s:if><s:else>${resultMap['B22-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B22-2'].moneyMust==null">0</s:if><s:else>${resultMap['B22-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B22-2'].moneyFact==null">0</s:if><s:else>${resultMap['B22-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B22-2'].moneyRate==null">0</s:if><s:else>${resultMap['B22-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B22-3'].moneyMust==null">0</s:if><s:else>${resultMap['B22-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B22-3'].moneyFact==null">0</s:if><s:else>${resultMap['B22-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B22-3'].moneyRate==null">0</s:if><s:else>${resultMap['B22-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B22-4'].moneyMust==null">0</s:if><s:else>${resultMap['B22-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B22-4'].moneyFact==null">0</s:if><s:else>${resultMap['B22-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B22-4'].moneyRate==null">0</s:if><s:else>${resultMap['B22-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.8" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.8&nbsp;清洁卫生费</td>
					<td><!--<s:if test="resultMap['B1-1'].moneyMust==null">0</s:if><s:else>${resultMap['B1-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B1-1'].moneyFact==null">0</s:if><s:else>${resultMap['B1-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B1-1'].moneyRate==null">0</s:if><s:else>${resultMap['B1-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B1-2'].moneyMust==null">0</s:if><s:else>${resultMap['B1-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B1-2'].moneyFact==null">0</s:if><s:else>${resultMap['B1-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B1-2'].moneyRate==null">0</s:if><s:else>${resultMap['B1-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B1-3'].moneyMust==null">0</s:if><s:else>${resultMap['B1-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B1-3'].moneyFact==null">0</s:if><s:else>${resultMap['B1-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B1-3'].moneyRate==null">0</s:if><s:else>${resultMap['B1-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B1-4'].moneyMust==null">0</s:if><s:else>${resultMap['B1-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B1-4'].moneyFact==null">0</s:if><s:else>${resultMap['B1-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B1-4'].moneyRate==null">0</s:if><s:else>${resultMap['B1-4'].moneyRate}</s:else>%--></td>
					
				</tr>
				<tr myid="2.9" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.9&nbsp;安全保卫费</td>
					<td><!--<s:if test="resultMap['B2-1'].moneyMust==null">0</s:if><s:else>${resultMap['B2-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B2-1'].moneyFact==null">0</s:if><s:else>${resultMap['B2-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B2-1'].moneyRate==null">0</s:if><s:else>${resultMap['B2-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B2-2'].moneyMust==null">0</s:if><s:else>${resultMap['B2-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B2-2'].moneyFact==null">0</s:if><s:else>${resultMap['B2-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B2-2'].moneyRate==null">0</s:if><s:else>${resultMap['B2-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B2-3'].moneyMust==null">0</s:if><s:else>${resultMap['B2-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B2-3'].moneyFact==null">0</s:if><s:else>${resultMap['B2-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B2-3'].moneyRate==null">0</s:if><s:else>${resultMap['B2-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B2-4'].moneyMust==null">0</s:if><s:else>${resultMap['B2-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B2-4'].moneyFact==null">0</s:if><s:else>${resultMap['B2-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B2-4'].moneyRate==null">0</s:if><s:else>${resultMap['B2-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.10" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.10&nbsp;绿化养护费</td>
					<td><!--<s:if test="resultMap['B3-1'].moneyMust==null">0</s:if><s:else>${resultMap['B3-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B3-1'].moneyFact==null">0</s:if><s:else>${resultMap['B3-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B3-1'].moneyRate==null">0</s:if><s:else>${resultMap['B3-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B3-2'].moneyMust==null">0</s:if><s:else>${resultMap['B3-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B3-2'].moneyFact==null">0</s:if><s:else>${resultMap['B3-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B3-2'].moneyRate==null">0</s:if><s:else>${resultMap['B3-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B3-3'].moneyMust==null">0</s:if><s:else>${resultMap['B3-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B3-3'].moneyFact==null">0</s:if><s:else>${resultMap['B3-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B3-3'].moneyRate==null">0</s:if><s:else>${resultMap['B3-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B3-4'].moneyMust==null">0</s:if><s:else>${resultMap['B3-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B3-4'].moneyFact==null">0</s:if><s:else>${resultMap['B3-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B3-4'].moneyRate==null">0</s:if><s:else>${resultMap['B3-4'].moneyRate}</s:else>%--></td>
					
				</tr>
				<tr myid="2.10" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.11&nbsp;工程维保费</td>
					<td><!--<s:if test="resultMap['B4-1'].moneyMust==null">0</s:if><s:else>${resultMap['B4-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B4-1'].moneyFact==null">0</s:if><s:else>${resultMap['B4-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B4-1'].moneyRate==null">0</s:if><s:else>${resultMap['B4-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B4-2'].moneyMust==null">0</s:if><s:else>${resultMap['B4-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B4-2'].moneyFact==null">0</s:if><s:else>${resultMap['B4-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B4-2'].moneyRate==null">0</s:if><s:else>${resultMap['B4-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B4-3'].moneyMust==null">0</s:if><s:else>${resultMap['B4-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B4-3'].moneyFact==null">0</s:if><s:else>${resultMap['B4-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B4-3'].moneyRate==null">0</s:if><s:else>${resultMap['B4-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B4-4'].moneyMust==null">0</s:if><s:else>${resultMap['B4-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B4-4'].moneyFact==null">0</s:if><s:else>${resultMap['B4-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B4-4'].moneyRate==null">0</s:if><s:else>${resultMap['B4-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.11" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.12&nbsp;能源费(无法收回或摊销)</td>
					<td><!--<s:if test="resultMap['B32-1'].moneyMust==null">0</s:if><s:else>${resultMap['B32-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B32-1'].moneyFact==null">0</s:if><s:else>${resultMap['B32-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B32-1'].moneyRate==null">0</s:if><s:else>${resultMap['B32-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B32-2'].moneyMust==null">0</s:if><s:else>${resultMap['B32-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B32-2'].moneyFact==null">0</s:if><s:else>${resultMap['B32-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B32-2'].moneyRate==null">0</s:if><s:else>${resultMap['B32-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B32-3'].moneyMust==null">0</s:if><s:else>${resultMap['B32-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B32-3'].moneyFact==null">0</s:if><s:else>${resultMap['B32-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B32-3'].moneyRate==null">0</s:if><s:else>${resultMap['B32-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B32-4'].moneyMust==null">0</s:if><s:else>${resultMap['B32-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B32-4'].moneyFact==null">0</s:if><s:else>${resultMap['B32-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B32-4'].moneyRate==null">0</s:if><s:else>${resultMap['B32-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.12" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.13&nbsp;其他费用</td>
					<td><!--<s:if test="resultMap['A_2_13-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_13-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_13-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_13-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_13-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_13-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_13-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_13-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_13-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_13-2'].moneyFact}</s:else>--></td>
					<td><!--<s:if test="resultMap['A_2_13-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_13-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_13-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_13-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_13-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_13-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_13-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_13-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_13-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_13-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_13-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_13-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_13-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_13-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.14" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.14&nbsp;资本性支出</td>
					<td><!--<s:if test="resultMap['B40-1'].moneyMust==null">0</s:if><s:else>${resultMap['B40-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B40-1'].moneyFact==null">0</s:if><s:else>${resultMap['B40-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B40-1'].moneyRate==null">0</s:if><s:else>${resultMap['B40-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B40-2'].moneyMust==null">0</s:if><s:else>${resultMap['B40-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B40-2'].moneyFact==null">0</s:if><s:else>${resultMap['B40-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B40-2'].moneyRate==null">0</s:if><s:else>${resultMap['B40-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B40-3'].moneyMust==null">0</s:if><s:else>${resultMap['B40-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B40-3'].moneyFact==null">0</s:if><s:else>${resultMap['B40-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B40-3'].moneyRate==null">0</s:if><s:else>${resultMap['B40-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B40-4'].moneyMust==null">0</s:if><s:else>${resultMap['B40-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B40-4'].moneyFact==null">0</s:if><s:else>${resultMap['B40-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B40-4'].moneyRate==null">0</s:if><s:else>${resultMap['B40-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.15" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.15&nbsp;营业税金及附加</td>
					<td><!--<s:if test="resultMap['A_2_18-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_18-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_18-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_18-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_18-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_18-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_18-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_18-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_18-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_18-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_18-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_18-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_18-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_18-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_18-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_18-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_18-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_18-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_18-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_18-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_18-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_18-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_18-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_18-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.16" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.16&nbsp;委托租赁支出（返租支出）</td>
					<td><!--<s:if test="resultMap['B34-1'].moneyMust==null">0</s:if><s:else>${resultMap['B34-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B34-1'].moneyFact==null">0</s:if><s:else>${resultMap['B34-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B34-1'].moneyRate==null">0</s:if><s:else>${resultMap['B34-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B34-2'].moneyMust==null">0</s:if><s:else>${resultMap['B34-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B34-2'].moneyFact==null">0</s:if><s:else>${resultMap['B34-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B34-2'].moneyRate==null">0</s:if><s:else>${resultMap['B34-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B34-3'].moneyMust==null">0</s:if><s:else>${resultMap['B34-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B34-3'].moneyFact==null">0</s:if><s:else>${resultMap['B34-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B34-3'].moneyRate==null">0</s:if><s:else>${resultMap['B34-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B34-4'].moneyMust==null">0</s:if><s:else>${resultMap['B34-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B34-4'].moneyFact==null">0</s:if><s:else>${resultMap['B34-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B34-4'].moneyRate==null">0</s:if><s:else>${resultMap['B34-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.17" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.17&nbsp;立项费用（大营运专用）</td>
					<td><!--<s:if test="resultMap['A_2_17-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_17-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_17-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_17-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_17-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_17-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_17-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_17-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_17-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_17-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_17-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_17-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_17-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_17-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_17-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_17-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_17-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_17-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_2_17-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_2_17-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_2_17-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_17-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_2_17-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_2_17-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="2.18" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.18&nbsp;代付款项资金支出</td>
					<td><!--<s:if test="resultMap['B38-1'].moneyMust==null">0</s:if><s:else>${resultMap['B38-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B38-1'].moneyFact==null">0</s:if><s:else>${resultMap['B38-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B38-1'].moneyRate==null">0</s:if><s:else>${resultMap['B38-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B38-2'].moneyMust==null">0</s:if><s:else>${resultMap['B38-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B38-2'].moneyFact==null">0</s:if><s:else>${resultMap['B38-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B38-2'].moneyRate==null">0</s:if><s:else>${resultMap['B38-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B38-3'].moneyMust==null">0</s:if><s:else>${resultMap['B38-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B38-3'].moneyFact==null">0</s:if><s:else>${resultMap['B38-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B38-3'].moneyRate==null">0</s:if><s:else>${resultMap['B38-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['B38-4'].moneyMust==null">0</s:if><s:else>${resultMap['B38-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['B38-4'].moneyFact==null">0</s:if><s:else>${resultMap['B38-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['B38-4'].moneyRate==null">0</s:if><s:else>${resultMap['B38-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="3" ifHasSub="0">
					<td class="indent_1" style="text-align:left;">3&nbsp;净利润</td>
					<td><!--<s:if test="resultMap['A_3-1'].moneyMust==null">0</s:if><s:else>${resultMap['A_3-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_3-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_3-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_3-1'].moneyRate==null">0</s:if><s:else>${resultMap['A_3-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_3-2'].moneyMust==null">0</s:if><s:else>${resultMap['A_3-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_3-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_3-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_3-2'].moneyRate==null">0</s:if><s:else>${resultMap['A_3-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_3-3'].moneyMust==null">0</s:if><s:else>${resultMap['A_3-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_3-3'].moneyFact==null">0</s:if><s:else>${resultMap['A_3-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_3-3'].moneyRate==null">0</s:if><s:else>${resultMap['A_3-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['A_3-4'].moneyMust==null">0</s:if><s:else>${resultMap['A_3-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['A_3-4'].moneyFact==null">0</s:if><s:else>${resultMap['A_3-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['A_3-4'].moneyRate==null">0</s:if><s:else>${resultMap['A_3-4'].moneyRate}</s:else>%--></td>
				</tr>
				<tr myid="4" ifHasSub="0">
					<td class="indent_1" style="text-align:left;">4&nbsp;租金</td>
					<td><!--<s:if test="resultMap['1-1'].moneyMust==null">0</s:if><s:else>${resultMap['1-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['1-1'].moneyFact==null">0</s:if><s:else>${resultMap['1-1'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['1-1'].moneyRate==null">0</s:if><s:else>${resultMap['1-1'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['1-2'].moneyMust==null">0</s:if><s:else>${resultMap['1-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['1-2'].moneyFact==null">0</s:if><s:else>${resultMap['1-2'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['1-2'].moneyRate==null">0</s:if><s:else>${resultMap['1-2'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['1-3'].moneyMust==null">0</s:if><s:else>${resultMap['1-3'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['1-3'].moneyFact==null">0</s:if><s:else>${resultMap['1-3'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['1-3'].moneyRate==null">0</s:if><s:else>${resultMap['1-3'].moneyRate}</s:else>%--></td>
					<td><!--<s:if test="resultMap['1-4'].moneyMust==null">0</s:if><s:else>${resultMap['1-4'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['1-4'].moneyFact==null">0</s:if><s:else>${resultMap['1-4'].moneyFact}</s:else></td>
					<td><!--<s:if test="resultMap['1-4'].moneyRate==null">0</s:if><s:else>${resultMap['1-4'].moneyRate}</s:else>%--></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div style="height:5px;"></div>
<script language="javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
isNotEmpty = function (str) {
	return !isEmpty(str);
};

$(function(){
	$("#tableLeft tr").each(function(){
		if(isNotEmpty($(this).attr("myid"))){
			$(this).bind("click",{myid:$(this).attr("myid")},trClick);
			if(1==$(this).attr("ifHasSub")){
				$(this).css("cursor","pointer");
			}
		}
	});
});
var tempMyId;
var tempifHasSub;
function trClick(event){
	tempMyId = event.data.myid;
	$("#tableLeft tr").each(function(){	
		var otid = $(this).attr("myid");
		if(otid==tempMyId){
			tempifHasSub = $(this).attr("ifHasSub");
		}
	});
	if(isNotEmpty(tempMyId)){
		$("#tableLeft tr").each(function(){
			
			var otid = $(this).attr("myid");
			if(isNotEmpty(otid)){
				if(otid==tempMyId){
					if(1==tempifHasSub){
						$(this).attr("ifHasSub",0);
						$(this).find('span').removeClass('triangle').addClass('triangledown');
					}else{
						$(this).attr("ifHasSub",1);
						$(this).find('span').removeClass('triangledown').addClass('triangle');
					}
				}
				if(tempMyId.length<otid.length){
					if(otid.substring(0,tempMyId.length)==tempMyId){
						if(1==tempifHasSub){
							$(this).show();
							$(this).attr("ifHasSub",0);
						}else{
							$(this).hide();
							$(this).attr("ifHasSub",1);
						}
					}
				}
			}
		});
	}
}
</script>