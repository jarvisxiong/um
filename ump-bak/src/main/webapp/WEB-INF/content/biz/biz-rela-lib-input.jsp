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
<body>
<form action="${ctx}/biz/biz-rela-lib!save.action" method="post" id="bizRelaForm">
<input type="hidden" id="recordVersion" name="recordVersion" value="${recordVersion}"/>
<input type="hidden"  id="dictNo"  name="relaProvince" />
<input type="hidden"  name="relaArea"/>
<input type="hidden"  name="relaTypeCd"/>
<input type="hidden"  name="relaName"/>
<input type="hidden"  name="relaUnit"/>
<input type="hidden"  name="relaLevelCd"/>
<input type="hidden"  id="relaLevelCd"/>
<input type="hidden"  name="submitCenterCd" id="submitCenterCd" "/>
<input type="hidden"   id="submitPersionCd" "/>
<div id="div_main_cont" style="margin-left: 0px;">
<div style="width:100%; height:32px; padding-top:5px;">
<fieldset style="padding-left: 0px;padding-right: 0px; ">
<div>
	<div id="queryLevel1" class="btn_white" onclick="queryLevel(this,1)">一级</div>
	<div id="queryLevel2" class="btn_white" onclick="queryLevel(this,2)">二级</div>
	<div id="queryLevel3" class="btn_white" onclick="queryLevel(this,3)">三级</div>
	<div id="queryLevel4" class="btn_blue"  onclick="queryLevel(this,4)">全部</div>
</div>
</fieldset>
</div>
	 <fieldset style="padding-left: 5px;padding-right: 5px; padding-top: 0px;">
	 <legend>&nbsp;&nbsp;
	 类别：<s:select style="width:65px;" list="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" listKey="key" listValue="value" id="relaTypeCd" class="query" name="queRelaTypeCd" onchange="bizRelaSearch();" ></s:select>
	 &nbsp;&nbsp;省份：<input style="width: 50px;" type="text" class="query" id="relaProvinceName"  />
	 &nbsp;&nbsp;地区：<input style="width: 50px;" type="text" class="query" id="relaArea" name="queRelaArea" />
	 &nbsp;&nbsp;姓名：<input style="width: 50px;" type="text" class="query" id="relaName" name="queRelaName" />
	 &nbsp;&nbsp;单位：<input style="width: 50px;" type="text" class="query" id="relaUnit" name="queRelaUnit" />
	 <security:authorize ifAnyGranted="A_BIZ_RELA_QUERY,A_BIZ_RELA_SUP,A_BIZ_RELA_ADMIN">
	 <input  class="btn_green" type="button" onclick="bizRelaSearch();" value="搜索"/>
	 </security:authorize>
	 <security:authorize ifAnyGranted="A_BIZ_RELA_SUP">
	 <input   class="btn_green" type="button" onclick="bizRelaAuth();" value="权限"/>
	 <!--<input   class="btn_orange" type="button" onclick="bizRelaImport();" value="导入"/>-->
	 </security:authorize>
	 <security:authorize ifAnyGranted="A_BIZ_RELA_SUP,A_BIZ_RELA_ADMIN">
	 <input   class="btn_orange" type="button" onclick="bizRelaAdd();" value="新增"/>
	 </security:authorize>
	 </legend>
	 </fieldset>
	<div  id="bizRelaAdd" style="display: none">
	<fieldset style="padding-left: 10px;padding-right: 10px;border:#DBE5F1 ">
		 <legend >&nbsp;&nbsp;&nbsp;公共关系库&nbsp;</legend>
		 <table class="shop-table">
	          <col width="100"/>
		      <col />
			  <col width="100"/>
			  <col />
			<tr>
			     <td><font style="color:red;">*</font>关系类别</td>
			     <td><s:select style="width:100%;" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" listKey="key" listValue="value" id="paRelaTypeCd"  ></s:select></td>			
			  	<td><font style="color:red;">*</font>省份</td>
		      	<td><input type="text"  validate="required"   id="pas_RelaProvinceName" name="relaProvinceName" /> </td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>姓名</td>
			   <td colspan="3"><input type="text"   id="paRelaName" validate="required" /></td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>通讯地址</td>
		       <td><input type="text" id="relaAddress" name="relaAddress" validate="required" /> </td>
			   <td><font style="color:red;">*</font>资料级别</td>
		       <td><s:select style="width:100%;" id="paRelaLevelCd"  validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapRelaLevel()" listKey="key" listValue="value"></s:select> </td>
			 </tr>
			 <tr>
			   <td><font style="color:red;">*</font>单位</td>
		       <td colspan="3"><input type="text"   id="paRelaUnit" validate="required" /> </td>
			 </tr>
			 <tr>
			   <td>职位</td>
			   <td><input type="text" id="relaPos" name="relaPos" /></td>
			   <td>&nbsp;&nbsp;电话</td>
			   <td><input type="text" name="relaPhone" id="relaPhone" /></td>
			 </tr>
			 <tr>
			  	 <td>&nbsp;&nbsp;<font style="color:red;">*</font>手机</td>
			 	 <td><input type="text" name="relaMobile" id="relaMobile" validate="required"/></td>
				 <td>传真</td>
				 <td><input type="text" name="relaFax"  id="relaFax"  /> </td>
			 </tr>
			 <tr>
			   <td><font style="color:red;">*</font>地区</td>
		       <td><input type="text"   validate="required" id ="paRelaArea"/> </td>
			   <td><font style="color:red;">*</font>序号</td>
		       <td><input type="text"  name="sequenceNo" validate="required" alt="amount" id ="sequenceNo" maxlength="9"/> </td>
			 </tr>
			 <tr>
			   <td><font style='color:red;'>*</font>提交公司/中心</td>
			   <td >			   
			   		<input type="text" name="submitCenterName" id="submitCenterName" validate="required" /> 

		   	   </td>
			   <td>提交人</td>
			   <td><input type="text" name="submitPersion" id="submitPersion"  /> </td>
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
					<td  align="center" style="padding-right: 0px">
					<input  class="btn_green"  type="button" onclick="saveBizRela();" value="保存"/>
					<input  class="btn_green"  type="button" onclick="outBizRela();" value="取消"/>
					</td>
				</tr>
			</table>
		</div>
	 </fieldset>
	</div>
