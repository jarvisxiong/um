<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>甲供料明细信息</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.example.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js" ></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/mate/mateCount.js"></script>
</head>
<body>
	<form action="${ctx}/mate/mate-count!save.action" method="post" id="mateForm">
	<div class="title_bar">
		<input type="hidden" name="ownerMaterialType" id="ownerMaterialType" value = "${mateBasic.ownerMaterialType}" />
		<input type="hidden" name="projectCd" id="projectCd" value ="${mateBasic.projectCd}"/>
		<input type="hidden" name=inputFlag id="inputFlag" />
		<input type="hidden" name="recordVersion" id="recordVersion" value="${recordVersion}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="${useStatus}" />
		<input type="hidden" name="instalBl" id="instalBl" value="${instalBl}" />
		<input type="hidden" name="clearAudit" id="clearAudit" value="${clearAudit}" />
		<input type="hidden" name="id" id="mateCountDetailId" value ="${mateCountDetailId}" />
		<input type="hidden" name="mateCountBasicId" id="mateCountBasicId" value ="${mateCountBasicId}" />
		<input type="hidden" id="mateAttaId" name="mateAttaId" value="${mateAttaId}"/>
		<input type="hidden" id="storeSignAtta" name="storeSignAtta" value="${storeSignAtta}"/>
		
		<div class="banTitle">甲供料明细信息</div>
	</div>
	
	
		<div>
			<div style="line-height: 35px;color:#316685;padding-left:10px;">材料类型:&nbsp;&nbsp;${ownerMate}</div>
			<fieldset>
				<legend >甲供料详细信息<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div id="cont_basic">
				
				<table class="cont-show-table" style="width:98%;">
					<col width="90"/>
					<col />
					<col width="120"/>
					<col />
					<col width="60"/>
					<col width="180"/>
				    <tr>
				    	<td colspan="6">
							<s:if test="inputFlag != null && inputFlag != ''">
							 <font  color=darkgray >${inputFlag}</font>
							</s:if>
				    	</td>
				    </tr>
				    <%--
				    <tr>
				    	<td align="right"><font  color=darkgray >材料类型:</font></td>
				    	<td colspan="5" class="pd-chill-tip" title='${ownerMate}'>(甲供料名称)&nbsp;&nbsp;${ownerMate}</td>
				    </tr>
				     --%>
				    <tr>
						<td align="right">*进退货状态:</td>
						<td>&nbsp;&nbsp;&nbsp;<s:radio list="#{'false':'退货','true':'进货'}" name="insOutBl"></s:radio></td>
						<td align="right">*进退货单编号:</td>
						<td><input type="text" id="insRegisterNumber" name ="insRegisterNumber" value="${insRegisterNumber}" maxlength='40'/></td>
						<td align="right">*定价方式:</td>
						<td>&nbsp;&nbsp;<s:radio list="#{'1':'合同价','2':'战略价','3':'核定价'}" name="priceWay"></s:radio></td>
						<!-- <td>施工合同名称</td>
						<td><input type="text" id="exeContName" name ="exeContName" value="${exeContName}" maxlength='50'/></td> -->  
					</tr>
					<tr>
						<td align="right"><font  color=darkgray >领货单位名称:</font></td>
						<td><input type="text" id="useUnitName" name ="useUnitName" value="${useUnitName}" maxlength='50'/></td>  
						<td align="right">进货日期:</td>
						<td width="140"><s:textfield name="insDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
						<td align="right">使用部位:</td>
						<td><input type="text" id="useSpace" name ="useSpace" value="${useSpace}" maxlength='50'/></td>  
					</tr>
					<tr>
						<td align="right">品名:</td>
						<td><input type="text" id="mateTasname" name ="mateTasname" value="${mateTasname}" maxlength='50'/></td>   
						<td align="right">品牌:</td>
						<td><input type="text" id="mateBrand" name ="mateBrand" value="${mateBrand}" maxlength='50'/></td>  
						<td align="right">材质:</td>
						<td><input type="text" id="mateChar" name ="mateChar" value="${mateChar}" maxlength='50'/></td> 
					</tr>
					<tr>
						<td align="right">规格:</td>
						<td><input type="text" id="mateStan" name ="mateStan" value="${mateStan}" maxlength='50'/></td>  
						<td align="right">型号:</td>
						<td><input type="text" id="mateType" name ="mateType" value="${mateType}" maxlength='50'/></td>  
						<td align="right">*单位:</td>
						<td><input type="text" id="unit" name ="unit" value="${unit}" maxlength='50'/></td> 
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td align="right">施工单位申请预算量:</td>
						<td><input type="text" id="exeUnitApplBud" name ="exeUnitApplBud" value="${exeUnitApplBud}" alt="amount" maxlength='14'/></td>
					</tr>
					<tr>
						<td align="right">*实际供应量:</td>
						<td><input type="text" id="realSupply" name ="realSupply" value="${realSupply}" alt="amount" maxlength='13' onblur="calculation()"/></td>
						<td align="right">*施工单位实际领用量:</td>
						<td><input type="text" id="exeUnitRealUse" name ="exeUnitRealUse" value="${exeUnitRealUse}" alt="amount" maxlength='13' onblur="calculation()"/></td>  
					</tr>
					<tr>
						<td align="right">*采购合同单价:</td>
						<td><input type="text" id="buyCoutSingle" name ="buyCoutSingle" value="${buyCoutSingle}" alt="amount" maxlength='13' onblur="calculation()"/></td>
						<td align="right">*施工合同暂定单价:</td>
						<td><input type="text" id="exeCoutSingle" name ="exeCoutSingle" value="${exeCoutSingle}" alt="amount" maxlength='13' onblur="calculation()"/></td>
					</tr>
					<tr>
						<td align="right"><font  color=darkgray >采购合同合价:</font></td>
						<td><input type="text" id="buyCoutGroup" name ="buyCoutGroup" value="${buyCoutGroup}" readonly="readonly" /></td>
						<td align="right"><font  color=darkgray >施工合同暂定合价:</font></td>
						<td><input type="text" id="exeCoutGroup" name ="exeCoutGroup" value="${exeCoutGroup}" readonly="readonly"  /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td align="right"><font  color=darkgray >库存量:</font></td>
						<td><input type="text" id="stockAll" name ="stockAll" value="${stockAll}" alt="amount" maxlength='14' readonly="readonly" /></td>  
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td align="right">库存管理单位:</td>
						<td><input type="text" id="stockManager" name ="stockManager" value="${stockManager}"  maxlength='100' /></td>  
						<td></td>
						<td></td>					
					</tr>
			 		<tr>
						<td align="right">三方签收附件:</td>
						<td>
						  <div class="atta_readOnly" style="float:left;cursor: pointer;"  onclick="showAttachment('${mateAttaId}')">
							   <s:if test="mateAttaList.get(0)==0">
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								</s:if>
								<s:else>
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
								</s:else>
								查看附件
						 </div>
						 <s:if test="useStatus!=1">
						 <div class="atta_edit" style="float:left;cursor: pointer;"  onclick="openAttachment(this,'${mateAttaId}','mateAttaId')">
						      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								添加附件
						  </div>
						 </s:if>
						</td>
						<td align="right">库存签收附件:</td>
						<td>
						  <div class="atta_readOnly" style="float:left;cursor: pointer;"  onclick="showAttachment('${storeSignAtta}')">
							   <s:if test="mateAttaList.get(1)==0">
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								</s:if>
								<s:else>
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
								</s:else>
								查看附件
						  </div>
						  <s:if test="useStatus!=1">
							  <div class="atta_edit" style="float:left;cursor: pointer;"  onclick="openAttachment(this,'${storeSignAtta}','storeSignAtta')">
							      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
									添加附件
							  </div>
						  </s:if>
						</td>
					</tr> 
					<tr>
						<td align="right">批量导入:</td>
						<td>
							<s:if test="importTypeFlg == true ">是</s:if>
							<s:else>否</s:else>
						</td>
					</tr>
				</table>
				</div>

			</fieldset>
			<s:if test="clearAudit!=1">
			<security:authorize ifAnyGranted="A_MATE_ACOUNT">
			<input type="button"  class="btn_green"  value="清算" onclick="clearSave('1');"/>
			</security:authorize>
			</s:if>
			<s:if test="useStatus!=1&&clearAudit!=1">
			<security:authorize ifAnyGranted="A_MATE_COMM">
			<input type="button"  class="btn_green"  value="保存" onclick="newChangeSave();"/>
			</security:authorize>
			</s:if>
	<!--	<security:authorize ifAnyGranted="A_MATE_SUBMMIT,A_MATE_ADMIN">
			
			</security:authorize>
			<security:authorize ifAnyGranted="A_MATE_ADMIN">
			<input type="button"  class="btn_blue"  value="取消提交" style="width:60px;" onclick="newEdit('0');"/>
			</security:authorize>-->
			<fieldset>
				<legend>备注（与合同履约有关的必要说明）<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div>
				<textarea id="remark" name="remark" style="width:98%;height:80px;border:1px solid #ccc;" >${remark}</textarea>
				</div>
			</fieldset> 
		
		</div>
	</form>
