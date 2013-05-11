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
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" href="${ctx}/resources/css/biz/biz-rela.css" type="text/css" />
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
</head>
<body>
<form action="${ctx}/biz/biz-htl-hoe!save.action" method="post" id="bizHtlHoeForm">
<input type="hidden" id="recordVersion" name="recordVersion" value="${entity.recordVersion}" />
<input type="hidden" id="hotelCd" name="hotelCd" value="${entity.hotelCd}"/>
<input type="hidden" id="bizHtlHoeId" name="bizHtlHoeId" value="${entity.bizHtlHoeId}"/>
<input type="hidden" id="hoeTypeCd" name="hoeTypeCd" value="${entity.hoeTypeCd}"/>
<input type="hidden" id="bizHoeAttaId" name="bizHoeAttaId" value="${entity.bizHoeAttaId}"/>
<input type="hidden" id="noSearch" value="${noSearch}"/>
<!--<input type="hidden" id="h_hoeTypeCd" name="h_hoeTypeCd" />
<input type="hidden" id="h_hotelCd" name="h_hotelCd" />-->
<div id="div_main_cont" style="margin-left: 0px;margin-top: 10px;">
	 <fieldset style="padding-left: 10px;padding-right: 10px;">
	 <legend>&nbsp;&nbsp;
	 酒店名称：<s:select style="width: 120px;" name="h_hotelName" id="h_hotelName" list="@com.hhz.ump.util.DictMapUtil@getMapHotelName()" listKey="key" listValue="value" onchange="bizHtlSearch();"></s:select>
	 &nbsp;&nbsp;品名类别：<input style="width: 80px;" type="text"    name="h_hoeTypeName" id="h_hoeTypeName" class="query" />
	 &nbsp;&nbsp;HOE名称：<input style="width: 80px;" type="text"    name="h_hoeName" id="h_hoeName" class="query"/>
	 <input  class="btn_green" type="button" onclick="bizHtlSearch();" value="搜索" />
	 <security:authorize ifAnyGranted="A_BIZ_HOTEL_ADMIN">
	 <input   class="btn_green" type="button" onclick="bizHitImport();" value="导入" />
	 <input   class="btn_orange" type="button" onclick="bizHtlAdd();" value="新增" />
	 </security:authorize>
	 </legend>
	 </fieldset>
	<div  id="bizHtlAdd" >
	<fieldset style="padding-left: 10px;padding-right: 10px;border:#DBE5F1 ">
		 <legend >&nbsp;&nbsp;&nbsp;HOE库&nbsp;<font color="red">${savesuccess}</font></legend>
		 <table class="shop-table">
	          <col width="100"/>
		      <col />
			  <col width="100"/>
			  <col />
			<tr>
			     <td><font style="color:red;">*</font>酒店名称</td>
			     <td><input type="text" name="hotelName" id="hotelName" value="${entity.hotelName}" validate="required" /></td>			
			  	<td><font style="color:red;">*</font>品名类别</td>
		      	<td><input type="text"    name="hoeTypeName" id="hoeTypeName" value="${entity.hoeTypeName}" validate="required" /></td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>供应商名称</td>
			   <td colspan="3"><input type="text"    name="supName" value="${entity.supName}" validate="required" /></td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>HOE名称</td>
		       <td><input type="text" name="hoeName" value="${entity.hoeName}" validate="required" /> </td>
			   <td>规格型号</td>
		       <td><input type="text" name="hoeModel" value="${entity.hoeModel}" /> </td>
			 </tr>
			 <tr>
			   <td>数量</td>
		       <td><input type="text"   name="hoeAmount" value="${entity.hoeAmount}" alt="amount"  maxlength="9"/> </td>
		  	   <td>序号</td>
		 	   <td><input type="text" name="sequenceNo"  value="${entity.sequenceNo}" id="sequenceNo" alt="amount" maxlength="9"/></td>
			 </tr>
			 <tr>
			   <td>单价</td>
			   <td><input type="text"  name="singelPrice" alt="amount"  value="${entity.singelPrice}" maxlength="9"/></td>
			   <td>总价</td>
			   <td><input type="text" name="totalPrice" id="totalPrice" value="${entity.totalPrice}" alt="amount"  maxlength="9"/></td>
			 </tr>
			  <tr>
			   <td rowspan="3">说明</td>
		       <td colspan="3" rowspan="3"><textarea id="remark" value="${entity.remark}" style="width:100%;height:100px;background-color: #DBE5F1;border: none;" name="remark">${entity.remark}</textarea></td>
			 </tr>
			 <tr></tr>
			 <tr></tr>
			 <tr>
	 			<td align="right">酒店Hoe附件:</td>
				<td colspan="3">
				  <div class="atta_readOnly" style="float:left;cursor: pointer; display: none;"  onclick="showAttachment('${entity.bizHoeAttaId}')">
					   <s:if test="bizHoeAttaList.get(0)==0||bizHoeAttaList.get(0)==null">
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						</s:if>
						<s:else>
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						</s:else>
						查看附件
				 </div>
				 <div class="atta_edit" style="float:left;cursor: pointer;"  onclick="openAttachment(this,'${entity.bizHoeAttaId}','bizHoeAttaId')">
				       <s:if test="bizHoeAttaList.get(0)==0||bizHoeAttaList.get(0)==null">
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						</s:if>
						<s:else>
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						</s:else>
						添加附件
				  </div>
				</td>
			 </tr>
		 </table>
		 <div style="float:left;font-size:12px;padding-right:20px;line-height:30px;width:100%;">
			<table  class="main_content" border="0" cellpadding="0" cellspacing="0"  style="width:100%;height:100%;table-layout:fixed">
				<col width="750"/>
				<tr>
					<td   style="padding-right: 0px">
					<input  class="btn_green"  type="button" onclick="saveBizHtl();" value="保存"/>
					<input  class="btn_green"  type="button" onclick="outBizHtl();" value="取消"/>
					</td>
				</tr>
			</table>
		</div>
	 </fieldset>
	</div>
