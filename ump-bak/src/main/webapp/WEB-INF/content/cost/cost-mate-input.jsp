<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<title>材料设备信息</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft">材料设备信息</div>
		<div class="fRight"> 
			<s:if test="costMateId != null && costMateId != ''">
				<input type="button" class="btn_new btn_add_new" style="width:70px;"  onclick="doAddCostMate();" value="继续新增"/>
				<input type="button" class="btn_new btn_add_new" style="width:70px;"  onclick="addCostMatePrice();" value="新增型号"/>
				<input type="button" class="btn_new btn_blue_new" style="width:80px;"  onclick="setMateParam();" value="配置属性列"/>
			</s:if>
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href;" value="刷新" />
		</div>
	</div>

	<div style="margin:10px;">
		<form action="${ctx}/cost/cost-mate!save.action" method="post" id="costMateForm">
			<input type="hidden" name="costMateId" id="costMateId" value = "${costMateId}" />
			<input type="hidden" name="recordVersion" id="recordVersion" value = "${recordVersion}" />
			
			<div id="cont_basic">
			<table class="cont-show-table" width="100%">
				<col style="width:100px;"/>
				<col/>
				<col style="width:120px;"/>
				<col/>
				<tr>
					<td align="right">*材料设备名称：</td>
				    <td><input type="text" id="mateName" name ="mateName" value="${mateName}" maxlength='40' /></td>
				    <td align="right">材料设备编号：</td>
				    <td><input type="text" id="mateBizCd" name ="mateBizCd" value="${mateBizCd}" maxlength='40' /></td>
				</tr>
				<tr>
					<td align="right">*所属类型：</td>
					<td>
						<input type="hidden" id="parentModuleId" name="parentModuleId" value="${parentModuleId}"/>
						<input 	style="cursor: pointer;" 
								type="text"
								id="mateModuleName" 
								onclick="selectCostMateModule();" 
								readonly="readonly" title="点击选择设备分类" 
							
								value="${parentModulePathName}"
								<%--
								value = '<p:code2name mapCodeName="mapCostMateModule" value="parentModuleId"/>'
								--%>
						/>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
			    	<td align="right">特殊类型：</td>
					<td style="padding-top:5px">&nbsp;&nbsp;
						<c:if test="${specTypeCd != null && specTypeCd!=''}">
							<s:radio list="#{'0':'其他','1':'电缆'}" name="specTypeCd" id="specTypeCd" onclick="showPriceDiv(this.value)"></s:radio>
						</c:if>
						<c:if test="${specTypeCd == null || specTypeCd==''}">
							<s:radio list="#{'0':'其他','1':'电缆'}" name="specTypeCd" id="specTypeCd" value="0" onclick="showPriceDiv(this.value)"></s:radio>
						</c:if>
					</td>
					<td align="right">价格计算方式：</td>
					<td style="padding-top:5px">&nbsp;&nbsp;
						<c:if test="${calcTypeCd != null && calcTypeCd!=''}">
							<s:radio list="#{'1':'定量','2':'公式','3':'总价浮动'}" name="calcTypeCd" id="calcTypeCd"></s:radio>
						</c:if>
						<c:if test="${calcTypeCd == null || calcTypeCd==''}">
							<s:radio list="#{'1':'定量','2':'公式','3':'总价浮动'}" name="calcTypeCd" id="calcTypeCd" value="1"></s:radio>
						</c:if>
					</td>
				</tr>
				<tr id="priceDiv" style="display: none;">
				    <td align="right">*均单价：</td>
				    <td><input type="text" id="basePrice" name ="basePrice" value="${basePrice}" maxlength='15' onkeyup="clearNoNum_1(this);"/></td>
				    <td align="right">*最低价：</td>
				    <td><input type="text" id="lowPrice" name ="lowPrice" value="${lowPrice}" maxlength='15' onkeyup="clearNoNum_1(this);"/></td>
				</tr>
				<tr id="priceDiv2" style="display: none;">
				    <td align="right">*上下浮动比例：</td>
				    <td><input type="text" id="floatRate" name ="floatRate" value="${floatRate}" maxlength='15' onkeyup="clearNoNum_1(this);" style="width: 100px;"/>&nbsp;%</td>
				    <td align="right">*最高价：</td>
				    <td><input type="text" id="highPrice" name ="highPrice" value="${highPrice}" maxlength='15' onkeyup="clearNoNum_1(this);"/></td>
				</tr>
				<tr>
				    <td align="right">总价浮动比例：</td>
				    <td><input type="text" id="totalFloatRate" name ="totalFloatRate" value="${totalFloatRate}" onkeyup="clearNoNum_1(this);" maxlength='10' style="width:100px;"/>&nbsp;%</td>
				</tr>
				<tr valign="top">
					<td align="right">公式说明：</td>
					<td colspan="3">&nbsp;
						<textarea id="fomuDesc" name="fomuDesc" style="width:93%;height:30px;border:1px solid #ccc;color: #316685;font-size: 13px;">${fomuDesc}</textarea>
					</td>
				</tr>
				<tr valign="top">
					<td align="right">说明：</td>
					<td colspan="3">&nbsp;
						<textarea id="memoDesc" name="memoDesc" style="width:93%;height:30px;border:1px solid #ccc;color: #316685;font-size: 13px;">${memoDesc}</textarea>
					</td>
				</tr>
				<tr>
			    	<td align="right">*是否启用：</td>
					<td style="padding-top:5px">&nbsp;&nbsp;
						<c:if test="${enableFlg != null && enableFlg!=''}">
							<s:radio list="#{'1':'是','0':'否'}" name="enableFlg" id="enableFlg"></s:radio>
						</c:if>
						<c:if test="${enableFlg == null || enableFlg==''}">
							<s:radio list="#{'1':'是','0':'否'}" name="enableFlg" id="enableFlg" value="1"></s:radio>
						</c:if>
					</td>
				</tr>
			    <tr>
			    	<td>&nbsp;</td>
			    	<td align="left" colspan="3">&nbsp;
						<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
				    		<input type="button"  class="btn_new btn_save_new" onclick="costMateSave();" value="保存" />
					    	
						</security:authorize>
					    <s:if test="inputFlag != null && inputFlag != ''"><font color="red">${inputFlag}</font></s:if>
			    	</td>
			    </tr>
			</table>
			</div>
		</form>
	
		<br/>
		<br/>
		<s:if test="costMateId != null && costMateId != ''">
			<div style="line-height: 30px;background-color: #cbcbcb;margin-left:-5px;padding-left:5px;">
				<div class="fRight btn_new btn_blue_new" id="btnSwitchCol" style="margin-right:10px;">显示更多</div>
				<div class="fRight btn_new btn_blue_new" id="btnSwitchListWidth" style="margin-right:10px;">扩展宽度</div>
			</div>
			<form action="${ctx}/cost/cost-mate-price!list.action" method="post" id="matePriceDetail" style="display: none;">
				<input type="hidden" name="costMateId" value ="${costMateId}" />
				<table id="searchTab" style="margin-bottom: 5px;">
					<tr>
						<td>
							关键字1:
							<input type="text" id="groupName" name="groupName" style="width:80px"  />
							关键字2:
							<input type="text" id="subGroupName" name="subGroupName" style="width:80px"  />
							名称:
							<input type="text" id="materialName" name="materialName" style="width:80px"  />
							规格:
							<input type="text" id="specName" name="specName" style="width:80px"  />
							 型号:
							<input type="text" id="modelName" name="modelName" style="width:80px"  /> 
							单价:
							<input type="text" id="stratPrice" name="stratPrice" style="width:50px" onkeyup="clearNoNum_1(this);"/>~
							<input type="text" id="endPrice" name="endPrice" style="width:50px" onkeyup="clearNoNum_1(this);"/>
							是否启用:
							<select style="width: 80px;" id="enableFlgPrice" name="enableFlgPrice" >
								 <option value="">&nbsp;</option>
								 <option value="1">是</option>
								 <option value="0">否</option>
							</select>
							<input type="button" class="btn_new btn_query_new" onclick="doQueryPrice();" value="搜索" style="margin-left: 10px;"/>
							<input type="button" class="btn_new btn_clean_new" onclick="clearPrice();"value="清空条件" style="width:70px;"/>
						</td>
					</tr>
				</table>
				<div id="costMatePriceList"  style="min-height: 100px;">
					&nbsp;
				</div>
			</form>
		</s:if>
	</div>
