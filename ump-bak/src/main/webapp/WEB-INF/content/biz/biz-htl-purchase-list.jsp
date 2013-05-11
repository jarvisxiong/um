<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" href="${ctx}/resources/css/biz/biz-rela.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
</head>

<form action="${ctx}/biz/biz-htl-purchase!list.action" method="post" id="bizPurSearchForm">
<div id="div_main_cont" style="margin-left: 0px;margin-top: 10px;">
	 <fieldset style="padding-left: 10px;padding-right: 10px;">
	 <legend>&nbsp;&nbsp;
	 酒店名称：<s:select style="width: 100px;" name="hotelName" id="h_hotelName" list="@com.hhz.ump.util.DictMapUtil@getMapHotelName()" listKey="key" listValue="value" onchange="bizPurchaseSearch();"></s:select>
	 &nbsp;&nbsp;细项类别：<s:select style="width: 80px;" name="itemTypeCd"  list="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" listKey="key" listValue="value" onchange="bizPurchaseSearch();"></s:select>
	 &nbsp;&nbsp;名称：<input type="text" class="query" name="itemName" value="${itemName}" style="width: 80px;" />
	 <input  class="btn_green" type="button" onclick="bizPurchaseSearch();" value="搜索" />
	 <security:authorize ifAnyGranted="A_BIZ_PURCHASE_ADMIN">
	 <input   class="btn_green" type="button" onclick="bizPurchaseImport();" value="导入" />
	 <input   class="btn_orange" type="button" onclick="bizPurchaseAdd();" value="新增" />
	 </security:authorize>
	 <security:authorize ifAnyGranted="A_BIZ_PURCHASE_SUP">
	 <input   class="btn_red" style="width: 80px;" type="button" onclick="purchaseBatchDel();" value="批量删除" />
	 </security:authorize>
	 </legend>
	 <legend>
	   &nbsp;&nbsp;开始日期：<s:textfield style="width: 100px;" name="fromDate"  onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
	   &nbsp;&nbsp;结束日期：<s:textfield style="width: 80px;" name="toDate"  onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
	 </legend>
	 </fieldset>
