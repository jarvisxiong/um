<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商业资产管理台账</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/base.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/assm.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/style.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"/>
		
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/assm/assm-main.js"></script>
</head>

<body>
<div id="warp">
<%-- 查看标示，用于左边树点击搜索判断值 --%>
<input type="hidden" id="accountView" 
	<security:authorize ifAnyGranted="A_ASSM_ACC_NEW,A_ASSM_ACC_EDIT,A_ASSM_ACC_DEL,A_ASSM_ACC_VIEW_PRO,A_ASSM_ACC_VIEW_ALL">
		value="accountView"
	</security:authorize>
/>
<input type="hidden" id="modelView" 
	<security:authorize ifAnyGranted="A_ASSM_MOD_NEW,A_ASSM_MOD_DEL,A_ASSM_MOD_VIEW">
		value="modelView"
	</security:authorize>
/>
<%--父节点ID，隐藏显示 --%>
<input type="hidden" name="pratentId" id="gloab_hide_pratentId"/>

<%--顶部栏 --%>
<div id="header">
    <div class="title1 clearfix">
        <h2 style="width: auto;float: left;">商业资产管理台帐</h2>
	 	<div class="btns">
				<security:authorize ifAnyGranted="A_ASSM_ACC_NEW,A_ASSM_ACC_EDIT,A_ASSM_ACC_DEL,A_ASSM_ACC_VIEW_PRO,A_ASSM_ACC_VIEW_ALL">
					<%--资产台账快速搜索 --%>
					<input style="float: left;margin-top:3px; padding: 2px;width: 150px;background:#FFF url(/PowerDesk/resources/images/desk2/search.png) no-repeat 136px 1px;color:#cccccc;"
					       title="支持资产台账名称、编码搜索"
					       value="快速搜索..."
					       onkeyup="quickSearchAccount();"
				    	   onclick="clearQuickSearch(this);"
					       onblur="resetQuickSearch(this);" 
					       name="account_quick" 
					       id="account_quick" 
					       class="text"/>
					<input type="hidden" id="account_quick_id"/>
				</security:authorize>
				<security:authorize ifAnyGranted="A_ASSM_MOD_NEW,A_ASSM_MOD_DEL,A_ASSM_MOD_VIEW">
					<%--设备型号快速搜索 --%>
					<input style="float: left;margin-top:3px; padding: 2px;width: 150px;background:#FFF url(/PowerDesk/resources/images/desk2/search.png) no-repeat 136px 1px;color:#cccccc;display: none;"
					       title="支持设备名称、编码、专业编码、长编码搜索"
					       value="快速搜索..."
					       onkeyup="quickSearchModel();"
				    	   onclick="clearQuickSearch(this);"
					       onblur="resetQuickSearch(this);" 
					       name="model_quick"
					       id="model_quick"
					       class="text"/>
					<input type="hidden" id="model_quick_id"/>
				</security:authorize>
			
			<button class="blue" type="button" onclick="depreMaint(this);">资产折旧</button>
			<security:authorize ifAnyGranted="A_ASSM_MOD_NEW,A_ASSM_MOD_DEL,A_ASSM_MOD_VIEW">
				<button type="button" class="blue" onclick="modelMaint(this);">设备型号管理</button>
			</security:authorize>
			
		    <security:authorize ifAnyGranted="A_ASSM_ACC_NEW,A_ASSM_ACC_EDIT,A_ASSM_ACC_DEL,A_ASSM_ACC_VIEW_PRO,A_ASSM_ACC_VIEW_ALL">
		          <button type="button" class="blue" onclick="accountMaint(this);">资产管理台账</button>
			</security:authorize>
			
			<security:authorize ifAnyGranted="A_ASSM_ACC_NEW,A_ASSM_ACC_EDIT,A_ASSM_ACC_DEL,A_ASSM_ACC_VIEW_PRO,A_ASSM_ACC_VIEW_ALL">
				<button type="button" class="gray" id="accountSearchBtu" onclick="showAccountSearchDiv();" style="margin-left: 5px;">高级搜索</button>
			</security:authorize>
			
			<security:authorize ifAnyGranted="A_ASSM_MOD_NEW,A_ASSM_MOD_DEL,A_ASSM_MOD_VIEW">
				<button type="button" class="gray" id="modelSearchBtu" onclick="showModelSearchDiv();" style="margin-left: 5px;display:none;">高级搜索</button>
			</security:authorize>
			
         </div>
    </div>
</div>

