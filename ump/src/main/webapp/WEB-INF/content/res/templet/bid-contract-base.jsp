<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<style>
.attach-tip {
	color: #FF0000 !important;
}
</style>

<%--合同文本库start --%>
<div align="center" class="billContent">
<table class="mainTable" style="margin-bottom: 5px;">
	<col width="110" />
	<col width="160" />
	<col />

	<tr id="contCkb" style="display: none">
		<td class="td_title"><span class="con-title">合同文本库</span></td>
		<td colspan="2" class="chkGroup" align="left" validate="required">
		<table class="tb_checkbox">
			<col width="120">
			<col width="120">
			<col>
			<tr class="conSelectItem">
				<s:if test="#canEdit == 'true'">
					<td><s:if test="resApproveInfoId==null">
						<s:checkbox name="templateBean.contlib" onclick="userCont('true');" checked="true" id="contlib" cssClass="group"></s:checkbox>
						<span class="use">使用</span>
					</s:if> <s:else>
						<s:checkbox name="templateBean.contlib" onclick="userCont('true');" id="contlib" cssClass="group"></s:checkbox>
						<span class="use">使用</span>
					</s:else></td>
					<td><s:checkbox name="templateBean.noncontlib" onclick="userCont('false');" id="noncontlib" cssClass="group"></s:checkbox><span class="useless">不使用</span>
					<span class="msg"></span></td>
				</s:if>
				<s:else>
					<s:if test="!templateBean.contractTempletInfoId.isEmpty() && #canEdit == 'false'">
						<td><s:checkbox id="contlib" cssClass="group" checked="true"></s:checkbox><span class="use">使用</span></td>
						<td><s:checkbox name="templateBean.noncontlib" id="noncontlib" cssClass="group"></s:checkbox><span class="useless">不使用</span></td>

					</s:if>
					<s:else>

						<td><s:checkbox id="contlib" cssClass="group"></s:checkbox><span class="use">使用</span></td>
						<td><s:checkbox id="noncontlib" cssClass="group" checked="true"></s:checkbox><span class="useless">不使用</span></td>

					</s:else>
				</s:else>
			</tr>
		</table>
		</td>
	</tr>
	<tr id="contlibno" style="display: none">
		<td class="td_title">合同文本库编号</td>
		<td align="left" style="width: 120px;">
		<input type="hidden" id="standard" name="templateBean.standard" value="${templateBean.standard}" />
		<input type="hidden" id="nonstandard" name="templateBean.nonstandard" value="${templateBean.nonstandard}" /> 
		<s:if	test="!templateBean.contractTempletInfoId.isEmpty() && #canEdit == 'false'">
			<span class="link scContractLink" onclick="getContById('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');"> ${templateBean.contractNo}
			</span>
			<input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="${templateBean.contractTempletInfoId}" />
			<input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="${templateBean.contractTempletHisId}" />

		</s:if> 
		<s:else>
			<input class="inputBorder" id="contractNo" validate="required" type="text" name="templateBean.contractNo" value="${templateBean.contractNo}" />
			<input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="${templateBean.contractTempletInfoId}" />
			<input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="${templateBean.contractTempletHisId}" />
		</s:else></td>
		<td><s:if test="#canEdit == 'true'">
			<input type="button" id="doCont" name="doCont" value="合同文本库" class="btn_green" onclick="doContTemplet();" style="width: 90px" />
			<input type="button" id="doMark" name="doMark" value="标记合同" class="btn_green" style="display: none;" onclick="doContMark();" />
			<span>&nbsp;请使用合同文本库新增合同后，再在左边输入合同编号选择</span>
		</s:if></td>

	</tr>
	<%-- 合同库 start --%>
</table>

<div id="scattachList" style="height: 100%; left: 0px; top: 0px; display: none;"></div>
</div>
<script>

