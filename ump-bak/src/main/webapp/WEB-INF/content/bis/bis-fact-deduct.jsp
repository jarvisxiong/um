<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<form action="${ctx}/bis/bis-fact!save.action" id="inputForm">
<legend>
	<s:if test="inputStatus==2"><!-- 抵扣 -->
		<span style="height:10px;width:90%;display:block"></span>
	</s:if>
</legend>
	<table width="90%" height="90%" class="sup_table" style="width:100%;line-height:30px;">
	<input type="hidden" id="id" name="id" value="${bisFactId}"/>
	<input type="hidden" id="bisFactId" name="id" value="${voFact.bisFactId}"/>
	<input type="hidden" id="currDetail" name="currDetail" value="${currDetail}"/>
	<input type="hidden" id="bisShopId" name="bisShopId" value="${voFact.bisShopId}"/>
	<input type="hidden" id="bisStoreId" name="bisStoreId" value="${voFact.bisStoreId}"/>
	<input type="hidden" id="currDetailName" name="currDetailName" value="${currDetailName}"/>
	<input type="hidden" id="bisContIdInput" name="bisContIdInput"/> 
		<tr  >
			<td align="right">
				项目：
			</td>
			<td>
				<input readonly id="bisProjectNameInput" value="${bisProjectName}" style="width:120px;cursor: pointer; font-size: 12px; color: #ff0000;" type="text"/>
				<input type="hidden" id="bisProjectIdInput"  value="${voFact.bisProjectId}"/>
			</td>
			<td align="right">
				业态：
			</td>
			<td><s:select  validate="required" 
					list="@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()" listKey="key" listValue="value" 
					name="voFact.contLayOutCd" id="layOutCdInput" onchange="changlayOutDetail2();" required="true"  disabled="true"   ></s:select>
			</td>
			<td align="right">
				<div id="currDetailLabel">租户:</div>
			</td>
			<td><input  style="color:#aca899"	value="${currDetailName }"	name="currDetail"  		id="currDetail"  readonly   />
			</td>
			<td align="right">
				类别：
			</td>
			<td><s:select cssClass="select_115_20" validate="required" 
			list="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" listKey="key" listValue="value" 
			name="voFact.chargeTypeCd" id="chargeTypeCdInput" disabled="true"   ></s:select>
			</td>
		</tr>
		<tr>
		<td align="right">
				年：
			</td>
			<td><s:select  validate="required" 
					list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value" 
					name="voFact.factYear" id="factYearOld" disabled="true"></s:select>
			</td>
			<td align="right">
				月：
			</td>
			<td><s:select validate="required" 
				list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key" listValue="value" 
				name="voFact.factMonth" id="factMonthOld" disabled="true"></s:select>
			</td>
			<td align="right">
				余额：
			</td>
			<td>
				<input type='text' id='balance' style="color:#aca899" name='balance' class="easyui-numberbox select_115_20"   value="${voFact.money }" readonly/>
			</td>
			<td colspan="2">
			</td>
		</tr>
		<tr>
			<td colspan="8"><div style="width:100%;height:30px;background-color:#eee"/></td>
		</tr>
		<tr>
			<td align="right">
				年：
			</td>
			<td><s:select validate="required" 
					list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value" 
					name="factYear" id="factYear" ></s:select>
			</td>
			<td align="right">
				月：
			</td>
			<td><s:select validate="required" 
				list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key" listValue="value" 
				name="factMonth" id="factMonth"></s:select>
			</td>
			<td align="right">实收金额：
			</td>
			<td>
				<input type='text' id='money' name='money' class="easyui-numberbox"   />
			</td>
			<td colspan="2">
			</td>
		</tr>
		<tr  >
			<td colspan="1"></td>
			<td colspan="2">
				<button type="button" class="btn_green_55_20" onclick="saveDeduct('inputForm');" >保存</button>
				<button type="button" class="btn_red_55_20" onclick="ymPromptClose();">取消</button>
			</td>
			<td colspan="2"></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
<!--
var text ;
switch($('#layOutCdInput').val()){
	case '1':text='租户：';break;
	case '2':text='公寓：';break;
	case '3':text='多经：';break;
}
$('#currDetailLabel').html('');
$('#currDetailLabel').html(text);

//-->
</script>
