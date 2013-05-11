<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<s:set var="mustRentSize"><s:property value="bisMustRents.size()"/></s:set>
<input id="rentyear" type="hidden" value="${mustRentSize}"/>

<table class="mainTable" style="width: 100%">
	<col width="100"/>
	<col width="100"/>
	<col />
	<tr>
		<td style="text-align: left; padding-left: 10px; font-weight: bold;">合同基本信息</td>
		<td colspan="2">
		<table class="tb_noborder">
			<col width="100"/>
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
					<input validate="required" type="hidden" id="bisStoreIds" name="bisStoreIds" value="${bisStoreIds}"  />
					<input validate="required" class="inputBorder search must" style="cursor:pointer;" searchtext="搜索商铺" type="text" name="bisContShopRents[0].storeNo" id="bisStoreNos" value="${bisStoreNos}" onclick="doStoreSelect('bisStoreIds','bisStoreNos')"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">楼栋名称：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" id="buildingName" name="buildingName" value="${buildingName}" />
				</td>
				<td class="td_title">建筑面积：</td>
				<td>
					<input class="inputBorder" type="text" id="square" name="square" value="${square}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">套内面积：</td>
				<td>
					<input class="inputBorder" type="text" id="innerSquare" name="innerSquare" value="${innerSquare}" readonly="readonly"/>
				</td>
				<td class="td_title">计租面积：</td>
				<td>
					<input class="inputBorder" type="text" id="showSquare" name="showSquare" value="${showSquare}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">实际收费面积：</td>
				<td>
					<input style="color: blue;" validate="required" class="inputBorder" type="text" id="rentSquare" name="rentSquare" value="${rentSquare}" onchange="calculateMonthRent();"/>
				</td>
				<td class="td_title">产权性质：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" listKey="key" listValue="value" name="equityNature" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同开始日期：</td>
				<td>
					<input validate="required" class="inputBorder Wdate must" type="text" id="contStartDate" name="contStartDate" value='<s:date name="contStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'contEndDate\')}'})" onchange="changeRentYears();"/>
				</td>
				<td class="td_title">合同结束日期：</td>
				<td>
					<input validate="required" class="inputBorder Wdate must" type="text" id="contEndDate" name="contEndDate" value='<s:date name="contEndDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}'})" onchange="changeRentYears();"/>
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
				<td colspan="2"></td>
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
			<tr>
				<td class="td_title">丙方：</td>
				<td colspan="3">
					<input class="inputBorder search" type="text" name="partyC" value="${partyC}" searchtext="只适用于委托金比例不足时，协助受托方签署的租赁合同" />
				</td>
			</tr>
		</table>	
		</td>
	</tr>
	
	<tr>
		<td rowspan="1" style="text-align: left; padding-left: 10px; font-weight: bold;">合同类别信息</td>
		<td colspan="2">
		<input type="hidden" name="bisContShopRents[0].bisContShopRentId" value="${bisContShopRents[0].bisContShopRentId}"/>
		<input type="hidden" name="bisContShopRents[0].recordVersion" value="${bisContShopRents[0].recordVersion}"/>
		<table class="tb_noborder">
			<col width="100"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">支付方式：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" listKey="key" listValue="value" name="bisContShopRents[0].paymentModeCd"></s:select>
				</td>
				<td class="td_title">租金支付周期：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPayCycle()" listKey="key" listValue="value" name="bisContShopRents[0].payCycleCd"></s:select>
				</td>
			</tr>
			<tr>
				<td class="td_title">免租期：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContShopRents[0].freeRentPeriod" value="${bisContShopRents[0].freeRentPeriod}" />
				</td>
				<td class="td_title">租金履约保证金：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].earnestMoney" value="${bisContShopRents[0].earnestMoney}"  />
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<%-- 
	<tr>
		<td class="td_title">
			签约租金单价&nbsp;&nbsp;<br>(元/平方.月)&nbsp;&nbsp;<br>
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
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit1" value="${bisContShopRents[0].rentUnit1}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第二年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit2" value="${bisContShopRents[0].rentUnit2}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第三年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit3" value="${bisContShopRents[0].rentUnit3}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit4==null">style="display: none;"</s:if> >
				<td class="td_title">第四年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit4" value="${bisContShopRents[0].rentUnit4}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit5==null">style="display: none;"</s:if> >
				<td class="td_title">第五年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit5" value="${bisContShopRents[0].rentUnit5}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit6==null">style="display: none;"</s:if> >
				<td class="td_title">第六年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit6" value="${bisContShopRents[0].rentUnit6}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit7==null">style="display: none;"</s:if> >
				<td class="td_title">第七年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit7" value="${bisContShopRents[0].rentUnit7}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit8==null">style="display: none;"</s:if> >
				<td class="td_title">第八年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit8" value="${bisContShopRents[0].rentUnit8}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit9==null">style="display: none;"</s:if> >
				<td class="td_title">第九年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit9" value="${bisContShopRents[0].rentUnit9}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentUnit10==null">style="display: none;"</s:if> >
				<td class="td_title">第十年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentUnit10" value="${bisContShopRents[0].rentUnit10}" />
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
	<tr>
		<td class="td_title">
			签约月租金&nbsp;&nbsp;<br>(元/月)&nbsp;&nbsp;<br>
			<s:if test="#canEdit=='true'">
			<button type="button" class="btn_blue" onclick="addRentTwoItem();" >新增</button>&nbsp;&nbsp;
			</s:if>
		</td>
		<td>
		<table class="tb_noborder" id="tbRentTwoItem" >
			<col width="80"/>
			<col />
			<col width="40"/>
			<tr>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth1" value="${bisContShopRents[0].rentMonth1}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第二年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth2" value="${bisContShopRents[0].rentMonth2}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第三年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth3" value="${bisContShopRents[0].rentMonth3}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth4==null">style="display: none;"</s:if> >
				<td class="td_title">第四年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth4" value="${bisContShopRents[0].rentMonth4}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth5==null">style="display: none;"</s:if> >
				<td class="td_title">第五年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth5" value="${bisContShopRents[0].rentMonth5}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth6==null">style="display: none;"</s:if> >
				<td class="td_title">第六年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth6" value="${bisContShopRents[0].rentMonth6}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth7==null">style="display: none;"</s:if> >
				<td class="td_title">第七年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth7" value="${bisContShopRents[0].rentMonth7}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth8==null">style="display: none;"</s:if> >
				<td class="td_title">第八年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth8" value="${bisContShopRents[0].rentMonth8}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth9==null">style="display: none;"</s:if> >
				<td class="td_title">第九年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth9" value="${bisContShopRents[0].rentMonth9}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopRents.size()==0 || bisContShopRents[0].rentMonth10==null">style="display: none;"</s:if> >
				<td class="td_title">第十年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopRents[0].rentMonth10" value="${bisContShopRents[0].rentMonth10}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	--%>
	<tr>
		<td rowspan="4" style="text-align: left; padding-left: 10px; font-weight: bold;">租金生成规则</td>
		<td colspan="2">
		<table class="tb_noborder">
			<col width="100"/>
			<col width="150"/>
			<col width="110"/>
			<col width="90"/>
			<col width="60"/>
			<col width="100"/>
			<col width="130"/>
			<col />
			<col width="65"/>
			<tr>
				<td class="td_title">月租金方式：</td>
				<td>
					<s:select cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPayWay()" listKey="key" listValue="value" name="bisContShopRents[0].payWay"></s:select>
				</td>
				<td class="td_title">最晚缴款日：</td>
				<td>
					<s:select id="payMonth" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPayLastMonth()" listKey="key" listValue="value" name="bisContShopRents[0].payMonth"></s:select>
				</td>
				<td>
					<input class="inputBorder Wdate" type="text" name="bisContShopRents[0].payDay" value='${bisContShopRents[0].payDay}' onfocus="WdatePicker({dateFmt:'dd'})"/>
				</td>
				<%-- 
				<td class="td_title">租金精度：</td>
				<td>
					<s:select id="moneyScale" cssClass="inputBorder" list="#{'1':'保留2位小数','2':'精确到整数'}" listKey="key" listValue="value" name="bisContShopRents[0].moneyScale" onchange="calculateMonthRent();"></s:select>
				</td>
				--%>
				<td colspan="3"></td>
				<td><s:if test="#canEdit=='true'"><button class="btn_orange" type="button" onclick="yearIncrease();" >年增长</button></s:if></td>
			</tr>
		</table>
		</td>
	</tr>
	
	<tr>
		<td class="td_title">
			签约租金单价&nbsp;&nbsp;<br>(元/平方.月)&nbsp;&nbsp;<br>
		</td>
		<td>
		<table class="tb_noborder" id="tb_yearUnitMoney">
			<col width="60"/>
			<col />
			<col width="60"/>
			<col />
			<col width="60"/>
			<col />
			<col width="60"/>
			<col />
			<%-- 
			<tr>
				<td class="td_title" id="td_moneyTitle">第1年：</td>
				<td id="td_unitMoney">
					<input class="inputBorder money" type="text" name="bisMustRents[0].unitMoney" value="${bisMustRents[0].unitMoney}" />
				</td>
				<td class="td_title">第2年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[1].unitMoney" value="${bisMustRents[1].unitMoney}" />
				</td>
				<td class="td_title">第3年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[2].unitMoney" value="${bisMustRents[2].unitMoney}" />
				</td>
				<td class="td_title">第4年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[3].unitMoney" value="${bisMustRents[3].unitMoney}" />
				</td>
			</tr>
			--%>
			<s:iterator value="bisMustRents" var="item" status="s">
			<input type="hidden" name="bisMustRents[<s:property value="#s.index" />].bisMustRentId" value="${bisMustRentId}"/>
			<input type="hidden" name="bisMustRents[<s:property value="#s.index" />].recordVersion" value="${recordVersion}"/>
			<s:if test="#s.index%4==0">
			<tr>
			</s:if>
				<td id='td_unit_title_<s:property value="#s.index" />' class='td_title'>第<s:property value="#s.index+1" />年</td>
				<td id='td_unit_value_<s:property value="#s.index" />'>
					<input id='inp_unit_<s:property value="#s.index" />' class='inputBorder money' type='text' name='bisMustRents[<s:property value="#s.index" />].unitMoney' value="${unitMoney}" onblur='changeUnitMoney($(this));'/>
				</td>
			<s:if test="#s.index%4==3">
			</tr>
			</s:if>
			</s:iterator>
		</table>
		</td>
	</tr>
	<tr>
		<td class="td_title">
			租金总价&nbsp;&nbsp;<br>(元/月)&nbsp;&nbsp;<br>
		</td>
		<td>
		<table class="tb_noborder" id="tb_yearTotalMoney">
			<col width="60"/>
			<col />
			<col width="60"/>
			<col />
			<col width="60"/>
			<col />
			<col width="60"/>
			<col />
			<s:iterator value="bisMustRents" var="item" status="s">
			<s:if test="#s.index%4==0">
			<tr>
			</s:if>
				<td id='td_total_title_<s:property value="#s.index" />' class='td_title'>第<s:property value="#s.index+1" />年</td>
				<td id='td_total_value_<s:property value="#s.index" />'>
					<input id='inp_total_<s:property value="#s.index" />' class='inputBorder money noswap' type='text' name='bisMustRents[<s:property value="#s.index" />].totalMoney' value="${totalMoney}" onblur='formatVal($(this));'/>
				</td>
			<s:if test="#s.index%4==3">
			</tr>
			</s:if>
			</s:iterator>
			<%-- 
			<tr>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[0].totalMoney" value="${bisMustRents[0].totalMoney}" />
				</td>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[1].totalMoney" value="${bisMustRents[1].totalMoney}" />
				</td>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[2].totalMoney" value="${bisMustRents[2].totalMoney}" />
				</td>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[3].totalMoney" value="${bisMustRents[3].totalMoney}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[4].totalMoney" value="${bisMustRents[4].totalMoney}" />
				</td>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[5].totalMoney" value="${bisMustRents[5].totalMoney}" />
				</td>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[6].totalMoney" value="${bisMustRents[6].totalMoney}" />
				</td>
				<td class="td_title">第一年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisMustRents[7].totalMoney" value="${bisMustRents[7].totalMoney}" />
				</td>
			</tr>
			--%>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="3" style="color: blue;">具体免租月份请于合同审核完毕后，在应收列表中维护。</td>
	</tr>
