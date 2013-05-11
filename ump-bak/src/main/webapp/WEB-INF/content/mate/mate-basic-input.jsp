<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>甲供料台账基本信息</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.example.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/mate/mateCount.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	
	<style>
		th{
			text-align: right;
		}
		td{
			text-align: left;
		}
	</style>
</head>
<body style="line-height:30px;">
	<div class="title_bar">
		<div class="fLeft banTitle" style="margin-left:5px;">甲供料信息</div>
		<div class="fRight">
		
			<s:if test="useStatus!=1">
			
				<s:if test="mateCountBasicId == null">
					<security:authorize ifAnyGranted="A_MATE_SUBMMIT">
						<input type="button"  class="btn_new btn_blue_new" onclick="basicSave('1');" value="提交" />
					</security:authorize>
					<security:authorize ifAnyGranted="A_MATE_COMM">
						<input type="button"  class="btn_new btn_green_new" onclick="basicSave();" value="保存" />
					</security:authorize>
				</s:if>
				<s:else>
					<security:authorize ifAnyGranted="A_MATE_COMM">
						<input type="button"  class="btn_new btn_blue_new" onclick="basicSave();" value="保存" />
					</security:authorize>
					
					<security:authorize ifAnyGranted="A_MATE_SUBMMIT">
						<input type="button"  class="btn_new btn_green_new" onclick="basicSave('1');" value="提交" />
					</security:authorize>
				</s:else>
		 
			</s:if>
			<s:if test="useStatus==1">
				<security:authorize ifAnyGranted="A_MATE_ADMIN">
					<input type="button"  class="btn_new btn_cancel_new" style="width:70px;text-align: center;"  onclick="basicSave('0');" value="取消提交"/>
				</security:authorize>
			</s:if>

			<input type="button" class="btn_new  btn_full_new" onclick="window.open(location.href);" value="全屏" />
			<input type="button" class="btn_new  btn_refresh_new" onclick="window.location.href=location.href;" value="刷新" />
		</div>
	</div>

	<form action="${ctx}/mate/mate-basic!save.action" method="post" id="mateForm">
		<%--选择甲供料树后，回填 --%>
		<input type="hidden" name="ownerMaterialType" id="ownerMaterialType" value = "${ownerMaterialType}" />
		<input type="hidden" name="projectCd" id="projectCd" value ="${projectCd}"/>
		
		<input type="hidden" name="inputFlag" id="inputFlag" />
		<input type="hidden" name="useStatus" id="useStatus" value = "${useStatus}"/>
		<input type="hidden" name="recordVersion" id="recordVersion" value="${recordVersion}" />
		<input type="hidden" name="id" id="mateCountBasicId" value ="${mateCountBasicId}" /><%--很重要 --%>
		<input type="hidden" name="supType" id="supType" />

		<div style="margin-left:5px;padding-left:65px;">
			甲供料类型：
			<input style="width:200px;padding: 2px 5px;cursor: pointer;" 
			type="text" id="ownerMaterialTypeName" name ="ownerMaterialTypeName" 
			onclick="selectMateType();" 
			readonly="readonly" 
			value="<p:code2name mapCodeName="mateTypeMap" value="ownerMaterialType" />"/>
		</div>
				 
		<s:if test="inputFlag != null && inputFlag != ''">
		 	<font  color="darkgray">${inputFlag}</font>
		</s:if>
		<div style="padding-left:5px; width:100%; background-color:#E4E7EC;">甲供料基本信息</div>
		<div id="cont_basic">
				<table class="cont-show-table">
					<col style="width:140px;"/>
					<col />
					<col style="width:100px;"/>
					<col />
					<col style="width:100px;"/>
					<col />
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<tr>
					    <th>*供货合同编号:</th>
					    <td><input type="text"   id="contNo" name ="contNo" value="${contNo}" maxlength='40' /></td>
					    <th><font  color="darkgray">供货合同名称:</font></th>
					    <td><input type="text" id="contName" name ="contName" value="${contName}" maxlength='40' /></td>
						<th><font  color="darkgray">供货单位名称:</font></th>
						<td><input type="text" id="suppUnitName" name ="suppUnitName" value ="${suppUnitName}" maxlength='90' readonly="readonly"/></td>
						<th>*是否安装:</th>
						<td style="padding-top:5px"><s:radio list="#{'false':'否','true':'是'}" name="instalBl" id="instalBl" onclick="insBl(this.value)" ></s:radio></td>
					</tr>
					<tr>
						<th>*施工合同编号:</th>
						<td><input type="text" id="exeContNo" name ="exeContNo" value="${exeContNo}" maxlength='40' /></td>
						<th><font  color="darkgray">施工合同名称:</font></th>
						<td><input type="text" id="exeContName" name ="exeContName" value="${exeContName}" maxlength='40' readonly="readonly"/></td>
						<th><font  color="darkgray">施工单位名称:</font></th>
						<td><input type="text" id="exeUnitName" name ="exeUnitName" value="${exeUnitName}" maxlength='40' readonly="readonly"/></td>
					</tr>
					<tr>
						<th>*合同供应预算量:</th>
						<td><input type="text" validate="required"  id="countSupplyBud" name ="countSupplyBud" value ="${countSupplyBud}" alt="amount" onblur="supplyBugget()" maxlength='14' /></td>
						<th>预算调整量:</th>
						<td><input type="text" id="budAdjust" name ="budAdjust" value="${budAdjust}" alt="amount" onblur="supplyBugget()" maxlength='14' /></td>
						<th><font  color="darkgray">预算供应总量:</font></th>
						<td><input type="text" id="budSupplyAll" name ="budSupplyAll" value ="${budSupplyAll}"  maxlength='14' readonly="readonly"/></td>
					</tr>
				    <tr>
						<th><font  color="darkgray">实际供应总量:</font></th>
						<td><input type="text" id="realSupplyAll" name ="realSupplyAll" value="${realSupplyAll}" alt="amount" readonly="readonly" maxlength='14' /></td>
						<th><font  color="darkgray">实际供应比例:</font></th>
						<td><input type="text" id="realSupplyRate" name ="realSupplyRate"  readonly="readonly" />%</td>
					</tr>
					<tr>
						<th><font  color=darkgray>施工单位实际领用总量:</font></th>
						<td><input type="text" id="exeUnitRealUse" name ="exeUnitRealUse" value="${exeUnitRealUse}" alt="amount" readonly="readonly" maxlength='14'/></td>
						<th><font  color="darkgray">库存总量:</font></th>
						<td><input type="text" id="stockAll" name ="stockAll" value="${stockAll}" alt="amount" readonly="readonly"/></td>  
					</tr>
					<tr>
						<th><font  color=darkgray>已清算总量:</font></th>
						<td><input type="text" id="clearAudit" name ="clearAudit" value="${clearAudit}" alt="amount" readonly="readonly"/></td>
						<th><font  color="darkgray">未清算总量:</font></th>
						<td><input type="text" id="noClearAudit" name ="noClearAudit" value="${noClearAudit}" alt="amount" readonly="readonly"/></td>  
					</tr>
				</table>
		</div>
	</form>
	
	
	<form action="${ctx}/mate/mate-count!list.action" method="post" id="mateDetail">
		<div style="padding-left:5px; width:100%; background-color:#E4E7EC;">甲供料明细搜索： </div>
		<input type="hidden" name="id"  value ="${mateCountBasicId}" />
		<table>
			<tr>
				<td style="padding-left:5px;">
					进货日期:
					 <input class="Wdate" type="text" id="insDate" name="insDate" style="width:90px" onfocus="WdatePicker()" onblur="updateContStatus('plan');"/>
					-
					 <input class="Wdate" type="text" id="insDateBetween" name="insDateBetween" style="width:90px" onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
					领货单位名称:
					<input type="text" id="useUnitName" name="useUnitName" style="width:120px"  />
					清算状态:
					<!--   <s:radio list="#{'0':'未','1':'部分','2':'全部'}" name="clearStatus" id="clearStatus"></s:radio>-->
					 <select id="clearStatus" name="clearStatus" >
						 <option value="">无</option>
						 <option value="0">未清算</option>
						 <option value="1">清算</option>
					 </select>
					 
					 <input type="button" id="ModBtn" class="btn_new btn_query_new" onclick="doQueryMateDetail();" value="搜索"/>
					 
					 <s:if test="useStatus!=1">
					 <security:authorize ifAnyGranted="A_MATE_COMM">
						<s:if test="mateCountBasicId != null">
							<input type="button"  class="btn_new btn_add_new"   style="width:100px;text-align: center;" onclick="mateDetail();" value="新增甲供料明细" />
						</s:if>
					 </security:authorize>
					 </s:if>
					 
					 <input type="button" id="ModBtn" class="btn_new btn_export_new" onclick="doExportExcel();" value="导出" title="导出所有记录(不分页)"/>
					 
				</td>
			</tr>
			<tr>
				<td style="padding-left:5px;">
					
					 <s:if test="useStatus!=1">
						 <security:authorize ifAnyGranted="A_MATE_COMM">
						 	<input type="button" id="ModBtn" class="btn_new btn_import_new" onclick="doImportExcel();" value="批量导入" style="width:70px;"/>
						 	<a class="" style="color:blue;margin-left:10px;text-decoration: underline;" href="${ctx}/app/download.action?fileName=materialDetailTmplate.xls&realFileName=甲供料材料明细-导入模板.xls&bizModuleCd=public">
						 	下载: 甲供料材料明细-导入模板.xls
						 	</a>
						 </security:authorize>
					</s:if>
				</td>
			</tr>
		</table>
		<security:authorize ifAnyGranted="A_MATE_COMM,A_MATE_VIEW,A_MATE_BASICDEL,A_MATE_TYPEDEL,A_MATE_ACOUNT,A_MATE_ADMIN,A_MATE_SUBMMIT">
		<div style="padding-left:5px;width:100%; background-color:#E4E7EC;">明细列表
		</div>
		<div id="mateDetailList">
			<%-- 材料明细信息列表 --%>
	  	</div>
		</security:authorize>
	</form>
