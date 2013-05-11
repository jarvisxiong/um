<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<s:iterator value="floorInfoList" status="s">
<div style="border:1px solid #aaabb0; height:130px;padding:10px 8px;margin-bottom:10px;">
<table>
	<col width="150"/>
	<col width="10"/>
	<col />
	<tr>
		<td>
			<div id="floor1" value="1" style="float:left; width:150px; height:110px; cursor:pointer; background-color: #464646;background-image: url('${ctx}/resources/images/bis/${floorUrl}');" <security:authorize ifAnyGranted="A_PLAN_QUERY">onclick="toBisAD('${bisFloorId}');"</security:authorize> >
			</div>
		</td>
		<td rowspan="2"></td>
		<td rowspan="2">
		<table class="tb_content" style="color:#5a5a5a;">
			<col width="75"/>
			<col />
			<tr>
				<td class="td_title_border">广告数量：</td>
				<td >
				<s:if test="storeCount != null">
				${storeCount}
				</s:if>&nbsp;
				</td>
			</tr>
			<tr>
				<td class="td_title_border">租约率：</td>
				<td >
				<s:if test="rentRate != null">
				${rentRate}%
				</s:if>&nbsp;
				</td>
			</tr>
			<tr>
				<td class="td_title_border">租金单价：</td>
				<td >
				<s:if test="rentUnitPrice != null">
				${rentUnitPrice}元/平方.月
				</s:if>&nbsp;
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td style="text-align:center;font-size:14px;font-weight:bolder;color:#0167A2;">${buildingNum} ${floorNum}楼</td>
	</tr>
</table>
</div>
</s:iterator>

