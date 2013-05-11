<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
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
   	<div style="cursor:hand ;overflow-y:scroll;word-break:break-all; height:210px;" id ="abcd" >
		<input type="hidden" id="bisTenantId" >
		<input type="hidden" id="tenantName" >
	    <table class="content_table" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px;">
		    <tr>
			    <th align="center">商家名称</th>
			    <th align="center">商铺号</th>
		    </tr>
		    <s:iterator value="shopStoreList">
			    <tr id="${bisTenantId}" class="mainTr">
					<td align="left" title='${nameCn}' style="padding-left: 8px;" onclick="selShopStore('${bisTenantId}');"><div class="ellipsisDiv_full" >${nameCn}</div></td>
					<td style="padding-left: 5px;" align="left" title='${storeNo}'  onclick="selShopStore('${bisTenantId}');">${storeNo}
						<input type="hidden" id="${bisTenantId}" value="${nameCn}`${manageCd}`${shopTypeCd}`${rentStartDate}`${rentEndDate}`${floorNum}`${buildingNum}`${storeNo}`${factSquare}">
					</td>
			    </tr>
		    </s:iterator>
		    <s:if test="shopStoreList.size == 0">
		    	<tr>
		    		<td colspan="2"><span class="color_red">没有搜索到相关的商铺或者商家。</span></td>
		    	</tr>
		    </s:if>
	    </table>
	</div>
</div>
<script type="text/javascript">
/**
function changeMouse(){
	
}
function getTenantName(){
	 return $('#tenantName').val();
}
function  getTenantId(){
	 return $('#bisTenantId').val();
}
function selShopStore(bisTenantId,tenantName){
//	alert($('#'+uniqueId).val());
     $('#bisTenantId').val(bisTenantId);
     $('#tenantName').val(tenantName);
	$('#mainTable  tr').removeClass("addFirst");
	$('#'+bisTenantId+'_TD').addClass("addFirst");
	
//	$('#nameCn').html(values[0]); 
	
//  alert($('#valueColl').val());
}
**/


</script>