</div>
	 <div id="bizRelaList" style="padding: 10px 8px 0px;"></div>
	 <div id="searchUserDiv" class="searchUserDiv"></div>
</form>

<!-- 弹出选择机构中心列表 -->
<div id="delayWindowDiv"
	style="position: absolute; width: 200px; height: 200px; top: 140px; left; 0 px; display: none; background-color: #fff; border: 1px solid #000; padding: 5px;"></div>


<script type="text/javascript">
//$('#submitCenterName').orgSelect({
//	muti:false
//});
$('#submitPersion').quickSearch("${ctx}/com/user-choose!quickSearch.action",['userName','uiid','orgName'],{userName:'submitPersion',uiid:"submitPersionCd"});
$('#submitCenterName').quickSearch("${ctx}/biz/biz-rela-lib!quickSearchOrg.action",['dictName'],{dictCd:'submitCenterCd',dictName:"submitCenterName"});
$("#pas_RelaProvinceName").quickSearch("${ctx}/app/app-dict-type!quickSearch.action",['dictName'],{dictCd:'dictNo',dictName:"pas_RelaProvinceName"},{dictTypeCd:'CITY_PROVINCE_TYPE'});
$(function(){
	$('#relaLevelCd').val('4');
	//$('#bizRelaAdd>:text').css('backgroundColor','#DBE5F1');
	$("#bizRelaAdd").find(":text").css('backgroundColor','#DBE5F1');
	bizRelaSearch();
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
});

$(".query").keydown(function(event){
	      if(event.keyCode==13){ 
	         //doSth
	    	  bizRelaSearch();
	      }
	   });  
/**
 * converName 
 * 阻止搜索覆盖新增内容
 */
function convertNameSea(){
	$("input[name='relaProvinceName']").val($('#relaProvinceName').val());
	$("input[name='relaLevelCd']").val($('#relaLevelCd').val());
	$("input[name='relaArea']").val($('#relaArea').val());
	$("input[name='relaName']").val($('#relaName').val());
	$("input[name='relaUnit']").val($('#relaUnit').val());
	$("input[name='relaTypeCd']").val($('#relaTypeCd').val());
}
 