<div style="position: absolute;bottom:0;top:39px;width:100%;overflow:auto;+overflow:visible;">
  	<table style="width:100%;height: 100%;">	
	<tr>
		<td id="leftPanel" style="width:160px;border-right: 1px solid #8c8f94;background-color:#e4e7ec;" valign="top">
			<%-- 左边设备树  --%>
			<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
				<tr>
					<td>
						<div id="leftTreePanel" style="height:100%;width:160px;padding-top: 5px; float: left; overflow-y: auto; overflow-x: hidden;border: none;">							
						</div>
					</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" title="您可以拖动,调整宽度" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td valign="top">
			<div id="mainDiv">
			  <form id="mainForm" action="${ctx}/assm/assm-model!loadList.action" method="post">
				<input type="hidden" name="assmAccountId" id="assmAccountId"/>
				<input type="hidden" name="parentId" id="parentId"/>
				<div id="header">
					<div id="searchDiv" class="form_body condition_panel none">
						<%--资产搜索 --%>
	          			<ul class="clearfix" id="searchAccountDiv" style="display: none;">
		                    <li>
								<label style="width: 60px;">商业公司：</label>
								<input type="text" 
									   class="text max"
									   style="cursor:pointer;width: 315px;"
								       id="s_projectName" 
								       name="s_projectName" 
								       <security:authorize ifNotGranted="A_ASSM_ACC_VIEW_ALL">
								       	 disabled="disabled"
								       </security:authorize>
								       title="点击选择项目公司"/>
								<input type="hidden" id="s_projectCd" name="s_projectCd"/>
		                       	<label>使用情况：</label><s:select cssClass="box" list="mapUseStatus" listKey="key" listValue="value" name="s_useStatus" id="s_useStatus" cssStyle="width:120px;"/>
							</li>
							<li>
								<label style="width: 60px;">资产名称：</label><input type="text" class="text" id="s_account_assmName" name="s_account_assmName"/>
								<label>资产编码：</label><input type="text" class="text" id="s_code" name="s_code"/>
								<label>登记人员：</label><input type="text" class="text" name="s_creatorName" id="s_creatorName" title="点击选择用户" style="cursor: pointer;"/><input type="hidden" id="s_creator" name="s_creator"/>
							</li>
							<li>
								<label style="width: 60px;">使用部门：</label><input type="text" class="text" id="s_useDepartmentName" name="s_useDepartmentName" title="点击选择部门" style="cursor: pointer;"/><input type="hidden" id="s_useDepartment" name="s_useDepartment" />
								<label>使用日期：</label><input type="text" class="text" id="s_useDate" name="s_useDate" onfocus="WdatePicker()"/>
								<label>保管人员：</label><input type="text" class="text" id="s_keeperName" name="s_keeperName" title="点击选择用户" style="cursor: pointer;"/><input type="hidden" id="s_keeperCd" name="s_keeperCd"/>
							</li>
							<li class="buttons clearfix">
		                        <button type="button" class="blue min" onclick="doQuery('assmAccount');">搜索</button>
		                        <security:authorize ifAnyGranted="A_ASSM_ACC_EXPORT">
		                        	<button type="button" class="green min" onclick="doExportAccount();">导出</button>
		                        </security:authorize>
		                        <button type="button" class="green min" onclick="doClear();">清空</button>
								<button type="button" class="red min" onclick="showAccountSearchDiv();">取消</button>
	                  		</li>
		           		</ul>
		        		<%--设备搜索 --%>
		       			<ul class="clearfix" id="searchModelDiv" style="display: none;">
		                    <li>
								<label style="width: 60px;">设备名称：</label><input type="text" class="text" id="s_assmName" name="s_assmName"/>
								<label>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label><input type="text" class="text" id="s_assmCode" name="s_assmCode"/>
							</li>
							<li>
								<label style="width: 60px;">专业编码：</label><input type="text" class="text" id="s_proCode" name="s_proCode"/>
								<label>长&nbsp;编&nbsp;&nbsp;码：</label><input type="text" class="text" id="s_fullCode" name="s_fullCode"/>
							</li>
							<li class="buttons clearfix">
		                        <button type="button" class="blue min" onclick="doQuery('assmModel');">搜索</button>
		                        <button type="button" class="green min" onclick="doClear();">清空</button>
								<button type="button" class="red min" onclick="showModelSearchDiv();">取消</button>
		                  	</li>
		           		</ul>
					</div>
				</div>
				
				<%--右边顶部显示div --%>
				<div style="margin: 10px 5px 0px 10px;" id="body">
				
					<div class="res_tip">
						<span id="titleSpan" style="margin-left: 10px;font-size: 16px;font-weight: bold;">资产管理台帐</span>
						<span id="modelDetail" style="display:none;margin-left: 10px;">
							<span id="modelNameHolder"  style="margin-right: 5px;"></span>
							(当前配置共&nbsp;&nbsp;<span id="hasNum_detail"></span>&nbsp;&nbsp;台，
							<span id="currentModelId" 
								  style="color: #0072bb;cursor: pointer;" 
								  onclick="standModel(this);" 
								  modelId="" title="点击查看标准配置">标准配置</span>&nbsp;&nbsp;
							<span id="stanNum_detail"></span>&nbsp;&nbsp;台)
						</span>
					</div>
					
					<%--资产台账新增、删除按钮 --%>
					<div style="padding: 8px 0;" id="assmAccountBtu">
						<div class="btns clearfix" style="">
							<security:authorize ifAnyGranted="A_ASSM_ACC_NEW">
								<button type="button" class="blue min" onclick="addAssmAccount();" style="margin-right: 8px;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;">新增</button>
							</security:authorize>
							<security:authorize ifAnyGranted="A_ASSM_ACC_DEL">
								<button type="button" class="red min" onclick="deleteBatchAccount();" style="padding-top: 1px;">删除</button>
							</security:authorize>
							<span id="accountDelTip" style="float: left;margin-top: 10px;margin-bottom:0px;text-align: center;"></span>
						</div>
					</div>
					
					<%--设备型号新增、删除按钮 --%>
					<div style=" padding: 8px 0;display: none;" id="assmModelBtu">
						<div class="btns clearfix">
							<security:authorize ifAnyGranted="A_ASSM_MOD_NEW">
								<button type="button" class="blue min" onclick="showModeFormDiv();" style="margin-right: 8px;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;">新增</button>
							</security:authorize>	
							<security:authorize ifAnyGranted="A_ASSM_MOD_DEL">
								<button type="button" class="red min" onclick="deleteModel(this);" style="padding-top: 1px;">删除</button>														
							</security:authorize>
							<span id="modelDelTip" style="float: left;margin-top: 10px;margin-bottom:0px;text-align: center;"></span>
						</div>
					</div>
					<div style="clear: both;"></div>
					
					<%--新增设备型号DIV --%>
					<div id="modeFormDiv" style="display: none;background: none repeat scroll 0 0 #F7F7F7;margin-top: 5px;margin-bottom: 10px;padding-top: 10px;padding-bottom: 10px;">
						<input type="hidden" name="inp_pratentId" id="hide_pratentId"></input>
						<table class="newTableForm">
							<col width="100"/>
							<col />
							<col width="100"/>
							<col/>
							<col/>
							<tr style="line-height: 30px;">
								<td align="right" style="padding-right: 5px;">
									<label>当前设备类型：</label>
								</td>
								<td colspan="3">
									<input name="inp_parentName" type="text" id="inp_parentassmName" disabled="disabled" class="text" style="width:98%;background-color: #EDEFF3;"/>
								</td>
							</tr>
							<tr style="line-height: 30px;">												
								<td align="right" style="padding-right: 5px;">
									<label>设备名称：</label>
								</td>
								<td>
									<input name="inp_assmName" type="text" id="inp_assmName" style=" border-left: 2px solid red;"/>
								</td>											
								<td align="right" style="padding-right: 5px;">
									<label>编&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
								</td>
								<td>
									<input name="inp_assmCode" type="text" id="inp_assmCode"  style=" border-left: 2px solid red;"/>
								</td>												
							</tr>
							<tr style="line-height: 30px;">												
								<td align="right" style="padding-right: 5px;">
									<label>专业编码：</label>
								</td>
								<td>
									<input name="inp_proCode" type="text" id="inp_proCode"/>
								</td>											
								<td align="right" style="padding-right: 5px;">
									<label>长编码：</label>
								</td>
								<td>
									<input name="inp_fullCode" type="text" id="inp_fullCode" />
								</td>												
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td colspan="3" align="left" style="padding-top: 10px;">
									<div class="btns clearfix">
										<button type="button" class="green min" onclick="saveNewModel(this);" style="margin-right:7px;">保存</button>
										<button type="button" class="red min" onclick="showModeFormDiv();">取消</button>
										<span id="rsTip" style="float: left;margin-top: 10px;margin-bottom:0px;text-align: center;"></span>
									</div>											
								</td>
							</tr>
						</table>
					</div>
        			<%--搜索结果Div --%>
	     			<div id="resultTable"></div>
	     		</div>
			  </form>
         	</div>
         		
       		<%--资产台账增加DIV --%>
			<div id="accountAddDiv" class="default"></div>
			<%--资产折旧DIV --%>
			<div id="depreciationDiv" class="default"></div>
		</td>
	</tr>
