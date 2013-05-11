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
		<td>
		<input type="hidden" name="equityNature" value="2"/>
		<table class="tb_noborder">
			<col width="120"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">业主：</td>
				<td>
					<input validate="required" class="inputBorder must" type="text" id="owner" name="owner" value="${owner}" />
				</td>
				<td class="td_title">楼栋名称：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="buildingName" value="${buildingName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">商铺：</td>
				<td colspan="3">
					<input validate="required" type="hidden" id="bisStoreIds" name="bisStoreIds" value="${bisStoreIds}" />
					<input validate="required" class="inputBorder search must" style="cursor:pointer;" searchtext="搜索商铺" type="text" name="bisContShopProps[0].storeNo" id="bisStoreNos" value="${bisStoreNos}" onclick="doStoreSelect('bisStoreIds','bisStoreNos');"/>
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
					<input class="inputBorder" type="text" id="showSquare" name="showSquare" value="${showSquare}" readonly="readonly" />
				</td>
				<td class="td_title">实际收费面积：</td>
				<td>
					<input validate="required" class="inputBorder" type="text" id="rentSquare" name="rentSquare" value="${rentSquare}" onchange="onchangeRentSquare();"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同开始日期：</td>
				<td>
					<input validate="required" class="inputBorder Wdate must" type="text" id="contStartDate" name="contStartDate" value='<s:date name="contStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()"/>
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
		</table>
		</td>
	</tr>
	<tr>
		<td style="text-align: left; padding-left: 10px; font-weight: bold;">应收生成规则</td>
		<td>
		<input type="hidden" name="bisMustFormulas[0].bisMustFormulaId" value="${bisMustFormulas[0].bisMustFormulaId}">
		<input type="hidden" name="bisMustFormulas[0].recordVersion" value="${bisMustFormulas[0].recordVersion}">
		<table class="tb_noborder" id="tbMustFormula">
			<col width="120"/>
			<col />
			<col width="110"/>
			<col />
			<tr>
				<td class="td_title">物业费类别：</td>
				<td colspan="3" >
					<input type="hidden" name="bisMustFormulas[0].chargeTypeCd" id="formulaChargeType" value="${bisMustFormulas[0].chargeTypeCd}" >
					<ul class="nav_bis_formula" id="ul_formula_charge" style="float:left; width: 100%;">
						<li value="3" <s:if test='bisMustFormulas[0].chargeTypeCd==3'>class="nav_bis_formula_click"</s:if> onclick="onclickFormulaCharge(this);">综合管理费</li>
						<li value="4" <s:if test='bisMustFormulas[0].chargeTypeCd==4'>class="nav_bis_formula_click"</s:if> onclick="onclickFormulaCharge(this);">物业费</li>
					</ul>
				</td>
			</tr>
			<tr id="tr_formula" style="display: none;">
				<td class="td_title">计算公式：</td>
				<td colspan="3" >
					<input type="hidden" name="bisMustFormulas[0].formula" id="formula" value="${bisMustFormulas[0].formula}" >
					<ul class="nav_bis_formula" id="ul_formula" style="float:left; width: 100%;">
						<li id="li_formula_1" value="1" <s:if test='bisMustFormulas[0].formula==1'>class="nav_bis_formula_click"</s:if> onclick="onclickFormula(this);">按面积计算</li>
					</ul>
				</td>
			</tr>
			<tr id="tr_formula_detail1" style="display: none;">
				<td class="td_title">月单价(元/㎡)：</td>
				<td>
					<input class="inputBorder money" type="text" id="formulaUnitPrice" name="bisMustFormulas[0].unitPrice" value="${bisMustFormulas[0].unitPrice}" onchange="calculatePrice();"/>
				</td>
				<td class="td_title">面积(㎡)：</td>
				<td>
					<input class="inputBorder" type="text" id="formulaSquare" name="bisMustFormulas[0].formulaSquare" value="${rentSquare}" readonly="readonly"/>
				</td>
			</tr>
			<tr id="tr_formula_detail2" style="display: none;">
				<td class="td_title">月总价(元)：</td>
				<td>
					<input validate="required" class="inputBorder money" type="text" id="formulaTotalPrice" name="bisMustFormulas[0].totalPrice" value="${bisMustFormulas[0].totalPrice}" />
				</td>
				<td class="td_title">参考价(元)：</td>
				<td>
					<input class="inputBorder" type="text" id="formulaCountPrice" name="bisMustFormulas[0].countPrice" value="${bisMustFormulas[0].countPrice}" readonly="readonly"/>
				</td>
			</tr>
			<tr id="tr_formula_detail3" style="display: none;">
				<td class="td_title">每月最晚缴款日：</td>
				<td>
					<input validate="required" class="inputBorder Wdate" type="text" id="formulaDate" name="bisMustFormulas[0].payDay" value='${bisMustFormulas[0].payDay}' onfocus="WdatePicker({dateFmt:'dd'})"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<script type="text/javascript">
	//$("#owner").quickSearch("${ctx}/bis/bis-tenant!quickSearch.action",['owner','storeNos'],{bisTenantId:'bisTenantId',owner:'owner',storeIds:'bisStoreIds',storeNos:'bisStoreNos',square:'square',innerSquare:'innerSquare',rentSquare:'rentSquare'});
	
	function loadFormula() {
		if(!isEmpty($("#formulaChargeType").val())) {
			$("#tr_formula").show();
		}
		if(!isEmpty($("#formula").val())) {
			$("#tr_formula_detail1").show();
			$("#tr_formula_detail2").show();
			$("#tr_formula_detail3").show();
		}
	}
	
	function onclickFormulaCharge(dom) {
		$("#ul_formula_charge li").removeClass("nav_bis_formula_click");
		$(dom).addClass("nav_bis_formula_click");
		$("#formulaChargeType").val($(dom).attr("value"));
		$("#tr_formula").show();
		
		$('#li_formula_1').trigger("onclick");
	}
	
	function onclickFormula(dom) {
		$("#ul_formula li").removeClass("nav_bis_formula_click");
		$(dom).addClass("nav_bis_formula_click");
		$("#formula").val($(dom).attr("value"));
		
		$("#formulaSquare").val($("#rentSquare").val());
		//if(isEmpty($("#formulaDate").val())) {
		//	$("#formulaDate").val($("#contStartDate").val());
		//}
		
		$("#tr_formula_detail1").show();
		$("#tr_formula_detail2").show();
		$("#tr_formula_detail3").show();
	}
	
	function onchangeUnitPrice(dom) {
		$("#formulaCountPrice").val(($(dom).val()*$("#formulaSquare").val()).toFixed(2));
		$("#formulaTotalPrice").val(($(dom).val()*$("#formulaSquare").val()).toFixed(2));
		$("#formulaTotalPrice").css({color:"#000000"});
	}
	
	function calculatePrice() {
		if(!isEmpty($("#formulaUnitPrice").val()) && !isEmpty($("#formulaSquare").val())) {
			$("#formulaCountPrice").val(($("#formulaUnitPrice").val()*$("#formulaSquare").val()).toFixed(2));
			$("#formulaTotalPrice").val(($("#formulaUnitPrice").val()*$("#formulaSquare").val()).toFixed(2));
			$("#formulaTotalPrice").css({color:"#000000"});
		}
	}
	
	function onchangeRentSquare() {
		$("#formulaSquare").val($("#rentSquare").val());
		calculatePrice();
	}
	
</script>

<script type="text/javascript">
	loadFormula();
</script>

<s:if test="#canEdit!='true'">
<script type="text/javascript">
	$("#tbMustFormula").find("li").attr("onclick","");
</script>
</s:if>

<s:if test="changeBl">
<script type="text/javascript">
	$("#owner").attr("readonly","readonly");
	$("#owner").addClass("inputBorder_readOnly");
</script>
</s:if>