<script type="text/javascript">
var curr_user_cont = '<%= SpringSecurityUtils.getCurrentUiid()%>'; 
//$("#suppUnitName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'suppUnitName',supType:'supType'});
$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName','partB'],{contNo:'contNo',contName:"contName",partB:'suppUnitName'});
$("#exeContNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName','partB'],{contNo:'exeContNo',contName:"exeContName",partB:'exeUnitName'});
$(function(){
	var budSupplyAll=eval($('#budSupplyAll').val()||0);
	var clearAudit=eval($('#clearAudit').val()||0);
	var noClearAudit=eval($('#noClearAudit').val()||0);
	var realSupplyAll=eval($('#realSupplyAll').val()||0);
	var ownerMaterialTypeName=$('#ownerMaterialTypeName').val();
	var instalBl=$("input[type=radio][name='instalBl']:checked").val();
	$('#clearAudit').val(clearAudit.toFixed(2));
	$('#noClearAudit').val(noClearAudit.toFixed(2));
	if(ownerMaterialTypeName!=""){
		$('#ownerMaterialTypeName').attr('disabled',true);
	}
	if(realSupplyAll!=null&&realSupplyAll!=""){
		$('#realSupplyRate').val((realSupplyAll/budSupplyAll*100).toFixed(2));
	}
	doQueryMateDetail();
	insBl(instalBl);
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
});
function doExportExcel(){
	$("#mateDetail").attr("action", "${ctx}/mate/mate-basic!exportExcel.action");
	$("#mateDetail").submit();
	$("#mateDetail").attr("action", "${ctx}/mate/mate-count!list.action");
}

//导入excel数据
function doImportExcel(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='mateCountImport'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 330,
		height : 120,
		title : "导入甲供料材料明细清单",
		closeBtn:true,
		afterShow : function() {
			var mateCountBasicId = $('#mateCountBasicId').val();
			
			var url = _ctx+"/mate/mate-count!mateCountImport.action";
			$.post(url,{mateCountBasicId : mateCountBasicId}, function(result) {
				$("#mateCountImport").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				if($("#file").val()=="") {
					alert("请先选择要导入的文件");
					$("#file").focus();
					return false;
				}
				TB_showMaskLayer("正在导入...");
				var importFile =$("#file").val();
				$("#mateCountUplodForm").ajaxSubmit(function(result){
					TB_removeMaskLayer();
					var msg = result.split(",");
					if(msg[1] == "success") {
					    alert("导入成功,耗时"+msg[2]+"秒");
					    $("#mateCountUplodForm").val("");
					} else {
						alert(msg[2]);
					}
					//刷新当前页
				    refresh($("#mateCountBasicId").val());
				});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}

function insBl(insl){
	if(insl == 'true'){
		$('#exeContNo').val('');
		$('#exeContNo').attr('readonly',true);
		$('#exeContName').val('');
		$('#exeUnitName').val('');
	}else{
		$('#exeContNo').removeAttr('readonly');
	}
}
function basicSave(useSta){
	$('#useStatus').val(useSta);
//	var suppUnitName=$('#suppUnitName').val();
	var countSupplyBud=$('#countSupplyBud').val();
	var ownerMaterialTypeName=$('#ownerMaterialTypeName').val();
	if(ownerMaterialTypeName==null||ownerMaterialTypeName==""){
		alert("请选择材料类型后在保存");
		return false;
	}
	if($("input[type=radio][name='instalBl']:checked").val()==null){
		alert("请选择是否已安装");
		return false;
	}
	if(countSupplyBud==""||$('#contNo').val()==""){
		alert("请将必填信息填写完整");
		return false;
	}
	if($('#exeContNo').val()==""&&$("input[type=radio][name='instalBl']:checked").val() == 'false'){
			alert("请输入施工合同编号");
			return false;
	}
	$('#mateForm').submit();
}

function supplyBugget(){
 	var countSupplyBud = eval($('#countSupplyBud').val()||0);
	var budAdjust = eval($('#budAdjust').val()||0);
          $('#budSupplyAll').val(budAdjust+countSupplyBud);
} 

function doQueryMateDetail(){
	var insDateBetween =$("#insDateBetween").val();
	var insDate =$("#insDate").val();	
	var useUnitName =$("#useUnitName").val();
	var clearStatus = $("#clearStatus").val();
	var useStatus =$("#useStatus").val();
	var mateCountBasicId =$("#mateCountBasicId").val();
	if(useStatus==1){
		$(':text,textarea').attr('disabled',true);
		$(':radio').attr('disabled',true);
	}else{
		$(':radio').attr('disabled',false);
	}
	
	$('#mateDetailList').mask('正在搜索...');
	$.post("${ctx}/mate/mate-count!list.action",
		{
	      insDateBetween:insDateBetween,
	      insDate:insDate,
	      useUnitName:useUnitName,
	      clearStatus:clearStatus,
	      mateCountBasicId:mateCountBasicId,
	      useStatus:useStatus
	    },
		function(result) {
	    	$('#mateDetailList').unmask();
			if (result) {
				$("#mateDetailList").html(result);
			}
		}
	);
}

function refresh(id){
	var url="${ctx}/mate/mate-basic!input.action?id="+id;
	parent.TabUtils.newTab("mate-basic-input","基本信息",url,true);
}

function mateDetail(){
	var mateCountBasicId = $('#mateCountBasicId').val();
	var ownerMate = $('#ownerMaterialTypeName').val();
	var instalBl = $("input[type=radio][name='instalBl']:checked").val();
	if(mateCountBasicId==""||mateCountBasicId==null){
		alert("请先保存基本信息在填写详细信息");
		return false;
	}
	var url="${ctx}/mate/mate-count!input.action?mateCountBasicId="+mateCountBasicId+'&ownerMate='+ownerMate+'&instalBl='+instalBl;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("mate-count-input","新增详细信息",url,true);
}

</script>
</body>
</html>
