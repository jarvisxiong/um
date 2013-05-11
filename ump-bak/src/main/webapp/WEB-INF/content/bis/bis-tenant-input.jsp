<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<input type="hidden" name="bisTenantId" value="${bisTenantId}" />
<input type="hidden" name="recordVersion" value="${recordVersion}" />
<input type="hidden" name="statusCd" value="${statusCd}" />
<input type="hidden" name="TypeCd" value="${TypeCd}" />
<input type="hidden" name="debtBl" value="${debtBl}" />
<input type="hidden" name="reportBl" value="${reportBl}" />
<input type="hidden" name="debtpreviouyear" value="${debtpreviouyear}" />
<input type="hidden" name="debtcurryear" value="${debtcurryear}" />
<input type="hidden" name="debtcurrmonth" value="${debtcurrmonth}" />
<input type="hidden" name="debtlevel" value="${debtlevel}" />
<input type="hidden" name="summust" value="${summust}" />
<input type="hidden" name="sumfact" value="${sumfact}" />
<input type="hidden" name="creator" value="${creator}" />
<input type="hidden" name="createdCenterCd" value="${createdCenterCd}" />
<input type="hidden" name="createdDeptCd" value="${createdDeptCd}" />
<input type="hidden" name="createdPositionCd" value="${createdPositionCd}" />
<input type="hidden" name="createdDate" value="${createdDate}" />

<div style="font-size:12px;padding-bottom:10px; line-height:30px;">
	<table class="tb_noborder" style="width:95%;">
		<col width="100"/>
		<col />
		<col width="100"/>
		<col />
		<col width="100"/>
		<col />
		<tr>
			<td style="text-align: right;">项目：</td>
			<td>
				<input validate="required" class="inputBorder" type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;" />
				<input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
			</td>
			<td style="text-align: right;">品牌：</td>
			<td>
				<input type="hidden" id="bisShopId" name="bisShopId" value="${bisShopId}" />
				<input class="inputBorder search" searchtext="搜索品牌" type="text" id="bisShopName" value="${bisShopName}" style="width:100%;"/>
			</td>
			<td style="text-align: right;">经销商名称：</td>
			<td>
				<s:select cssClass="inputBorder" cssStyle="width:100%;" list="mapBisShopConn" listKey="key" listValue="value" id="bisShopConnId" name="bisShopConnId" ></s:select>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">商铺：</td>
			<td>
				<input validate="required" type="hidden" id="storeIds" name="storeIds" value="${storeIds}"  />
				<input validate="required" class="inputBorder search" style="cursor:pointer; width:100%;" searchtext="搜索商铺" type="text" name="storeNos" id="storeNos" value="${storeNos}" onclick="doStoreSelect('storeIds','storeNos',1)"/>
			</td>
			<td style="text-align: right;">开始日期：</td>
			<td>
				<input validate="required" class="inputBorder Wdate" type="text" name="rentStartDate" value='<s:date name="rentStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" style="width: 100%;"/>
			</td>
			<td style="text-align: right;">开始日期：</td>
			<td>
				<input validate="required" class="inputBorder Wdate" type="text" name="rentEndDate" value='<s:date name="rentEndDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" style="width: 100%;"/>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">业主：</td>
			<td>
				<input class="inputBorder" type="text" id="owner" name="owner" value='${owner}' style="width: 100%;"/>
			</td>
			<td style="text-align: right;">经营性质：</td>
			<td>
				<s:select validate="required" cssClass="inputBorder" cssStyle="width:100%;" list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key" listValue="value" name="manageCd"></s:select>
			</td>
			<td style="text-align: right;">业态：</td>
			<td>
				<s:select cssClass="inputBorder" cssStyle="width:100%;" list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key" listValue="value" name="layoutCd"></s:select>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">在平面图显示：</td>
			<td>
				<s:select cssClass="inputBorder" cssStyle="width:100%;" list="#{'true':'是','false':'否'}" listKey="key" listValue="value" name="imageBl" ></s:select>
			</td>
			<td style="text-align: right;">状态：</td>
			<td>
				<s:select cssClass="inputBorder" cssStyle="width:100%;" disabled="true" list="#{'true':'有效','false':'无效'}" listKey="key" listValue="value" name="activeBl" ></s:select>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="5" style="padding-top: 10px;">
				<%-- 
				<button type="button" class="btn_green" onclick="doSaveTenant();" >保存</button>
				<button type="button" class="btn_red" onclick="doCancel();" >取消</button>
				--%>
				<button type="button" class="btn_blue" style="width:75px;" onclick="addBisCont('${bisTenantId}');" >新增合同</button>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
$(function(){
	$('#bisProjectName').onSelect({muti:false});
	$("#bisShopName").quickSearch("${ctx}/bis/bis-shop!quickSearch.action",['shopName','companyName'],{bisShopId:'bisShopId',shopName:'bisShopName'},{},getShopConn);
	
	$("#processForm *").filter("[validate*=required]").addClass("required");
	
	bindSearchEv();
});

/**
 * 联动搜索经销商
 */
function getShopConn(){
	var shopId = $('#bisShopId').val();
	var subSele = $('#bisShopConnId');
	subSele.empty();
	
	var url = _ctx+"/bis/bis-shop!getMapShopConn.action";
	$.post(url,{id:shopId},function(data){
		var data = eval('('+data+')');
		var len = 0;
		if(Boolean(data)){
			for(i in data)len++;
		}
		if(len != 1) {
			subSele.append('<option value="">--选择--</option>');
		}
		$.each(data,function(i,n){
			var option = '<option value="'+i+'">'+n+'</option>';
			subSele.append(option);
		});
		//_hs.val(_hs.attr('tempValue'));
	});
};
</script>