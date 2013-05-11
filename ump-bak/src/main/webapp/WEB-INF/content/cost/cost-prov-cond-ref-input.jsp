<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<form name="costprovcondrefSaveForm" action="${ctx}/cost/cost-prov-cond-ref!save.action" method="post" id="costprovconfrefFormId">
	<!-- 这个很重要！ -->
	<input type="hidden" name="id" id="id" value = "${costProvCondRefId}" />
	<input type="hidden" name="costProjectSectionId" id="costProjectSectionId" value = "${costProvCondRefId}" />
	<input type="hidden" name="recordVersion" id="recordVersion" value = "${recordVersion}" />
	<fieldset style="margin-left: 5px;margin-right: 5px;margin-top: 10px;">
	<table class="cont-show-table">
		<col style="width:90px;"/>
		<col />
		<tr>
			<td align="right">*分类:</td>
			<td>
				<input type="text" name="costProvCondRef.typeName"  id="typeName" value="${typeName}" style="width: 250px;" maxlength="50" onblur="fillnullrow(this,'typeNametip')"/>
				<label class="inputtips" id="typeNametip">必填项！</label> 
			</td>
		</tr>
		<tr>
			<td align="right" height="50" >*供方内容:</td>
		    <td>
		    <textarea id="provDesc" class="cstinput" name="costProvCondRef.provDesc" style="width:88%;height:35px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>100) value=value.substr(0,100)"  onblur="fillnullrow(this,'provDesctip')">${provDesc}</textarea>
		    <label class="inputtips" id="provDesctip">必填项！</label> 
		    </td>
		</tr>
		<tr>
			<td align="right" height="50">*专业资质:</td>
		    <td>
		    <textarea id="profqualdesc" class="cstinput" name="costProvCondRef.profQualDesc" title="如果为空，系统会自动填入'/'" style="width:88%;height:35px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${profqualdesc}</textarea>
		    </td>
		</tr>
		<tr>
			<td align="right" height="50">*行业排名:</td>
		    <td><textarea id="indurankdesc" class="cstinput" name="costProvCondRef.induRankDesc" title="如果为空，系统会自动填入'/'" style="width:88%;height:35px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${indurankdesc}</textarea></td>
		</tr>
		<tr>
			<td align="right" valign="top" height="60">*企业业绩:</td>
			<td>
				<textarea id="enteperfdesc" class="cstinput" name="costProvCondRef.entePerfDesc" title="如果为空，系统会自动填入'/'" style="width:88%;height:50px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${enteperfdesc}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top" height="60">*体系认证:</td>
			<td>
				<textarea id="systcertdesc" class="cstinput" name="costProvCondRef.systCertDesc" title="如果为空，系统会自动填入'/'" style="width:88%;height:50px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>200) value=value.substr(0,200)" onblur="fillnullrow(this)" onfocus="initTextArea(this,1)">${systcertdesc}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right">*排列顺序:</td>
			<td><input type="text" id="sequenceNo" class="cstinput" value="${sequenceNo }" name="costProvCondRef.sequenceNo" style="border:1px solid #ccc;color: #316685;font-size: 13px; width: 120px;" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" onblur="fillnullrow(this,'sequenceNotip')">
			<label class="inputtips" id="sequenceNotip">必填项！</label>
			</td>
		</tr>
	    <tr>
	    	<td>&nbsp;</td>
	    	<td>&nbsp;
		    	<security:authorize ifAnyGranted="A_CONT_COND_EDIT">
				<input type="button" class="searchBtn" onclick="costprovcondSave();" value="保存" style="cursor: pointer;margin-left: 0px;"/>
				</security:authorize>
		    	<input type="button"  class="searchBtn" onclick="close2back();" value="关闭" style="cursor: pointer;margin-left: 5px;"/>
				<span id="tips" style="color: red;"></span>
	    	</td>
	    </tr>
	</table>
	</fieldset>
</form>
<script type="text/javascript">
$(function(){
	//选择人员
	$("#curUserName").userSelect({
        muti:true,
        nameField:'curUserName',
        cdField:'authUiid'
	});
	/**页面表单事件*/
	initTextArea($("#profqualdesc"));
	initTextArea($("#indurankdesc"));
	initTextArea($("#enteperfdesc"));
	initTextArea($("#systcertdesc"));
});

//保存项目
function costprovcondSave(){
	TB_showMaskLayer("正在校验,请稍候...");
	if(valiblank('typeName')||valiblank('provDesc')||valiblank('profqualdesc')||valiblank('indurankdesc')||valiblank('enteperfdesc')||valiblank('systcertdesc')||valiblank('sequenceNo')){
		TB_removeMaskLayer();
		alert("验证失败，请仔细按提示输入之后再操作！");
		return false;
	}
	var url1 = "${ctx}/cost/cost-prov-cond-ref!valiExistProvDesc.action";
	var param1 =  $("#costprovconfrefFormId").serialize();
	$.post(url1,param1,function(data){
		if("success"==data){
			var param =  $("#costprovconfrefFormId").serialize();
			var url = $("#costprovconfrefFormId").attr("action");
			$.post(url,param,function(data){
				if(data=="success"){
					/**将表单还原*/
					resetforms();
					$('#tips').html('保存成功!').show().fadeOut(5000);
					//初始化树形结构 调用父窗口的方法
					getSectionTree("itemDiv");
				}else{
					alert("操作失败！");
					$('#tips').html('保存失败!').show().fadeOut(5000);
				}
			});
		}else{
			alert("操作失败。 同一分类下已经存在该供方，请勿重复提交！");
			$('#tips').html('保存失败!').show().fadeOut(5000);
		}
	});
	TB_removeMaskLayer();
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
/**验证在同一分类下是否存在相同的供方*/
function valiExistProvDesc(){
	var url = "${ctx}/cost/cost-prov-cond-ref!valiExistProvDesc.action";
	var param =  $("#costprovconfrefFormId").serialize();
	$.post(url,param,function(data){
		if("success"==data){
			return ture;
		}else{
			return false;
		}
	});
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
/**刷新窗体 */
function close2back(){
	window.location.href = _ctx + "/cost/cost-prov-cond-ref!main.action";
}
function resetforms(){
	document.forms['costprovcondrefSaveForm'].reset();
	$("#typeName").focus();
	$("#typeName").blur();
	$("#provDesc").focus();
	$("#provDesc").blur();
	$("#sequenceNo").focus();
	$("#sequenceNo").blur();
	initTextArea($("#profqualdesc"));
	initTextArea($("#indurankdesc"));
	initTextArea($("#enteperfdesc"));
	initTextArea($("#systcertdesc"));
}
</script>
	
