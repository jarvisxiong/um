<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<table class="mainTable" style="width: 100%">
	<col width="100"/>
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
				<td class="td_title">多经编号：</td>
				<td>
					<input validate="required" type="hidden" id="bisMultiIds" name="bisMultiIds" value="${bisMultiIds}"  />
					<input validate="required" class="inputBorder search must" style="cursor:pointer;" searchtext="搜索多经" type="text" name="bisContMultis[0].multiName" id="bisMultiNos" value="${bisMultiNos}" onclick="doStoreSelect('bisMultiIds','bisMultiNos')"/>
				</td>
				<td class="td_title">承租方：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="bisContMultis[0].renterName" value="${bisContMultis[0].renterName}" />
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
				<td class="td_title">合同金额：</td>
				<td>
					<input validate="required" class="inputBorder money" type="text" name="contMoney" value="${contMoney}" />
				</td>
				<td class="td_title">合同期限：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="rentYears" value="${rentYears}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">联系人：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="connPeople" value="${connPeople}" />
				</td>
				<td class="td_title">联系电话：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="connTel" value="${connTel}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">联系传真：</td>
				<td>
					<input class="inputBorder" type="text" name="connFax" value="${connFax}" />
				</td>
				<td class="td_title">联系人银行账号：</td>
				<td>
					<input class="inputBorder" type="text" name="connAccountNo" value="${connAccountNo}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">联系地址：</td>
				<td>
					<input class="inputBorder" type="text" name="connAddr" value="${connAddr}" />
				</td>
				<td class="td_title">招商人员：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="bisPeople" value="${bisPeople}" />
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
		<td style="text-align: left; padding-left: 10px; font-weight: bold;">合同类别信息</td>
		<td colspan="2">
		<input type="hidden" name="bisContMultis[0].bisContMultiId" value="${bisContMultis[0].bisContMultiId}"/>
		<input type="hidden" name="bisContMultis[0].recordVersion" value="${bisContMultis[0].recordVersion}"/>
		<table class="tb_noborder">
			<col width="110"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">经营项目：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" cssStyle="width: 100%" list="@com.hhz.ump.util.DictMapUtil@getMapBisOperationProjec()" listKey="key" listValue="value" name="bisContMultis[0].operationProjectCd"></s:select>
				</td>
				<td class="td_title">支付方式：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" cssStyle="width: 100%" list="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" listKey="key" listValue="value" name="bisContMultis[0].paymentModeCd"></s:select>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同约定保证金：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContMultis[0].contPromiseBond" value="${bisContMultis[0].contPromiseBond}" />
				</td>
				<td class="td_title">付款日期：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContMultis[0].paymentTime" value='${bisContMultis[0].paymentTime}' />
				</td>
			</tr>
			<tr>
				<td class="td_title">位置区域：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="bisContMultis[0].operationArea" value="${bisContMultis[0].operationArea}" />
				</td>
				<td colspan="2"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
