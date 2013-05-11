<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div style="height:10px;line-height:10px;"><span style="font-size:14px;"></span></div>

<form action="${ctx}/bis/bis-project!saveFlat.action" method="post" id="flatDetaForm">
	<input type="hidden" name="bisFlat.recordVersion" id="recordVersion" value="${bisFlat.recordVersion}" />
	<input type="hidden" name="bisFlat.bisFlatId" id="bisFlatId" value="${bisFlat.bisFlatId}" />
	<input type="hidden" name="bisFlat.bisFloor.bisFloorId"  value="${bisFlat.bisFloor.bisFloorId}" />
	<input type="hidden" name="bisFlat.bisProjectId" id="bisProjectId" value="${bisFlat.bisProjectId}" />
	<table  class="main_content" style="line-height:30px;">
		<col width="100"/>
		<col width="100"/>
		<col width="120"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<tr>
			<td align="right">编号：</td>
			<td>
				<input type="text" id="flatNo" class="required" name="bisFlat.flatNo" value="${bisFlat.flatNo}" />
			</td>
			<td align="right">客户名称：</td>
			<td>
				<input class="required" type="text" id="customerName" name="bisFlat.customerName" value="${bisFlat.customerName}" />
			</td>
			<td align="right">住宅底商：</td>
			<td>
				<s:select cssClass="required" list="@com.hhz.ump.util.DictMapUtil@getMapEnableFlgNum()" listKey="key" listValue="value" name="bisFlat.houseShopFlag" id="houseShopFlag" ></s:select>
			</td>
		</tr>
		<tr>
			<td align="right">建筑面积㎡：</td>
			<td>
				<input class="required" type="text" id="square" alt="amount" name="bisFlat.square" value="${bisFlat.square}"/>
			</td>
			<td align="right">套内面积㎡：</td>
			<td>
				<input class="required" type="text" id="innerSquare" alt="amount" name="bisFlat.innerSquare" value="${bisFlat.innerSquare}"/>
			</td>
			<td align="right">实测建筑面积㎡：</td>
            <td>
                <input class="required" type="text" id="actualSquare" name="bisFlat.actualSquare" value="${bisFlat.actualSquare}"/>
            </td>
		</tr>
		<tr>
			<td align="right">实测套内面积㎡：</td>
			<td>
				<input class="required" type="text" alt="amount" id="actualInnerSquare" name="bisFlat.actualInnerSquare" value="${bisFlat.actualInnerSquare}" />
			</td>
			<td align="right">月物管费单价(元/月)：</td>
			<td>
				<input class="required" type="text" id="monPromanfeePrice" alt="amount" name="bisFlat.monPromanfeePrice" value="${bisFlat.monPromanfeePrice}"/>
			</td>
			<td align="right">开业日期：</td>
			<td>
				<s:textfield name="bisFlat.openDate"  onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
			</td>
			<%--
			<td align="right">以前年度累计应收(物管费)：</td>
            <td>
                <input class="required" type="text" id="annualYsMan" alt="amount" name="bisFlat.annualYsMan" value="${bisFlat.annualYsMan}"/>
            </td>
             --%>
		</tr>
		
		<%-- 
		<tr>
			<td align="right">以前年度累计实收(物管费)：</td>
			<td>
                <input class="required" type="text" id="annualSsMan" alt="amount" name="bisFlat.annualSsMan" value="${bisFlat.annualSsMan}"/>
			</td>
			<td align="right">以前年度累计预收(物管费)：</td>
			<td>
                <input class="required" type="text" id="annualYusMan" alt="amount" name="bisFlat.annualYusMan" value="${bisFlat.annualYusMan}"/>
			</td>
			<td align="right">以前年度累计欠收(物管费)：</td>
			<td>
                <input class="required" type="text" id="annualQsMan" alt="amount" name="bisFlat.annualQsMan" value="${bisFlat.annualQsMan}"/>
			</td>
		</tr>
		<tr>
			<td align="right">以前年度累计应收(水费)：</td>
			<td>
                <input class="required" type="text" id="annualYsWater" alt="amount" name="bisFlat.annualYsWater" value="${bisFlat.annualYsWater}"/>
			</td>
			<td align="right">以前年度累计实收(水费)：</td>
			<td>
                <input class="required" type="text" id="annualSsWater" alt="amount" name="bisFlat.annualSsWater" value="${bisFlat.annualSsWater}"/>
			</td>
			<td align="right">以前年度累计预收(水费)：</td>
			<td>
                <input class="required" type="text" id="annualYusWater" alt="amount" name="bisFlat.annualYusWater" value="${bisFlat.annualYusWater}"/>
			</td>
		</tr>
		<tr>
			<td align="right">以前年度累计欠收(水费)：</td>
			<td>
                <input class="required" type="text" id="annualQsWater" alt="amount" name="bisFlat.annualQsWater" value="${bisFlat.annualQsWater}"/>
			</td>
			<td align="right">以前年度累计应收(电费)：</td>
			<td>
				<input class="required" type="text" id="annualYsElec" alt="amount" name="bisFlat.annualYsElec" value="${bisFlat.annualYsElec}"/>
			</td>
			<td align="right">以前年度累计实收(电费)：</td>
			<td>
				<input class="required" type="text" id="annualSsElec" alt="amount" name="bisFlat.annualSsElec" value="${bisFlat.annualSsElec}"/>
			</td>
		</tr>
		<tr>
			<td align="right">以前年度累计预收(电费)：</td>
			<td>
				<input class="required" type="text" id="annualYusElec" alt="amount" name="bisFlat.annualYusElec" value="${bisFlat.annualYusElec}"/>
			</td>
			<td align="right">以前年度累计欠收(电费)：</td>
			<td>
				<input class="required" type="text" id="annualQsElec" alt="amount" name="bisFlat.annualQsElec" value="${bisFlat.annualQsElec}"/>
			</td>
			<td align="right">开业日期：</td>
			<td>
				<s:textfield name="bisFlat.openDate"  onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
			</td>
		</tr>
		--%>
		<tr>
			<!--<td width=9% align="right">公摊面积：</td>
			<td>
				<input type="text" id="publicSquare" alt="amount" name="bisFlat.publicSquare" value="${bisFlat.publicSquare}"/>
			</td>
			-->
			<td align="right">房屋结构：</td>
			<td>
				<s:select list="@com.hhz.ump.util.DictMapUtil@getMapHouseStru()" listKey="key" listValue="value" name="bisFlat.houseStruCd" id="houseStruCd" ></s:select>
			</td>
			<td align="right">业态：</td>
			<td>
				<s:select  list="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" listKey="key" listValue="value" name="bisFlat.layoutCd" id="layoutCd"></s:select>
			</td>
		</tr>
		<tr>
			<td align="right" >备注：</td>
			<td colspan="3">
			<textarea  name="bisFlat.remark">${bisFlat.remark}</textarea>
			</td>
		</tr>
	</table>
</form>