</table>
</div>
</div>

<script type="text/javascript">
$(function(){
	//autoHeight();
	//$("#leftTreePanel").height($(window.top.document).find("#bodyLoad").height()-$("#header").height()-51);
	//var bodyHeight = Number($(document).height()-35);
	//$("#leftTreePanel").css("height",bodyHeight+"px");
	var bodyHeight = Number($(document).height()-80);
	$("#leftTreePanel").css("height",bodyHeight+"px");
	//左右拖拉
	$('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 160, height: ($('#mainDiv').height()) },
        max: { width: 300, height: ($('#mainDiv').height()) },
        onResize: function(e) {
        	$('#leftTreePanel').width($('#leftPanel').width()-7);
        }
    });
    
	loadAssmTree('','','true');
	<security:authorize ifAnyGranted="A_ASSM_ACC_VIEW_PRO,A_ASSM_ACC_VIEW_ALL">
   		loadAccount();
	</security:authorize>
   	
    //-------搜索-------//
    //商业公司|总部快速搜索
	$('#s_projectName').onSelect({
    	top:10,
    	muti:false, //true多选、false单选 
    	callback:function(obj){
	    	if('' != obj.bisProjectId) {
		    	parent.bisProjectId = obj.bisProjectId;
		    	$("#s_projectName").val(obj.projectName);
		    	$("#s_projectName").next().val(obj.orgCd);
	    	}
    	}
    });
   	
  	//选择保管人员
	$("#s_keeperName").userSelect({
        muti:false,
        nameField:'keeperName',
        cdField:'keeperCd'
	});
	//录入人员
	$("#s_creatorName").userSelect({
        muti:false,
        nameField:'creatorName',
        cdField:'creator'
	});
	//选择使用部门
	$('#s_useDepartmentName').orgSelect({});
});

