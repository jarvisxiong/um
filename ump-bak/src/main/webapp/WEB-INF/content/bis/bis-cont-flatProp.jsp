<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<table class="mainTable" style="width: 100%">
	<col width="100"/>
	<col width="110"/>
	<col />
	<tr>
		<td style="text-align: left; padding-left: 10px; font-weight: bold;">合同基本信息</td>
		<td colspan="2">
		<table class="tb_noborder">
			<col width="110"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">业主：</td>
				<td>
					<input validate="required" validate="required" class="inputBorder must" type="text" name="bisContFlatProps[0].owner" value="${bisContFlatProps[0].owner}" />
				</td>
				<td class="td_title">身份证号码：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="bisContFlatProps[0].idCardNo" value="${bisContFlatProps[0].idCardNo}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">楼栋名称：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="buildingName" value="${buildingName}" />
				</td>
				<td class="td_title">公寓编号：</td>
				<td>
					<input validate="required" type="hidden" id="bisFlatIds" name="bisFlatIds" value="${bisFlatIds}"  />
					<input validate="required" class="inputBorder search must" style="cursor:pointer;" searchtext="搜索公寓" type="text" name="bisContFlatProps[0].flatNo" id="bisFlatNos" value="${bisFlatNos}" onclick="doStoreSelect('bisFlatIds','bisFlatNos')"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">建筑面积：</td>
				<td>
					<input id="square" class="inputBorder" type="text" name="square" value="${square}" />
				</td>
				<td class="td_title">套内面积：</td>
				<td>
					<input id="innerSquare" class="inputBorder" type="text" name="innerSquare" value="${innerSquare}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">合同开始日期：</td>
				<td>
					<input validate="required" class="inputBorder Wdate must" type="text" id="contStartDate" name="contStartDate" value='<s:date name="contStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'contEndDate\')}'})"/>
				</td>
				<td class="td_title">合同结束日期：</td>
				<td>
					<input validate="required" class="inputBorder Wdate must" type="text" id="contEndDate" name="contEndDate" value='<s:date name="contEndDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}'})"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">住宅类型：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" cssStyle="width: 100%" list="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" listKey="key" listValue="value" name="bisContFlatProps[0].housingTypeCd"></s:select>
				</td>
				<td class="td_title">合同期限：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="rentYears" value="${rentYears}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">甲方：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="partyA" value="${partyA}" />
				</td>
				<td class="td_title">乙方：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="partyB" value="${partyB}" />
				</td>
			</tr>
		</table>	
		</td>
	</tr>
	<tr>
		<td rowspan="2" style="text-align: left; padding-left: 10px; font-weight: bold;">合同类别信息</td>
		<td colspan="2">
		<input type="hidden" name="bisContFlatProps[0].bisContFlatPropId" value="${bisContFlatProps[0].bisContFlatPropId}"/>
		<input type="hidden" name="bisContFlatProps[0].recordVersion" value="${bisContFlatProps[0].recordVersion}"/>
		<table class="tb_noborder">
			<col width="110"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">支付方式：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" cssStyle="width: 100%" list="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" listKey="key" listValue="value" name="bisContFlatProps[0].paymentModeCd"></s:select>
				</td>
				<td class="td_title">首个收费周期&nbsp;&nbsp;<br>应收费用：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].firstCycleMustRent" value="${bisContFlatProps[0].firstCycleMustRent}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">物业费减免金额：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].propertyDerateMoney" value="${bisContFlatProps[0].propertyDerateMoney}" />
				</td>
				<td class="td_title">物业管理费&nbsp;&nbsp;<br>减免期限：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContFlatProps[0].integMoneyFreeRentPeriod" value="${bisContFlatProps[0].integMoneyFreeRentPeriod}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">综合理费起收日：</td>
				<td>
					<input class="inputBorder Wdate" type="text" name="bisContFlatProps[0].integMoneyFirstRentDay" value='<s:date name="bisContFlatProps[0].integMoneyFirstRentDay" format="yyyy-MM-dd"/>' onfocus="WdatePicker()"/>
				</td>
				<td colspan="2"></td>
				<!--
				<td class="td_title">综合管理费标准&nbsp;&nbsp;<br>(元/平方/月)：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].integMoneyStandard" value="${bisContFlatProps[0].integMoneyStandard}" />
				</td>
				-->
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="td_title">
			综合管理费标准&nbsp;&nbsp;<br>(元/平方/月)&nbsp;&nbsp;<br>
			<s:if test="#canEdit=='true'">
			<button type="button" class="btn_blue" onclick="addRentOneItem();" >新增</button>&nbsp;&nbsp;
			</s:if>
		</td>
		<td>
		<table class="tb_noborder" id="tbRentOneItem" >
			<col width="80"/>
			<col />
			<col width="40"/>
			<tr>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee1" value="${bisContFlatProps[0].manageFee1}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第二年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee2" value="${bisContFlatProps[0].manageFee2}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第三年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee3" value="${bisContFlatProps[0].manageFee3}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee4==null">style="display: none;"</s:if> >
				<td class="td_title">第四年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee4" value="${bisContFlatProps[0].manageFee4}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee5==null">style="display: none;"</s:if> >
				<td class="td_title">第五年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee5" value="${bisContFlatProps[0].manageFee5}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee6==null">style="display: none;"</s:if> >
				<td class="td_title">第六年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee6" value="${bisContFlatProps[0].manageFee6}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee7==null">style="display: none;"</s:if> >
				<td class="td_title">第七年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee7" value="${bisContFlatProps[0].manageFee7}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee8==null">style="display: none;"</s:if> >
				<td class="td_title">第八年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee8" value="${bisContFlatProps[0].manageFee8}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee9==null">style="display: none;"</s:if> >
				<td class="td_title">第九年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee9" value="${bisContFlatProps[0].manageFee9}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContFlatProps.size()==0 || bisContFlatProps[0].manageFee10==null">style="display: none;"</s:if> >
				<td class="td_title">第十年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContFlatProps[0].manageFee10" value="${bisContFlatProps[0].manageFee10}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