</table>
<s:if test="#canEdit=='true'">
<script type="text/javascript">
$(function(){
	initMustRent();
});
</script>
</s:if>

<script type="text/javascript">
$(function(){
	$("#bisShopName").quickSearch("${ctx}/bis/bis-shop!quickSearch.action",['projectName','shopName','companyName','salesman','hqAddr'],{bisShopId:'bisShopId',shopName:'bisShopName',manageCd:'manageCd'},{},getShopConn);
	
	swapTotalMoney();
});

function swapTotalMoney() {
	var rentSquare = Number($("#rentSquare").val());
	$(".noswap").filter("input").each(function(i){
		var unitid = this.id.replace("total","unit");
		var origval = formatMoney(($("#"+unitid).val()*rentSquare).toFixed(2),2);
	  	$(this).wrap("<span class='pd-chill-tip' title='"+origval+"'></span>");
		if(origval != $(this).val()) {
			$(this).css("color","blue");
		}
	});
}

function initMustRent() {
	var rentyear = $("#rentyear").val();
	var sdate = $("#contStartDate").val();
	var edate = $("#contEndDate").val();
	if(isEmpty(sdate) || isEmpty(edate) || rentyear!=0) {
		return;
	}
	changeRentYears();
}

function changeRentYears() {
	
	var rentyear = $("#rentyear").val();
	var sdate = $("#contStartDate").val();
	var edate = $("#contEndDate").val();
	if(isEmpty(sdate) || isEmpty(edate)) {
		return;
	}
	var sdates = sdate.split("-");
	var edates = edate.split("-");
	var year = edates[0]-sdates[0];
	if(edates[1]>sdates[1]) {
		year++;
	} else if(edates[1]=sdates[1]) {
		if(edates[2]>=sdates[2]) {
			year++;
		}
	}
	if(isEmpty(rentyear)) {
		rentyear=0;
	}
	if(rentyear==year) {
		return;
	} else if(rentyear<year) {
		
		for(var i=rentyear; i<year; i++) {
			if(i%4==0) {
				$("#tb_yearUnitMoney").append("<tr>");
				$("#tb_yearTotalMoney").append("<tr>");
			}
			var titleHtml_unit = "<td id='td_unit_title_"+i+"' class='td_title'>"+"第"+(Number(i)+1)+"年"+"</td>";
			var inputHtml_unit = "<td id='td_unit_value_"+i+"'><input id='inp_unit_"+i+"' class='inputBorder money' type='text' name='bisMustRents["+i+"].unitMoney' onblur='changeUnitMoney($(this));'/></td>";
			var titleHtml_total = "<td id='td_total_title_"+i+"' class='td_title'>"+"第"+(Number(i)+1)+"年"+"</td>";
			var inputHtml_total = "<td id='td_total_value_"+i+"'><input id='inp_total_"+Number(i)+"' class='inputBorder money' type='text' name='bisMustRents["+i+"].totalMoney' onblur='formatVal($(this));'/></td>";
			$("#tb_yearUnitMoney tr:last").append(titleHtml_unit);
			$("#tb_yearUnitMoney tr:last").append(inputHtml_unit);
			
			$("#tb_yearTotalMoney tr:last").append(titleHtml_total);
			$("#tb_yearTotalMoney tr:last").append(inputHtml_total);
		}
	} else {
		
		for(var i=rentyear; i>year; i--) {
			$("#td_unit_title_"+(i-1)).remove();
			$("#td_unit_value_"+(i-1)).remove();
			$("#td_total_title_"+(i-1)).remove();
			$("#td_total_value_"+(i-1)).remove();
			
			if(i%4==1) {
				$("#tb_yearUnitMoney tr:last").remove();
				$("#tb_yearTotalMoney tr:last").remove();
			}
			//$("#tb_yearUnitMoney tr");
		}
	}
	$("#rentyear").val(year);
}

