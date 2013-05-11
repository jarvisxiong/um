<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
.choose{
float:left;
margin-left:5px;
}
.msg{
color:red;
}

</style>

<%--合同库 start --%>
<div align="center" class="billContent">
			<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="110"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title"><span class="con-title"">评审类别:</span></td>
				<td colspan="3"  class="chkGroup" align="left" validate="required">
					<table class="tb_checkbox" style="width:100%!important">
						<col width="120">
						<col width="120">
						<col>
						<tr class="conSelectItem">
						<s:if test="#canEdit == 'true'">
						<td colspan="3">
						
						<div class="choose"><s:checkbox name="templateBean.noncontlib" onclick="userCont('false');"  checked="true"  id="noncontlib"  cssClass="group"></s:checkbox>
						<span class="useless">招标文件</span></div>
						<div class="choose">						
						<s:if test="resApproveInfoId==null">
							<s:checkbox name="templateBean.contlib" onclick="userCont('true');" id="contlib"  cssClass="group"></s:checkbox><span class="use">合同</span>
							 &nbsp;&nbsp;<span class="msg" style="display:none">已有合同，必须使用标准合同库生成合同文本。同时不可上传附件了</span>
							</s:if>
							<s:else>
							<s:checkbox name="templateBean.contlib" onclick="userCont('true');" id="contlib"  cssClass="group"></s:checkbox><span class="use">合同</span>
							</s:else>
						</div>
						</td>
						
							
						</s:if>
						<s:else>
							<td><s:checkbox name="templateBean.noncontlib" id="noncontlib"  cssClass="group"></s:checkbox><span class="useless">招标文件</span></td>
							<td><s:checkbox name="templateBean.contlib"  id="contlib"  cssClass="group"></s:checkbox><span class="use">合同</span></td>
						</s:else>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="contlibno" style="display:none;">
				<td class="td_title">合同库编号</td>
				<td align="left">
				<input type="hidden" id="standard" name="templateBean.standard" value="${templateBean.standard}"/>
				<input type="hidden" id="nonstandard" name="templateBean.nonstandard" value="${templateBean.nonstandard}"/>
				<s:if test="!templateBean.contractTempletInfoId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContById('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');">${templateBean.contractNo}</span>
						<input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="${templateBean.contractTempletInfoId}" />
						<input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="${templateBean.contractTempletHisId}" />
					
				</s:if>
				<s:else>
						<input class="inputBorder" id="contractNo" validate="required" type="text" name="templateBean.contractNo" value="${templateBean.contractNo}" />
						<input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="${templateBean.contractTempletInfoId}" />
						<input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="${templateBean.contractTempletHisId}" />
				</s:else>
				</td>
				<td colspan="2">
				<s:if test="#canEdit == 'true'">
				<input type="button" id="doCont" name="doCont" value="合同文本库"  class="btn_green" onclick="doContTemplet();" />
					</s:if>
				</td>
				
			</tr>
	
			</table>
</div>
<script>
//是否是初始化
var isInit=false;

function userCont(flag){
	if($("input[name=templateBean.engineeringName]").attr("readonly") && !isInit){
	
		return;
	}
	
	if(flag == 'true'){
		$("#bidAttachment").parent().hide();
		$("#doCont").val("合同库");
		$("#bidAttachment").attr("validate","");
		$("#contlibno").show().find("[validate]").each(function(){
			$(this).attr("validate","required");
		});
		$("#contlibname").show().find("[validate]").each(function(){
			$(this).attr("validate","required");
		});

		$("#attachdetail").hide().find("[validate]").each(function(){
			$(this).attr("validate","");
		});	
		$("#funcPanelDiv").find("input[isAttach]").each(function(){
			$(this).hide();
		});
		$("#contractNo").attr("validate","required");
		$(".msg").show();
	}
	else{
		//合同提示
		$(".msg").hide();
		$("#contlibno").find("input").val("");
		$("#bidAttachment").parent().show();
		$("#bidAttachment").attr("validate","required");
		$("#contlibno").hide().find("[validate]").each(function(){
			$(this).attr("validate","");
		});
		$("#contlibname").hide().find("[validate]").each(function(){
			$(this).attr("validate","");
		});
		$("#attachdetail").show().find("[validate]").each(function(){
			$(this).attr("validate","required");
		});
		$("#funcPanelDiv").find("input[isAttach]").each(function(){
			$(this).show();
		});
	}
}

var ids={
		contractNo:'contractNo',
		contractTempletInfoId:'contractTempletInfoId',
		//contractName:'contractName',
		contractTempletHisId:'contractTempletHisId',
		projectCd:'projectCd',
		projectName:'projectName'
	};

//标准合同查看
function getContById(id,hisId){
	 var isStandard=false;
	  $.ajax({
			url :"${ctx}/sc/sc-contract-templet-info!isStandardCont.action",
			type : 'POST',
			data : {id:id},
			async : false, // ajax同步
			timeout : 1000,
			error : function(responseText) {
				if(responseText.indexOf("true")>-1){
					isStandard=true;
					}
			},

			success : function(responseText) {
				if(responseText.indexOf("true")>-1){
					isStandard=true;
					}
				}
			});
	if (!isStandard) {
    	  var getContUrl="${ctx}/sc/sc-contract-templet-info!readNonStandardCon.action?scContId="+ id ;
		  	if(parent.TabUtils==null)
		  		window.open(getContUrl);
		  	else
		  	parent.TabUtils.newTab("sc-contract-templet-info","非标准合同查看",getContUrl,true);
      }else{//markContract
    	  var getContUrl="${ctx}/sc/sc-contract-templet-info!readContract.action?scContId="
  			+ id 
  			+ "&contractTempletHisId=" + hisId
  			+ "&statusCd=${statusCd}" 
  			;
    		if(parent.TabUtils==null)
    			window.open(getContUrl);
    		else
    		  parent.TabUtils.newTab("sc-contract-templet-info","标准合同查看",getContUrl,true);
          }
      
	
	
}

function doContTemplet(){
	 var getContUrl="${ctx}/sc/sc-contract-templet-info!conSelect.action?frameId=156&sn=QQ";
	if(parent.TabUtils==null)
		window.open(getContUrl);
	else
	  parent.TabUtils.newTab("scconInfo_Select","合同库",getContUrl,true);

	 
}


$("#contractNo").quickSearch("${ctx}/sc/sc-contract-templet-info!quickSearch.action",['contractName','contractNo'],ids,{},function(e){
	var $s = $(e);
	
	if($s.attr("isStandard") == '1'){
		$("#nonstandard").val("");
		$("#standard").val("1");
	
	}else{

		$("#nonstandard").val("1");
		$("#standard").val("0");
	}
});

//是否有选择合同库
	isInit=true;
if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
	$("#noncontlib").attr("checked",false);
	userCont('true');
}else{
	userCont('false');
}
isInit=false;
</script>
<%-- 合同库 end--%>
