<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

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
				<td class="td_title">楼栋名称：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="buildingName" value="${buildingName}" />
				</td>
				<td class="td_title">商铺：</td>
				<td>
					<input validate="required" type="hidden" id="bisStoreIds" name="bisStoreIds" value="${bisStoreIds}"  />
					<input validate="required" class="inputBorder search must" style="cursor:pointer;" searchtext="搜索商铺" type="text" name="bisContShopEntrs[0].storeNo" id="bisStoreNos" value="${bisStoreNos}" onclick="doStoreSelect('bisStoreIds','bisStoreNos')"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">建筑面积：</td>
				<td>
					<input class="inputBorder" type="text" id="square" name="square" value="${square}" readonly="readonly" />
				</td>
				<td class="td_title">套内面积：</td>
				<td>
					<input class="inputBorder" type="text" id="innerSquare" name="innerSquare" value="${innerSquare}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td class="td_title">计租面积：</td>
				<td>
					<input class="inputBorder" type="text" id="rentSquare" name="rentSquare" value="${rentSquare}" />
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
				<td class="td_title">业态：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key" listValue="value" name="layoutCd"></s:select>
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
		<td rowspan="3" style="text-align: left; padding-left: 10px; font-weight: bold;">合同类别信息</td>
		<td colspan="2">
		<input type="hidden" name="bisContShopEntrs[0].bisContShopEntrId" value="${bisContShopEntrs[0].bisContShopEntrId}"/>
		<input type="hidden" name="bisContShopEntrs[0].recordVersion" value="${bisContShopEntrs[0].recordVersion}"/>
		<table class="tb_noborder">
			<col width="100"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">支付方式：</td>
				<td>
					<s:select validate="required" cssClass="inputBorder" cssStyle="width: 100%" list="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" listKey="key" listValue="value" name="bisContShopEntrs[0].paymentModeCd"></s:select>
				</td>
				<td class="td_title">保证金：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].earnestMoney" value="${bisContShopEntrs[0].earnestMoney}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">免租期：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContShopEntrs[0].freeRentPeriod" value="${bisContShopEntrs[0].freeRentPeriod}" />
				</td>
				<td class="td_title">付款日期：</td>
				<td>
					<input class="inputBorder" type="text" name="bisContShopEntrs[0].paymentTime" value='${bisContShopEntrs[0].paymentTime}' />
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="td_title">
			租金(元/月)&nbsp;&nbsp;<br>
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
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent1" value="${bisContShopEntrs[0].rent1}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第二年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent2" value="${bisContShopEntrs[0].rent2}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第三年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent3" value="${bisContShopEntrs[0].rent3}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent4==null">style="display: none;"</s:if> >
				<td class="td_title">第四年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent4" value="${bisContShopEntrs[0].rent4}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent5==null">style="display: none;"</s:if> >
				<td class="td_title">第五年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent5" value="${bisContShopEntrs[0].rent5}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent6==null">style="display: none;"</s:if> >
				<td class="td_title">第六年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent6" value="${bisContShopEntrs[0].rent6}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent7==null">style="display: none;"</s:if> >
				<td class="td_title">第七年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent7" value="${bisContShopEntrs[0].rent7}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent8==null">style="display: none;"</s:if> >
				<td class="td_title">第八年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent8" value="${bisContShopEntrs[0].rent8}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent9==null">style="display: none;"</s:if> >
				<td class="td_title">第九年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent9" value="${bisContShopEntrs[0].rent9}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentOne" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rent10==null">style="display: none;"</s:if> >
				<td class="td_title">第十年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rent10" value="${bisContShopEntrs[0].rent10}" />
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
			委托金比例(%)&nbsp;&nbsp;<br>
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
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio1" value="${bisContShopEntrs[0].rentRatio1}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第二年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio2" value="${bisContShopEntrs[0].rentRatio2}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr>
				<td class="td_title">第三年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio3" value="${bisContShopEntrs[0].rentRatio3}" />
				</td>
				<td align="center"></td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio4==null">style="display: none;"</s:if> >
				<td class="td_title">第四年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio4" value="${bisContShopEntrs[0].rentRatio4}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio5==null">style="display: none;"</s:if> >
				<td class="td_title">第五年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio5" value="${bisContShopEntrs[0].rentRatio5}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio6==null">style="display: none;"</s:if> >
				<td class="td_title">第六年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio6" value="${bisContShopEntrs[0].rentRatio6}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio7==null">style="display: none;"</s:if> >
				<td class="td_title">第七年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio7" value="${bisContShopEntrs[0].rentRatio7}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio8==null">style="display: none;"</s:if> >
				<td class="td_title">第八年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio8" value="${bisContShopEntrs[0].rentRatio8}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio9==null">style="display: none;"</s:if> >
				<td class="td_title">第九年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio9" value="${bisContShopEntrs[0].rentRatio9}" />
				</td>
				<td align="center">
					<s:if test="#canEdit=='true'">
					<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
				</td>
			</tr>
			<tr class="trRentTwo" <s:if test="bisContShopEntrs.size()==0 || bisContShopEntrs[0].rentRatio10==null">style="display: none;"</s:if> >
				<td class="td_title">第十年：</td>
				<td>
					<input class="inputBorder money" type="text" name="bisContShopEntrs[0].rentRatio10" value="${bisContShopEntrs[0].rentRatio10}" />
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
</table>

<script type="text/javascript">
	$("#bisShopName").quickSearch("${ctx}/bis/bis-shop!quickSearch.action",['shopName','companyName'],{bisShopId:'bisShopId',shopName:'bisShopName'},{},getShopConn);
</script>
