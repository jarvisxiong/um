<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<style type="text/css">
<!--
.addFirst {
	background-color: #61becf;
	color:#ffffff;
}
-->
</style>
<div style="border: 1px solid #cccccc; margin-top:20px; margin-left: 8px; margin-right: 8px;">
   	<div style= " cursor:hand ;overflow-y:scroll;word-break:break-all; height:210px;" id ="abcd" >
			<input type="hidden" id="bisTenantId" >
			<input type="hidden" id="tenantName" >
		    <table id="mainTable" border="0" cellpadding="0" cellspacing="0" >
			    <tr>
				    <th style="padding-left: 5px;">商铺号&nbsp;</th>
				    <th>商家姓名</th>
			    </tr>
			    <s:iterator value="shopStoreList"  status="status">
			    <tr id="${bisTenantId}_TD" ">
					<td   style="padding-left: 5px;" align="left" class="pd-chill-tip" title='${storeNo}'  onclick="selShopStore('${bisTenantId}','${nameCn}-${storeNo }');">${storeNo}
					</td>
					<td  align="left" class="pd-chill-tip" title='${nameCn}' onclick="selShopStore('${bisTenantId}','${nameCn}-${storeNo }');"><div class="ellipsisDiv_full" >${nameCn}</div></td>
			    </tr>
			    </s:iterator>
		    </table>

	</div>
</div>
<script type="text/javascript">
function getTenantName(){
	 return $('#tenantName').val();
}
function  getTenantId(){
	 return $('#bisTenantId').val();
}
function selShopStore(bisTenantId,tenantName){
//	alert($('#'+uniqueId).val());
	//window.open("bis-tenant!main.action?bisTenantId="+bisTenantId);
	parent.TabUtils.newTab("123","租户信息",_ctx+"/bis/bis-tenant!main.action?bisTenantId="+bisTenantId);
     $('#bisTenantId').val(bisTenantId);
     $('#tenantName').val(tenantName);
	$('#mainTable  tr').removeClass("addFirst");
	$('#'+bisTenantId+'_TD').addClass("addFirst");
	
//	$('#nameCn').html(values[0]); 
	
//  alert($('#valueColl').val());
}
</script>