function convertNameSave(){
	$("input[name='relaProvinceName']").val($('#pas_RelaProvinceName').val());
	$("input[name='relaLevelCd']").val($('#paRelaLevelCd').val());
	$("input[name='relaArea']").val($('#paRelaArea').val());
	$("input[name='relaName']").val($('#paRelaName').val());
	$("input[name='relaUnit']").val($('#paRelaUnit').val());
	$("input[name='relaTypeCd']").val($('#paRelaTypeCd').val());
}
function doExportExcel(){
	$("#bizRelaForm").attr("action", "${ctx}/biz/biz-rela-lib!exportExcel.action");
	$("#bizRelaForm").submit();
	$("#bizRelaForm").attr("action", "${ctx}/biz/biz-rela-lib!save.action");
}
function saveBizRela(){
	convertNameSave();
	var paRelaUnit=$('#paRelaUnit').val();
	var paRelaName=$('#paRelaName').val();
	var relaMobile=$('#relaMobile').val();
	var pdv = new Validate("bizRelaForm");
	if (pdv.validate()) {
	$.post(_ctx+"/biz/biz-rela-lib!uniqueExist.action",
			{
			  queRelaName:paRelaName,
			  queRelaUnit:paRelaUnit,
			  relaMobile:relaMobile
		    },
			function(result) {
				if (result=='success') {
					$('#bizRelaForm').submit();
				}else{
						alert("您录入的数据已存在");
					return false;
				}
			}
		);
	}
/**	
	$('#bizRelaForm').ajaxSubmit(function(result) {
		if(result!=null){
			alert("保存成功");
			claerRelaDate();
			bizRelaSearch();
		}
	});
	**/
}
function queryLevel(dom,relaLevelCd){
	$(dom).addClass('btn_blue').siblings().removeClass('btn_blue').addClass("btn_white");
	$('#relaLevelCd').val(relaLevelCd);
	bizRelaSearch();
	/*
	$(dom).siblings().css('color','#0167a2');
	$(dom).css('color','red');
	*/
	
	
	/*
	if(relaLevelCd==1){
		$("#queryLevel"+2).removeClass();
		$("#queryLevel"+3).removeClass();
		$("#queryLevel"+2).addClass("btn_white");
		$("#queryLevel"+3).addClass("btn_white");
		$("#queryLevel"+relaLevelCd).addClass("btn_blue");
		bizRelaSearch();
	}else if(relaLevelCd==2){
		$("#queryLevel"+1).removeClass();
		$("#queryLevel"+3).removeClass();
		$("#queryLevel"+1).addClass("btn_white");
		$("#queryLevel"+3).addClass("btn_white");
		$("#queryLevel"+relaLevelCd).addClass("btn_blue");
		bizRelaSearch();
	}else{
		$("#queryLevel"+1).removeClass();
		$("#queryLevel"+2).removeClass();
		$("#queryLevel"+1).addClass("btn_white");
		$("#queryLevel"+2).addClass("btn_white");
		$("#queryLevel"+relaLevelCd).addClass("btn_blue");
		bizRelaSearch();
	}
	*/
}
function bizRelaSearch(pageNo){
	convertNameSea();
	$('#bizRelaAdd').hide();
	var relaTypeCd= $('#relaTypeCd').val();
	var relaProvinceName= $('#relaProvinceName').val();
	var relaLevelCd= $('#relaLevelCd').val();
	var relaArea= $('#relaArea').val();
	var relaName= $('#relaName').val();
	var relaUnit= $('#relaUnit').val();
	TB_showMaskLayer("正在搜索请稍后...");
	var data={
			relaTypeCd:relaTypeCd,
			relaProvinceName:relaProvinceName,
			relaLevelCd:relaLevelCd,
			relaArea:relaArea,
			relaName:relaName,
			relaUnit:relaUnit
	    };
	if(pageNo!=""&&null!=pageNo){
		data={
				relaTypeCd:relaTypeCd,
				relaProvinceName:relaProvinceName,
				relaLevelCd:relaLevelCd,
				relaArea:relaArea,
				relaName:relaName,
				relaUnit:relaUnit,
				"page.pageNo":pageNo
		    };
	}
	$.post(_ctx+"/biz/biz-rela-lib!list.action",
			data,
			function(result) {
				if (result) {
					$("#bizRelaList").html(result);
				}
				TB_removeMaskLayer();
			}
		);
}
function claerRelaDate(){
	$(":text,texrarea").val("");
}
function bizRelaAdd(){
	$('#bizRelaAdd').show();
}
function outBizRela(){
	$('#bizRelaAdd').hide();
}

function bizRelaImport(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='bizRelaImport'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 330,
		height : 140,
		title : "导入公共关系库",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/biz/biz-rela-lib!bizRelaImport.action";
			$.post(url,{}, function(result) {
				$("#bizRelaImport").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
					//$('#bizRelaDetaForm').ajaxSubmit(function(result) { 
					//	bizRelaSearch();
					//});
				$('#relaUplodForm').submit();
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
	$("#bizRelaForm").attr("action", "${ctx}/biz/biz-rela-lib!list.action");
	$("#bizRelaForm").ajaxSubmit(function(result) {
		$("#bizRelaList").html(result);
	});
	$("#bizRelaForm").attr("action", "${ctx}/biz/biz-rela-lib!save.action");
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function bizRelaReduct(){
	var url=_ctx+"/biz/biz-rela-lib!relaLibReduction.action";
	parent.TabUtils.newTab("biz-rela-lib-reduction","关系库还原",url,true);
}
function bizRelaAuth(){
	var url=_ctx+"/biz/biz-rela-lib!relaAuth.action";
	parent.TabUtils.newTab("biz-rela-lib-relaAuth","权限",url,true);
}

/**
function dispatchOrgList(){
	var url = '${ctx}/biz/biz-rela-lib!dispatchedOrgList.action';
	var data = {userId: userId};
	$.post(url, data, function(result){
		$('#org_div').html(result);
	});
}
**/
</script>
</body>
</html>