var ids = {
	contractNo : 'contractNo',
	contractTempletInfoId : 'contractTempletInfoId',
	//contractName:'contractName',
	contractTempletHisId : 'contractTempletHisId',
	projectCd : 'projectCd',
	projectName : 'projectName'
};
//不自动设置项目编号
if ($("#isAutosetProject").val() == "0") {
	//合同文本库参数
	ids = {
		contractNo : 'contractNo',
		contractTempletInfoId : 'contractTempletInfoId',
		contractTempletHisId : 'contractTempletHisId'

	};

}
//默认选择合同库文件不执行回调函数
var isCallChooseCont=false;
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
	/**
	 * 是否使用合同文本库
	 */
	function userCont(flag) {	
		if($("#isShowChkBox").val()=="1"){
                   $("#contCkb").show();
		
			}

	
		 if($("#btn_edit").css("display")!="none" && $("#btn_edit").css("display")!=undefined){
			// alert("display");
			 //当前是编辑状态，则不允许用户勾选，防止之前的数据被清掉
			 return;
		 }	
		if (flag == 'true') {
	
			$("#contlibno").show().find("[validate]").each(function() {
				$(this).attr("validate", "required");
			});
			$("#contlibname").show().find("[validate]").each(function() {
				$(this).attr("validate", "required");
			});
			/*
			$("#attachdetail").hide().find("[validate]").each(function() {
				$(this).attr("validate", "");
			});
			*/
			$("#funcPanelDiv").find("input[isAttach]").each(function() {
				$(this).hide();
			});

			  
			//对合同文本库编号与合同台账编号进行校验,不想校验，请不要在页面加#isJudageCtLedgerCons 隐藏域控件
			$("#isJudageCtLedgerCons").val("1");
		} else {
			
			$("#contlibno").hide().find("[validate]").each(function() {
				$(this).attr("validate", "");
			});
			$("#contlibname").hide().find("[validate]").each(function() {
				$(this).attr("validate", "");
			});
			$("#attachdetail").show().find("[validate]").each(function() {
				$(this).attr("validate", "required");
			});
			$("#funcPanelDiv").find("input[isAttach]").each(function() {
				$(this).show();
			});
                //不使用则清除合同文本库对应的选项		
				if(ids)
					for(var j in ids){				
						$("#"+ids[j]).val("");
					}
				//不对合同文本库编号与合同台账编号进行校验
				$("#isJudageCtLedgerCons").val("0");
		
		}	
		//选择合同文本库回调函数
		if (isCallChooseCont) {//typeof (callChooseContlibFun) == "function"
		
			callChooseContlibFun(flag);
		}
	}

	//标准合同查看
	function getContById(id, hisId) {
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
			var getContUrl = "${ctx}/sc/sc-contract-templet-info!readNonStandardCon.action?scContId=" + id;
			if (parent.TabUtils == null)
				window.open(getContUrl);
			else
				parent.TabUtils.newTab("sc-contract-templet-info", "非标准合同查看", getContUrl, true);
		} else {
			var getContUrl = "${ctx}/sc/sc-contract-templet-info!readContract.action?scContId=" + id //+ "&contractTempletHisId=" + hisId
					+ "&statusCd=${statusCd}"+"&displayMarkBtn="+$("#display_mark_btn").val();
			if (parent.TabUtils == null)
				window.open(getContUrl);
			else
				parent.TabUtils.newTab("sc-contract-templet-info", "标准合同查看", getContUrl, true);
		}

	}

	function doContTemplet() {
		var getContUrl = "${ctx}/sc/sc-contract-templet-info!conSelect.action?frameId=156";
		if (parent.TabUtils == null)
			window.open(getContUrl);
		else
			parent.TabUtils.newTab("scconInfo_Select", "合同文本库", getContUrl, true);

	}

	function doContMark() {
		var getContUrl = "${ctx}/sc/sc-contract-templet-info!markContract.action?scContId=";
		if ($("#contractNo").val() != "" && $("#contractTempletInfoId").val() != "") {
			getContUrl += $("#contractTempletInfoId").val();
			if (parent.TabUtils == null)
				window.open(getContUrl);
			else
				parent.TabUtils.newTab("sc-contract-templet-info", "合同文本库", getContUrl, true);
		} else {
			alert("请先选择合同！！");
		}
	}
	/**
	选择合同文本库自动关联合同台账信息
	**/
function autoQuickSearchCtledger(contNo){
	var url = "${ctx}/cont/cont-ledger!quickSearch.action?contNo="+$.trim(contNo);
    //选择合同台账所自动设置的页面选项ID
	if(typeof(idctLedgerArray) != "undefined")
	$.post(url, ctLedgerParams, function(result) {
		result = eval(result);
		var htmlL = '';
		var isHasVal = false;
			$.each(result, function(i, n) {
				if(idctLedgerArray)
					for(var j in idctLedgerArray){				
						$("#"+idctLedgerArray[j]).val(n[j]);
					}
				isHasVal = true;
			});
	   if(!isHasVal){//如果未关联合同台账则清除之前的数据
		   if(idctLedgerArray)
				for(var j in idctLedgerArray){				
					//$("#"+idctLedgerArray[j]).val("");
				}
		   }

			//是否判断合同文本库编号与合同库编号
			if($("#isJudageCtLedgerCons").val() == "1"){			
				if($("#contNo").val() != $("#contractNo").val()){				
					//alert('找不到该编号的合同，请到合同文本库中新增合同');
					////$("#contractNo").focus();
					//return;
				}			
			}
			//如果存在定标审批表方法，则调用
			if(typeof(showBidAppr) != "undefined"){
				showBidAppr();
			}
			//end
		});
	reloadContAttachToRes();
	}



	/**
	 * 根据合同编号搜索合同
	 */
	function autoQuickSearch(contNo) {
		var url = "${ctx}/sc/sc-contract-templet-info!quickSearch.action";
		var sn = $("#isSearchDesignCon").val();
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
	//编辑状态下才绑定搜索
	<s:if test="#canEdit == 'true'">
		$("#contractNo").quickSearch("${ctx}/sc/sc-contract-templet-info!quickSearch.action", [ 'contractName', 'contractNo' ], ids, {}, function(e) {
			var $s = $(e);
			if ($s.attr("isStandard") == '1') {
				//$("#nonstandard").removeAttr("checked");
				//$("#standard").attr("checked","true");
				$("#doMark").show();
			} else {
				//$("#standard").removeAttr("checked");
				//$("#nonstandard").attr("checked","true");
				$("#doMark").hide();
			}
	
			if(typeof(autoRelationCtLedger) !="undefined"){
				autoQuickSearchCtledger($("#contractNo").val());
			
				}
			reloadContAttachToRes();
			
		});
	</s:if>
	//userCont('false');
	//是否有选择合同库

	if ($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked") {
		userCont('true');
	} else {
		userCont('false');
	}
	/**
	 * 自动从合同文本库中抓取附件
	 */
	function reloadContAttachToRes() {
		//清空之前附件列表
		$("#attachList").html("");
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
						  	$.each($("td[attachHdName="+resAttachName+"]"),function(){
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
						
						   //附件
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
							    	$.each($("td[attachHdName="+resAttachName+"]"),function(){ 
											    	//设置值，为空则不允许提交
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
