<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/select.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<style type="text/css">
	.m5span{margin-left: 10px;color: red;}
	.cstinput{padding-left: 5px;padding-top: 2px; margin-left: 10px;}
	.inputtips{color: red; font-size: 12px; margin-left: 5px;display: none;}
	</style>
</head>
<body>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div style="float:left;margin-right: 5px;">
			<img src="${ctx}/images/fin/pic_Supplier.gif" style="margin-top:3px;"/>
		</div>
		<div style="float:left;">
			入库方详情：<span class="m5span">${costProvCondRef.typeName }</span> ——> <span class="m5span">${costProvCondRef.provDesc }</span>
		</div>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">			
	<form name="costprovcondrefSaveForm" action="${ctx}/cost/cost-prov-cond-ref!save.action" method="post" id="costprovconfrefFormId">
		<!-- 这个很重要！ -->
		<input type="hidden" name="id" id="id" value = "${costProvCondRef.costProvCondRefId}" />
		<input type="hidden" name="costProjectSectionId" id="costProjectSectionId" value = "${costProvCondRef.costProvCondRefId}" />
		<fieldset style="margin-left: 5px;margin-right: 5px;margin-top: 10px;border-width: 0px;">
		<table class="cont-show-table">
			<col style="width:90px;"/>
			<col />
			<tr>
				<td align="right">*分类:</td>
				<td>
					<input type="text" name="costProvCondRef.typeName" readonly="readonly" id="typeName" value="${costProvCondRef.typeName}" style="width: 250px;" maxlength="50" onblur="fillnullrow(this,'typeNametip')"/>
					<label class="inputtips" id="typeNametip">必填项！</label>  
				</td>
			</tr>
			<tr>
				<td align="right" height="50" >*供方内容:</td>
			    <td>
			    <textarea id="provDesc" class="cstinput" name="costProvCondRef.provDesc" readonly="readonly" style="width:88%;height:35px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>100) value=value.substr(0,100)" onblur="fillnullrow(this,'provDesctip')" onblur="fillnullrow(this,'provDesctip')">${costProvCondRef.provDesc}</textarea>
			    <label class="inputtips" id="provDesctip">必填项！</label> 
			    </td>
			</tr>
			<tr>
				<td align="right" height="50">*专业资质:</td>
			    <td>
			    <textarea id="profqualdesc" class="cstinput" name="costProvCondRef.profQualDesc" title="如果为空，系统会自动填入'/'" readonly="readonly" style="width:88%;height:35px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${costProvCondRef.profQualDesc}</textarea>
			    </td>
			</tr>
			<tr>
				<td align="right" height="50">*行业排名:</td>
			    <td><textarea id="indurankdesc" class="cstinput" name="costProvCondRef.induRankDesc" title="如果为空，系统会自动填入'/'" readonly="readonly" style="width:88%;height:35px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${costProvCondRef.induRankDesc}</textarea></td>
			</tr>
			<tr>
				<td align="right" valign="top" height="60">*企业业绩:</td>
				<td>
					<textarea id="enteperfdesc" class="cstinput" name="costProvCondRef.entePerfDesc" title="如果为空，系统会自动填入'/'" readonly="readonly" style="width:88%;height:50px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${costProvCondRef.entePerfDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top" height="60">*体系认证:</td>
				<td>
					<textarea id="systcertdesc" class="cstinput" name="costProvCondRef.systCertDesc" title="如果为空，系统会自动填入'/'" readonly="readonly" style="width:88%;height:50px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${costProvCondRef.systCertDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td align="right">*排列顺序:</td>
				<td><input type="text" id="sequenceNo" class="cstinput" name="costProvCondRef.sequenceNo" readonly="readonly" value="${costProvCondRef.sequenceNo}" style="border:1px solid #ccc;color: #316685;font-size: 13px; width: 120px;" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" onblur="fillnullrow(this,'sequenceNotip')"/>
				<label class="inputtips" id="sequenceNotip">必填项！</label>
				</td>
			</tr>
		    <tr>
		    	<td>&nbsp;</td>
		    	<td>&nbsp;
		    	<security:authorize ifAnyGranted="A_CONT_COND_EDIT">
					<input type="button" class="searchBtn" onclick="costprovcondSave();" value="保存" style="cursor: pointer;margin-left: 0px;"/>
			    	<input type="reset"  class="searchBtn"  value="取消" style="cursor: pointer;margin-left: 5px;"/>
			    	<input type="button"  class="searchBtn"  value="删除" onclick="costprovcondDel()" style="cursor: pointer;margin-left: 5px;"/>
		    	</security:authorize>
		    	<input type="button"  class="searchBtn"  value="关闭" onclick="costprovcondClose()" style="cursor: pointer;margin-left: 5px;"/>
				<span id="tips" style="color: red;"></span>
		    	</td>
		    </tr>
		</table>
		</fieldset>
	</form>
	</div>
