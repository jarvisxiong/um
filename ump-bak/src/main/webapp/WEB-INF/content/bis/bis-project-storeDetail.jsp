<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<div class="list_header2" style="height:10px;line-height:10px;"><span style="font-size:14px;"></span></div>
<form action="${ctx}/bis/bis-project!store.action" method="post" id="storeDetaForm">
	<input type="hidden" name="bisStore.recordVersion" id="recordVersion" value="${bisStore.recordVersion}" />
	<input type="hidden" name="bisStore.statusCd" id="statusCd" value="${bisStore.statusCd}" />
	<input type="hidden" name="bisStore.bisStoreId" id="bisStoreId" value="${bisStore.bisStoreId}" />
	<input type="hidden" name="bisStoreId"  value="${bisStore.bisStoreId}" />
	<input type="hidden" name="bisStore.bisFloor.bisFloorId"  value="${bisStore.bisFloor.bisFloorId}" />
	<input type="hidden" name="bisStore.bisProjectId" id="bisProjectId" value="${bisStore.bisProjectId}" /><%-- 
	<input type="hidden" name="bisStore.virtualArea" id="virtualArea" value="${bisStore.virtualArea}" /> --%>
	<input type="hidden" name="bisStore.ifChild" id="ifChild" value="${bisStore.ifChild}" />
	<input type="hidden" name="currentPageNo" value="${currentPageNo}"/>
	<table class="main_content" style="line-height:30px;">
		<col width="140"/>
		<col />
		<col width="140"/>
		<col />
		<col width="140"/>
		<col />
		<tr>
			<td align="right">编号：</td>
			<td>
				<input type="text" id="storeNo" class="requiredStore" name="bisStore.storeNo" value="${bisStore.storeNo}" onkeyup="storeValidate(this);"/>
			</td>
			<td align="right">建筑面积㎡(图纸)：</td>
			<td>
				<input type="text" id="square" class="requiredStore" alt="amount" name="bisStore.square" value="${bisStore.square}"/>
			</td>
			<td align="right">套内面积㎡(图纸)：</td>
			<td>
				<input type="text" id="innerSquare" class="requiredStore" alt="amount" name="bisStore.innerSquare" value="${bisStore.innerSquare}"/>
			</td>
		</tr>
		<tr>
			<td align="right">建筑面积㎡(实测)：</td>
			<td>
				<input type="text" id="square" alt="amount" name="bisStore.squareReal" value="${bisStore.squareReal}"/>
			</td>
			<td align="right">套内面积㎡(实测)：</td>
			<td>
				<input type="text" id="innerSquare" alt="amount" name="bisStore.innerSquareReal" value="${bisStore.innerSquareReal}"/>
			</td>
			<td align="right">计租面积㎡：</td>
			<td> 
				<input type="text" id="rentSquare" class="requiredStore" alt="amount" name="bisStore.rentSquare" value="${bisStore.rentSquare}" />
			</td>
		</tr>
		<tr>
			
			<td align="right">销售单价(元/㎡)：</td>
			<td>
				<input type="text" id="sellPrice" alt="amount" name="bisStore.sellPrice" value="${bisStore.sellPrice}"/>
			</td>
			<td align="right">开业日期：</td>
			<td>
			<%-- onblur="updateContStatus('plan');" --%>
				<s:textfield name="bisStore.openDate"  onfocus="WdatePicker()" />
			</td>
			<td align="right">商铺定位：</td>
			<td>
			<s:select  cssClass="requiredStore" list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key" listValue="value" name="bisStore.shopPosition"></s:select>
			</td>
		</tr>
		<tr>
			<td align="right">规划业态：</td>
			<td>
				<s:select  cssClass="requiredStore" list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key" listValue="value" name="bisStore.layoutCd" id="layoutCd" ></s:select>
			</td>
			<td align="right">租金标准(元/㎡)：</td>
			<td>
				<input type="text" id="sellPrice" class="requiredStore"  name="bisStore.rentStandard" value="${bisStore.rentStandard}" maxlength="20"/>
			</td>
			<td align="right">物业标准(元/㎡)：</td>
			<td>
				<input type="text" id="sellPrice" class="requiredStore" alt="amount" name="bisStore.tenemStandard" value="${bisStore.tenemStandard}"/>
			</td>
		</tr>
		<tr>
			<td align="right" >产权性质：</td>
			<td >
			<s:select  cssClass="requiredStore" list="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" listKey="key" listValue="value" name="bisStore.equityNature" id="equityNature1" onchange="showStatus(this);" ></s:select>
			</td>
			
			<td colspan="2"></td>
		</tr>
		<tr>
			<td align="right">
			    <span class="propRight" style="display:none"> 业主：</span>
			</td>
			<td>
				 <input class="propRight requiredStore" style="display:none" type="text" id="owner1" name="bisStore.owner" value="${bisStore.owner}"/>
			</td>
			<td align="right">
			    <span class="propRight" style="display:none"> 经营现状：</span>
			</td>
			<td>
				 <s:select id="manageStatus1" cssClass="propRight requiredStore" cssStyle="display:none" list="@com.hhz.ump.util.DictMapUtil@getBisManagementStatus()" listKey="key" listValue="value" name="bisStore.managementStatus"></s:select>
			</td>
			<td align="right">
				<span class="propRight" style="display:none">是否开业：</span> 
			</td>
			<td>
			  <select id="ifPractice1" name="bisStore.ifPractice"  class="propRight requiredStore" style="display:none">
			     <option value="">--选择--</option>
                    <option value="1" <s:if test="bisStore.ifPractice==1">selected</s:if> >已开业</option>
                    <option value="2" <s:if test="bisStore.ifPractice==2">selected</s:if>>未开业</option>
			  </select>
			</td>
		</tr>
		<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
		<s:if test="#curUserCd == 'dengyh'">
		<tr>
			<td align="right" >所在平面图位置：</td>
			<td colspan="3">
			<textarea style="width:100%;height:15px;" id="coords" name="bisStore.coords"   >${bisStore.coords}</textarea>
			</td>
			<td colspan="2"><input style="margin-left:4px;" class="btn_blue" onclick="updateStoreInfo('${bisStore.bisStoreId}')" type="button" value="更新"/></td>
		</tr>
		<tr id="virtualArea1" >
			<td align="right" >逻辑分区：</td>
			<td colspan="3">
			<textarea style="width:100%;height:15px;" id="virtualArea" name="bisStore.virtualArea"   >${bisStore.virtualArea}</textarea>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr id="virtualArea1" style="display:none;">
			<td align="right" >所在逻辑分区名：</td>
			<td colspan="3">
			<textarea style="width:100%;height:15px;" name="virtualFloor.remark" >${virtualFloor.remark}</textarea>
			</td>
			<td colspan="2"></td>
		</tr>
		</s:if>
		<s:else>
			<input type="hidden" name="bisStore.virtualArea" id="virtualArea" value="${bisStore.virtualArea}" />
			<input type="hidden" name="bisStore.coords"  value="${bisStore.coords}" />
		</s:else>
		<tr>
			<td align="right" >备注：</td>
			<td colspan="3">
			<textarea style="width:100%;height:40px;" name="bisStore.storeRemark">${bisStore.storeRemark}</textarea>
			</td>
			<td colspan="2"></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
$(function(){
	showStatus('#equityNature1');
});

function showStatus(dom){
	if("2"==$(dom).val()){
		$(".propRight").show();
	}else{
		$(".propRight").val("").hide();
	}
}

</script>