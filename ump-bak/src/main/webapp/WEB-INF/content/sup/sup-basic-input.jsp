<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/mailStyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/fin.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/sup/sup.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js" ></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/sup/sup-basic.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
</head>
<body>

   	<security:authorize ifAnyGranted="A_ADMIN">
	<s:if test="supBasicId != null">
	<div class="title_bar" id="changeSupPanel">
		更改供应商级别 :<s:select list="mapSupEvaluate" listValue="value" name="supManaEval" id="handSupEval"/> 
		<input type="button" onclick="changeSupEval('${supBasicId}')" value="保存" class="btn_new btn_blue_new"/>	
		<script type="text/javascript">
			function changeSupEval(id){
				var supEvalLevel = $('#handSupEval').val();
				if($.trim(supEvalLevel) == ''){
					alert('请选择供方级别!');
					return;
				};
				/*
				if(!window.confirm('确认保存?')){
					return;
				}*/
				var url = '${ctx}/sup/sup-basic!changeEvalLevel.action';
				var data =  {id: id, supEvalLevel: supEvalLevel};
				$.post(url, data, function(result){
					if('success' == result){
						window.location.href= "${ctx}/sup/sup-basic!input.action?id="+id;
					}else{
						alert(result);
					}
				});
			}
		</script>
	</div>
	</s:if>
	</security:authorize>
	
	<form id="supForm" action="${ctx}/sup/sup-basic!save.action" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${supBasicId}"/>
		<input type="hidden" name="bizModuleCd" value="suppliers" />
		<input type="hidden" name="uiid" value="<%=SpringSecurityUtils.getCurrentUiid() %>" />
		<input type="hidden" id="bizEntId" name="bizEntityId"/>
		<input type="hidden" id="tableType" name="tableType"/>
		<input type="hidden" name="recordVersion" value="${recordVersion}"/>
		<input type="hidden" id="supAudit" name="supAudit" value="${supAudit}"/>
		<input type="hidden" name="deleteFlg" value="0"/>
		<input type="hidden" name="supDetails[0].supDetailId" value="${supDetails[0].supDetailId}"/>
		<input type="hidden" name="supDetails[0].recordVersion" value="${supDetails[0].recordVersion}"/>
		<div>
		<div class="title_bar">
		
           	<div class="fLeft banTitle">供应商详情</div>
		   	<div class="fLeft" style="margin-left:20px;">当前状态: 
				<span id="auditStat" style="margin-left:5px;font-weight:bold;color:red;"></span>
			</div>
		
		   <div class="fRight">
		   
		    	<security:authorize ifAnyGranted="A_SUP_CHECK">
				<s:if test="supBasicId != null">
		  			<input type="button" style="display:none" id="audit_btn" class="btn_new btn_blue_new" onclick="checkSup('${supBasicId}')" value="审核" />
		  		</s:if>
		    	</security:authorize>
		    	
				<security:authorize ifAnyGranted="A_SUP_NEW">
				  	<s:if test="supAudit!=2">
				  		<input type="button" id="div_edit" style="display:none;" class="btn_new btn_blue_new"  onclick="modSup();" value="编辑" />
		    			<input type="button" style="display:none;" id="submit_btn" class="btn_new btn_blue_new" onclick="submitSup('${supBasicId}')" value="提交"/>
				  	</s:if>
				</security:authorize>
				<security:authorize ifAnyGranted="A_SUP_CHECK,A_SUP_MODBASIC,A_SUP_MODAUDIT">
			  		<input type="button" id="div_edit" style="display:none;" class="btn_new btn_blue_new" onclick="modSup();" value="编辑" />
				</security:authorize>
				
				<s:if test="supBasicId == null">
		    	<input type="button" style="display:none;" id="save_btn" class="btn_new btn_green_new" onclick="doAddSaveSup('${supBasicId}');" value="保存" />
		    	</s:if>
		    	<s:else>
		    	<input type="button" style="display:none;" id="save_btn" class="btn_new btn_blue_new" onclick="doAddSaveSup('${supBasicId}');" value="保存" />
		    	</s:else>
		    	 
		    	<input type="button" style="display:none;" id="cancel_btn" class="btn_new btn_cancel_new" onclick="cancelSup();" value="取消" />
		    	
	  			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
	  			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
		   </div>
		   
		</div>
		<div style="margin:5px;">
		 	<table style="width:100%;">
		 	<thead>
			    <col />
				<col width="70"/>
				<col width="150"/>
				<col width="70"/>
				<col width="80"/>
				<col width="120"/>
			</thead>
			<tbody>
		  	<tr>
				<td class="sup_title pd-chill-tip" title="${supName}" rowspan="2" valign="top">
					<span class="spanBasic banTitle" style="color:black; font-weight: bolder/">${supName}
						<s:hidden id="supTypeSn" name="supTypeSn"/>
					</span>
					<s:if test="supBasicId ==null">
					 	<input type="text" id="supName" name="supName" validate="required" class="txtbox" value="${supName}"/>
					</s:if>
				</td>
				<td style="text-align: right;"><span class="td_bold">供方网站:</span></td>
				<td>
					<span class="supQuery spanBasic">
						<a href= "#" onclick="openWebSite('${companyWebSite}');">${companyWebSite}</a>&nbsp;
					</span>
					<span class="supEdit spanBasic"><input type="text" name="companyWebSite" validate="required" class="txtbox" value="${companyWebSite}"/></span>
				</td>
				<td  style="text-align: right;" class="sup_title_1 pd-chill-tip">
					<span class="td_bold">信息来源: </span>
				</td>
				<td>
					<span class="supQuery spanBasic">${supDetails[0].supComeFrom}&nbsp;</span>
					<span class="supEdit spanBasic"><s:textfield name="supDetails[0].supComeFrom" maxlength="25" class="txtbox"/></span>
				</td>
				<td class="sup_title_1" align="right" width="160" style="color:#0167a2;">
					<span class="td_bold" style="color:#0167a2;">更新:</span>
				  	<s:date name ="updatedDate" format="yyyy-MM-dd"/>
				</td>
		  	</tr>
			<tr>
				<td style="text-align: right;">
					<span class="td_bold">实力评价:</span>
				</td>
				<td style="text-align: left;">
					<span class="supQuery spanAudit">${tag2}</span>
					<span class="supEdit spanAudit"><s:textfield name="tag2" maxlength="25" class="txtbox"/></span>
				</td>
				<td style="text-align: right;"><span class="td_bold">级别:</span></td>
				<td><p:code2name mapCodeName="mapSupEvaluate" value="supManaEval" />
					<%--
					<span class="supQuery spanBasic"></span>
					<span class="supEdit spanBasic"><s:select list="mapSupEvaluate" listKey="key" listValue="value" id="supManaEval" name="supManaEval"/></span>
					 --%>
				</td>
				<td align="right"width="70" colspan="3">
					 
				</td>
		  	</tr>
		  	<tr>
				<td colspan="5">
					<table style="width:100%;">
						<tr>
							<td style="text-align: right;width:70px;"><span class="td_bold">适用地区:</span></td>
							<td style="text-align: left;">
								<span class="supQuery spanAudit">${tag1}</span>
								<span class="supEdit spanAudit"><input type="text" name="tag1" validate="required" class="txtbox" value="${tag1}"/></span>
							</td>
						</tr>
					</table>
				</td>
		  	</tr>
		 	</tbody>
		 	</table> 
		</div>
		<div style="padding-left:5px;padding-right:5px;">
		<table id="supInfo" class="sup_table" cellspacing="0" cellpadding="0">
		    <col width="30px"/>
			<col width="140px"/>
			<col/>
			<col/>
			<col width="120px"/>
		    <tr >
		     <th  align="left" nowrap="nowrap" style="padding-left:5px;">序号</th>
		     <th  align="left" nowrap="nowrap">|评审项目</th>
		     <th  align="left" nowrap="nowrap" colspan="2" style="padding-left:0px;">|评审情况</th>
		     <th  align="left" nowrap="nowrap">|附件</th>
		    </tr>
		</table>
		</div>
		</div>
		<div id="content_div" style="top:5px;overflow:auto;padding-left:5px;padding-right:5px;">
		   <%@ include file="sup-basic-detail.jsp"%>
		</div>
	</form>
	
	
