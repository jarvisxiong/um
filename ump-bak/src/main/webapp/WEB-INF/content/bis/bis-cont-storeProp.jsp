<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<table class="mainTable" style="width: 100%">
	<col width="100"/>
	<col width="120"/>
	<col />
	<tr>
		<td style="text-align: left; padding-left: 10px; font-weight: bold;">合同基本信息</td>
		<td colspan="2">
		<table class="tb_noborder">
			<col width="120"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">品牌：</td>
				<td>
					<input validate="required" type="hidden" id="bisShopId" name="bisShopId" value="${bisShopId}" />
					<input validate="required" class="inputBorder search must" searchtext="搜索品牌" type="text" id="bisShopName" name="bisShopName" value="${bisShopName}" />
				</td>
				<td class="td_title">经销商名称：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder must" list="mapBisShopConn" listKey="key" listValue="value" id="bisShopConnId" name="bisShopConnId" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="td_title">商铺：</td>
				<td colspan="3">
					<input validate="required" type="hidden" id="bisStoreIds" name="bisStoreIds" value="${bisStoreIds}" />
					<input validate="required" class="inputBorder search must" style="cursor:pointer;" searchtext="搜索商铺" type="text" name="bisContShopProps[0].storeNo" id="bisStoreNos" value="${bisStoreNos}" onclick="doStoreSelect('bisStoreIds','bisStoreNos')"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">楼栋名称：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="buildingName" value="${buildingName}" />
				</td>
				<td class="td_title">建筑面积：</td>
				<td>
					<input class="inputBorder" type="text" id="square" name="square" value="${square}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td class="td_title">套内面积：</td>
				<td>
					<input class="inputBorder" type="text" id="innerSquare" name="innerSquare" value="${innerSquare}" readonly="readonly" />
				</td>
				<td class="td_title">计租面积：</td>
				<td>
					<input class="inputBorder" type="text" id="showSquare" name="showSquare" value="${showSquare}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">实际收费面积：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" id="rentSquare" name="rentSquare" value="${rentSquare}" />
				</td>
				<td class="td_title">产权性质：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" listKey="key" listValue="value" name="equityNature" ></s:select>
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
				<td class="td_title">签约时间：</td>
				<td>
					<input validate="required" class="inputBorder Wdate" type="text" id="signDate" name="signDate" value='<s:date name="signDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()"/>
				</td>
				<td class="td_title">计租起始日：</td>
				<td>
					<input validate="required" class="inputBorder Wdate" type="text" name="rentDate" value='<s:date name="rentDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}', maxDate:'#F{$dp.$D(\'contEndDate\')}'})"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同期限：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="rentYears" value="${rentYears}" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="td_title">业态：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key" listValue="value" name="layoutCd"></s:select>
				</td>
				<td class="td_title">经营性质：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key" listValue="value" name="manageCd" id="manageCd"></s:select>
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
				<td colspan="3">
					<input class="inputBorder" type="text" name="connAddr" value="${connAddr}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">合作方式：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapBisCoopWay()" listKey="key" listValue="value" name="coopWayCd"></s:select>
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
		<td rowspan="2" style="text-align: left; padding-left: 10px; font-weight: bold;">合同类别信息</td>
		<td colspan="2">
		<input type="hidden" name="bisContShopProps[0].bisContShopPropId" value="${bisContShopProps[0].bisContShopPropId}"/>
		<input type="hidden" name="bisContShopProps[0].recordVersion" value="${bisContShopProps[0].recordVersion}"/>
		<table class="tb_noborder">
			<col width="120"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">支付方式：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" cssStyle="width: 100%" list="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" listKey="key" listValue="value" name="bisContShopProps[0].paymentModeCd"></s:select>
				</td>
				<td class="td_title">物业履约保证金：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].performanceBond" value="${bisContShopProps[0].performanceBond}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">免租期：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContShopProps[0].integMoneyFreeRentPeriod" value="${bisContShopProps[0].integMoneyFreeRentPeriod}" />
				</td>
				<td class="td_title">经营保证金：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].operationBond" value="${bisContShopProps[0].operationBond}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">综合管理费起收日：</td>
				<td>
					<input class="inputBorder Wdate" type="text" name="bisContShopProps[0].integMoneyFirstRentDay" value='<s:date name="bisContShopProps[0].integMoneyFirstRentDay" format="yyyy-MM-dd"/>' onfocus="WdatePicker()"/>
				</td>
				<td class="td_title">付款日期：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContShopProps[0].paymentTime" value='${bisContShopProps[0].paymentTime}' />
				</td>
			</tr>
			<tr>
				<td class="td_title">是否参与公摊：</td>
				<td>
					<s:select cssClass="inputBorder" list="#{'true':'是','false':'否'}" listKey="key" listValue="value" name="bisContShopProps[0].inPoolBl"></s:select>
				</td>
				<td colspan="2"></td>
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
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee1" value="${bisContShopProps[0].manageFee1}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第二年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee2" value="${bisContShopProps[0].manageFee2}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第三年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee3" value="${bisContShopProps[0].manageFee3}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee4==null">style="display: none;"</s:if> >
				<td class="td_title">第四年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee4" value="${bisContShopProps[0].manageFee4}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee5==null">style="display: none;"</s:if> >
				<td class="td_title">第五年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee5" value="${bisContShopProps[0].manageFee5}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee6==null">style="display: none;"</s:if> >
				<td class="td_title">第六年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee6" value="${bisContShopProps[0].manageFee6}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee7==null">style="display: none;"</s:if> >
				<td class="td_title">第七年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee7" value="${bisContShopProps[0].manageFee7}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee8==null">style="display: none;"</s:if> >
				<td class="td_title">第八年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee8" value="${bisContShopProps[0].manageFee8}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee9==null">style="display: none;"</s:if> >
				<td class="td_title">第九年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee9" value="${bisContShopProps[0].manageFee9}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopProps.size()==0 || bisContShopProps[0].manageFee10==null">style="display: none;"</s:if> >
				<td class="td_title">第十年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopProps[0].manageFee10" value="${bisContShopProps[0].manageFee10}" />
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

<script type="text/javascript">
	$("#bisShopName").quickSearch("${ctx}/bis/bis-shop!quickSearch.action",['projectName','shopName','companyName','salesman','hqAddr'],{bisShopId:'bisShopId',shopName:'bisShopName',manageCd:'manageCd'},{},getShopConn);
</script>
