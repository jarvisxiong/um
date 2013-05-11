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
#setContUl li{line-height:20px;height:20px;padding-left:5px;  
 white-space:nowrap;      overflow: hidden;      text-overflow:ellipsis;  }
 /*隐藏li多余字*/
#setContUl li:hover{background-color: #61becf;padding-left:5px;   white-space:nowrap;      overflow: hidden;  
    text-overflow:ellipsis;  
	color:#ffffff;}
-->
</style>
<div style=" margin-top:20px;  height:180px;padding-left:20px;"  >
<div id="searchTenantDiv" style="    border: 1px solid #CCCCCC;    float: left;    height: 100%;    width: 180px;">
	<div id="searchTil" style="border-top:4px solid #9CB6C6 ;height:30px;line-height:30px">

	 	<h3 align="center">		<font color="#ff0000"  style="margin-right:8px;">*</font>租户</h3>
	</div>
	<div style=" border-top: 1px solid #CCCCCC;padding-top:5px;" align="center">
		<input type="text" 
    		class="search_input_shop" value="搜索商家..." 
    		onfocus="clearInput(this);"
    		id="quickSearchShop" style="color: rgb(230, 230, 230);margin:3px 0  8px 0">
    </div>
    <div align="center">
		<input type="text" 
    		class="search_input_store" value="搜索商铺..." 
    		onfocus="clearInput(this);"
    		id="quickSearchStore" style="color: rgb(230, 230, 230);margin:3px 0  8px 0">
   		<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
    </div>
 </div>  	
 <div id="setContDiv" style="    border: 1px solid #CCCCCC;    float: left;    height: 100%;    width: 180px;margin-left:8px">
 	<div id="contTil" style="border-top:4px solid #9CB6C6 ;height:30px;line-height:30px">
 	<h3 align="center">		<font color="#ff0000" style="margin-right:8px;">*</font>合同列表</h3>
 	</div>
 	<ul id="setContUl" style=" border-top: 1px solid #CCCCCC;padding-top:5px;overflow-y:auto; " align="center;"></ul>
 </div>
   	<div style= "display:none; cursor:hand ;overflow-y:scroll;word-break:break-all; height:300px;padding:5px;border: 1px solid #cccccc;" id ="abcd" >
   		<div id="shopStoreTable">
			<input type="hidden" id="bisTenantId" >
			<input type="hidden" id="tenantName" >
		    <table id="mainTable" border="0" cellpadding="0" cellspacing="0" >
		    	<col width="250">
		    	<col width="250">
			    <tr>
				    <th align="left">商家名称</th>
				    <th style="padding-left: 5px;" align="left">商铺号&nbsp;</th>
			    </tr>
			    <s:iterator value="shopStoreList">
			    <tr id="${bisTenantId}_TD"  ">
					<td  align="left" class="pd-chill-tip" title='${nameCn}' onclick="selShopStore('${bisTenantId}','${nameCn}-${storeNo }');"><div class="ellipsisDiv_full" >${nameCn}</div></td>
					<td   style="padding-left: 5px;" align="left" class="pd-chill-tip" title='${storeNo}'  onclick="selShopStore('${bisTenantId}','${nameCn}-${storeNo }');">${storeNo}
					</td>
			    </tr>
			    </s:iterator>
		    </table>
   		</div>

	</div>
</div>
<script type="text/javascript">
var DEFAULT_SEARCH_TIP = '搜索商家...';
//按键时若存在默认字符,清空
function clearInput(_this){
		$(_this).val('');
		$(_this).css('color','#333333');
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
</script>