function changeUnitMoney(dom){
	var totalid=dom.attr("id").replace("inp_unit_","inp_total_");
	//if($("#moneyScale").val()==2) {
	//	$("#"+totalid).val((dom.val()*$("#rentSquare").val()).toFixed(0));
	//} else {
		$("#"+totalid).val((dom.val()*$("#rentSquare").val()).toFixed(2));
	//}
	formatVal(dom);
	formatVal($("#"+totalid));
}

function calculateMonthRent() {
	
	var rentSquare = $("#rentSquare").val();
	if(isEmpty(rentSquare)) {
		return;
	}
	var rentYear = $("#rentyear").val();
	for(var i=0; i<rentYear; i++) {
		var unitMoney = $("#inp_unit_"+i).val();
		if(!isEmpty(unitMoney)) {
			//if($("#moneyScale").val()==2) {
			//	$("#inp_total_"+i).val((unitMoney*rentSquare).toFixed(0));
			//} else {
				$("#inp_total_"+i).val((unitMoney*rentSquare).toFixed(2));
			//}
			formatVal($("#inp_total_"+i));
		}
	}
}

function yearIncrease() {
	var year = $("#rentyear").val();
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='yearIncreaseDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width  : 290,
		height : 200,
		title : "租金录入辅助器",
		closeBtn:true,
		winPos : 'c',
		afterShow : function() {
			var url = "${ctx}/bis/bis-cont!toYearIncrease.action";
			$.post(url, {rentYear:year}, function(result) {
				$("#yearIncreaseDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var incMode = $('#incMode').val();
				var incStartYear = $('#incStartYear').val();
				var incStep = $('#incStep').val();
				var incNum = Number($('#incNum').val());
				
				var baseMoney = $("#inp_unit_"+(incStartYear-2)).val();
				if(isEmpty(baseMoney)) {
					return;
				}
				
				for(var i=incStartYear-1; i<year; i++) {
					
					baseMoney = Number($("#inp_unit_"+(i-1)).val());
					if(incMode==1) {
						//alert(baseMoney*incNum);
						if(i%incStep==0) {
							//alert(baseMoney+"*"+(1+incNum/100)+"="+(baseMoney*(1+incNum/100)).toFixed(2));
							$("#inp_unit_"+i).val((baseMoney*(1+incNum/100)).toFixed(2));
						} else {
							$("#inp_unit_"+i).val(baseMoney);
						}
					} else if(incMode==2) {
						//alert(baseMoney+incNum);
						if(i%incStep==0) {
							$("#inp_unit_"+i).val((baseMoney+incNum).toFixed(2));
						} else {
							$("#inp_unit_"+i).val(baseMoney);
						}
					}
				}
				
				calculateMonthRent();
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'close']]
	});
}

function changeIncMode(incMode) {
	if(incMode == 1) {
		$("#spPercent").show();
	} else {
		$("#spPercent").hide();
	}
}
</script>