<script type="text/javascript">
$(function(){

	//注册事件
	$('#btnSwitchListWidth').toggle(
		function(){
			$('#listOfPrice').css({"width": "200%"});	
			$('#btnSwitchCol').html("自适应宽度");
		},
		function(){
			$('#listOfPrice').css({"width": "100%"});	
			$('#btnSwitchCol').html("扩展宽度");
		}
	);

	//注册事件
	$('#btnSwitchCol').toggle(
		function(){
			$('.syscol').show();
			$(this).html("显示精简");	
		},
		function(){
			$('.syscol').hide();
			$(this).html("显示更多");	
		}
	);
	
	var specTypeCd = "${specTypeCd}";
	var costMateId =$("#costMateId").val();
	if(costMateId != null && costMateId !=""){
		$("#matePriceDetail").show();
		doQueryPrice();
	}
	if(specTypeCd !=null && specTypeCd == '1'){
		$('#priceDiv').show();
		$('#priceDiv2').show();
	}else{
		$('#priceDiv').hide();
		$('#priceDiv2').hide();
	}
});
function doAddCostMate(){
	var url="${ctx}/cost/cost-mate!input.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-input","新增设备信息",url,true);
}
/**
 * 选择设备分类
 */
function selectCostMateModule(){
	var selectId ="";
	var selectName ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"材料设备类型",
		message:"<div id='moduleType_Div'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_ctx+"/cost/cost-mate-module!getCostMateModuleTreeNoCheck.action", function(result){
				var tree = new TreePanel({
					renderTo:"moduleType_Div",
					'root' : eval('('+result+')'),
					'ctx':_ctx
				});
				tree.on(function(node){
					var nodeId = node.attributes.id;
					var nodeType = node.attributes.nodeType;
					if(nodeType != '0'){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
					if(nodeId != '0'){
						selectId = node.attributes.id;
						selectName = node.attributes.text;
					}
				});
				tree.render();
			});
		},
		handler:function(e){
			if("ok"==e){
				$("#parentModuleId").val(selectId);
				$("#mateModuleName").val(selectName);
			}
		}
	});
}
//保存设备
function costMateSave(){
	var mateName = $('#mateName').val();
	var parentModuleId = $('#parentModuleId').val();
	var mateModuleName = $('#mateModuleName').val();
	if(parentModuleId == null || parentModuleId=="" 
			|| mateModuleName==null || mateModuleName==""){
		alert("请选择设备类型");
		return false;
	}
	if($.trim(mateName) == null || $.trim(mateName)==""){
		alert("请输入材料设备名称");
		return false;
	}
	if($("input[type=radio][name='enableFlg']:checked").val()==null){
		alert("请选择是否启用");
		return false;
	}
	var specTypeCd = $("input[type=radio][name='specTypeCd']:checked").val();
	if(specTypeCd !=null && specTypeCd == '1'){
		var basePrice = $('#basePrice').val();
		var lowPrice = $('#lowPrice').val();
		var highPrice = $('#highPrice').val();
		var floatRate = $('#floatRate').val();
		if($.trim(basePrice) == null || $.trim(basePrice)==""){
			alert("请输入均单价");
			return false;
		}
		if($.trim(lowPrice) == null || $.trim(lowPrice)==""){
			alert("请输入最低价");
			return false;
		}
		if($.trim(highPrice) == null || $.trim(highPrice)==""){
			alert("请输入最高价");
			return false;
		}
		if($.trim(floatRate) == null || $.trim(floatRate)==""){
			alert("请输入上下浮动比例");
			return false;
		}
	}
	var url = "${ctx}/cost/cost-mate!checkMateName.action";
	var data ={mateName:$.trim(mateName) ,costMateId:$("#costMateId").val()};
	$.post(url,data,function(result) {
		if("success"==result){
			alert("设备名称已存在，请更换!");
			$('#mateName').focus();
			return false;
		}else{
			$('#costMateForm').submit();
		}
	});
}

