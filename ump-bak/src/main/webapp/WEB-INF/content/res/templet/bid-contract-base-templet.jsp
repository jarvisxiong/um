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
							 &nbsp;&nbsp;<span class="msg" style="display:none">已有合同，必须使用标准合同库生成合同文本,同时不可上传附件.</span>
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
				<td class="td_title">合同文本库编号</td>
				<td align="left">
				<input type="hidden" id="standard" name="templateBean.standard" value="${templateBean.standard}"/>
				<input type="hidden" id="nonstandard" name="templateBean.nonstandard" value="${templateBean.nonstandard}"/>
				<s:if test="!templateBean.contractTempletInfoId.isEmpty() && #canEdit == 'false'">
						<span  class="link scContractLink" onclick="getContById('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');" >${templateBean.contractNo}</span>
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
					<input type="button" id="doCont" name="doCont" value="合同文本库"  class="btn_green" onclick="doContTemplet();"  style="width:90px;"/>
				</s:if>
				</td>
				
			</tr>
	
			</table>
</div>
<div id="scattachList" style="height: 100%; left: 0px; top: 0px; display: none;"></div>

<script>
//是否是初始化
var isInit=false;
//默认选择合同库文件不执行回调函数
var isCallChooseCont=true;
/**
 * 合同文本库选择回调函数
 */