<script type="text/javascript">
var curr_user_cont = '<%= SpringSecurityUtils.getCurrentUiid()%>';
$(function(){
	if ($('#instalBl').val()=='true'){
		$('#exeUnitRealUse').attr('readonly','readonly');
		$('#exeCoutSingle').attr('readonly','readonly');
	}
	if($('#useStatus').val()=='1'||$('#clearAudit').val()=='1'){
		$(':text,textarea').attr('disabled',true);
		$(':radio').attr('disabled',true);
	}
	if($('#useStatus').val()=='0'&&('#clearAudit').val()!='1'){
		$(':text,textarea').attr('readOnly','');
		$(':radio').attr('disabled',false);
	}
	var buyCoutSingle=eval($('#buyCoutSingle').val()||0);
	var buyCoutGroup=eval($('#buyCoutGroup').val()||0);
	var exeCoutSingle=eval($('#exeCoutSingle').val()||0);
	var exeCoutGroup=eval($('#exeCoutGroup').val()||0);
	$('#buyCoutSingle').val(buyCoutSingle.toFixed(2));
	$('#buyCoutGroup').val(buyCoutGroup.toFixed(2));
	$('#exeCoutSingle').val(exeCoutSingle.toFixed(2));
	$('#exeCoutGroup').val(exeCoutGroup.toFixed(2));
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
	autoHeight();
});
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=mateCount&filterType=image|office&onlyShow=true",
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
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=mateCount&filterType=image|office",
		width:500,
		height:300,
		title: _title,
		afterShow:function(){},
		iframe:true
		});
}
/**		function newEdit(useStatus){
			if(useStatus!=null&&useStatus!=""){
				$('#useStatus').val(useStatus);
				}
				$('#mateForm').submit();
		}
*/
function newChangeSave(){
	var instalBl=$('#instalBl').val();
	var exeUnitRealUse =$('#exeUnitRealUse').val();
	var exeCoutSingle =$('#exeCoutSingle').val();
	if($("input[type=radio][name='priceWay']:checked").val()==null){
		alert("请选择定价方式");
		return false;
	}
	if(instalBl=='false'&&(exeCoutSingle==''||exeUnitRealUse=='')){
		alert("请将信息填写完整在保存");
		return false;
	}
	$('#mateForm').submit();
}
function clearSave(clearAudit){
	var mateCountDetailId = $('#mateCountDetailId').val();
	if(mateCountDetailId==""||mateCountDetailId==null){
		alert("请保存后在清算");
		return false;
	}
	$('#clearAudit').val(clearAudit);
	$('#mateForm').submit();
}
function calculation(){
	var realSupply=eval($('#realSupply').val()||0);
	var buyCoutSingle=eval($('#buyCoutSingle').val()||0);
	var exeCoutSingle=eval($('#exeCoutSingle').val()||0);
	var exeUnitRealUse=eval($('#exeUnitRealUse').val()||0);
	$('#buyCoutGroup').val((buyCoutSingle*realSupply).toFixed(3));
	$('#exeCoutGroup').val((exeUnitRealUse*exeCoutSingle).toFixed(3));
	$('#stockAll').val((realSupply-exeUnitRealUse).toFixed(3));
}
</script>
</body>
</html>