</div>

<script type="text/javascript">
<security:authorize ifAnyGranted="A_CONT_COND_EDIT">
$(document).ready(function(){
	unfrozen();
});
</security:authorize>
//保存项目
function costprovcondSave(){
	TB_showMaskLayer("正在执行操作,请稍候...");
	if(valiblank('typeName')||valiblank('provDesc')||valiblank('profqualdesc')||valiblank('indurankdesc')||valiblank('enteperfdesc')||valiblank('systcertdesc')||valiblank('sequenceNo')){
		TB_removeMaskLayer();
		alert("验证失败，请仔细按提示输入之后再操作！");
		return false;
	}

	var param =  $("#costprovconfrefFormId").serialize();
	var url = $("#costprovconfrefFormId").attr("action");
	$.post(url,param,function(data){
		if(data=="success"){
			$('#tips').html('更新成功!').show().fadeOut(5000);
			/**刷新前一个选项卡的列表*/
			var pevent = {data:{tabId:"219",src:"",typeCd:1}};
			var tframe = parent.TabUtils.getTabFrame(pevent);
			/**刷新搜索状态*/
			$(tframe).contents().find("input[id='btnSearchcostInfo']").click();
			$(tframe).contents().find("input[id='toRefleshLeftTreeBtn']").click();
			window.location.href = window.location.href;
		}else{
			$('#tips').html('更新失败!').show().fadeOut(5000);
		}
	});
	TB_removeMaskLayer();
}
//删除项目
function costprovcondDel(){
	if(confirm("确定要删除吗?")){
		var url = "${ctx}/cost/cost-prov-cond-ref!delete.action";
		var param = {"costProvCondRef.costProvCondRefId":$("#id").val()};
		$.post(url,param,function(data){
			if(data=="success"){
				//$('#tips').html('更新成功!').show().fadeOut(5000);
				alert("操作成功!");
				/**刷新前一个选项卡的列表*/
				var pevent = {data:{tabId:"219",src:"",typeCd:1}};
				var tframe = parent.TabUtils.getTabFrame(pevent);
				/**刷新搜索状态*/
				$(tframe).contents().find("input[id='btnSearchcostInfo']").click();  
				$(tframe).contents().find("input[id='toRefleshLeftTreeBtn']").click();  
				/**关闭当前选项卡*/
				var event = {data:{tabId:"cost-prov-cond-ref-detail",src:"",typeCd:1}};
				parent.TabUtils.closeTab(event);
			}else{
				$('#tips').html('操作失败!').show().fadeOut(5000);
			}
		});
	}
}
/**去除空字符 并且填充*/
function fillnullrow(demo,tp){
	if(tp!=null&&tp.length>0){
		if(demo==null||demo==undefined||$.trim($(demo).val())==""){
			$("#"+tp).show();
		}else{
			$("#"+tp).hide();
			$(demo).val($.trim($(demo).val()));
		} 
	}else{
		if(demo==null||demo==undefined||$.trim($(demo).val())==""){
			$(demo).val("/");
		}
		$(demo).val($.trim($(demo).val())); 
	}
}
/**表单验证空白*/
function valiblank(demo){
	if(demo==null||demo==undefined||$.trim($("#"+demo).val())==""||$.trim($("#"+demo).val())==undefined){
		$("#"+demo).blur();
		return true;
	}else{
		return false;
	}
}
/**解除只读*/
function unfrozen(){
	$("#costprovconfrefFormId").find("input").removeAttr("readonly");
	$("#costprovconfrefFormId").find("textarea").removeAttr("readonly");
}
//关闭选项卡
function costprovcondClose(){
	var event = {data:{tabId:"cost-prov-cond-ref-detail",src:"",typeCd:1}};
	parent.TabUtils.closeTab(event);
}
/**初始化textarea的值*/
function initTextArea(demo,tp){
	if(demo==null||demo==undefined||$.trim($(demo).val())==""){
		$(demo).val("/");
	}
	if(tp==1){
		if($.trim($(demo).val())=="/"){
			$(demo).val("");
		}
	}
}
</script>
</body>
</html>