function callChooseContlibFun(flag){

	//移除标记合同功能
	$("#doMark").remove();	
	if(flag == "true"){
		$("tr[otype=attach]").find(":button").hide();
		$(".attach-tip").remove();
		$("tr[otype=attach]").find(":button").before("<span class='attach-tip'>附件请上传至合同文本库中</span>");
		$("tr[otype=attach]").find("td[validate=required]").each(function() {
			//$(this).attr("validate", "");
			//$(this).removeClass();
		});
	           //  $("tr[otype=attach]").hide();
		}else{
			$(".attach-tip").remove();
			$("tr[otype=attach]").find(":button").show();	
			$("tr[otype=attach]").find("td[validate=]").each(function() {
				$(this).attr("validate", "required");
				$(this).addClass("required");
			});
			// $("tr[otype=attach]").show();
		}
}
function userCont(flag){
	if($("#contlib").attr("checked") == false){
		//未勾选合同
		flag=false;
		}
	if(flag == 'true'){
		$("#doCont").val("合同文本库");
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
	//选择合同文本库回调函数
	if (isCallChooseCont) {//typeof (callChooseContlibFun) == "function"
	
		callChooseContlibFun(flag);
	}
}

var ids={
		contractNo:'contractNo',
		contractTempletInfoId:'contractTempletInfoId',
		//contractName:'contractName',
		contractTempletHisId:'contractTempletHisId'
	
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
      }else{//markContract.action
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
	//sn=QQ
	 var getContUrl="${ctx}/sc/sc-contract-templet-info!conSelect.action?frameId=156&sn=";
	if(parent.TabUtils==null)
		window.open(getContUrl);
	else
	  parent.TabUtils.newTab("scconInfo_Select","合同文本库",getContUrl,true);

	 
}
//$("#isSearchDesignCon").val()
$("#contractNo").quickSearch("${ctx}/sc/sc-contract-templet-info!quickSearch.action",['contractName','contractNo'],ids,{sn:""},function(e){
	var $s = $(e);
	
	if($s.attr("isStandard") == '1'){
		$("#nonstandard").val("");
		$("#standard").val("1");
	}else{
		$("#nonstandard").val("1");
		$("#standard").val("0");
	}
	reloadContAttachToRes();
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


/**
 * 根据合同编号搜索合同
 */
function autoQuickSearch(contNo) {
	var url = "${ctx}/sc/sc-contract-templet-info!quickSearch.action";
	var sn = "";
	if (sn == "undefined" || sn == undefined) {
		sn = "";
	}
	$.post(url, {
		sn : sn,
		contNo :$.trim(contNo)
	}, function(result) {

		result = eval(result);
		var htmlL = '';
		//清除合同编号
		//$("#contractNo").val("");
		$.each(result, function(i, n) {

			$("#contractNo").val(n['contractNo']);
			$("#contractTempletInfoId").val(n['contractTempletInfoId']);
			$("#contractTempletHisId").val(n['contractTempletHisId']);
			//合同文本库参数
				ids = {
					contractNo : 'contractNo',
					contractTempletInfoId : 'contractTempletInfoId',
					contractTempletHisId : 'contractTempletHisId'

				};

				//设置项目编号
				if ($("#isAutosetProject").val() != "0") {
					$("#projectCd").val(n['projectCd']);
					$("#projectName").val(n['projectName']);
				}
                 
			});
		 reloadContAttachToRes();
		//是否判断合同文本库编号与合同库编号
		if($("#isJudageCtLedgerCons").val() == "1"){			
			if($("#contNo").val() != $("#contractNo").val()){				
				//alert('找不到该编号的合同，请到合同文本库中新增合同！');
				//$("#contractNo").focus();
				//return;
			}			
		}
		//end
	});

}
/**
 * 附件抓取步骤：
    1.当用户选择合同文本库编号时会自动加载选中的合同编号中的附件到当前页面 #scattachList DIV中
 *
 * 功能：自动从合同文本库中抓取附件与网批中的附件对应显示
 */
function reloadContAttachToRes() {
	//清空app-attachment-resShow.jsp页面中#attachList DIV中合同附件列表 
	//这是由于当网批发布提交后进入页面会先执行reloadContAttachToRes() 加载一次合同文库中附件  页面加完时会调用 app-attachment-resShow.jsp reloadScAttachs() 再加载一次合同文库库附件
	$("#attachList").html("");
	//清空当前页面附件区域
	$("#scattachList").html("");
	var scContractId = $("#contractTempletInfoId").val();
	if (isNotEmpty(scContractId)) {
		var data = {
			id : scContractId
		};
		data.scContractId = $("#contractTempletInfoId").val();
		data.hisContId = $("#contractTempletHisId").val();
	     //是否从合同文本库中抓取附件
     //   if($("#isGraspAttach").val() == "1"){
		$.post(_ctx + "/sc/sc-contract-templet-info!loadResAttachList.action",
				data, function(result) {
			        //加载合同文本中对应的附件
					$("#scattachList").html(result);
					if($("#scattachList").find("#attachSize").val() == "0"){	
						//无附件不抓取			
						return;
					}
				
            	   //抓取的附件个数
                   var graspCount=0;
					//附件
					$.each($("td[resattachname]"),function(i,td){
					  var resAttachName= $(td).attr("resattachname");
					 //如果当前附件不显示，则直不需要抓取
					 if($(td).parent().css("display") != 'none' && resAttachName){
					  //清空附件中的值
					  $(td).val("");
					  $(td).next().next().html('');	
					  $(td).next().find(":hidden").val("");
					      var resAttachName= $(td).attr("resattachname");
					  	$.each($("#scattachList").find("td[attachHdName="+resAttachName+"]"),function(){
					   	//设置值，为空则不允许提交
					  		$(td).val(resAttachName);
					  		$(td).next().find(":hidden").val(resAttachName);
					  		$(td).next().next().append($(this).html());
					  		graspCount++;
								});
					  	if($(td).val()==""){
									  //还原始数据，否则不使用合同库,直接使用页面上传文件成功附件不显示
							   $(td).next().next().html('<div id="show_bidContractFileId"></div>');
										}
                       //移除合同文本库附件区域中对应附件
					  	$("tr[attachHdName="+resAttachName+"]").remove();
				     	$("div[attachHdName="+resAttachName+"]").remove();
					 }
					});
					
					   //附件 处理网批中DIV[resattachname] 
					  $.each($("div[resattachname]"),function(i,div){								
						   var resAttachName= $(div).attr("resattachname");
						     if(resAttachName.indexOf("采购数量及技术参数确认单")>-1){
                              resAttachName="采购数量及技术参数确认单";
						      }
						   //如果当前附件不显示，则直不需要抓取
						   if($(div).parent().css("display") != 'none' && resAttachName){
							   //清空当前附件列表
							   $(div).html('');
							   //清空附件中的值
							  $(div).prev().prev().val("");	
							  //清空隐藏域中的值	
							   $(div).prev().find(":hidden").val("");			
							     // 查找					    
						    	$.each($("#scattachList").find("td[attachHdName="+resAttachName+"]"),function(){ 
										    	//设置值，为空则不允许提交 （发）
									    		$(div).prev().prev().val(resAttachName);
									    		$(div).prev().find(":hidden").val(resAttachName);
									    		$(div).append($(this).html());
									    		graspCount++;
											});
						    		if($(div).prev().prev().val()==""){
												  //还原始数据，否则不使用合同库,直接使用页面上传文件成功附件不显示
										   $(div).html('<div id="show_bidContractFileId"></div>');
											}
						        //移除合同文本库附件区域中对应附件
						    	$("tr[attachHdName="+resAttachName+"]").remove();
						       	$("div[attachHdName="+resAttachName+"]").remove();
						   }
					  });
				});

        }
	//}

}
</script>
<%-- 合同库 end--%>