//搜索设备型号价格
function doQueryPrice(){
	var costMateId =$("#costMateId").val();
	var specName =$("#specName").val();
	var modelName =$("#modelName").val();
	var stratPrice =$("#stratPrice").val();
	var endPrice =$("#endPrice").val();
	var enableFlg = $("#enableFlgPrice").val();
	$('#costMatePriceList').mask('正在搜索,请稍候...');
	$.post("${ctx}/cost/cost-mate-price!list.action",{
			costMateId:costMateId,
			specName:specName,
			modelName:modelName,
			stratPrice:stratPrice,
			endPrice:endPrice,
			enableFlg:enableFlg
		},function(result) {
		if (result) {
	    	$('#costMatePriceList').unmask();
			$("#costMatePriceList").html(result);
			mergeRows();
		}
	});
}

function mergeRows(){

	/*
	//处理合并行
	var tmpCell;
	var preMeterialName = 'none';
	var tmpMeterialName;
	var rowCount = 0;
	var startRowId = '';//materialName_index (1,2,3...)
	
	$('#table1 tbody tr').each(function(i,n){
		
		tmpCell = $(this).find("td:eq(1)");
		tmpMeterialName = tmpCell.html();
		
		if(preMeterialName == tmpMeterialName){
			rowCount++;
			alert(preMeterialName +','+ tmpMeterialName + ',' + rowCount);
			$(this).find("td:eq(1)").remove();
			$('#' + startRowId).attr('rowspan', rowCount);
		}else{
			rowCount = 1;
			startRowId = tmpCell.attr('id');
			preMeterialName = tmpMeterialName;
		}
	});
	*/
}
//刷新
function refresh(id){
	var url="${ctx}/cost/cost-mate!input.action?id="+id;
	parent.TabUtils.newTab("cost-mate-input","设备基本信息",url,true);
}
//配置属性
function setMateParam(){
	var costMateId = $('#costMateId').val();
	if(costMateId == "" || costMateId == null){
		alert("请先保存材料设备基本信息");
		return false;
	}else{
		var url="${ctx}/cost/cost-mate-col!list.action?costMateId="+costMateId;
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cost-mate-col-list","配置属性",url,true);	
	}
}
//添加型号价格
function addCostMatePrice(id){
	var costMateId = $('#costMateId').val();
	if(costMateId == "" || costMateId == null){
		alert("请先保存材料设备基本信息");
		return false;
	}
	var url="${ctx}/cost/cost-mate-price!input.action?costMateId="+costMateId;
	location.href = url;
}

function showPriceDiv(value){
	//if($("#priceDiv").is(":hidden")){
	if(value=='1'){
		$("#priceDiv").show();
		$("#priceDiv2").show();
		$("input[name=calcTypeCd][value=2]").attr("checked",true);
		$("#fomuDesc").val("(1+(时价-均单价)/1000*上下浮动比例)*型号价格");
	}else{
		$('#priceDiv').hide();
		$('#priceDiv2').hide();
		$("input[name=calcTypeCd][value=1]").attr("checked",true);
		$('#basePrice').val('');
		$('#lowPrice').val('');
		$('#highPrice').val('');
		$("#floatRate").val('');
		$("#fomuDesc").val('');
	}
}

function clearPrice(){
	$("#specName").val('');
	$("#modelName").val('');
	$("#stratPrice").val('');
	$("#endPrice").val('');
	$("#enableFlgPrice").val('');
	doQueryPrice();
}

function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#matePriceDetail").ajaxSubmit(function(result) {
		$("#costMatePriceList").html(result);
		mergeRows();
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
</script>
</body>
</html>