//快速搜索资产台账
function quickSearchAccount(){
	$("#account_quick").quickSearch(
   		"${ctx}/assm/assm-account!quickSearch.action",
   		['assmName','code'],
   		{assmAccountId:'account_quick_id',assmName:'account_quick'},'',
   		function(result){
   			var id = $("#account_quick_id").val();
   			var url=_ctx+"/assm/assm-account!getAssmModelParentId.action";
   			TB_showMaskLayer("正在搜索...");
   			$.post(url,{assmAccountId:id},function(result){
   				$("#assmAccountId").val(id);
   	   			var ids = result.split(',');
   	   			if("" != ids[0] && "" != ids[1]){
	   				var url2 = _ctx+"/assm/assm-account!list.action";
	   				TB_showMaskLayer("正在搜索33...");
	   	   			$.post(url2,{assmAccountId:id},function(result){
	   	   				TB_removeMaskLayer();
	   	   				$("#accountAddDiv").hide();
	   	   				$("#resultTable").html(result);
	   	   				$("#mainDiv").show();
		   	   			loadAssmTree(ids[0],ids[1],'true');
	   	   			});
   	   	   		}
   	   		});
       	}
   	);
}
//快速搜索设备型号
function quickSearchModel(){
	$("#model_quick").quickSearch(
   		"${ctx}/assm/assm-model!quickSearch.action",
   		['assmName','assmCode','proCode','fullCode'],
   		{assmModelId:'model_quick_id',assmName:'model_quick'},'',
   		function(result){
       		var modelId = $("#model_quick_id").val();
       		var url=_ctx+"/assm/assm-model!getParentId.action";
   			$.post(url,{modelId:modelId},function(result){
   	   			var ids = result.split(',');
   	   			if("" != ids[0] && "" != ids[1]){
   	   				alert(_ctx);
	   	   			var url2=_ctx+"/assm/assm-model!loadModelById.action";
	   	   			TB_showMaskLayer("正在搜索...");
	   	   			$.post(url2,{assmModelId:modelId},function(result){
	   	   				TB_removeMaskLayer();
	   	   				$("#resultTable").html(result);
	   	   				loadAssmTree(ids[0],ids[1]);
	   	   			});
   	   			}
   			});
   		}
   	);
}

//导出Excel-资产数据
function doExportAccount(){
	//如果查询框中没有值，则按快速查询的值导出，否则清空assmAccountId的值
	$("#searchAccountDiv").find('input').each(function(i){
		var value = $(this).val().trim();
		if('' != value){
			$("#assmAccountId").val('');
		}
	});
	if("" != $.trim($("#s_useStatus").val())){
		$("#assmAccountId").val('');
	}
	TB_showMaskLayer("正在导出...",5000);
	$("#mainForm").attr("action","${ctx}/assm/assm-account!doExportAccount.action");
	$("#mainForm").submit();
}
</script>
</body>
</html>