<script type="text/javascript">
var curr_user_cont = '<%= SpringSecurityUtils.getCurrentUiid()%>'; 
$(function(){
	//var bodyHeight = Number(document.body.clientHeight-90);
	//$("#content_div").css("height",bodyHeight+"px");
	//显示当前审核状态
	var audit =$("#supAudit").val();
	if(audit==""||audit=="0"){
		$('#auditStat').text('未提交');
	}else if(audit=="1"){
		$('#auditStat').text('未审核');
	}else if(audit=="2"){
		$('#auditStat').text('已审核');
	}
	if($('#id').val() == ''){
		//新增
		$('#tipStatus').text('新增');
		ShowPrompt();
		editSup();
	}else{
		//搜索
		$('#div_edit').show();
		$('.supQuery').show();
		$('.supEdit').hide();
		<security:authorize ifNotGranted="A_SUP_SUPERUSER">
		<security:authorize ifAnyGranted="A_SUP_USER">
		  $("#div_audit").hide();
		</security:authorize>
		</security:authorize>
		getItemTree();
	}
	autoHeight();
});
//提示
function ShowPrompt(){
	var _this =$('input[alt="ShowPrompt"]');
	_this.live("click",function(){
		if("如该栏目无相关信息，请填写‘无’"==$(this).val()){
			$(this).val("");
			$(this).removeAttr("style");
		}
	});
	_this.live("mouseout",function(){
		if(""==_this.val()){
			_this.val("如该栏目无相关信息，请填写‘无’");
			_this.css("color","#909090");
		}
	});
	_this.val("如该栏目无相关信息，请填写‘无’");
	_this.css("color","#909090");
}
//编辑状态
function editSup(){
	var audit =$("#supAudit").val();
	//如果未提交、未审核状态，都可以填，如果已审核，那么 修改供方审核信息人员 才可以改审核信息
	if(audit==""||audit=="0"||audit=="1"){
		$('.supQuery').hide();
		$('.supEdit').show();
	}else{
		<security:authorize ifNotGranted="A_SUP_MODAUDIT">
		<security:authorize ifAnyGranted="A_SUP_MODBASIC">
		//只能修改供方的基本信息
		  $('.supQuery:not(.spanAudit)').hide();
		  $('.supEdit:not(.spanAudit)').show();
		</security:authorize>
		</security:authorize>
		<security:authorize ifAnyGranted="A_SUP_MODAUDIT">
		  $('.supQuery:not(.spanBasic)').hide();
		  $('.supEdit:not(.spanBasic)').show();
		</security:authorize>
	}
	$(':text').addClass("txtbox");
	$('tr').attr("title","");
	$('.sup_title_1,.sup_title').attr("title","");
	$('#save_btn,#cancel_btn,#submit_btn,#audit_btn').show();
}
function modSup(){
	$('#tipStatus').text('编辑');
	editSup();
	//按钮隐身
	$('#div_edit').hide();
}
function openWebSite(webSite){
	if(webSite!=""){
		if(webSite.indexOf('http://')>=0)
			window.open(webSite);
		else{
			window.open('http://'+webSite);
		}
	}
}
function getItemTree(){
	if(""!=$("#supTypeSn").val()){
		$.post("${ctx}/sup/sup-basic!itemTreeByQuery.action",{id:$("#id").val()}, function(result){
			var tree = new TreePanel({
				renderTo:'itemDiv',
				'root' : eval('('+result+')'),
				'ctx':'${ctx}'
			});
			tree.on(function(node){
				$("#id").val(node.attributes.trueId);
				$("#itemName").val(node.attributes.text);
				$("#parentCd").val(node.attributes.parentId);
				$("#parentName").val(node.attributes.parentName);
				$("#orderNo").val(node.attributes.orderNo);
				$("#itemType").val(node.attributes.finType);
				$("#itemCd").val(node.attributes.finItemCd);
			});
			tree.render();
			doExpandTreeNode(tree.getRootNode().childNodes);
		});
	}
}
function doExpandTreeNode(children){
	if(children!=null&&children.length>0){
		for(var i=0;i<children.length;i++){
			var node =children[i];
			node.expand();
			doExpandTreeNode(node.childNodes);
		}
	}
}
</script>
</body>
</html>
