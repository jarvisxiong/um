<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<style>
	.td_class td{height:30px;}
	.td1{width:50px;}
	.td2{width:60px;}
	.td1 div{width:50px;}
	.td2 div{width:60px;}
	.font2{font-size:7px;}
	.font1{font-size:12px;}
</style>
<div style="position:absolute;" style="width:120px;">
	<table cellpadding="0" cellspacing="0" border="0" width="120px">
		<tr>
			<th><div style="width:120px; height:30px;">&nbsp;</div></th>
	 	</tr>
		<tr>
			<th nowrap="nowrap"><div style="width:120px; height:30px; text-align:right; padding-right:4px;">费用类型</div></th>
	 	</tr>
		<s:iterator value="resultLis">
			<tr class="tdclass">
				<td><div style="width:120px; height:30px; text-align:right; padding-right:4px;"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" value="chargeTypeCd"/></div></td>
			</tr>
		</s:iterator>
	</table>
</div>
<table id="tenantFeeDiv" width="1920px" class="innerTable" cellpadding="0" cellspacing="0">
  <col width="120px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <col width="50px"/>
  <col width="50px"/>
  <col width="60px"/>
  <tr>
		<th width="120px"><div style="width:120px;">&nbsp;</div></th>
	  <th colspan="3" style="text-align:center; <s:if test="1==nowMonth">background-color:#6EB1CF;</s:if>">1月</th>
	  <th colspan="3" style="text-align:center; <s:if test="2==nowMonth">background-color:#6EB1CF;</s:if>"">2月</th>
	  <th colspan="3" style="text-align:center; <s:if test="3==nowMonth">background-color:#6EB1CF;</s:if>"">3月</th>
	  <th colspan="3" style="text-align:center; <s:if test="4==nowMonth">background-color:#6EB1CF;</s:if>"">4月</th>
	  <th colspan="3" style="text-align:center; <s:if test="5==nowMonth">background-color:#6EB1CF;</s:if>"">5月</th>
	  <th colspan="3" style="text-align:center; <s:if test="6==nowMonth">background-color:#6EB1CF;</s:if>"">6月</th>
	  <th colspan="3" style="text-align:center; <s:if test="7==nowMonth">background-color:#6EB1CF;</s:if>"">7月</th>
	  <th colspan="3" style="text-align:center; <s:if test="8==nowMonth">background-color:#6EB1CF;</s:if>"">8月</th>
	  <th colspan="3" style="text-align:center; <s:if test="9==nowMonth">background-color:#6EB1CF;</s:if>"">9月</th>
	  <th colspan="3" style="text-align:center; <s:if test="10==nowMonth">background-color:#6EB1CF;</s:if>"">10月</th>
	  <th colspan="3" style="text-align:center; <s:if test="11==nowMonth">background-color:#6EB1CF;</s:if>"">11月</th>
	  <th colspan="3" style="text-align:center; <s:if test="12==nowMonth">background-color:#6EB1CF;</s:if>"">12月</th>
	</tr>

	<tr>
	  <td width="120px"><div style="width:120px;">&nbsp;</div></td>
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	  <td><div class="td1">应收</div></td>
	  <td><div class="td1">实收</div></td>
	  <td><div style="width:55px;">收缴率</div></td> 
	</tr>
	<s:iterator value="resultLis">

	<tr class="td_class">
		<td width="120px;">&nbsp;</td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${oneMustMoney}">${oneMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${oneFactMoney}">${oneFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${oneMoneyRate}%">${oneMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${twoMustMoney}">${twoMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${twoFactMoney}">${twoFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${twoMoneyRate}%">${twoMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${thrMustMoney}">${thrMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${thrFactMoney}">${thrFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${thrMoneyRate}%">${thrMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${fouMustMoney}">${fouMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${fouFactMoney}">${fouFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${fouMoneyRate}%">${fouMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${fivMustMoney}">${fivMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${fivFactMoney}">${fivFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${fivMoneyRate}%">${fivMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${sixMustMoney}">${sixMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${sixFactMoney}">${sixFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${sixMoneyRate}%">${sixMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${sevMustMoney}">${sevMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${sevFactMoney}">${sevFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${sevMoneyRate}%">${sevMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${eigMustMoney}">${eigMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${eigFactMoney}">${eigFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${eigMoneyRate}%">${eigMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${ninMustMoney}">${ninMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${ninFactMoney}">${ninFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${ninMoneyRate}%">${ninMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${tenMustMoney}">${tenMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${tenFactMoney}">${tenFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${tenMoneyRate}%">${tenMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${eleMustMoney}">${eleMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${eleFactMoney}">${eleFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${eleMoneyRate}%">${eleMoneyRate}%</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${tweMustMoney}">${tweMustMoney}</div></td>
	  <td class="td1" ><div class="ellipsisDiv_full"  title="${tweFactMoney}">${tweFactMoney}</div></td>
	  <td class="td2" ><div class="ellipsisDiv_full"  title="${tweMoneyRate}%">${tweMoneyRate}%</div></td>
	</tr>
	</s:iterator>
</table>
<div id="div_manageCd" style="display:none;"><s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key" listValue="value" name="shopStoreVo.manageCd" disabled="true"></s:select></div>
<div id="div_layoutCd" style="display:none;"><s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key" listValue="value" name="shopStoreVo.layoutCd" disabled="true"></s:select></div>
<div id="div_equityNature" style="display:none;">
    <s:select validate="required" cssClass="inputBorder" list="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" listKey="key" listValue="value" name="shopStoreVo.equityNature" disabled="true">
    </s:select>
</div>

<script language="javascript">
	$('#bisShopId').val("${shopStoreVo.bisShopId}");
	$('#td_nameCn').html("${shopStoreVo.nameCn}");
	$('#td_manageCd').html($('#div_manageCd').html());
	$('#td_layoutCd').html($('#div_layoutCd').html());

	$('#td_equityNature').html($('#div_equityNature').html());

	$('#td_rentDate').html("${shopStoreVo.rentStartDate}"+"至"+"${shopStoreVo.rentEndDate}");
	$('#td_storeNo').html("${shopStoreVo.storeNo}");
	$('#td_storeNo1').html("${shopStoreVo.storeNo}");
	$('#td_owner').html("${owners}");
	$('#td_storeNo').attr("title","${shopStoreVo.storeNo}");
	$('#td_storeNo1').attr("title","${shopStoreVo.storeNo}");
	$('#td_owner').attr("title","${owners}");
	$('#td_powerSquare').html("${shopStoreVo.powerSquare}m²");
	$('#td_factSquare').html("${shopStoreVo.factSquare}m²");
	$('#td_storelayoutCds').html(loadStoreLayout());
	function loadStoreLayout(){
		var storeLayoutHtml = '<ul >';
		<s:iterator value="storeLayoutCds" status="st">
			storeLayoutHtml+='<li class="li_store_layoutcd" layoutcd=${id}><div title="<p>${text}</p><br><p>规划业态：${key}</p>" class="ellipsisDiv pd-chill-tip" >[${key}]${text}</div></li>';
		</s:iterator>
	
		return storeLayoutHtml+'</ul>';
	}

	$(document).ready(function(){
		$('tr[class=td_class]').find('.ellipsisDiv_full').each(function(){
			var html = $(this).html();
			if(html=='0'||html=='0%'){
				$(this).html('');
			}
		});
		$('.li_store_layoutcd').bind('click',function(){
			var url = "${ctx}/bis/bis-shop!main.action?shopSortId="+$(this).attr('layoutcd');
			parent.TabUtils.newTab("bis-shop-input","商家信息",url,true);
		}).bind('mouseout',function(){
			if($(this).attr('valu')!='n'){
				$(this).css('text-decoration','underline');
			}
		}).bind('mouseover',function(){
			if($(this).attr('valu')!='n')
			$(this).css('text-decoration','none');});
		});
</script>