</div>
	 <div id="bizHtlList" style="padding: 10px 10px 0px;"></div>
</form>

<script type="text/javascript">
var curr_user_cont = '<%= SpringSecurityUtils.getCurrentUiid()%>';
$(function(){
	<security:authorize ifNotGranted="A_BIZ_HOTEL_ADMIN">
	$(".atta_edit").hide();
	$(".atta_readOnly").show();
	$("#bizHtlAdd").find(":text,textarea").css('backgroundColor','#dddbdc');
	$("#bizHtlAdd").find(":text,textarea").attr('readOnly',"readOnly");
	</security:authorize>
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
	var noSearch=$('#noSearch').val();//阻止搜索事件
	if(noSearch!="noSearch"){
		bizHtlSearch();
	}
});
$("#hotelName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearch.action",['dictName'],{dictCd:'hotelCd',dictName:"hotelName"});
$("#hoeTypeName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearchHoe.action",['dictName'],{dictCd:'hoeTypeCd',dictName:"hoeTypeName"});
function bizHtlAdd(){
	$("input[name='hotelCd']").val('');
	$("input[name='hoeTypeName']").val('');
	$("input[name='hoeName']").val('');
	$('#bizHtlAdd').show();
	$('#bizHtlList').hide();
}
function outBizHtl(){
	$('#bizHtlList').show();
	$('#bizHtlAdd').hide();
}
function saveBizHtl(){
	var pdv = new Validate("bizHtlHoeForm");
	if (pdv.validate()) {
		$('#bizHtlHoeForm').submit();
	}
}
function convertNameSea(){
	$("input[name='hotelCd']").val($('#h_hotelName').val());
	$("input[name='hoeTypeName']").val($('#h_hoeTypeName').val());
	$("input[name='hoeName']").val($('#h_hoeName').val());
}
function bizHtlSearch(pageNo){
		$('#bizHtlList').show();
		$('#bizHtlAdd').hide();
		convertNameSea();
		var hotelCd=$('#h_hotelName').val();
		var hoeTypeName=$('#h_hoeTypeName').val();
		var hoeName=$('#h_hoeName').val();
		TB_showMaskLayer("正在搜索请稍后...");
		var data={
				hotelCd:hotelCd,
				hoeTypeName:hoeTypeName,
				hoeName:hoeName
		    };
		if(pageNo!=""&&null!=pageNo){
			var data={
					hotelCd:hotelCd,
					hoeTypeName:hoeTypeName,
					hoeName:hoeName,
					"page.pageNo":pageNo
			    };
		}
		$.post(_ctx+"/biz/biz-htl-hoe!list.action",
				data,
				function(result) {
					if (result) {
						$("#bizHtlList").html(result);
					}
					TB_removeMaskLayer();
				}
			);
}
$(".query").keydown(function(event){
    if(event.keyCode==13){ 
       //doSth
  	  bizHtlSearch();
    }
 }); 
function bizHitImport(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='bizHtlImport'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 330,
		height : 140,
		title : "导入公共关系库",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/biz/biz-htl-hoe!bizHtlImport.action";
			$.post(url,{}, function(result) {
				$("#bizHtlImport").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
					//$('#bizRelaDetaForm').ajaxSubmit(function(result) { 
					//	bizRelaSearch();
					//});
				$('#htlUplodForm').submit();
			}
//				doInput(bigTypeId, smallTypeId);
//			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#bizHtlHoeForm").attr("action", "${ctx}/biz/biz-htl-hoe!list.action");
	$("#bizHtlHoeForm").ajaxSubmit(function(result) {
		$("#bizHtlList").html(result);
	});
	$("#bizHtlHoeForm").attr("action", "${ctx}/biz/biz-htl-hoe!save.action");
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bizHtlHoe&filterType=image|office&onlyShow=true",
		width:500,
		height:300,
		title: '附件查看',
		afterShow:function(){},
		iframe:true
	});
}
function openAttachment(dom,entityId,hiddenId,title){
	var _title = title||'上传附件';
	if(!entityId||entityId == ''){
		//var hiddenId = $(dom).children(":hidden");
		if($.trim($('#'+hiddenId).val()) == ''){
			entityId = 'agree_'+curr_user_cont+'_'+String((new Date().getTime()) ^ Math.random());
			$('#'+hiddenId).val(entityId);
		}else{
			entityId = $('#'+hiddenId).val();
		}
	}
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bizHtlHoe&filterType=image|office",
		width:500,
		height:300,
		title: _title,
		afterShow:function(){},
		iframe:true
		});
}
function bizHtlEdit(id){
	$('#id').val(id);
	$('#bizHtlListForm').submit();
			/**$.post("${ctx}/biz/biz-htl-hoe!bizHtlDetail.action",
					data,
					function(data) {
						var data = eval('('+data+')');
						$.each(data,function(i,n){
						     $('#'+i).val(n);
						});
					}
				);**/
}
</script>
</body>
</html>