</div>
<div id="TableDiv"  style="height: 100%; ;padding-left: 10px;padding-right: 10px; overflow: hidden;">
	<table width="100%" id="result_table" class="content_table"  >
	  <thead>
	        <tr class="header">
	            <th scope="col" width="85px;" align="left" style="background: none;">酒店名称</th>
	            <th scope="col" align="left">类别</th>
	            <th scope="col" align="left">名称</th>
	            <th scope="col" align="left" >单位</th>
	            <th scope="col" align="left" >市场价(元)</th>
	            <th scope="col" align="left" >上次定价(元)</th>
	            <th scope="col" align="left" >本月定价(元)</th>
	            <th scope="col" align="left" >开始日期</th>
	            <th scope="col" align="left" >结束日期</th>
	            <security:authorize ifAnyGranted="A_BIZ_PURCHASE_ADMIN">
	            <th scope="col" align="left" width="50px;" >操作</th>
	            </security:authorize>
	        </tr>
	  </thead>
	  <tbody>
			  <s:iterator value="page.result">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='${hotelName}'  onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" >${hotelName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" value="itemTypeCd"/>'  onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" value="itemTypeCd"/></div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${itemName}' onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" >${itemName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${unitName}' onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" >${unitName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${marketPrice}' onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" >${marketPrice}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${lastPrice}' onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" >${lastPrice}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${price}'  onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" >${price}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${fromDate}'  onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" ><s:date name ="fromDate" format="yy-MM-dd"/></div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${toDate}'  onclick="bizPurchaseEdit('${bizHtlPurchaseId}');"  ><div class="ellipsisDiv_full" ><s:date name ="toDate" format="yy-MM-dd"/></div></td>
		  	    		<security:authorize ifAnyGranted="A_BIZ_PURCHASE_ADMIN">
		  	    		<td><input id="delBtn" name="delBtn"  class="btn_red" onclick="doBizPurchaseDelete('${bizHtlPurchaseId}');" type="button" value ="删除"/></td>
		  	    		</security:authorize>
			    	</tr>
			  </s:iterator>
		</tbody>
	</table>
		<div class="table_pager" style="margin-top:5px;">
			<p:page pageInfo="page"/>
		</div>
</div>
</form>
<form action="${ctx}/biz/biz-htl-purchase!save.action" method="post" id="bizHtlPurchaseNew">
	<input type="hidden" id="hotelCd" name="hotelCd" />
	<div  id="bizPurchaseAdd" style="display: none">
		<fieldset style="padding-left: 10px;padding-right: 10px;border:#DBE5F1 ">
			 <legend >&nbsp;&nbsp;&nbsp;&nbsp;采购数据</legend>
			 <table class="shop-table">
		          <col width="100"/>
			      <col />
				  <col width="100"/>
				  <col />
				<tr>
				     <td><font style="color:red;">*</font>酒店名称</td>
				     <td><input type="text" name="hotelName" id="hotelName" validate="required" /></td>			
				  	 <td><font style="color:red;">*</font>细项类</td>
			      	 <td><s:select validate="required" style="width:100%;" name="itemTypeCd"  list="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" listKey="key" listValue="value" ></s:select></td>
				</tr>
				<tr>
				   <td><font style="color:red;">*</font>单位名称</td>
				   <td><input type="text"    name="unitName" id="unitName" validate="required" /></td>
				   <td><font style="color:red;">*</font>市场价(元)</td>
			       <td><input type="text" name="marketPrice" alt="amount" validate="required" /> </td>
				</tr>
				<tr>
					<td><font style="color:red;">*</font>细项名称</td>
					<td colspan="3"><input  type="text" name="itemName" id="itemName" validate="required"/></td>
				</tr>
				<tr>
				   <td>上次定价(元)</td>
			       <td><input type="text" name="lastPrice" alt="amount"  maxlength="9" /> </td>
				   <td>本月定价(元)</td>
			       <td><input type="text"   name="price" alt="amount"  maxlength="9"/> </td>
				 </tr>
				 <tr>
			  	   <td>开始日期</td>
			 	   <td><s:textfield name="fromDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');" /></td>
				   <td>结束日期</td>
				   <td><s:textfield name="toDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');" /></td>
				 </tr>
				  <tr>
				   <td>说明</td>
			       <td colspan="3" rowspan="3"><textarea id="remark"  style="width:100%;height:100px;background-color: #DBE5F1;border: none;" name="remark"></textarea></td>
				 </tr>
			 </table>
			 <div style="float:left;font-size:12px;padding-right:20px;line-height:30px;width:100%;">
				<table  class="main_content" border="0" cellpadding="0" cellspacing="0"  style="width:100%;height:100%;table-layout:fixed">
					<col width="750"/>
					<tr>
						<td   style="padding-right: 0px">
						<input  class="btn_green"  type="button" onclick="saveBizPurchase();" value="保存"/>
						<input  class="btn_green"  type="button" onclick="outBizPurchase();" value="取消"/>
						</td>
					</tr>
				</table>
			</div>
		 </fieldset>
	</div>
</form>
</html>
<script language="javascript">
$(function(){
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
});
$("#hotelName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearch.action",['dictName'],{dictCd:'hotelCd',dictName:"hotelName"});
//回车搜索事件
$(".query").keydown(function(event){
    if(event.keyCode==13){ 
       //doSth
  	  bizPurchaseSearch();
    }
 });
 
function bizPurchaseAdd(){
	$("#TableDiv").hide();
	$("#bizPurchaseAdd").show();
}
function outBizPurchase(){
	$("#bizPurchaseAdd").hide();
	$("#TableDiv").show();
}
function saveBizPurchase(){
	var pdv = new Validate("bizHtlPurchaseNew");
	if (pdv.validate()) {
		TB_showMaskLayer("正在保存请稍后...");
		$('#bizHtlPurchaseNew').ajaxSubmit(function(result) { 
			if(result=="success"){
				alert("保存成功");
				bizPurchaseSearch();
			}
		});
		TB_removeMaskLayer();
	}
}
function bizPurchaseSearch(){
	$("#bizPurchaseAdd").hide();
	TB_showMaskLayer("正在搜索请稍后...");
	$('#bizPurSearchForm').submit();
	TB_removeMaskLayer();
}
function bizPurchaseEdit(id){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='bizPurchaseDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 540,
		height : 330,
		title : "编辑详细信息",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/biz/biz-htl-purchase!bizPurchaseDetail.action";
			$.post(url,{id:id}, function(result) {
				$("#bizPurchaseDiv").html(result);

			});
		},
		handler : function(btn){
			if(btn=='ok'){
					$('#bizPurchaseDataForm').ajaxSubmit(function(result) { 
						bizPurchaseSearch();
					});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
function doBizPurchaseDelete(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/biz/biz-htl-purchase!delete.action";
			$.post(url,{id:id},
					function(result) {
				if('success' == result){
					bizPurchaseSearch();
					alert("删除成功");
				}else{
					alert("删除失败");
					return false;
				}
					});
		}
	}
}
function purchaseBatchDel(){
	if(confirm("将删除搜索出的全部记录，继续吗？")){
		var url="${ctx}/biz/biz-htl-purchase!deleteBatch.action";
		$("#bizPurSearchForm").attr("action", "${ctx}/biz/biz-htl-purchase!deleteBatch.action");
		$("#bizPurSearchForm").ajaxSubmit(function(result) {
			if('success' == result){
				alert("删除成功");
			}
		$("#bizPurSearchForm").attr("action", "${ctx}/biz/biz-htl-purchase!list.action");
		$("#bizPurSearchForm").submit();
		});
	}
}

function bizPurchaseImport(){
	var url="${ctx}/biz/biz-htl-purchase!bizPurchaseImport.action";

	parent.TabUtils.newTab("biz-htl-purchase","采购数据导入",url,true);
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#bizPurSearchForm").submit();
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
</script>